package com.resume_screener.util;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator {
    public static void validate(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName == null || (!fileName.endsWith(".pdf") && !fileName.endsWith(".docx"))) {
            throw new IllegalArgumentException("Only PDF or DOCX files are supported.");
        }

        if (file.getSize() > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("File size must be less than 5 MB.");
        }
    }
}
