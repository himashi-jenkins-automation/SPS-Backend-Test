package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TMP_MTR_DETAILS")
@IdClass(MeterDetailId.class)
public class MeterDetail {

    @Id
    @Column(name = "ORDER_CARD_NO", length = 20, nullable = false)
    private String orderCardNo;

    @Id
    @Column(name = "MTR_TYPE", length = 6, nullable = false)
    private String mtrType;

    @Column(name = "ACC_NBR", length = 10,nullable = true)
    private String accNbr;

    @Column(name = "ADDED_BLCY", length = 3)
    private String addedBlcy;

    @Column(name = "AREA_CD", length = 2)
    private String areaCd;

    @Column(name = "BR_CODE", length = 2)
    private String brCode;

    @Column(name = "CNNCT_TRNPAN", length = 10)
    private String cnctTrnpan;

    @Column(name = "CT_RATIO", length = 12)
    private String ctRatio;

    @Column(name = "DP_CODE", length = 2)
    private String dpCode;

    @Column(name = "EDITED_DTIME")
    private LocalDateTime editedDtime;

    @Column(name = "EDITED_USER_ID", length = 8)
    private String editedUserId;

    @Column(name = "EFFCT_BLCY", length = 3)
    private String effctBlcy;

    @Column(name = "EFFCT_DATE")
    private LocalDateTime effctDate;

    @Column(name = "ENTERED_DTIME")
    private LocalDateTime enteredDtime;

    @Column(name = "JOB_NBR", length = 10)
    private String jobNbr;

    @Column(name = "MTR_1SET", precision = 5)
    private BigDecimal mtr1set;

    @Column(name = "MTR_2SET", precision = 5)
    private BigDecimal mtr2set;

    @Column(name = "MTR_3SET", precision = 5)
    private BigDecimal mtr3set;

    @Column(name = "MTR_NBR", length = 10)
    private String mtrNbr;

    @Column(name = "MTR_ORDER", precision = 5)
    private BigDecimal mtrOrder;

    @Column(name = "MTR_RATIO", length = 12)
    private String mtrRatio;

    @Column(name = "MTR_SEQ", precision = 10)
    private BigDecimal mtrSeq;

    @Column(name = "M_FACTOR", precision = 10)
//    private BigDecimal mFactor;
    private  BigDecimal mtrFactor;

    @Column(name = "NO_MTR_SETS", precision = 10)
    private BigDecimal noMtrSets;

    @Column(name = "NO_OF_PHASES", length = 1)
    private String noOfPhases;

    @Column(name = "PRSNT_RDN", precision = 10)
    private BigDecimal prsntRdn;

    @Column(name = "SET_TYPE", precision = 5)
    private BigDecimal setType;

    @Column(name = "TR_CB", length = 2)
    private String trCb;

    @Column(name = "TRNSF_AMPS", precision = 7)
    private BigDecimal trnsfAmps;

    @Column(name = "TRNSF_VOLT", precision = 7)
    private BigDecimal trnsfVolt;

    @Column(name = "USER_ID", length = 9)
    private String userId;
}