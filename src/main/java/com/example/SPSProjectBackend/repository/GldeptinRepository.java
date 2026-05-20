package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.dto.AreaCodeDTO;
import com.example.SPSProjectBackend.model.Gldeptin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GldeptinRepository extends JpaRepository<Gldeptin, String> {

    // @Query(value = """
    // SELECT g.AREA_CODE
    // FROM GLDEPTIN g
    // WHERE g.DEPT_ID = (
    // SELECT w.AREA_CODE
    // FROM WIRING_LAND_DETAIL_CON w
    // WHERE w.APPLICATION_ID = TRIM(:applicationId)
    // )
    // AND g.DEPT_TYPE = 'AREA'
    // AND ROWNUM = 1
    // """, nativeQuery = true)

    // @Query(value = """
    // SELECT g.AREA_CODE
    // FROM GLDEPTIN g
    // WHERE g.DEPT_ID = (
    // SELECT w.AREA_CODE
    // FROM WIRING_LAND_DETAIL_CON w
    // WHERE w.APPLICATION_ID = (
    // SELECT a.APPLICATION_ID
    // FROM APPLICATIONS a
    // WHERE TRIM(a.APPLICATION_NO) = TRIM(:applicationNo)
    // )
    // )
    // AND g.DEPT_TYPE = 'AREA'
    // AND ROWNUM = 1
    // """, nativeQuery = true)

    @Query(value = """
                SELECT g.AREA_CODE as areaCode, g.DEPT_CODE as deptCode
                FROM GLDEPTIN g
                WHERE g.DEPT_ID = (
                    SELECT w.AREA_CODE
                    FROM WIRING_LAND_DETAIL_CON w
                    WHERE w.APPLICATION_ID = TRIM(:applicationId)
                )
                AND g.DEPT_TYPE = 'AREA'
                AND ROWNUM = 1
            """, nativeQuery = true)
    AreaCodeDTO findAreaCodeByApplicationId(@Param("applicationId") String applicationId);

    // Trim deptId and substring rptUser to handle CHAR padding
    @Query("SELECT g.deptId FROM Gldeptin g WHERE TRIM(g.deptId) = TRIM(SUBSTRING(:rptUser, 1, 6))")
    String findDeptIdByRptUser(@Param("rptUser") String rptUser);

    // Add this method to GldeptinRepository.java
    @Query("SELECT g.deptType FROM Gldeptin g WHERE TRIM(g.deptId) = TRIM(:deptId)")
    String findDeptTypeByDeptId(@Param("deptId") String deptId);

}

// @Repository
// public interface GldeptinRepository extends JpaRepository<Gldeptin, String> {
//
// @Query(value = """
// SELECT g.AREA_CODE, g.DEPT_CODE
// FROM GLDEPTIN g
// WHERE g.DEPT_ID = (
// SELECT w.AREA_CODE
// FROM WIRING_LAND_DETAIL_CON w
// WHERE w.APPLICATION_ID = TRIM(:applicationId)
// )
// AND g.DEPT_TYPE = 'AREA'
// AND ROWNUM = 1
// """, nativeQuery = true)
// Object[] findAreaCodeByApplicationId(@Param("applicationId") String
// applicationId);
// }