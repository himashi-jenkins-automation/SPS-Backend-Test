package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Spstdestdmt;
import com.example.SPSProjectBackend.model.SpstdestdmtId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpstdestdmtRepository extends JpaRepository<Spstdestdmt, SpstdestdmtId> {
    List<Spstdestdmt> findAllByIdStdNo(String stdNo);

    @Query(value = "SELECT s.LINE_TYPE, s.UOM, s.LINEDES, s.LENGTH, s.EST_COST, s.LINE_COST " +
            "FROM SPSTDESTDMT s WHERE s.DEPT_ID = :deptId",
            nativeQuery = true)
    List<Object[]> findEstimateItemsByDeptId(@Param("deptId") String deptId);
}