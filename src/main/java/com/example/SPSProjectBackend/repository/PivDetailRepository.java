package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.PivDetail;
import com.example.SPSProjectBackend.model.PivDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PivDetailRepository extends JpaRepository<PivDetail, PivDetailId> {
    // Add custom queries if needed
    // @Query("SELECT p FROM PivDetail p WHERE p.estReferenceNo = :estimateNo")
    // List<PivDetail> findByEstReferenceNo(@Param("estimateNo") String estimateNo);

    // PivDetailRepository.java
    // Correct query to find PIV details by estimate number through application
    // relationship
    @Query(value = "SELECT pd.* FROM PIV_DETAIL pd " +
            "WHERE pd.REFERENCE_NO IN (" +
            "    SELECT a.APPLICATION_NO FROM APPLICATIONS a " +
            "    JOIN ESTIMATE_REFERENCEBS er ON TRIM(a.APPLICATION_NO) = TRIM(er.SESTIMATE_NO) " +
            "    WHERE TRIM(er.WESTIMATE_NO) = :estimateNo" +
            ")", nativeQuery = true)
    List<PivDetail> findByEstimateNo(@Param("estimateNo") String estimateNo);
}