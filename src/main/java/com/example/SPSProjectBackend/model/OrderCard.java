package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "SPODRCRD")
@IdClass(OrderCardId.class) // Add this annotation
public class OrderCard {

    @Id // Add @Id annotation
    @Column(name = "PROJECT_NO", length = 20, nullable = false)
    private String projectNo;

    @Id // Add @Id annotation
    @Column(name = "DEPT_ID", length = 6, nullable = false)
    private String deptId;

    @Column(name = "NO_OF_METERS", precision = 1)
    private BigDecimal noOfMeters;

    @Column(name = "METER_NO_1", length = 15)
    private String meterNo1;

    @Column(name = "INIT_READING_1", precision = 10)
    private BigDecimal initReading1;

    @Column(name = "METER_TYPE_1", length = 1)
    private String meterType1;

    @Column(name = "NO_OF_DIGITS_1", precision = 2)
    private BigDecimal noOfDigits1;

    @Column(name = "METER_NO_2", length = 15)
    private String meterNo2;

    @Column(name = "INIT_READING_2", precision = 10)
    private BigDecimal initReading2;

    @Column(name = "METER_TYPE_2", length = 1)
    private String meterType2;

    @Column(name = "NO_OF_DIGITS_2", precision = 2)
    private BigDecimal noOfDigits2;

    @Column(name = "METER_NO_3", length = 15)
    private String meterNo3;

    @Column(name = "INIT_READING_3", precision = 10)
    private BigDecimal initReading3;

    @Column(name = "METER_TYPE_3", length = 1)
    private String meterType3;

    @Column(name = "NO_OF_DIGITS_3", precision = 2)
    private BigDecimal noOfDigits3;

    @Column(name = "CONNECTED_DATE")
    private LocalDateTime connectedDate;

    @Column(name = "AVG_CONSUMP", precision = 5)
    private BigDecimal avgConsump;

    @Column(name = "KVA_RATING", precision = 3)
    private BigDecimal kvaRating;

    @Column(name = "READER_CODE", length = 2)
    private String readerCode;

    @Column(name = "PACK_NO", length = 2)
    private String packNo;

    @Column(name = "WALK_SEQ", length = 5)
    private String walkSeq;

    @Column(name = "OLD_ACC_NO", length = 10)
    private String oldAccNo;

    @Column(name = "UPD_BY", length = 10)
    private String updBy;

    @Column(name = "UPD_DATE")
    private LocalDateTime updDate;

    @Column(name = "DEPOT_CODE", length = 1)
    private String depotCode;

    @Column(name = "AREA_CODE", length = 2)
    private String areaCode;

    @Column(name = "ORDER_CARD_NO", length = 20)
    private String orderCardNo;

    // ============ NEWLY ADDED FIELDS for SPODRCRD because we have to save all ui data in ordercard  ============

    @Column(name = "CUSTOMER_NAME", length = 50)
    private String customerName;

    @Column(name = "ADDRESS_OF_SUPPLY", length = 50)
    private String addressOfSupply;

    @Column(name = "CONTRACT_DEMAND", precision = 20)
    private BigDecimal contractDemand;

    @Column(name = "CUSTOMER_CATEGORY", length = 1)
    private String customerCategory;

    @Column(name = "NATURE_OF_SUPPLY_CATEGORY", length = 1)
    private String natureOfSupplyCategory;

    @Column(name = "ISIC_NUMBER", precision = 5)
    private BigDecimal isicNumber;

    @Column(name = "CUSTOMER_OWNERSHIP_TYPE", length = 2)
    private String customerOwnershipType;

    @Column(name = "TARIFF_TYPE", length = 10)
    private String tariffType;

    //new added fields

    @Column(name = "TRANSFORMER_NUMBER", length = 10)
    private String transformerNumber;

    @Column(name = "SIN_NUMBER", length = 20)
    private String sinNumber;

    // ============ NEWLY ADDED 6 COLUMNS for save the PIV details ============
    @Column(name = "EST_PIV_NUMBER", length = 50)
    private String estPivNumber;

    @Column(name = "EST_AMOUNT", precision = 15)
    private BigDecimal estAmount;

    @Column(name = "EST_PAY_DATE")
    private LocalDateTime estPayDate;

    @Column(name = "DEP_PIV_NUMBER", length = 50)
    private String depPivNumber;

    @Column(name = "DEPOSIT_AMOUNT", precision = 15)
    private BigDecimal depositAmount;

    @Column(name = "DEPOSIT_DATE")
    private LocalDateTime depositDate;

    // ============ NEWLY ADDED 3 LOAN COLUMNS ============
    @Column(name = "IS_LOAN_APP", length = 1)
    private String isLoanApp;

    @Column(name = "LOAN_AMOUNT", precision = 15)
    private BigDecimal loanAmount;

    @Column(name = "LOAN_TYPE", length = 5)
    private String loanType;

    // ============ NEWLY ADDED 3  COLUMNS ============
    @Column(name = "JOB_TYPE", length = 50)
    private String jobType;

    @Column(name = "VOLTAGE_LEVEL", length = 5)
    private String voltageLevel;

    @Column(name = "SECTION_ID", length = 50)
    private String sectionId;

    @Column(name = "VAT_REG_NO", length = 20)
    private String vatRegNo;

}