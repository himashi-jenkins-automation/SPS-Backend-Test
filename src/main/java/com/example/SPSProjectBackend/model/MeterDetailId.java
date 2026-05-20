package com.example.SPSProjectBackend.model;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class MeterDetailId implements Serializable {
    private String orderCardNo;
    private String mtrType;

    public MeterDetailId() {}

    public MeterDetailId(String orderCardNo, String mtrType) {
        this.orderCardNo = orderCardNo;
        this.mtrType = mtrType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeterDetailId that = (MeterDetailId) o;
        return Objects.equals(orderCardNo, that.orderCardNo) &&
                Objects.equals(mtrType, that.mtrType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderCardNo, mtrType);
    }
}