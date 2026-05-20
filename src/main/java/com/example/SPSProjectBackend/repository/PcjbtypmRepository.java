package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Pcjbtypm;
import com.example.SPSProjectBackend.model.PcjbtypmId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PcjbtypmRepository extends JpaRepository<Pcjbtypm, PcjbtypmId> {
    List<Pcjbtypm> findById_DeptId(String deptId);
}