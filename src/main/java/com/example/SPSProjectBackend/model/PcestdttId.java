package com.example.SPSProjectBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PcestdttId implements Serializable {
    private String estimateNo;
    private BigDecimal revNo;
    private String deptId;
    private String resCd;
}
