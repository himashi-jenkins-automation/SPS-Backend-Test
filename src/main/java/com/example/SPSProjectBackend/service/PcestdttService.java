package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.Pcestdtt;
import com.example.SPSProjectBackend.model.PcestdttId;
import com.example.SPSProjectBackend.repository.PcestdttRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PcestdttService {

    @Autowired
    private PcestdttRepository repository;

    // ✅ Filter rows by ESTIMATE_NO
    public List<Pcestdtt> getByEstimateNo(String estimateNo) {
        return repository.findByEstimateNo(estimateNo);
    }

    public Optional<Pcestdtt> getById(PcestdttId id) {
        return repository.findById(id);
    }

    public Pcestdtt save(Pcestdtt pcestdtt) {
        return repository.save(pcestdtt);
    }

    public void deleteById(PcestdttId id) {
        repository.deleteById(id);
    }
}
