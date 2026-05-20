package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Pcesthtt;
import com.example.SPSProjectBackend.model.PcesthttId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcesthttRepository extends JpaRepository<Pcesthtt, PcesthttId> {

    // ✅ Trim CHAR spaces for Oracle
    @Query("SELECT h FROM Pcesthtt h WHERE TRIM(h.id.estimateNo) = :estimateNo")
    List<Pcesthtt> findByEstimateNo(@Param("estimateNo") String estimateNo);
}
