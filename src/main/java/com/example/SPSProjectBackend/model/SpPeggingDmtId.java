package com.example.SPSProjectBackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class SpPeggingDmtId implements Serializable {

    @Column(name = "LINESECTIONTYPEID", length = 200)
    private String lineSectionTypeId;

    @Column(name = "RES_CD", length = 20)
    private String resCd;

    @Column(name = "DEPT_ID", length = 6)
    private String deptId;
}
