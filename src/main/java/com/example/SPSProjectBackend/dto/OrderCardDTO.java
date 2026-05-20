package com.example.SPSProjectBackend.dto;

// import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderCardDTO {
    private String projectNo;
    private String deptId;
    private BigDecimal noOfMeters;
    private String meterNo1;
    private BigDecimal initReading1;
    private String meterType1;
    private BigDecimal noOfDigits1;
    private String meterNo2;
    private BigDecimal initReading2;
    private String meterType2;
    private BigDecimal noOfDigits2;
    private String meterNo3;
    private BigDecimal initReading3;
    private String meterType3;
    private BigDecimal noOfDigits3;
    private LocalDateTime connectedDate;
    private BigDecimal avgConsump;
    private BigDecimal kvaRating;
    private String readerCode;
    private String packNo;
    private String walkSeq;
    private String oldAccNo;
    private String updBy;
    private LocalDateTime updDate;
    private String depotCode;
    private String areaCode;
    private String orderCardNo;

    // ============ NEWLY ADDED FIELDS to ordercard table which is SPODRCRD
    // ============

    private String customerName;
    private String addressOfSupply;
    private BigDecimal contractDemand;
    private String customerCategory;
    private String natureOfSupplyCategory;
    private BigDecimal isicNumber;
    private String customerOwnershipType;
    private String tariffType;
    private String transformerNumber;
    private String sinNumber;

    // ============ NEWLY ADDED 6 FIELDS ============
    private String estPivNumber;
    private BigDecimal estAmount;
    private LocalDateTime estPayDate;
    private String depPivNumber;
    private BigDecimal depositAmount;
    private LocalDateTime depositDate;

    // ============ NEWLY ADDED 3 LOAN FIELDS ============
    private String isLoanApp;
    private BigDecimal loanAmount;
    private String loanType;

    // ============ NEWLY ADDED 3 COLUMNS ============

    private String jobType;
    private String voltageLevel;
    private String sectionId;
    private String vatRegNo;
}