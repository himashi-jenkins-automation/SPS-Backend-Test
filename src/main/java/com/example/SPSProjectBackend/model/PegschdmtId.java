package com.example.SPSProjectBackend.model;

import java.io.Serializable;
import java.util.Objects;

public class PegschdmtId implements Serializable {

    private String estimateNo;
    private String nodeId;

    public PegschdmtId() {}

    public PegschdmtId(String estimateNo, String nodeId) {
        this.estimateNo = estimateNo;
        this.nodeId = nodeId;
    }

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

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PegschdmtId)) return false;
        PegschdmtId that = (PegschdmtId) o;
        return Objects.equals(estimateNo, that.estimateNo) &&
                Objects.equals(nodeId, that.nodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estimateNo, nodeId);
    }
}