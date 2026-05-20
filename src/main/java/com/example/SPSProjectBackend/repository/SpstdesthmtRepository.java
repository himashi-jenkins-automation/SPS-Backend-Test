package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Applicant;
// import com.example.SPSProjectBackend.model.ApplicationModel;
import com.example.SPSProjectBackend.model.Spstdesthmt;
import com.example.SPSProjectBackend.model.SpstdesthmtId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpstdesthmtRepository extends JpaRepository<Spstdesthmt, SpstdesthmtId> {

    @Query("select a from Applicant a " +
            "join ApplicationModel ap on ap.idNo = a.idNo " +
            "join Spstdesthmt s on s.id.appNo = ap.applicationNo " +
            "where s.id.appNo = :appNo")
    Optional<Applicant> findApplicantByAppNo(@Param("appNo") String appNo);

    @Query("select s from Spstdesthmt s where s.id.appNo = :appNo")
    Optional<Spstdesthmt> findFirstByAppNo(@Param("appNo") String appNo);

    // Add this native query method to get status counts:
    @Query(value = "SELECT status, COUNT(*) AS count FROM SPS.SPSTDESTHMT GROUP BY status", nativeQuery = true)
    List<Object[]> findStatusCountsNative();

    // This one for estimate detail page first form
    @Query(value = "SELECT " +
            "s.SECDEPOSIT, s.CEBCOST, s.REBATE_COST, s.VATCOST, " +
            "s.NBTCOST, s.SCTCOST, s.TOCONPAY " +
            "FROM SPSTDESTHMT s " +
            "WHERE s.DEPT_ID = :deptId", nativeQuery = true)
    List<Object[]> findEstimateDetailsByDeptId(@Param("deptId") String deptId);

    // This one for estimate detail page second table
    @Query(value = "SELECT s.LINE_TYPE, s.UOM, s.LINEDES, s.LENGTH, s.EST_COST, s.LINE_COST " +
            "FROM SPSTDESTDMT s WHERE s.DEPT_ID = :deptId", nativeQuery = true)
    List<Object[]> findEstimateItemsByDeptId(@Param("deptId") String deptId);

}
