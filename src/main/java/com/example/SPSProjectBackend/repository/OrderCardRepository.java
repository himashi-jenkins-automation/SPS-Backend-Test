package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.dto.JobTypeCountDTO;
import com.example.SPSProjectBackend.model.OrderCard;
import com.example.SPSProjectBackend.model.OrderCardId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderCardRepository extends JpaRepository<OrderCard, OrderCardId> { // Change to OrderCardId

////     Find by composite key - use the ID class
//    Optional<OrderCard> findByProjectNoAndDeptId(String projectNo, String deptId);

    //    above query has trailing space issue with testdatabase which is dancons16
    @Query("SELECT oc FROM OrderCard oc WHERE TRIM(oc.projectNo) = TRIM(:projectNo) AND TRIM(oc.deptId) = TRIM(:deptId)")
    Optional<OrderCard> findByProjectNoAndDeptId(@Param("projectNo") String projectNo, @Param("deptId") String deptId);


    // Find all order cards for a project
    List<OrderCard> findByProjectNo(String projectNo);


    // Find the maximum order card number for a given project prefix
    @Query("SELECT MAX(oc.orderCardNo) FROM OrderCard oc WHERE oc.orderCardNo LIKE :prefix%")
    String findMaxOrderCardNoByPrefix(@Param("prefix") String prefix);

    // Check if order card number exists
    boolean existsByOrderCardNo(String orderCardNo);

    // Find by order card number
    Optional<OrderCard> findByOrderCardNo(String orderCardNo);

    // Get job types and their counts
    @Query("""
            SELECT new com.example.SPSProjectBackend.dto.JobTypeCountDTO(
                oc.jobType,
                COUNT(oc)
            )
            FROM OrderCard oc
            WHERE oc.jobType IS NOT NULL
            GROUP BY oc.jobType
            ORDER BY COUNT(oc) DESC
            """)
    List<JobTypeCountDTO> countByJobType();

}

