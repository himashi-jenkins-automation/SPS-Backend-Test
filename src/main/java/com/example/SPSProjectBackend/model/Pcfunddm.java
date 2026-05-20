package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PCFUNDDM")
public class Pcfunddm {

    @EmbeddedId
    private PcfunddmId id;

    @Column(name = "DESCR", length = 100)
    private String descr;

    @Column(name = "FUNDED_AMT")
    private BigDecimal fundedAmt;

    @Column(name = "COMMITED_AMT")
    private BigDecimal commitedAmt;

    @Column(name = "ALLOCATED_AMT")
    private BigDecimal allocatedAmt;

    @Column(name = "REF_1", length = 30)
    private String ref1;

    @Column(name = "REF_2", length = 30)
    private String ref2;

    @Column(name = "SRC_DOC_NO", length = 20)
    private String srcDocNo;

    @Column(name = "DOC_DT", nullable = false)
    private LocalDate docDt;

    @Column(name = "EFECT_DT", nullable = false)
    private LocalDate efectDt;

    @Column(name = "EXPIRY_DT", nullable = false)
    private LocalDate expiryDt;

    @Column(name = "STATUS")
    private BigDecimal status;

    @Column(name = "LOG_ID")
    private BigDecimal logId;

    @Column(name = "MOD_BY", length = 12)
    private String modBy;

    @Column(name = "MOD_DT", nullable = false)
    private LocalDate modDt;

    @Column(name = "ENT_BY", length = 12)
    private String entBy;

    @Column(name = "ENT_DT", nullable = false)
    private LocalDate entDt;

    // Default constructor required by JPA
    public Pcfunddm() {}

    // Parameterized constructor for convenience
    public Pcfunddm(PcfunddmId id, String descr, BigDecimal fundedAmt, BigDecimal commitedAmt,
                    BigDecimal allocatedAmt, String ref1, String ref2, String srcDocNo, LocalDate docDt,
                    LocalDate efectDt, LocalDate expiryDt, BigDecimal status, BigDecimal logId,
                    String modBy, LocalDate modDt, String entBy, LocalDate entDt) {
        this.id = id;
        this.descr = descr;
        this.fundedAmt = fundedAmt;
        this.commitedAmt = commitedAmt;
        this.allocatedAmt = allocatedAmt;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.srcDocNo = srcDocNo;
        this.docDt = docDt;
        this.efectDt = efectDt;
        this.expiryDt = expiryDt;
        this.status = status;
        this.logId = logId;
        this.modBy = modBy;
        this.modDt = modDt;
        this.entBy = entBy;
        this.entDt = entDt;
    }
}