package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(InwrhmapId.class)
@Table(name = "INWRHMAP")
public class Inwrhmap  implements Serializable {
    @Id
    @Column(name = "DEPT_ID", length = 6)
    private String deptId;

    @Id
    @Column(name = "WRH_DEPT", length = 6)
    private String wrhDept;

    @Column(name = "CON_RAT")
    private Double conRat;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "SYS_NAME", length = 3)
    private String sysName;

    // Getters and Setters
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getWrhDept() {
        return wrhDept;
    }

    public void setWrhDept(String wrhDept) {
        this.wrhDept = wrhDept;
    }

    public Double getConRat() {
        return conRat;
    }

    public void setConRat(Double conRat) {
        this.conRat = conRat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

}
