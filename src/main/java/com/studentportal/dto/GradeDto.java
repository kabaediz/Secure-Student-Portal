package com.studentportal.dto;

public class GradeDto {
    private String courseName;
    private Double score;

    public GradeDto(String courseName, Double score) {
        this.courseName = courseName;
        this.score = score;
    }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
}

