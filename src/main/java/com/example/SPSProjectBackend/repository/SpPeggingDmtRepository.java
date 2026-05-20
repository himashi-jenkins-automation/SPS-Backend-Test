package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.SpPeggingDmt;
import com.example.SPSProjectBackend.model.SpPeggingDmtId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpPeggingDmtRepository extends JpaRepository<SpPeggingDmt, SpPeggingDmtId> {
    List<SpPeggingDmt> findByIdLineSectionTypeId(String lineSectionTypeId);
}
