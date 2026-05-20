// Updated WiringLandDetailRepository.java (add custom query)
package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.WiringLandDetail;
import com.example.SPSProjectBackend.model.WiringLandDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WiringLandDetailRepository extends JpaRepository<WiringLandDetail, WiringLandDetailId> {

    @Query("SELECT w FROM WiringLandDetail w WHERE w.id.deptId = :deptId")
    List<WiringLandDetail> findByIdDeptId(@Param("deptId") String deptId);

    @Query("SELECT w.demand FROM WiringLandDetail w WHERE w.id.deptId = :deptId")
    BigDecimal findDemandByDeptId(@Param("deptId") String deptId);
}