package com.resume_screener.controller;

import com.resume_screener.dto.ResumeScreeningResponse;
import com.resume_screener.service.ResumeScreeningService;
import com.resume_screener.util.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/screen-resume")
public class ResumeScreeningController {

    @Autowired
    private ResumeScreeningService service;
    @PostMapping
    public ResponseEntity<?> screenResume(
        @RequestParam("resumeFile") MultipartFile resumeFile,
        @RequestParam("jobDescription") String jobDescription
    ) {
        try {
            FileValidator.validate(resumeFile);

            ResumeScreeningResponse response = service.screen(resumeFile, jobDescription);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong: " + e.getMessage());
        }
    }
}
