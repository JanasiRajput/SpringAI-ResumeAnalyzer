package com.example.resumeanalyzer.service;

import com.example.resumeanalyzer.model.AnalysisRequest;
import com.example.resumeanalyzer.model.AnalysisResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResumeAnalysisService {

    private final ChatClient chatClient;

    public ResumeAnalysisService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public AnalysisResponse analyzeResume(AnalysisRequest request) {
        log.info("Starting AI Resume Analysis... (Fast Mode)");
        long startTime = System.currentTimeMillis();

        String promptText = """
            Analyze this resume against the job description.
            
            Resume: {resumeText}
            Job Description: {jobDescription}
            
            Return ONLY a valid JSON object with:
            - matchScore: 0-100
            - missingSkills: list of strings
            - suggestions: list of strings
            
            STRICT: No preamble, no explanation, just the JSON.
            """;

        // Fast Mode Configuration: Optimized for speed and minimal token cost
        GoogleGenAiChatOptions options = GoogleGenAiChatOptions.builder()
                .temperature(0.1)
                .build();

        try {
            AnalysisResponse response = chatClient.prompt()
                    .options(options)
                    .user(userSpec -> userSpec
                            .text(promptText)
                            .param("resumeText", request.getResumeText())
                            .param("jobDescription", request.getJobDescription())
                    )
                    .call()
                    .entity(new ParameterizedTypeReference<AnalysisResponse>() {});
            
            log.debug("Raw AI Response received and parsed: {}", response);
            long duration = System.currentTimeMillis() - startTime;
            log.info("Analysis completed successfully in {}ms", duration);
            return response;
        } catch (Exception e) {
            log.error("AI Content Generation Failed after {}ms: {}", (System.currentTimeMillis() - startTime), e.getMessage());
            throw e;
        }
    }
}
