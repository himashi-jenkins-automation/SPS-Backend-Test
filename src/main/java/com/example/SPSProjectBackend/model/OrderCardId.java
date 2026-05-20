package com.example.SPSProjectBackend.model;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class OrderCardId implements Serializable {
    private String projectNo;
    private String deptId;

    // Default constructor
    public OrderCardId() {}

    // Parameterized constructor
    public OrderCardId(String projectNo, String deptId) {
        this.projectNo = projectNo;
        this.deptId = deptId;
    }

    // Override equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCardId that = (OrderCardId) o;
        return Objects.equals(projectNo, that.projectNo) &&
                Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectNo, deptId);
    }
}