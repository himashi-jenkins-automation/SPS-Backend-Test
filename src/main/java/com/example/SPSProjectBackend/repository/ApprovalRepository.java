//package com.example.SPSProjectBackend.repository;
//
//import com.example.SPSProjectBackend.model.Approval;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import java.util.List;
//
//@Repository
//public interface ApprovalRepository extends JpaRepository<Approval, String> {
//    @Query("SELECT a FROM Approval a WHERE a.referenceNo = :estimateNo") // Changed parameter name
//    List<Approval> findByEstimateNo(@Param("estimateNo") String estimateNo);
//}


//new code
package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, String> {

    /**
     * Find approvals by estimate/reference number
     */
    @Query("SELECT a FROM Approval a WHERE a.referenceNo = :referenceNo")
    List<Approval> findByReferenceNo(@Param("referenceNo") String referenceNo);

    /**
     * Find the last approval ID for a given reference number pattern
     * Used for generating the next sequential approval ID
     */
    @Query("SELECT a.approvalId FROM Approval a WHERE a.approvalId LIKE :pattern ORDER BY a.approvalId DESC")
    List<String> findLastApprovalIdByPattern(@Param("pattern") String pattern);

    /**
     * Find by approval ID
     */
    Optional<Approval> findByApprovalId(String approvalId);

    /**
     * Find approvals by reference number and approval type
     */
    @Query("SELECT a FROM Approval a WHERE a.referenceNo = :referenceNo AND a.approvalType = :approvalType")
    List<Approval> findByReferenceNoAndApprovalType(@Param("referenceNo") String referenceNo,
                                                    @Param("approvalType") String approvalType);

    /**
     * Find approvals by reference number, from status, and to status
     */
    @Query("SELECT a FROM Approval a WHERE a.referenceNo = :referenceNo AND a.fromStatus = :fromStatus AND a.toStatus = :toStatus")
    List<Approval> findByReferenceNoAndStatusChange(@Param("referenceNo") String referenceNo,
                                                    @Param("fromStatus") String fromStatus,
                                                    @Param("toStatus") String toStatus);
}