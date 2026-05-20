// package com.example.SPSProjectBackend.repository;

// import com.example.SPSProjectBackend.model.SpestedyCon;
// import com.example.SPSProjectBackend.model.SpestedyConId;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface SpestedyConRepository extends JpaRepository<SpestedyCon, SpestedyConId> {
//     List<SpestedyCon> findByIdDeptId(String deptId); // filter by deptId

    
// }

package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.dto.SpestedyConDTO;
import com.example.SPSProjectBackend.model.SpestedyCon;
import com.example.SPSProjectBackend.model.SpestedyConId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpestedyConRepository extends JpaRepository<SpestedyCon, SpestedyConId> {

    List<SpestedyCon> findByIdDeptId(String deptId); // filter by deptId
    
    @Query("""
    SELECT s.westimateNo, s.description
    FROM SpestedyCon s
    WHERE TRIM(s.allocatedTo) = :allocatedTo
    """)
    List<Object[]> findWestimateNoAndDescriptionByAllocatedTo(
            @Param("allocatedTo") String allocatedTo);


//     @Query("""
//     SELECT s.westimateNo FROM SpestedyCon s
//     WHERE TRIM(s.allocatedTo) = :allocatedTo AND TRIM(s.referenceNo) = :referenceNo
//     """)
//     List<Object[]> findByReferenceNo(
//             @Param("allocatedTo") String allocatedTo,
//             @Param("referenceNo") String referenceNo);
}


    

