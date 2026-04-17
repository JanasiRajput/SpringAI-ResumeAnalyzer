# Comprehensive Teaching Guide: AI Resume Analyzer

This guide is designed for teachers to explain the full-stack development process of an AI-powered application to students.

## Phase 1: Spring Boot & Spring AI (The Brain)
We start by building a robust backend that can "think" using Google's Gemini AI.

### 1. Project Setup
- **Spring AI**: A framework that lets Java apps talk to AI models easily.
- **Dependencies**: Added `spring-boot-starter-web` (for the API) and `spring-ai-starter-model-google-genai` (for Gemini).
- **CORS Configuration**: We added `@CrossOrigin` to the controller. This is a security feature that allows our frontend (port 4200) to talk to our backend (port 8080).

### 2. Prompt Engineering
Explain to students that how we ask questions matters!
- We use a specific **prompt structure** that tells the AI to analyze the "Resume" vs "Job Description" and return results in **JSON format**.
- **Structured Output**: We map the AI's response directly to a Java Class (`AnalysisResponse`), making it easy to process.

---

## Phase 2: Angular Frontend (The Body)
We build a beautiful interface in `src/main/webapp` using the Angular framework.

### 2. Modern UI/UX Design (The WOW Factor)
Teaching kids about design is as important as logic. We implemented a **Mocha & Rose** aesthetic.
- **Color Contrast**: We learned that text visibility is non-negotiable. We use high-contrast white text on deep brown backgrounds.
- **Call to Action (CTA)**: The "Submit for Analysis" button is designed to be the "Main Event". It uses a bright pink gradient and a scale effect on hover.
- **Glassmorphism**: We use semi-transparent backgrounds with "blur" effects to create a premium, layered feel.
- **Validation**: Instead of just disabling the button, we provide an alert if fields are empty. This is better UX as it tells the user *why* they can't proceed.

### 3. State Management
- **Loading states**: We show "Analyzing..." while waiting for the AI. This is crucial for User Experience (UX) so users know the app is working.

---

## Full Stack Workflow (The Handshake)
1. **User Input**: The student pastes a resume and job description.
2. **HTTP Request**: Angular sends a `POST` request to `/analyze`.
3. **AI Processing**: Spring Boot calls Gemini with our custom prompt.
4. **Data Return**: AI returns a JSON score and skills list.
5. **UI Update**: Angular receives the data and displays it beautifully in Brown and Pink cards.

## Classroom Discussion Ideas
- **Ethics**: What happens if the AI is biased?
- **AI Limitations**: Why might the match score be slightly different each time?
- **Extension**: Can you add a feature to save the results to a history list?
