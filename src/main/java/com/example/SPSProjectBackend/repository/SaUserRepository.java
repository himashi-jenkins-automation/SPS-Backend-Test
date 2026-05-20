//package com.example.SPSProjectBackend.repository;
//
//import com.example.SPSProjectBackend.model.SaUser;
//import org.springframework.data.jpa.repository.Query;
////import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.query.Param;
//
//import java.util.Optional;
//
////@Repository
////public interface SaUserRepository extends JpaRepository<SaUser, String> {
////    Optional<SaUser> findByUserIdAndPassword(String userId, String password);
////}
//
//public interface SaUserRepository extends CrudRepository<SaUser, String> {
//        //    @Query("SELECT u FROM SaUser u WHERE TRIM(u.userId) = :userId AND u.password = :password")
////    Optional<SaUser> findByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);
//        @Query("SELECT u FROM SaUser u WHERE TRIM(u.userId) = :userId")
//        Optional<SaUser> findByUserId(@Param("userId") String userId);
//}

////NEW UPDATED CODES
//package com.example.SPSProjectBackend.repository;
//
//import com.example.SPSProjectBackend.model.SaUser;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository  // CHANGED: Added @Repository annotation
//public interface SaUserRepository extends CrudRepository<SaUser, String> {
//
//        // CHANGED: Added UPPER function in query to ensure case-insensitive search
//        @Query("SELECT u FROM SaUser u WHERE UPPER(TRIM(u.userId)) = UPPER(:userId)")
//        Optional<SaUser> findByUserId(@Param("userId") String userId);
//}

//NEW CODES 2
package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.SaUser;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaUserRepository extends CrudRepository<SaUser, String> {
        @Query("SELECT u FROM SaUser u WHERE UPPER(TRIM(u.userId)) = UPPER(:userId)")
        Optional<SaUser> findByUserId(@Param("userId") String userId);

        //this is for getting userDate using EPF number
        @Query("SELECT u FROM SaUser u WHERE UPPER(TRIM(u.epfno)) = UPPER(:epfno)")
        Optional<SaUser> findByEpfNo(@Param("epfno") String epfno);

        @Query(value = """
        SELECT USER_ID 
        FROM SAUSERM
        WHERE TRIM(RPT_USER) LIKE CONCAT(:prefix, '%')
        AND UPPER(TRIM(USER_LEVEL)) IN ('EE','ES')
        """, nativeQuery = true)
        List<String> findUserIdsByRptUserPrefix(@Param("prefix") String prefix);
}
