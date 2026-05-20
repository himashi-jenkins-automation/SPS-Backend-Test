package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.dto.CommissionDTO;
import com.example.SPSProjectBackend.model.Pcesthmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcesthmtRepository extends JpaRepository<Pcesthmt, String> {

        @Query("SELECT p.id.estimateNo FROM Pcesthmt p")
        List<String> findAllEstimateNumbers();

        @Query(value = "SELECT TO_CHAR(ETIMATE_DT, 'YYYY-MM-DD') AS ETIMATE_DT, " +
                        "TO_CHAR(PRJ_ASS_DT, 'YYYY-MM-DD') AS PRJ_ASS_DT " +
                        "FROM SPSNEW.PCESTHMT " +
                        "WHERE ESTIMATE_NO = :estimateNo", nativeQuery = true)
        Object[] findEstimateAndProjectDates(@Param("estimateNo") String estimateNo);

        @Query(value = "SELECT " +
                        "p.PROJECT_NO AS projectNo, " +
                        "p.ESTIMATE_NO AS estimateNo, " +
                        "p.STD_COST AS totalCost, " +
                        "p.DEPT_ID AS deptId, " +
                        "p.DESCR AS description, " +
                        "p.STATUS AS status, " +
                        "s.ORDER_CARD_NO AS orderCardNo " +
                        "FROM PCESTHMT p " +
                        "LEFT JOIN SPODRCRD s ON p.PROJECT_NO = s.PROJECT_NO AND p.DEPT_ID = s.DEPT_ID " +
                        "WHERE p.STATUS = :status " +
                        "AND p.DEPT_ID IN (" +
                        "    SELECT s2.DEPT_ID " +
                        "    FROM SAUSRDPM s2 " +
                        "    WHERE RTRIM(s2.USER_ID) = :userId" +
                        ") " +
                        "ORDER BY p.ESTIMATE_NO, p.DEPT_ID", nativeQuery = true)
        List<CommissionDTO> findCommissionDetailsByUserIdAndStatus(
                        @Param("userId") String userId,
                        @Param("status") Integer status);

        @Query("SELECT DISTINCT CAST(p.id.deptId AS string) FROM Pcesthmt p WHERE p.id.estimateNo = :estimateNo")
        List<String> findDeptIdsByEstimateNo(@Param("estimateNo") String estimateNo);

        @Modifying
        @Query("UPDATE Pcesthmt p SET p.status = :status WHERE p.id.estimateNo = :estimateNo AND p.id.deptId = :deptId")
        void updateStatusByEstimateNoAndDeptId(@Param("estimateNo") String estimateNo,
                        @Param("deptId") String deptId,
                        @Param("status") Short status);
}