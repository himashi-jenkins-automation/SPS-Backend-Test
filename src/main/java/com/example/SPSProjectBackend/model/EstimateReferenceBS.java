// EstimateReferenceBS.java
package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ESTIMATE_REFERENCEBS")
@IdClass(EstimateReferenceBSId.class)
public class EstimateReferenceBS {

    @Id
    @Column(name = "SESTIMATE_NO", length = 26, nullable = false)
    private String sestimateNo;

    @Id
    @Column(name = "DEPT_ID", length = 6, nullable = false)
    private String deptId;

    @Id
    @Column(name = "WESTIMATE_NO", length = 21, nullable = false)
    private String westimateNo;

    @Column(name = "STATUS", length = 2)
    private String status;

    @Column(name = "ID_NO", length = 10)
    private String idNo;

    @Column(name = "PROJECTNO", length = 21)
    private String projectNo;

    @Column(name = "ENTRY_BY", length = 10)
    private String entryBy;

    @Column(name = "POSTED_BY", length = 8)
    private String postedBy;

    @Column(name = "POSTED_DATE")
    @Temporal(TemporalType.DATE)
    private Date postedDate;

    @Column(name = "POSTED_TIME", length = 11)
    private String postedTime;

    @Column(name = "FILE_REF", length = 100)
    private String fileRef;
}