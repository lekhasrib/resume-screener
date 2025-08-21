package com.resume_screener.parser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class ResumeParser {

    public static String extractText(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        InputStream is = file.getInputStream();

        if (fileName.endsWith(".pdf")) {
            try (PDDocument document = PDDocument.load(is)) {
                return new PDFTextStripper().getText(document);
            }
        } else if (fileName.endsWith(".docx")) {
            try (XWPFDocument doc = new XWPFDocument(is)) {
                StringBuilder sb = new StringBuilder();
                for (XWPFParagraph p : doc.getParagraphs()) {
                    sb.append(p.getText()).append("\n");
                }
                return sb.toString();
            }
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }
}

