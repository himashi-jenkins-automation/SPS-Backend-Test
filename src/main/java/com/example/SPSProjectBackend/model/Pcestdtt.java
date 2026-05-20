package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PCESTDTT")
@IdClass(PcestdttId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pcestdtt implements Serializable {

    @Id
    @Column(name = "ESTIMATE_NO", length = 20, nullable = false)
    private String estimateNo;

    @Id
    @Column(name = "REV_NO", precision = 38, nullable = false)
    private BigDecimal revNo;

    @Id
    @Column(name = "DEPT_ID", length = 6, nullable = false)
    private String deptId;

    @Id
    @Column(name = "RES_CD", length = 20, nullable = false)
    private String resCd;

    @Column(name = "RES_TYPE", length = 20)
    private String resType;

    @Column(name = "RES_CAT", precision = 20)
    private BigDecimal resCat;

    @Column(name = "GEN_RES", length = 1)
    private String genRes;

    @Column(name = "UOM", length = 4)
    private String uom;

    @Column(name = "UNIT_PRICE", precision = 38)
    private BigDecimal unitPrice;

    @Column(name = "ESTIMATE_QTY", precision = 38)
    private BigDecimal estimateQty;

    @Column(name = "ESTIMATE_COST", precision = 38)
    private BigDecimal estimateCost;

    @Column(name = "TOLERANCE", precision = 38)
    private BigDecimal tolerance;

    @Column(name = "ISSUED_QTY", precision = 38)
    private BigDecimal issuedQty;

    @Column(name = "COMMITED_QTY", precision = 38)
    private BigDecimal commitedQty;

    @Column(name = "COMMITED_COST", precision = 38)
    private BigDecimal commitedCost;

    @Column(name = "ISSUED_COST", precision = 38)
    private BigDecimal issuedCost;

    @Column(name = "NORM_DEFAULT", length = 1)
    private String normDefault;

    @Column(name = "REQUESTED_QTY", precision = 38)
    private BigDecimal requestedQty;

    @Column(name = "REQUESTED_COST", precision = 38)
    private BigDecimal requestedCost;

    @Column(name = "APPROVED_QTY", precision = 38)
    private BigDecimal approvedQty;

    @Column(name = "APPROVED_COST", precision = 38)
    private BigDecimal approvedCost;

    @Column(name = "RETURNED_QTY", precision = 38)
    private BigDecimal returnedQty;

    @Column(name = "RETURNED_COST", precision = 38)
    private BigDecimal returnedCost;

    @Column(name = "CUSTOMER_QTY", precision = 38)
    private BigDecimal customerQty;

    @Column(name = "DAMAGE_QTY", precision = 38)
    private BigDecimal damageQty;

    @Column(name = "MNT_QTY", precision = 38)
    private BigDecimal mntQty;

    @Column(name = "FUND_QTY", precision = 38)
    private BigDecimal fundQty;

    @Column(name = "RES_CD_1", length = 255)
    private String resCd1;
}
