package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Pcfundhm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcfundhmRepository extends JpaRepository<Pcfundhm, String> {

    @Query("SELECT p.fundSource FROM Pcfundhm p")
    List<String> findAllFundSources();
}
