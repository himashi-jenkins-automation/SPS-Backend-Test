// EstimateReferenceBSDTO.java
package com.example.SPSProjectBackend.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class EstimateReferenceBSDTO {
    private String sestimateNo;
    private String deptId;
    private String westimateNo;
    private String status;
    private String idNo;
    private String projectNo;
    private String entryBy;
    private String postedBy;
    private Date postedDate;
    private String postedTime;
    private String fileRef;
}