package com.example.SPSProjectBackend.dto;

public class MtrTypeDto {
    private String mtrType;
    private Short mtrOrder;

    public MtrTypeDto(String mtrType, Short mtrOrder) {
        this.mtrType = mtrType;
        this.mtrOrder = mtrOrder;
    }

    // Getters and Setters
    public String getMtrType() { return mtrType; }
    public void setMtrType(String mtrType) { this.mtrType = mtrType; }

    public Short getMtrOrder() { return mtrOrder; }
    public void setMtrOrder(Short mtrOrder) { this.mtrOrder = mtrOrder; }
}