package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "WIRING_LAND_DETAIL")
public class WiringLandDetail {

    @EmbeddedId
    private WiringLandDetailId id;

    @Column(name = "SERVICE_STREET_ADDRESS", length = 100, nullable = false)
    private String serviceStreetAddress;

    @Column(name = "SERVICE_SUBURB", length = 100)
    private String serviceSuburb;

    @Column(name = "SERVICE_CITY", length = 100)
    private String serviceCity;

    @Column(name = "SERVICE_POSTAL_CODE", length = 5)
    private String servicePostalCode;

    @Column(name = "OWNERSHIP", length = 1, nullable = false)
    private String ownership;

    @Column(name = "OCCUPY_OWNER_CERTIFIED", length = 1, nullable = false)
    private String occupyOwnerCertified;

    @Column(name = "IS_GOVERNMENT_PLACE", length = 1, nullable = false)
    private String isGovernmentPlace;

    @Column(name = "ASSESSMENT_NO", length = 6)
    private String assessmentNo;

    @Column(name = "NEIGHBOURS_ACC_NO", length = 10)
    private String neighboursAccNo;

    @Column(name = "NO_OF_BULBS", precision = 3)
    private BigDecimal noOfBulbs;

    @Column(name = "NO_OF_FANS", precision = 3)
    private BigDecimal noOfFans;

    @Column(name = "NO_OF_PLUGS_5A", precision = 3)
    private BigDecimal noOfPlugs5A;

    @Column(name = "NO_OF_PLUGS_15A", precision = 3)
    private BigDecimal noOfPlugs15A;

    @Column(name = "MOTOR_TOTAL", precision = 3)
    private BigDecimal motorTotal;

    @Column(name = "WELDING_PLANT", precision = 3)
    private BigDecimal weldingPlant;

    @Column(name = "METAL_CRUSHER", precision = 3)
    private BigDecimal metalCrusher;

    @Column(name = "SAW_MILLS", precision = 3)
    private BigDecimal sawMills;

    @Column(name = "PHASE", precision = 1, nullable = false)
    private BigDecimal phase;

    @Column(name = "CONNECTION_TYPE", precision = 2, nullable = false)
    private BigDecimal connectionType;

    @Column(name = "TARIFF_CAT_CODE", length = 2, nullable = false)
    private String tariffCatCode;

    @Column(name = "TARIFF_CODE", length = 2, nullable = false)
    private String tariffCode;

    @Column(name = "CUSTOMER_CATEGORY", length = 4, nullable = false)
    private String customerCategory;

    @Column(name = "CUSTOMER_TYPE", length = 4, nullable = false)
    private String customerType;

    @Column(name = "EXISTING_ACC_NO", length = 10)
    private String existingAccNo;

    @Column(name = "NO_OF_DMG_METERS", precision = 1)
    private BigDecimal noOfDmgMeters;

    @Column(name = "SERVICE_DISTRICT", length = 30)
    private String serviceDistrict;

    @Column(name = "ZONE_ID", length = 2)
    private String zoneId;

    @Column(name = "DEMAND", precision = 20)
    private BigDecimal demand;

    @Column(name = "SOLAR_CAPACITY", precision = 7)
    private BigDecimal solarCapacity;

    @Column(name = "SOLAR_SCHEME_ID", precision = 22)
    private BigDecimal solarSchemeId;

    @Column(name = "NET_SUBTYPE", length = 10)
    private String netSubtype;

    // Getters and Setters
    public WiringLandDetailId getId() { return id; }
    public void setId(WiringLandDetailId id) { this.id = id; }
    public String getServiceStreetAddress() { return serviceStreetAddress; }
    public void setServiceStreetAddress(String serviceStreetAddress) { this.serviceStreetAddress = serviceStreetAddress; }
    public String getServiceSuburb() { return serviceSuburb; }
    public void setServiceSuburb(String serviceSuburb) { this.serviceSuburb = serviceSuburb; }
    public String getServiceCity() { return serviceCity; }
    public void setServiceCity(String serviceCity) { this.serviceCity = serviceCity; }
    public String getServicePostalCode() { return servicePostalCode; }
    public void setServicePostalCode(String servicePostalCode) { this.servicePostalCode = servicePostalCode; }
    public String getOwnership() { return ownership; }
    public void setOwnership(String ownership) { this.ownership = ownership; }
    public String getOccupyOwnerCertified() { return occupyOwnerCertified; }
    public void setOccupyOwnerCertified(String occupyOwnerCertified) { this.occupyOwnerCertified = occupyOwnerCertified; }
    public String getIsGovernmentPlace() { return isGovernmentPlace; }
    public void setIsGovernmentPlace(String isGovernmentPlace) { this.isGovernmentPlace = isGovernmentPlace; }
    public String getAssessmentNo() { return assessmentNo; }
    public void setAssessmentNo(String assessmentNo) { this.assessmentNo = assessmentNo; }
    public String getNeighboursAccNo() { return neighboursAccNo; }
    public void setNeighboursAccNo(String neighboursAccNo) { this.neighboursAccNo = neighboursAccNo; }
    public BigDecimal getNoOfBulbs() { return noOfBulbs; }
    public void setNoOfBulbs(BigDecimal noOfBulbs) { this.noOfBulbs = noOfBulbs; }
    public BigDecimal getNoOfFans() { return noOfFans; }
    public void setNoOfFans(BigDecimal noOfFans) { this.noOfFans = noOfFans; }
    public BigDecimal getNoOfPlugs5A() { return noOfPlugs5A; }
    public void setNoOfPlugs5A(BigDecimal noOfPlugs5A) { this.noOfPlugs5A = noOfPlugs5A; }
    public BigDecimal getNoOfPlugs15A() { return noOfPlugs15A; }
    public void setNoOfPlugs15A(BigDecimal noOfPlugs15A) { this.noOfPlugs15A = noOfPlugs15A; }
    public BigDecimal getMotorTotal() { return motorTotal; }
    public void setMotorTotal(BigDecimal motorTotal) { this.motorTotal = motorTotal; }
    public BigDecimal getWeldingPlant() { return weldingPlant; }
    public void setWeldingPlant(BigDecimal weldingPlant) { this.weldingPlant = weldingPlant; }
    public BigDecimal getMetalCrusher() { return metalCrusher; }
    public void setMetalCrusher(BigDecimal metalCrusher) { this.metalCrusher = metalCrusher; }
    public BigDecimal getSawMills() { return sawMills; }
    public void setSawMills(BigDecimal sawMills) { this.sawMills = sawMills; }
    public BigDecimal getPhase() { return phase; }
    public void setPhase(BigDecimal phase) { this.phase = phase; }
    public BigDecimal getConnectionType() { return connectionType; }
    public void setConnectionType(BigDecimal connectionType) { this.connectionType = connectionType; }
    public String getTariffCatCode() { return tariffCatCode; }
    public void setTariffCatCode(String tariffCatCode) { this.tariffCatCode = tariffCatCode; }
    public String getTariffCode() { return tariffCode; }
    public void setTariffCode(String tariffCode) { this.tariffCode = tariffCode; }
    public String getCustomerCategory() { return customerCategory; }
    public void setCustomerCategory(String customerCategory) { this.customerCategory = customerCategory; }
    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }
    public String getExistingAccNo() { return existingAccNo; }
    public void setExistingAccNo(String existingAccNo) { this.existingAccNo = existingAccNo; }
    public BigDecimal getNoOfDmgMeters() { return noOfDmgMeters; }
    public void setNoOfDmgMeters(BigDecimal noOfDmgMeters) { this.noOfDmgMeters = noOfDmgMeters; }
    public String getServiceDistrict() { return serviceDistrict; }
    public void setServiceDistrict(String serviceDistrict) { this.serviceDistrict = serviceDistrict; }
    public String getZoneId() { return zoneId; }
    public void setZoneId(String zoneId) { this.zoneId = zoneId; }
    public BigDecimal getDemand() { return demand; }
    public void setDemand(BigDecimal demand) { this.demand = demand; }
    public BigDecimal getSolarCapacity() { return solarCapacity; }
    public void setSolarCapacity(BigDecimal solarCapacity) { this.solarCapacity = solarCapacity; }
    public BigDecimal getSolarSchemeId() { return solarSchemeId; }
    public void setSolarSchemeId(BigDecimal solarSchemeId) { this.solarSchemeId = solarSchemeId; }
    public String getNetSubtype() { return netSubtype; }
    public void setNetSubtype(String netSubtype) { this.netSubtype = netSubtype; }
}