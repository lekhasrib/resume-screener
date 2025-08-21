package com.resume_screener.dto;

import java.util.List;

public class ResumeScreeningResponse {
    private int matchScore;
    private String summary;
    private List<String> suggestions;
    private String resultStatus; // Default status

    public ResumeScreeningResponse() {}

    public ResumeScreeningResponse(int matchScore, String summary, List<String> suggestions, String resultStatus) {
        this.matchScore = matchScore;
        this.summary = summary;
        this.suggestions = suggestions;
        this.resultStatus = resultStatus;
    }

    // Getters and setters
    public int getMatchScore() { return matchScore; }
    public void setMatchScore(int matchScore) { this.matchScore = matchScore; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public List<String> getSuggestions() { return suggestions; }
    public void setSuggestions(List<String> suggestions) { this.suggestions = suggestions; }

    public String getResultStatus() { return resultStatus; }
    public void setResultStatus(String resultStatus) { this.resultStatus = resultStatus; }
}

