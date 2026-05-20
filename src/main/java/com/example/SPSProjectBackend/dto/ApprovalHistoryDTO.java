package com.example.SPSProjectBackend.dto;

import java.util.Date;

public class ApprovalHistoryDTO {
    private String actionBy;
    private Date actionDate;
    private String actionTime;
    private String userLevel;
    private String comments;
    private String fromStatus;
    private String toStatus;

    // Constructor
    public ApprovalHistoryDTO(String actionBy, Date actionDate, String actionTime,
                              String userLevel, String comments, String fromStatus, String toStatus) {
        this.actionBy = actionBy;
        this.actionDate = actionDate;
        this.actionTime = actionTime;
        this.userLevel = userLevel;
        this.comments = comments;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
    }

    // Getters and Setters
    public String getActionBy() { return actionBy; }
    public void setActionBy(String actionBy) { this.actionBy = actionBy; }
    public Date getActionDate() { return actionDate; }
    public void setActionDate(Date actionDate) { this.actionDate = actionDate; }
    public String getActionTime() { return actionTime; }
    public void setActionTime(String actionTime) { this.actionTime = actionTime; }
    public String getUserLevel() { return userLevel; }
    public void setUserLevel(String userLevel) { this.userLevel = userLevel; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public String getFromStatus() { return fromStatus; }
    public void setFromStatus(String fromStatus) { this.fromStatus = fromStatus; }
    public String getToStatus() { return toStatus; }
    public void setToStatus(String toStatus) { this.toStatus = toStatus; }
}