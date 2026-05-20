package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(PegschdmtId.class)
@Table(name = "PEGSCHDMT")
public class Pegschdmt implements Serializable {

    @Id
    @Column(name = "ESTIMATENO", length = 50, nullable = false)
    private String estimateNo;

    @Id
    @Column(name = "NODEID", length = 50, nullable = false)
    private String nodeId;

    @Column(name = "NOOFITEM")
    private Double noOfItem;

    @Column(name = "NODEDES", length = 100)
    private String nodeDes;

    @Column(name = "DEPT_ID", length = 10)
    private String deptId;

    // Constructors
    public Pegschdmt() {}

    // Getters and Setters
    public String getEstimateNo() {
        return estimateNo;
    }

    public void setEstimateNo(String estimateNo) {
        this.estimateNo = estimateNo;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Double getNoOfItem() {
        return noOfItem;
    }

    public void setNoOfItem(Double noOfItem) {
        this.noOfItem = noOfItem;
    }

    public String getNodeDes() {
        return nodeDes;
    }

    public void setNodeDes(String nodeDes) {
        this.nodeDes = nodeDes;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}