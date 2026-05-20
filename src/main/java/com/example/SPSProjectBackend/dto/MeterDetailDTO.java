package com.example.SPSProjectBackend.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class MeterDetailDTO {
    private String orderCardNo;
    private String mtrType;
    private String accNbr;
    private String addedBlcy;
    private String areaCd;
    private String brCode;
    private String cnctTrnpan;
    private String ctRatio;
    private String dpCode;
    private LocalDateTime editedDtime;
    private String editedUserId;
    private String effctBlcy;
    private LocalDateTime effctDate;
    private LocalDateTime enteredDtime;
    private String jobNbr;
    private BigDecimal mtr1set;
    private BigDecimal mtr2set;
    private BigDecimal mtr3set;
    private String mtrNbr;
    private BigDecimal mtrOrder;
    private String mtrRatio;
    private BigDecimal mtrSeq;
//    private BigDecimal mFactor;
    private BigDecimal mtrFactor;
    private BigDecimal noMtrSets;
    private String noOfPhases;
    private BigDecimal prsntRdn;
    private BigDecimal setType;
    private String trCb;
    private BigDecimal trnsfAmps;
    private BigDecimal trnsfVolt;
    private String userId;
}