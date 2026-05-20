package com.example.SPSProjectBackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PCFUNDHM")
public class Pcfundhm {
    @Id
    @Column(name = "FUND_SOURCE", nullable = false, length = 10)
    private String fundSource;

    @Column(name = "NAME", length = 60)
    private String name;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "LOG_ID")
    private Integer logId;

    @Column(name = "MOD_BY", length = 12)
    private String modBy;

    @Column(name = "MOD_DT", nullable = false)
    private LocalDate modDt;

    @Column(name = "ENT_BY", length = 12)
    private String entBy;

    @Column(name = "ENT_DT", nullable = false)
    private LocalDate entDt;

}