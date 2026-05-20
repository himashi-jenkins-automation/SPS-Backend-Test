package com.example.SPSProjectBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SPSProjectBackend.model.SppegInformation;
import com.example.SPSProjectBackend.model.SppegInformationId;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SppegInformationRepository extends JpaRepository<SppegInformation, SppegInformationId> {

    @Query("SELECT s FROM SppegInformation s WHERE s.deptId = :deptId AND s.parentId = :parentId")
    List<SppegInformation> findByParentId(String deptId, String parentId);

    //    @Query("SELECT s FROM SppegInformation s WHERE s.deptId = '4' AND s.parentId = '1'")
    //    List<SppegInformation> findByParentId();

    // NEW: Find by department only
    @Query("SELECT s FROM SppegInformation s WHERE s.deptId = :deptId")
    List<SppegInformation> findByDeptId(@Param("deptId") String deptId);
}
