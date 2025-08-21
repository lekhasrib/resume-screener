package com.resume_screener.dto;

import org.springframework.web.multipart.MultipartFile;

public class ResumeScreeningRequest {
    private MultipartFile resumeFile;
    private String jobDescription;

    // Getters and setters
    public MultipartFile getResumeFile() { return resumeFile; }
    public void setResumeFile(MultipartFile resumeFile) { this.resumeFile = resumeFile; }

    public String getJobDescription() { return jobDescription; }
    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }
}
