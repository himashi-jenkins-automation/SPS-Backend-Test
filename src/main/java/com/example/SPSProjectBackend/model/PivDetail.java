package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "PIV_DETAIL")

public class PivDetail {

    @EmbeddedId
    private PivDetailId id;  // Composite key (PIV_NO and DEPT_ID)

    @Column(name = "PIV_SEQ_NO", precision = 2, nullable = false)
    private BigDecimal pivSeqNo;

    @Column(name = "PIV_RECEIPT_NO", length = 10)
    private String pivReceiptNo;

    @Column(name = "REFERENCE_TYPE", length = 3, nullable = false)
    private String referenceType;

    @Column(name = "REFERENCE_NO", length = 50, nullable = false)
    private String referenceNo;

    @Column(name = "APP_REFERENCE_NO", length = 24)
    private String appReferenceNo;

    @Column(name = "EST_REFERENCE_NO", length = 21)
    private String estReferenceNo;

    @Column(name = "REVISE_REFERENCE_NO", length = 21)
    private String reviseReferenceNo;

    @Column(name = "ID_NO", length = 12, nullable = false)
    private String idNo;

    @Column(name = "PIV_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date pivDate;

    @Column(name = "PIV_AMOUNT", precision = 24, nullable = false)
    private BigDecimal pivAmount;

    @Column(name = "CEB_BRANCH_NAME", length = 100)
    private String cebBranchName;

    @Column(name = "BANK_CODE", length = 4)
    private String bankCode;

    @Column(name = "BRANCH_CODE", length = 4)
    private String branchCode;

    @Column(name = "PAYMENT_MODE", length = 1)
    private String paymentMode;

    @Column(name = "BANK_PAID_DATE")
    @Temporal(TemporalType.DATE)
    private Date bankPaidDate;

    @Column(name = "PAID_DATE")
    @Temporal(TemporalType.DATE)
    private Date paidDate;

    @Column(name = "CHEQUE_BANK_CODE", length = 4)
    private String chequeBankCode;

    @Column(name = "CHEQUE_BRANCH_CODE", length = 4)
    private String chequeBranchCode;

    @Column(name = "CHEQUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date chequeDate;

    @Column(name = "CHEQUE_NO", precision = 8)
    private BigDecimal chequeNo;

    @Column(name = "MISCELLANEOUS_INCOME", precision = 20)
    private BigDecimal miscellaneousIncome;

    @Column(name = "ELECTRICITY_DEBTORS", precision = 20)
    private BigDecimal electricityDebtors;

    @Column(name = "SECURITY_DEPOSIT", precision = 24)
    private BigDecimal securityDeposit;

    @Column(name = "SER_CONN_OR_ELEC_SCH", precision = 24)
    private BigDecimal serConnOrElecSch;

    @Column(name = "TENDER_DEPOSIT", precision = 20)
    private BigDecimal tenderDeposit;

    @Column(name = "MISCELLANEOUS_DEPOSIT", precision = 20)
    private BigDecimal miscellaneousDeposit;

    @Column(name = "CASH_IN_TRANSIT", precision = 20)
    private BigDecimal cashInTransit;

    @Column(name = "FOR_DISHONORED_CHEQUE", precision = 20)
    private BigDecimal forDishonoredCheque;

    @Column(name = "SUB_TOTAL", precision = 24)
    private BigDecimal subTotal;

    @Column(name = "VAT", precision = 20)
    private BigDecimal vat;

    @Column(name = "GRAND_TOTAL", precision = 24, nullable = false)
    private BigDecimal grandTotal;

    @Column(name = "LOAN_REFERENCE", length = 10)
    private String loanReference;

    @Column(name = "LOAN_AMOUNT", precision = 20)
    private BigDecimal loanAmount;

    @Column(name = "PREPARED_BY", length = 12, nullable = false)
    private String preparedBy;

    @Column(name = "APPROVED_BY", length = 12)
    private String approvedBy;

    @Column(name = "CONFIRMED_BY", length = 12)
    private String confirmedBy;

    @Column(name = "CONFIRMED_DATE")
    @Temporal(TemporalType.DATE)
    private Date confirmedDate;

    @Column(name = "CONFIRMED_TIME", length = 11)
    private String confirmedTime;

    @Column(name = "STATUS", length = 2, nullable = false)
    private String status;

    @Column(name = "ADD_USER", length = 12)
    private String addUser;

    @Column(name = "ADD_DATE")
    @Temporal(TemporalType.DATE)
    private Date addDate;

    @Column(name = "ADD_TIME", length = 11)
    private String addTime;

    @Column(name = "UPD_USER", length = 12)
    private String updUser;

    @Column(name = "UPD_DATE")
    @Temporal(TemporalType.DATE)
    private Date updDate;

    @Column(name = "UPD_TIME", length = 11)
    private String updTime;

    @Column(name = "CANCEL_USER", length = 12)
    private String cancelUser;

    @Column(name = "CANCEL_DATE")
    @Temporal(TemporalType.DATE)
    private Date cancelDate;

    @Column(name = "CANCEL_TIME", length = 11)
    private String cancelTime;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "POS_CENTER", length = 6)
    private String posCenter;

    @Column(name = "POS_A", length = 5)
    private String posA;

    @Column(name = "NBT_AMOUNT", precision = 20)
    private BigDecimal nbtAmount;

    @Column(name = "BANK_CHECK_NO", length = 14)
    private String bankCheckNo;

    @Column(name = "BANK_STATUS", length = 1)
    private String bankStatus;

    @Column(name = "BANK_PAID_TIME")
    @Temporal(TemporalType.DATE)
    private Date bankPaidTime;

    @Column(name = "PAID_AMOUNT", precision = 20)
    private BigDecimal paidAmount;

    @Column(name = "PROCESSING_FEE", precision = 12)
    private BigDecimal processingFee;

    @Column(name = "TITLE_CD", length = 40)
    private String titleCd;

    @Column(name = "ISSUED_DATE")
    private Timestamp issuedDate;

    @Column(name = "PREPARED_DATE")
    private Timestamp preparedDate;

    @Column(name = "APPROVED_DATE")
    private Timestamp approvedDate;

    @Column(name = "PAID_AGENT", length = 6)
    private String paidAgent;

    @Column(name = "PAID_BRANCH", length = 6)
    private String paidBranch;

    @Column(name = "REJECTED_BY", length = 12)
    private String rejectedBy;

    @Column(name = "REJECTED_DATE")
    @Temporal(TemporalType.DATE)
    private Date rejectedDate;

    @Column(name = "REJECTED_REASON", length = 150)
    private String rejectedReason;

    @Column(name = "CURRENCY_CODE", length = 3)
    private String currencyCode;

    @Column(name = "EXPIRY_DATE")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Column(name = "SYS_TYPE", length = 4)
    private String sysType;

    @Column(name = "CUS_VAT_NO", length = 20)
    private String cusVatNo;

    @Column(name = "AMOUNT_IN_WORDS", length = 200)
    private String amountInWords;

    @Column(name = "OTHER_PIV_REF", length = 100)
    private String otherPivRef;

    @Column(name = "ENTERED_TIME")
    private Timestamp enteredTime;

    @Column(name = "PAID_TIME")
    private Timestamp paidTime;

    @Column(name = "PAID_DEPT_ID", length = 6)
    private String paidDeptId;

    @Column(name = "SETOFF_FROM", length = 50)
    private String setoffFrom;

    @Column(name = "SETOFF_TO", length = 20)
    private String setoffTo;

    @Column(name = "CA_APPLICATION_NO", precision = 22)
    private BigDecimal caApplicationNo;
}