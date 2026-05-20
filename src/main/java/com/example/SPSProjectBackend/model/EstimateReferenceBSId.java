// EstimateReferenceBSId.java
package com.example.SPSProjectBackend.model;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class EstimateReferenceBSId implements Serializable {
    private String sestimateNo;
    private String deptId;
    private String westimateNo;

    // Default constructor
    public EstimateReferenceBSId() {}

    // Parameterized constructor
    public EstimateReferenceBSId(String sestimateNo, String deptId, String westimateNo) {
        this.sestimateNo = sestimateNo;
        this.deptId = deptId;
        this.westimateNo = westimateNo;
    }

    // Implement equals() and hashCode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstimateReferenceBSId that = (EstimateReferenceBSId) o;

        if (!sestimateNo.equals(that.sestimateNo)) return false;
        if (!deptId.equals(that.deptId)) return false;
        return westimateNo.equals(that.westimateNo);
    }

    @Override
    public int hashCode() {
        int result = sestimateNo.hashCode();
        result = 31 * result + deptId.hashCode();
        result = 31 * result + westimateNo.hashCode();
        return result;
    }
}