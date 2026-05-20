package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PCJBTYPM")
public class Pcjbtypm {

    @EmbeddedId
    private PcjbtypmId id;

    @Column(name = "PRENT_CAT", length = 20)
    private String prentCat;

    @Column(name = "UOM_CD", length = 4)
    private String uomCd;

    @Column(name = "NORM_UNITS")
    private Long normUnits;

    @Column(name = "EST_NO_GEN")
    private Boolean estNoGen;

    @Column(name = "EST_FRMT_CD", length = 10)
    private String estFrmtCd;

    @Column(name = "EST_SRS_CD")
    private Short estSrsCd;

    @Column(name = "PRJ_NO_GEN")
    private Boolean prjNoGen;

    @Column(name = "PRJ_FRMT_CD", length = 10)
    private String prjFrmtCd;

    @Column(name = "PRJ_SRS_CD")
    private Short prjSrsCd;

    @Column(name = "EST_NO_APR")
    private Boolean estNoApr;

    @Column(name = "REV_NO_APR")
    private Boolean revNoApr;

    @Column(name = "DET_EST_APR")
    private Boolean detEstApr;

    @Column(name = "SUB_AC", length = 20)
    private String subAc;

    @Column(name = "GL_CD", length = 27)
    private String glCd;

    @Column(name = "CHRG_SUB_AC", length = 20)
    private String chrgSubAc;

    @Column(name = "CHRG_GL_CD", length = 27)
    private String chrgGlCd;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "LOG_ID")
    private Integer logId;

    @Column(name = "ENT_BY", length = 12)
    private String entBy;

    @Column(name = "ENT_DT", nullable = false)
    private LocalDate entDt;

    @Column(name = "MODI_BY", length = 12)
    private String modiBy;

    @Column(name = "MODI_DT", nullable = false)
    private LocalDate modiDt;

    // Default constructor required by JPA
    public Pcjbtypm() {}

    // Parameterized constructor for convenience
    public Pcjbtypm(PcjbtypmId id, String prentCat, String uomCd, Long normUnits, Boolean estNoGen,
                    String estFrmtCd, Short estSrsCd, Boolean prjNoGen, String prjFrmtCd, Short prjSrsCd,
                    Boolean estNoApr, Boolean revNoApr, Boolean detEstApr, String subAc, String glCd,
                    String chrgSubAc, String chrgGlCd, Boolean status, Integer logId, String entBy,
                    LocalDate entDt, String modiBy, LocalDate modiDt) {
        this.id = id;
        this.prentCat = prentCat;
        this.uomCd = uomCd;
        this.normUnits = normUnits;
        this.estNoGen = estNoGen;
        this.estFrmtCd = estFrmtCd;
        this.estSrsCd = estSrsCd;
        this.prjNoGen = prjNoGen;
        this.prjFrmtCd = prjFrmtCd;
        this.prjSrsCd = prjSrsCd;
        this.estNoApr = estNoApr;
        this.revNoApr = revNoApr;
        this.detEstApr = detEstApr;
        this.subAc = subAc;
        this.glCd = glCd;
        this.chrgSubAc = chrgSubAc;
        this.chrgGlCd = chrgGlCd;
        this.status = status;
        this.logId = logId;
        this.entBy = entBy;
        this.entDt = entDt;
        this.modiBy = modiBy;
        this.modiDt = modiDt;
    }
}