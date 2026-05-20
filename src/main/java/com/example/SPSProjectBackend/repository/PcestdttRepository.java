package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Pcestdtt;
import com.example.SPSProjectBackend.model.PcestdttId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcestdttRepository extends JpaRepository<Pcestdtt, PcestdttId> {

    @Query("SELECT p FROM Pcestdtt p WHERE TRIM(p.estimateNo) = :estimateNo")
    List<Pcestdtt> findByEstimateNo(@Param("estimateNo") String estimateNo);
}
