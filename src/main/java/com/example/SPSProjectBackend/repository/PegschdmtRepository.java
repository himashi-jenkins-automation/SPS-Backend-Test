package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Pegschdmt;
import com.example.SPSProjectBackend.model.PegschdmtId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PegschdmtRepository extends JpaRepository<Pegschdmt, PegschdmtId> {
}