package com.example.SPSProjectBackend.model;

import java.io.Serializable;
import java.util.Objects;

public class InwrhmapId implements Serializable {
    private String deptId;
    private String wrhDept;

    public InwrhmapId() {
    }

    public InwrhmapId(String deptId, String wrhDept) {
        this.deptId = deptId;
        this.wrhDept = wrhDept;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InwrhmapId)) return false;
        InwrhmapId that = (InwrhmapId) o;
        return Objects.equals(deptId, that.deptId) && Objects.equals(wrhDept, that.wrhDept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId, wrhDept);
    }

}
