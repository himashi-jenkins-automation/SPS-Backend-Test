package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PCESTHTT")
public class Pcesthtt {

    @EmbeddedId
    private PcesthttId id;

    @Column(name = "PROJECT_NO", length = 20)
    private String projectNo;

    @Column(name = "CAT_CD", length = 20)
    private String catCd;

    @Column(name = "PARTIAL_PMT", length = 255)
    private String partialPmt;

    @Column(name = "PART_PCNT")
    private Long partPcnt;

    @Column(name = "PARTIAL_AMT")
    private Long partialAmt;

    @Column(name = "TAX_PCNT")
    private Long taxPcnt;

    @Column(name = "TAX_AMT")
    private Long taxAmt;

    @Column(name = "SUB_CONT", length = 255)
    private String subCont;

    @Column(name = "CONT_NO", length = 20)
    private String contNo;

    @Column(name = "SUP_CD", length = 10)
    private String supCd;

    @Column(name = "TMPL_ID", length = 20)
    private String tmplId;

    @Column(name = "LABEL_1", length = 30)
    private String label1;

    @Column(name = "LABEL_2", length = 30)
    private String label2;

    @Column(name = "LABEL_3", length = 30)
    private String label3;

    @Column(name = "LABEL_4", length = 30)
    private String label4;

    @Column(name = "LABEL_5", length = 30)
    private String label5;

    @Column(name = "LABEL_6", length = 30)
    private String label6;

    @Column(name = "LABEL_7", length = 30)
    private String label7;

    @Column(name = "LABEL_8", length = 30)
    private String label8;

    @Column(name = "LABEL_9", length = 30)
    private String label9;

    @Column(name = "LABEL_10", length = 30)
    private String label10;

    @Column(name = "ETIMATE_DT", nullable = false)
    private LocalDateTime etimateDt;

    @Column(name = "ACTUAL_UNITS")
    private Long actualUnits;

    @Column(name = "FUND_SOURCE", length = 10)
    private String fundSource;

    @Column(name = "FUND_ID", length = 27)
    private String fundId;

    @Column(name = "STD_COST")
    private Long stdCost;

    @Column(name = "CONTROLLED", length = 255)
    private String controlled;

    @Column(name = "CLIENT_NM", length = 100)
    private String clientNm;

    @Column(name = "PRIORITY")
    private Long priority;

    @Column(name = "CUST_CONTRIB")
    private Long custContrib;

    @Column(name = "PAID_AMT")
    private Long paidAmt;

    @Column(name = "ALLOC_PAID")
    private Long allocPaid;

    @Column(name = "FUND_CONTRIB")
    private Long fundContrib;

    @Column(name = "SETTLED_AMT")
    private Long settledAmt;

    @Column(name = "ALLOC_SETTLE")
    private Long allocSettle;

    @Column(name = "NORM_DEFAULT")
    private Long normDefault;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "LOG_ID")
    private Long logId;

    @Column(name = "ENT_BY", length = 12)
    private String entBy;

    @Column(name = "ENT_DT", nullable = false)
    private LocalDateTime entDt;

    @Column(name = "CONF_BY", length = 12)
    private String confBy;

    @Column(name = "CONF_DT", nullable = false)
    private LocalDateTime confDt;

    @Column(name = "APR_UID1", length = 12)
    private String aprUid1;

    @Column(name = "APR_DT1", nullable = false)
    private LocalDateTime aprDt1;

    @Column(name = "APR_UID2", length = 12)
    private String aprUid2;

    @Column(name = "APR_DT2", nullable = false)
    private LocalDateTime aprDt2;

    @Column(name = "APR_UID3", length = 12)
    private String aprUid3;

    @Column(name = "APR_DT3", nullable = false)
    private LocalDateTime aprDt3;

    @Column(name = "APR_UID4", length = 12)
    private String aprUid4;

    @Column(name = "APR_DT4", nullable = false)
    private LocalDateTime aprDt4;

    @Column(name = "APR_UID5", length = 12)
    private String aprUid5;

    @Column(name = "APR_DT5", nullable = false)
    private LocalDateTime aprDt5;

    @Column(name = "REJCT_UID", length = 12)
    private String rejctUid;

    @Column(name = "REJCT_DT", nullable = false)
    private LocalDateTime rejctDt;

    @Column(name = "REVISE_EST")
    private Long reviseEst;

    @Column(name = "EST_TYPE", length = 40)
    private String estType;

    @Column(name = "REVISE_UID", length = 12)
    private String reviseUid;

    @Column(name = "REVISE_DT", nullable = false)
    private LocalDateTime reviseDt;

    @Column(name = "REV_REASON", length = 300)
    private String revReason;

    @Column(name = "DESCR", length = 2000)
    private String descr;

    @Column(name = "PRJ_ASS_DT")
    private LocalDateTime prjAssDt;

    @Column(name = "REJECT_REASON", length = 800)
    private String rejectReason;

    @Column(name = "OMS_REF_NO", length = 22)
    private String omsRefNo;

    @Column(name = "ESTIMATED_YEAR", length = 6)
    private String estimatedYear;

    @Column(name = "ESTIMATEDYEAR", length = 4)
    private String estimatedyear;

    @Column(name = "LB_RATE_YEAR", length = 7)
    private String lbRateYear;

    @Column(name = "FUND_COST")
    private Long fundCost;

    @Column(name = "SEC_DEP_YEAR", length = 6)
    private String secDepYear;
}
