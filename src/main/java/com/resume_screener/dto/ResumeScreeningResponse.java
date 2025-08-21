package com.resume_screener.dto;

import java.util.List;

public class ResumeScreeningResponse {

    private int fitScore;
    private List<String> strengths;
    private List<String> weaknesses;
    private String summary;

    public ResumeScreeningResponse() {}

    public ResumeScreeningResponse(int fitScore, List<String> strengths, List<String> weaknesses, String summary) {
        this.fitScore = fitScore;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.summary = summary;
    }

    public int getFitScore() {
        return fitScore;
    }

    public void setFitScore(int fitScore) {
        this.fitScore = fitScore;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
