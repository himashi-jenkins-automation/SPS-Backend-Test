package com.example.SPSProjectBackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PcfunddmId implements Serializable {
    private static final long serialVersionUID = -1234567890123456789L;

    @Column(name = "FUND_SOURCE", nullable = false, length = 10)
    private String fundSource;

    @Column(name = "FUND_ID", nullable = false, length = 27)
    private String fundId;

    @Column(name = "DEPT_ID", nullable = false, length = 6)
    private String deptId;

    // Default constructor required by JPA
    public PcfunddmId() {}

    // Parameterized constructor for convenience
    public PcfunddmId(String fundSource, String fundId, String deptId) {
        this.fundSource = fundSource;
        this.fundId = fundId;
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PcfunddmId entity = (PcfunddmId) o;
        return Objects.equals(fundSource, entity.fundSource) &&
                Objects.equals(fundId, entity.fundId) &&
                Objects.equals(deptId, entity.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fundSource, fundId, deptId);
    }
}