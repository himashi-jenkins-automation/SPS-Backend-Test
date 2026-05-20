//package com.example.SPSProjectBackend.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import java.math.BigDecimal;
//import java.util.Date;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "APPROVAL") // Matches your table name
//public class Approval {
//
//    @Id
//    @Column(name = "APPROVAL_ID", length = 25, nullable = false)
//    private String approvalId;
//
//    @Column(name = "REFERENCE_NO", length = 21, nullable = false)
//    private String referenceNo;
//
//    @Column(name = "DEPT_ID", length = 6, nullable = false)
//    private String deptId;
//
//    @Column(name = "APPROVAL_TYPE", length = 8, nullable = false)
//    private String approvalType;
//
//    @Column(name = "APPROVED_LEVEL", length = 5, nullable = false)
//    private String approvedLevel;
//
//    @Column(name = "FROM_STATUS", length = 2, nullable = false)
//    private String fromStatus;
//
//    @Column(name = "TO_STATUS", length = 2, nullable = false)
//    private String toStatus;
//
//    @Column(name = "APPROVED_BY", length = 10, nullable = false)
//    private String approvedBy;
//
//    @Column(name = "REASON", length = 500)
//    private String reason;
//
//    @Column(name = "APPROVED_DATE")
//    @Temporal(TemporalType.DATE)
//    private Date approvedDate;
//
//    @Column(name = "APPROVED_TIME", length = 11)
//    private String approvedTime;
//
//    @Column(name = "STANDARD_COST", precision = 15, scale = 2)
//    private BigDecimal standardCost;
//
//    @Column(name = "DETAILED_COST", precision = 15, scale = 2)
//    private BigDecimal detailedCost;
//
//    @Column(name = "SYSTEM_BY", length = 3)
//    private String systemBy;
//}

//new code
package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;


@Entity
@Getter
@Setter
@Table(name = "APPROVAL")
public class Approval {

    @Id
    @Column(name = "APPROVAL_ID", length = 25, nullable = false)
    private String approvalId;

    @Column(name = "REFERENCE_NO", length = 21, nullable = false)
    private String referenceNo;

    @Column(name = "DEPT_ID", length = 6, nullable = false)
    private String deptId;

    @Column(name = "APPROVAL_TYPE", length = 8, nullable = false)
    private String approvalType;

    @Column(name = "APPROVED_LEVEL", length = 5, nullable = false)
    private String approvedLevel;

    @Column(name = "FROM_STATUS", length = 2, nullable = false)
    private String fromStatus;

    @Column(name = "TO_STATUS", length = 2, nullable = false)
    private String toStatus;

    @Column(name = "APPROVED_BY", length = 10, nullable = false)
    private String approvedBy;

    @Column(name = "REASON", length = 500)
    private String reason;

    @Column(name = "APPROVED_DATE")
    private LocalDate approvedDate;

    @Column(name = "APPROVED_TIME", length = 11)
    private String approvedTime;

    @Column(name = "STANDARD_COST", precision = 15, scale = 2)
    private BigDecimal standardCost;

    @Column(name = "DETAILED_COST", precision = 15, scale = 2)
    private BigDecimal detailedCost;

    @Column(name = "SYSTEM_BY", length = 3)
    private String systemBy;

    // Default constructor
    public Approval() {
    }

    // Constructor for creating approval logs
    public Approval(String referenceNo, String deptId, String approvalType,
                    String approvedLevel, String fromStatus, String toStatus,
                    String approvedBy, String reason, BigDecimal standardCost,
                    BigDecimal detailedCost, String systemBy) {
        this.referenceNo = referenceNo;
        this.deptId = deptId;
        this.approvalType = approvalType;
        this.approvedLevel = approvedLevel;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.approvedBy = approvedBy;
        this.reason = reason;
        this.standardCost = standardCost;
        this.detailedCost = detailedCost;
        this.systemBy = systemBy;

        // Set current date and time
//        this.approvedDate = LocalDate.now();

        //fix the time and date isuue
         this.approvedDate = LocalDate.now(ZoneId.of("Asia/Colombo"));

//        this.approvedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        this.approvedTime = LocalTime.now(ZoneId.of("Asia/Colombo"))
                .format(DateTimeFormatter.ofPattern("hh:mm:ss a"));

    }
}