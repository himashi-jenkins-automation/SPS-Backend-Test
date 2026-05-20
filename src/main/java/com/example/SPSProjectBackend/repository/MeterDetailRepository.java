package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.MeterDetail;
import com.example.SPSProjectBackend.model.MeterDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeterDetailRepository extends JpaRepository<MeterDetail, MeterDetailId> {

    List<MeterDetail> findByOrderCardNo(String orderCardNo);

    Optional<MeterDetail> findByOrderCardNoAndMtrType(String orderCardNo, String mtrType);

    @Query("SELECT md FROM MeterDetail md WHERE md.orderCardNo = :orderCardNo ORDER BY md.mtrOrder")
    List<MeterDetail> findByOrderCardNoOrderByMtrOrder(@Param("orderCardNo") String orderCardNo);

    boolean existsByOrderCardNoAndMtrType(String orderCardNo, String mtrType);

    void deleteByOrderCardNo(String orderCardNo);

    long countByOrderCardNo(String orderCardNo);
}