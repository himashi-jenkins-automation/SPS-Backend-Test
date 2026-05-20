//This one for estimate detail page second table
package com.example.SPSProjectBackend.dto;

import java.math.BigDecimal;

// public class EstimateItemDTO {
import lombok.Getter;
import lombok.Setter;

// import java.math.BigDecimal;

@Setter
@Getter
public class EstimateItemDTO {
    // Getters and Setters
    private String lineType; // Maps to LINE_TYPE (code)
    private String uom; // Maps to UOM
    private String linedes; // Maps to LINEDES (description)
    private Float length; // Maps to LENGTH (quantity/length)
    private Double estCost; // Maps to EST_COST (standard rate)
    private Double lineCost; // Maps to LINE_COST (cost)
    private BigDecimal demand; // Maps to DEMAND from WIRING_LAND_DETAIL

    // Getters and Setters
    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getLinedes() {
        return linedes;
    }

    public void setLinedes(String linedes) {
        this.linedes = linedes;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Double getEstCost() {
        return estCost;
    }

    public void setEstCost(Double estCost) {
        this.estCost = estCost;
    }

    public Double getLineCost() {
        return lineCost;
    }

    public void setLineCost(Double lineCost) {
        this.lineCost = lineCost;
    }

    public BigDecimal getDemand() {
        return demand;
    }

    public void setDemand(BigDecimal demand) {
        this.demand = demand;
    }
}