package com.example.SPSProjectBackend.dto;

public class CommissionDTO {
    private String projectNo;
    private String estimateNo;
    private Long totalCost;
    private String deptId;
    private String description;
    private Short status;
    private String orderCardNo;

    public CommissionDTO() {
    }

    public CommissionDTO(
            String projectNo,
            String estimateNo,
            Number totalCost,
            String deptId,
            String description,
            Number status,
            String orderCardNo) {
        this.projectNo = projectNo;
        this.estimateNo = estimateNo;
        this.totalCost = (totalCost == null) ? null : totalCost.longValue();
        this.deptId = deptId;
        this.description = description;
        this.status = (status == null) ? null : status.shortValue();
        this.orderCardNo = orderCardNo;
    }

    public String getEstimateNo() {
        return estimateNo;
    }

    public void setEstimateNo(String estimateNo) {
        this.estimateNo = estimateNo;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getOrderCardNo() {
        return orderCardNo;
    }

    public void setOrderCardNo(String orderCardNo) {
        this.orderCardNo = orderCardNo;
    }
}