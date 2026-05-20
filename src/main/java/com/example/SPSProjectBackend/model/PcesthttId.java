package com.example.SPSProjectBackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PcesthttId implements Serializable {

    @Column(name = "ESTIMATE_NO", nullable = false, length = 20)
    private String estimateNo;

    @Column(name = "REV_NO", nullable = false)
    private Short revNo;

    @Column(name = "DEPT_ID", nullable = false, length = 6)
    private String deptId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PcesthttId))
            return false;
        PcesthttId that = (PcesthttId) o;
        return Objects.equals(estimateNo, that.estimateNo) &&
                Objects.equals(revNo, that.revNo) &&
                Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estimateNo, revNo, deptId);
    }
}
