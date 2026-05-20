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
public class PcjbtypmId implements Serializable {
    private static final long serialVersionUID = -6263122568627260040L;

    @Column(name = "CAT_CD", nullable = false, length = 20)
    private String catCd;

    @Column(name = "DEPT_ID", nullable = false, length = 6)
    private String deptId;

    // Default constructor required by JPA
    public PcjbtypmId() {}

    // Parameterized constructor for convenience
    public PcjbtypmId(String catCd, String deptId) {
        this.catCd = catCd;
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PcjbtypmId entity = (PcjbtypmId) o;
        return Objects.equals(catCd, entity.catCd) &&
                Objects.equals(deptId, entity.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catCd, deptId);
    }
}