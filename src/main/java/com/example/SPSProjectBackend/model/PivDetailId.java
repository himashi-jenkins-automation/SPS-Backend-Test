package com.example.SPSProjectBackend.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PivDetailId implements Serializable {
    private String pivNo;    // Maps to PIV_NO varchar2(24)
    private String deptId;   // Maps to DEPT_ID char(6)

    // Default constructor
    public PivDetailId() {}

    // Constructor for convenience
    public PivDetailId(String pivNo, String deptId) {
        this.pivNo = pivNo;
        this.deptId = deptId;
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

    // equals() and hashCode() are required for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PivDetailId that = (PivDetailId) o;
        return java.util.Objects.equals(pivNo, that.pivNo) && java.util.Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(pivNo, deptId);
    }
}