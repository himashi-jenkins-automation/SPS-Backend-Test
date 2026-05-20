package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Spstdesthmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanDetailsRepository extends JpaRepository<Spstdesthmt, String> {

    @Query(value = """
        SELECT 
            a.IS_LOAN_APP as isLoanApp,
            s.LOANAMOUNT as loanAmount,
            a.APPLICATION_NO as applicationNo
        FROM APPLICATIONS a
        INNER JOIN SPSTDESTHMT s ON TRIM(a.APPLICATION_NO) = TRIM(s.APP_NO)
        WHERE TRIM(a.APPLICATION_NO) IN (
            SELECT TRIM(er.SESTIMATE_NO) 
            FROM ESTIMATE_REFERENCEBS er 
            WHERE TRIM(er.WESTIMATE_NO) = :estimateNo
        )
    """, nativeQuery = true)
    List<LoanDetailsProjection> findLoanDetailsByEstimateNo(@Param("estimateNo") String estimateNo);

    // Projection interface
    interface LoanDetailsProjection {
        String getIsLoanApp();
        Float getLoanAmount(); // Changed from Double to Float to match your entity
        String getApplicationNo();
    }
}