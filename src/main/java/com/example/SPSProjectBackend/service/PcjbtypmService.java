package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.Pcjbtypm;
import com.example.SPSProjectBackend.model.PcjbtypmId;
import com.example.SPSProjectBackend.repository.PcjbtypmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PcjbtypmService {

    private final PcjbtypmRepository pcjbtypmRepository;

    public PcjbtypmService(PcjbtypmRepository pcjbtypmRepository) {
        this.pcjbtypmRepository = pcjbtypmRepository;
    }

    public Pcjbtypm save(Pcjbtypm pcjbtypm) {
        return pcjbtypmRepository.save(pcjbtypm);
    }

    public Optional<Pcjbtypm> findById(PcjbtypmId id) {
        return pcjbtypmRepository.findById(id);
    }

    public List<Pcjbtypm> findAll() {
        return pcjbtypmRepository.findAll();
    }

    public boolean existsById(PcjbtypmId id) {
        return pcjbtypmRepository.existsById(id);
    }

    public void deleteById(PcjbtypmId id) {
        pcjbtypmRepository.deleteById(id);
    }

    public List<Pcjbtypm> findByDeptId(String deptId) {
        return pcjbtypmRepository.findById_DeptId(deptId);
    }
}