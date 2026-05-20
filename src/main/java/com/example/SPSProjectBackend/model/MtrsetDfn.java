package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mtrset_dfn")
public class MtrsetDfn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mtr_type", length = 6)
    private String mtrType;

    @Column(name = "cus_cat", length = 1)
    private String cusCat;

    @Column(name = "mtrset_type")
    private Short mtrsetType;

    @Column(name = "mtr_order")
    private Short mtrOrder;

    // Constructors
    public MtrsetDfn() {}

    public MtrsetDfn(String mtrType, String cusCat, Short mtrsetType, Short mtrOrder) {
        this.mtrType = mtrType;
        this.cusCat = cusCat;
        this.mtrsetType = mtrsetType;
        this.mtrOrder = mtrOrder;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMtrType() { return mtrType; }
    public void setMtrType(String mtrType) { this.mtrType = mtrType; }

    public String getCusCat() { return cusCat; }
    public void setCusCat(String cusCat) { this.cusCat = cusCat; }

    public Short getMtrsetType() { return mtrsetType; }
    public void setMtrsetType(Short mtrsetType) { this.mtrsetType = mtrsetType; }

    public Short getMtrOrder() { return mtrOrder; }
    public void setMtrOrder(Short mtrOrder) { this.mtrOrder = mtrOrder; }
}