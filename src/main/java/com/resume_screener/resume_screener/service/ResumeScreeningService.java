package com.resume_screener.service;

import com.resume_screener.ai.OpenAIConnect;
import com.resume_screener.dto.ResumeScreeningResponse;
import com.resume_screener.parser.ResumeParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


// @Service
// public class ResumeScreeningService {

//     public ResumeScreeningResponse screen(MultipartFile file, String jobDescription) throws Exception {
//         String resumeText = ResumeParser.extractText(file);
//         String modelResponse = OllamaClient.sendToModel(resumeText, jobDescription);
//         return OllamaClient.parseJsonResponse(modelResponse);
//     }
// }
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeScreeningService {

    private final OpenAIConnect aiClient;

    @Autowired
    public ResumeScreeningService(OpenAIConnect aiClient) {
        this.aiClient = aiClient;
    }

    public String screenResume(String resumeText, String jobDescription) {
        String prompt = """
            You are an AI resume screener.
            Compare the following resume against the job description.
            Highlight strengths, weaknesses, and a fit score (0-100).

            Resume:
            %s

            Job Description:
            %s
            """.formatted(resumeText, jobDescription);

        return aiClient.ask(prompt);
    }
}
