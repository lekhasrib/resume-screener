package com.resume_screener.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume_screener.ai.GroqAPIClient;
import com.resume_screener.dto.ResumeScreeningResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeScreeningService {
    @Autowired
    private final GroqAPIClient aiClient;

    public ResumeScreeningService(GroqAPIClient aiClient) {
        this.aiClient = aiClient;
    }

    public ResumeScreeningResponse screenResume(String resumeText, String jobDescription) throws Exception {
        String prompt = """
        You are an intelligent resume evaluator for job applicants. 
        Analyze the candidate's resume against the job description and provide a concise, actionable summary for the applicant. 

        Instructions:
        1. Focus only on key points that help the applicant improve or understand their fit.
        2. Be brief — no long paragraphs.
        3. Respond strictly in JSON format with the following keys:

        {
        "fit_score": <integer 0-100, higher = better match>,
        "strengths": [top 3-5 key strengths or skills relevant to the job],
        "weaknesses": [top 3-5 gaps or areas to improve],
        "summary": "One or two sentences highlighting overall fit and suggested next steps"
        }

        Resume:
        %s

        Job Description:
        %s
        """.formatted(resumeText, jobDescription);



        // Raw Groq response
        String aiResponse = aiClient.callGroq(prompt);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(aiResponse);

        // Extract the inner JSON (content field)
        String content = root.path("choices").get(0).path("message").path("content").asText();

        // Strip markdown code fences if present
        content = content.replaceAll("```json", "")
                         .replaceAll("```", "")
                         .trim();

        // Now parse the actual JSON answer
        JsonNode json = mapper.readTree(content);

        return new ResumeScreeningResponse(
                json.path("fit_score").asInt(),
                mapper.convertValue(json.path("strengths"), new TypeReference<List<String>>() {}),
                mapper.convertValue(json.path("weaknesses"), new TypeReference<List<String>>() {}),
                json.path("summary").asText()
        );
    }
}
