package com.example.SPSProjectBackend.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class WiringLandDetailId implements Serializable {
    @Column(name = "APPLICATION_ID", length = 24, nullable = false)
    private String applicationId;

    @Column(name = "DEPT_ID", length = 6, nullable = false)
    private String deptId;

    // Constructors, getters, setters, equals, and hashCode
    public WiringLandDetailId() {}

    public WiringLandDetailId(String applicationId, String deptId) {
        this.applicationId = applicationId;
        this.deptId = deptId;
    }

    // Getters and Setters
    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }
    public String getDeptId() { return deptId; }
    public void setDeptId(String deptId) { this.deptId = deptId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WiringLandDetailId that = (WiringLandDetailId) o;
        return Objects.equals(applicationId, that.applicationId) &&
                Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, deptId);
    }
}
