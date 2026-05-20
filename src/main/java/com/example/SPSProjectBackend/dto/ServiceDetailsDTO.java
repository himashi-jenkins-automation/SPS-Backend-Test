// Updated ServiceDetailsDTO.java
package com.example.SPSProjectBackend.dto;

import java.math.BigDecimal;

public class ServiceDetailsDTO {
    private String serviceStreetAddress;
    private String serviceSuburb;
    private String serviceCity;
    private String servicePostalCode;
    private String serviceDistrict;
    private BigDecimal demand;
    private String neighboursAccNo;
    private String area; // Added field for area (from WiringLandDetailCon)

    // Default constructor
    public ServiceDetailsDTO() {}

    // Parameterized constructor (updated to include area)
    public ServiceDetailsDTO(String serviceStreetAddress, String serviceSuburb,
                             String serviceCity, String servicePostalCode,
                             String serviceDistrict, BigDecimal demand,
                             String neighboursAccNo, String area) {
        this.serviceStreetAddress = serviceStreetAddress;
        this.serviceSuburb = serviceSuburb;
        this.serviceCity = serviceCity;
        this.servicePostalCode = servicePostalCode;
        this.serviceDistrict = serviceDistrict;
        this.demand = demand;
        this.neighboursAccNo = neighboursAccNo;
        this.area = area;
    }

    // Getters and Setters
    public String getServiceStreetAddress() {
        return serviceStreetAddress;
    }

    public void setServiceStreetAddress(String serviceStreetAddress) {
        this.serviceStreetAddress = serviceStreetAddress;
    }

    public String getServiceSuburb() {
        return serviceSuburb;
    }

    public void setServiceSuburb(String serviceSuburb) {
        this.serviceSuburb = serviceSuburb;
    }

    public String getServiceCity() {
        return serviceCity;
    }

    public void setServiceCity(String serviceCity) {
        this.serviceCity = serviceCity;
    }

    public String getServicePostalCode() {
        return servicePostalCode;
    }

    public void setServicePostalCode(String servicePostalCode) {
        this.servicePostalCode = servicePostalCode;
    }

    public String getServiceDistrict() {
        return serviceDistrict;
    }

    public void setServiceDistrict(String serviceDistrict) {
        this.serviceDistrict = serviceDistrict;
    }

    public BigDecimal getDemand() {
        return demand;
    }

    public void setDemand(BigDecimal demand) {
        this.demand = demand;
    }

    public String getNeighboursAccNo() {
        return neighboursAccNo;
    }

    public void setNeighboursAccNo(String neighboursAccNo) {
        this.neighboursAccNo = neighboursAccNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}