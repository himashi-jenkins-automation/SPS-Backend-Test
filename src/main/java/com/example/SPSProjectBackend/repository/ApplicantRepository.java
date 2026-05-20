package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import com.example.SPSProjectBackend.model.Applicant;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, String> {
    Optional<Applicant> findByIdNo(String idNo);

    // List<Applicant> findByDeptId(String deptId);

    // this is for testing purpose of commission applicant

    @Query("SELECT a FROM Applicant a WHERE a.deptId = :deptId")
    List<Applicant> findByDeptId(@Param("deptId") String deptId);

    // ApplicantRepository.java
    @Query("SELECT a FROM Applicant a WHERE a.deptId IN :deptIds")
    List<Applicant> findByDeptIdIn(@Param("deptIds") List<String> deptIds);

    // NEW: Complete method that gets applicant details directly by estimateNo
    @Query(value = """
                SELECT app.*
                FROM APPLICANT app
                WHERE TRIM(app.ID_NO) IN (
                    SELECT DISTINCT TRIM(a.ID_NO)
                    FROM APPLICATIONS a
                    WHERE TRIM(a.APPLICATION_NO) IN (
                        SELECT TRIM(er.SESTIMATE_NO)
                        FROM ESTIMATE_REFERENCEBS er
                        WHERE TRIM(er.WESTIMATE_NO) = :estimateNo
                    )
                )
            """, nativeQuery = true)
    List<Applicant> findApplicantsByEstimateNo(@Param("estimateNo") String estimateNo);

}
// List<Applicant> findByDeptId(String deptId);
