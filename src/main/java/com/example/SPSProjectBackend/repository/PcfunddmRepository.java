package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Pcfunddm;
import com.example.SPSProjectBackend.model.PcfunddmId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcfunddmRepository extends JpaRepository<Pcfunddm, PcfunddmId> {
    List<Pcfunddm> findById_DeptId(String deptId);
    List<Pcfunddm> findById_DeptIdAndId_FundSource(String deptId, String fundSource);
}