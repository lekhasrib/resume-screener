package com.resume_screener.controller;

import com.resume_screener.dto.ResumeScreeningRequest;
import com.resume_screener.dto.ResumeScreeningResponse;
import com.resume_screener.parser.ResumeParser;
import com.resume_screener.service.ResumeScreeningService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/screen-resume")
@CrossOrigin(origins = "https://lekhasrib.github.io/resume-screener-frontend/")
public class ResumeScreeningController {

    private final ResumeScreeningService screeningService;

    public ResumeScreeningController(ResumeScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @PostMapping
    public ResumeScreeningResponse screenResume(@ModelAttribute ResumeScreeningRequest request) throws Exception {
        // Extract text from uploaded file
        String resumeText = ResumeParser.extractText(request.getResumeFile());

        // Call service with extracted text and job description
        return screeningService.screenResume(resumeText, request.getJobDescription());
    }
}
