package com.example.resumeanalyzer.controller;

import com.example.resumeanalyzer.model.AnalysisRequest;
import com.example.resumeanalyzer.model.AnalysisResponse;
import com.example.resumeanalyzer.service.ResumeAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResumeController {

    private final ResumeAnalysisService resumeAnalysisService;

    @PostMapping("/analyze")
    public AnalysisResponse analyzeResume(@RequestBody AnalysisRequest request) {
        return resumeAnalysisService.analyzeResume(request);
    }
}
