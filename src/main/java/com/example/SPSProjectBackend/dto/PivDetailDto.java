// PivDetailDto.java
package com.example.SPSProjectBackend.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PivDetailDto {
    private String pivNo;
    private String deptId;
    private BigDecimal pivAmount;
    private String paymentMode;
    private String status;

    // added three new fields for getting piv details
    private String referenceType;
    private BigDecimal serConnOrElecSch;
    private BigDecimal securityDeposit;

    private Date pivDate;

    // Constructor
    public PivDetailDto(String pivNo, String deptId, BigDecimal pivAmount, String paymentMode, String status,
            String referenceType, BigDecimal serConnOrElecSch, BigDecimal securityDeposit, Date pivDate) {
        this.pivNo = pivNo;
        this.deptId = deptId;
        this.pivAmount = pivAmount;
        this.paymentMode = paymentMode;
        this.status = status;
        this.referenceType = referenceType;
        this.serConnOrElecSch = serConnOrElecSch;
        this.securityDeposit = securityDeposit;
        this.pivDate = pivDate;

    }

    // Getters and Setters
    public String getPivNo() {
        return pivNo;
    }

    public void setPivNo(String pivNo) {
        this.pivNo = pivNo;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public BigDecimal getPivAmount() {
        return pivAmount;
    }

    public void setPivAmount(BigDecimal pivAmount) {
        this.pivAmount = pivAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // New getters and setters
    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public BigDecimal getSerConnOrElecSch() {
        return serConnOrElecSch;
    }

    public void setSerConnOrElecSch(BigDecimal serConnOrElecSch) {
        this.serConnOrElecSch = serConnOrElecSch;
    }

    public BigDecimal getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(BigDecimal securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public Date getPivDate() {
        return pivDate;
    }

    public void setPivDate(Date pivDate) {
        this.pivDate = pivDate;
    }
}