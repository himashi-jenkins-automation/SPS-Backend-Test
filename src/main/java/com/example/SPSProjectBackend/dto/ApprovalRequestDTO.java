package com.example.SPSProjectBackend.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * DTO for receiving approval log creation requests from frontend
 */
@Getter
@Setter
public class ApprovalRequestDTO {

    // Required parameters from frontend
    private String referenceNo;
    private String deptId;
    private String approvalType;
    private String approvedLevel;
    private String fromStatus;
    private String toStatus;
    private String approvedBy;

    // Optional parameters
    private String reason;
    private BigDecimal standardCost;
    private BigDecimal detailedCost;
    private String systemBy;

    // Default constructor
    public ApprovalRequestDTO() {
    }

    // Constructor for easier testing
    public ApprovalRequestDTO(String referenceNo, String deptId, String approvalType,
                              String approvedLevel, String fromStatus, String toStatus,
                              String approvedBy, String reason, BigDecimal standardCost,
                              BigDecimal detailedCost, String systemBy) {
        this.referenceNo = referenceNo;
        this.deptId = deptId;
        this.approvalType = approvalType;
        this.approvedLevel = approvedLevel;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.approvedBy = approvedBy;
        this.reason = reason;
        this.standardCost = standardCost;
        this.detailedCost = detailedCost;
        this.systemBy = systemBy;
    }
}