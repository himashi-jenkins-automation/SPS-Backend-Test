package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "SPPEGGINGDMT")
public class SpPeggingDmt {

    @EmbeddedId
    private SpPeggingDmtId id;

    @Column(name = "POLETYPEID", length = 100)
    private String poleTypeId;

    @Column(name = "POINTTYPEID", length = 200)
    private String pointTypeId;

    @Column(name = "RES_TYPE", length = 20)
    private String resType;

    @Column(name = "RES_CAT", precision = 20)
    private BigDecimal resCat;

    @Column(name = "RES_NAME", length = 150)
    private String resName;

    @Column(name = "UOM", length = 4)
    private String uom;

    @Column(name = "ESTIMATE_QTYOLD", precision = 10)
    private BigDecimal estimateQtyOld;

    @Column(name = "TOLERANCE", precision = 22)
    private BigDecimal tolerance;

    @Column(name = "UNIT_PRICE")
    private Double unitPrice;

    @Column(name = "ESTIMATE_QTY", precision = 10)
    private BigDecimal estimateQty;
}
