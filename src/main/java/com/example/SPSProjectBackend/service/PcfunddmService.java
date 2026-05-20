package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.Pcfunddm;
import com.example.SPSProjectBackend.model.PcfunddmId;
import com.example.SPSProjectBackend.repository.PcfunddmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PcfunddmService {

    private final PcfunddmRepository pcfunddmRepository;

    public PcfunddmService(PcfunddmRepository pcfunddmRepository) {
        this.pcfunddmRepository = pcfunddmRepository;
    }

    public Pcfunddm save(Pcfunddm pcfunddm) {
        return pcfunddmRepository.save(pcfunddm);
    }

    public Optional<Pcfunddm> findById(PcfunddmId id) {
        return pcfunddmRepository.findById(id);
    }

    public List<Pcfunddm> findAll() {
        return pcfunddmRepository.findAll();
    }

    public boolean existsById(PcfunddmId id) {
        return pcfunddmRepository.existsById(id);
    }

    public void deleteById(PcfunddmId id) {
        pcfunddmRepository.deleteById(id);
    }

    public List<Pcfunddm> findByDeptId(String deptId) {
        return pcfunddmRepository.findById_DeptId(deptId);
    }

    public List<Pcfunddm> findByDeptIdAndFundSource(String deptId, String fundSource) {
        return pcfunddmRepository.findById_DeptIdAndId_FundSource(deptId, fundSource);
    }
}