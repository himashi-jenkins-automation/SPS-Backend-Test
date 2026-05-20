package com.example.SPSProjectBackend.model;

import java.io.Serializable;
import java.util.Objects;

public class SppegInformationId implements Serializable {
    private String id;
    private String parentId;
    private String deptId;

    public SppegInformationId() {
    }

    public SppegInformationId(String id, String parentId, String deptId) {
        this.id = id;
        this.parentId = parentId;
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SppegInformationId)) return false;
        SppegInformationId that = (SppegInformationId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, deptId);
    }
}