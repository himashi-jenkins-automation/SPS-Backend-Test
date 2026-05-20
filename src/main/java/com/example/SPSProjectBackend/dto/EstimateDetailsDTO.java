//This one for estimate detail page first form

package com.example.SPSProjectBackend.dto;

import java.math.BigDecimal;

// public class EstimateDetailsDTO {
// package com.example.SPSProjectBackend.dto;

import lombok.Getter;
import lombok.Setter;

// import java.math.BigDecimal;

@Setter
@Getter
public class EstimateDetailsDTO {
    // Getters and Setters
    private Double secdeposit;
    private Double cebcost;
    private Double rebateCost;
    private Double vatcost;
    private Double nbtcost;
    private Double sctcost;
    private Double toconpay;
    private BigDecimal demand;

    // Getters and Setters
    public Double getSecdeposit() {
        return secdeposit;
    }

    public void setSecdeposit(Double secdeposit) {
        this.secdeposit = secdeposit;
    }

    public Double getCebcost() {
        return cebcost;
    }

    public void setCebcost(Double cebcost) {
        this.cebcost = cebcost;
    }

    public Double getRebateCost() {
        return rebateCost;
    }

    public void setRebateCost(Double rebateCost) {
        this.rebateCost = rebateCost;
    }

    public Double getVatcost() {
        return vatcost;
    }

    public void setVatcost(Double vatcost) {
        this.vatcost = vatcost;
    }

    public Double getNbtcost() {
        return nbtcost;
    }

    public void setNbtcost(Double nbtcost) {
        this.nbtcost = nbtcost;
    }

    public Double getSctcost() {
        return sctcost;
    }

    public void setSctcost(Double sctcost) {
        this.sctcost = sctcost;
    }

    public Double getToconpay() {
        return toconpay;
    }

    public void setToconpay(Double toconpay) {
        this.toconpay = toconpay;
    }

    public BigDecimal getDemand() {
        return demand;
    }

    public void setDemand(BigDecimal demand) {
        this.demand = demand;
    }
}