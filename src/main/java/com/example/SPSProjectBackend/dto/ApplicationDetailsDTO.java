package com.example.SPSProjectBackend.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ApplicationDetailsDTO {
    private String applicationNo;
    private String applicationId;
    private String applicationType;
    private String description;
    private Long demand;
    private String fundSource;
    private String fullAddress;

    public ApplicationDetailsDTO(Object[] result) {
        this.applicationNo = result[0] != null ? result[0].toString().trim() : null;
        this.applicationId = result[1] != null ? result[1].toString().trim() : null;
        this.applicationType = result[2] != null ? result[2].toString().trim() : null;
        this.description = result[3] != null ? result[3].toString().trim() : null;

        // Handle demand - could be BigDecimal or Number from Oracle
        if (result[4] != null) {
            if (result[4] instanceof BigDecimal) {
                this.demand = ((BigDecimal) result[4]).longValue();
            } else if (result[4] instanceof Number) {
                this.demand = ((Number) result[4]).longValue();
            }
        }

        this.fundSource = result[5] != null ? result[5].toString().trim() : null;
        // Add fullAddress handling - it will be at index 6
        this.fullAddress = result[6] != null ? result[6].toString().trim() : null;

    }

    // Default constructor
    public ApplicationDetailsDTO() {}

    // Full constructor
    public ApplicationDetailsDTO(String applicationNo, String applicationId, String applicationType,
                                 String description, Long demand, String fundSource,String fullAddress) {
        this.applicationNo = applicationNo;
        this.applicationId = applicationId;
        this.applicationType = applicationType;
        this.description = description;
        this.demand = demand;
        this.fundSource = fundSource;
        this.fullAddress= fullAddress;
    }
}