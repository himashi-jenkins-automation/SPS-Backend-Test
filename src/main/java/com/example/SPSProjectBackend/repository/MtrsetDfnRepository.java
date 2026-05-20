package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.dto.MtrTypeDto;
import com.example.SPSProjectBackend.model.MtrsetDfn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MtrsetDfnRepository extends JpaRepository<MtrsetDfn, Long> {

    @Query("SELECT new com.example.SPSProjectBackend.dto.MtrTypeDto(m.mtrType, m.mtrOrder) " +
            "FROM MtrsetDfn m " +
            "WHERE m.cusCat = :cusCat AND m.mtrsetType = :mtrsetType " +
            "ORDER BY m.mtrOrder ASC")
    List<MtrTypeDto> findByCusCatAndMtrsetTypeOrderByMtrOrder(
            @Param("cusCat") String cusCat,
            @Param("mtrsetType") Short mtrsetType);

    // Alternative method if you want the full entities
    List<MtrsetDfn> findByCusCatAndMtrsetTypeOrderByMtrOrderAsc(String cusCat, Short mtrsetType);
}