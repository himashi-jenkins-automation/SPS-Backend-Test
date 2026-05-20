package com.example.SPSProjectBackend.dto;

public class JobTypeCountDTO {
    private String jobType;
    private Long count;

    public JobTypeCountDTO() {
    }

    public JobTypeCountDTO(String jobType, Long count) {
        this.jobType = jobType;
        this.count = count;
    }

    // Getters and Setters
    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}