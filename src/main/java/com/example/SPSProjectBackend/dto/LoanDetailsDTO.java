package com.example.SPSProjectBackend.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class LoanDetailsDTO {
    private String isLoanApp;
    private BigDecimal loanAmount;
    private String applicationNo;
//    private String estimateNo;

    // Constructor for easy object creation
    public LoanDetailsDTO(String isLoanApp, BigDecimal loanAmount, String applicationNo, String estimateNo) {
        this.isLoanApp = isLoanApp;
        this.loanAmount = loanAmount;
        this.applicationNo = applicationNo;
//        this.estimateNo = estimateNo;
    }

    public LoanDetailsDTO() {
        // Default constructor
    }
}