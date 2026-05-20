package com.example.SPSProjectBackend.repository;

// import com.example.SPSProjectBackend.dto.ApplicationDTO;
import com.example.SPSProjectBackend.model.ApplicationModel;
import com.example.SPSProjectBackend.model.ApplicationModelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel, ApplicationModelId> {
    Optional<ApplicationModel> findById_ApplicationId(String applicationId);

    // This one for application details in commission
    // @Query("SELECT a FROM ApplicationModel a WHERE TRIM(a.deptId) IN :deptIds")
    // List<ApplicationModel> findByDeptIdIn(@Param("deptIds") List<String>
    // deptIds);
    @Query("SELECT a FROM ApplicationModel a WHERE TRIM(a.deptId) IN :deptIds")
    List<ApplicationModel> findByDeptIdIn(@Param("deptIds") List<String> deptIds);

    @Query("SELECT a.applicationNo FROM ApplicationModel a")
    List<String> findAllApplicationNos();

    @Query("SELECT COUNT(a) > 0 FROM ApplicationModel a WHERE a.applicationNo = :applicationNo")
    boolean existsByApplicationNo(String applicationNo);

    @Query(value = """
            SELECT a.APPLICATION_NO, a.DEPT_ID, a.APPLICATION_TYPE, ap.FULL_NAME, a.DESCRIPTION
            FROM SPSNEW.APPLICATIONS a
            JOIN SPSNEW.APPLICANT ap ON TRIM(a.ID_NO) = ap.ID_NO
            JOIN SPSNEW.WIRING_LAND_DETAIL_CON con ON TRIM(a.APPLICATION_ID) = con.APPLICATION_ID
            JOIN SPSNEW.APPLICATION_REFERENCE ref ON TRIM(a.APPLICATION_ID) = ref.APPLICATION_ID
            WHERE TRIM(a.APPLICATION_NO) = :applicationNo
            ORDER BY a.APPLICATION_NO
            """, nativeQuery = true)
    List<Object[]> findApplicationDetailsByApplicationNo(@Param("applicationNo") String applicationNo);

    @Query("SELECT a.status, COUNT(a) FROM ApplicationModel a WHERE a.deptId = :deptId GROUP BY a.status")
    List<Object[]> getStatusCountsByDeptId(@Param("deptId") String deptId);

    // This is for application details in commissioning

    @Query(value = """
            SELECT
                a.APPLICATION_NO as applicationNo,
                a.APPLICATION_ID as applicationId,
                a.APPLICATION_TYPE as applicationType,
                a.DESCRIPTION as description,
                wld.DEMAND as demand,
                wldcon.FUND_SOURCE as fundSource,
                COALESCE(wld.SERVICE_STREET_ADDRESS, '') ||\s
                            CASE\s
                                WHEN wld.SERVICE_SUBURB IS NOT NULL THEN ',' || wld.SERVICE_SUBURB\s
                                ELSE ''\s
                            END ||
                            CASE\s
                                WHEN wld.SERVICE_CITY IS NOT NULL THEN ',' || wld.SERVICE_CITY\s
                                ELSE ''\s
                            END ||
                            CASE\s
                                 WHEN wld.SERVICE_POSTAL_CODE IS NOT NULL THEN ',' || wld.SERVICE_POSTAL_CODE\s
                                 ELSE ''\s
                            END as fullAddress
            FROM APPLICATIONS a
            JOIN ESTIMATE_REFERENCEBS er ON TRIM(a.APPLICATION_NO) = TRIM(er.SESTIMATE_NO)
            LEFT JOIN WIRING_LAND_DETAIL wld ON TRIM(a.APPLICATION_ID) = TRIM(wld.APPLICATION_ID)
            LEFT JOIN WIRING_LAND_DETAIL_CON wldcon ON TRIM(a.APPLICATION_ID) = TRIM(wldcon.APPLICATION_ID)
            WHERE TRIM(er.WESTIMATE_NO) = :estimateNo
            """, nativeQuery = true)
    List<Object[]> findApplicationDetailsByEstimateNo(@Param("estimateNo") String estimateNo);

    // New query to get distinct applicant ID_NOs for a given estimateNo(FOR
    // APPLICANT IN COMMISSIONING)
    @Query(value = """
                SELECT DISTINCT TRIM(a.ID_NO)
                FROM APPLICATIONS a
                JOIN ESTIMATE_REFERENCEBS er ON TRIM(a.APPLICATION_NO) = TRIM(er.SESTIMATE_NO)
                WHERE TRIM(er.WESTIMATE_NO) = :estimateNo
            """, nativeQuery = true)
    List<String> findApplicantIdNosByEstimateNo(@Param("estimateNo") String estimateNo);

}