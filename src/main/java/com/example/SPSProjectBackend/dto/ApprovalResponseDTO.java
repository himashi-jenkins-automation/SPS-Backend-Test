package com.example.SPSProjectBackend.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for returning approval log response after creation
 */
@Getter
@Setter
public class ApprovalResponseDTO {

    private String approvalId;
    private String referenceNo;
    private String deptId;
    private String approvalType;
    private String approvedLevel;
    private String fromStatus;
    private String toStatus;
    private String approvedBy;
    private String reason;
    private LocalDate approvedDate;
    private String approvedTime;
    private BigDecimal standardCost;
    private BigDecimal detailedCost;
    private String systemBy;
    private String message;

    // Default constructor
    public ApprovalResponseDTO() {
    }

    // Constructor with message for success/failure
    public ApprovalResponseDTO(String message) {
        this.message = message;
    }
}