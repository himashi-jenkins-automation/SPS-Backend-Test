package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.Pcesthtt;
import com.example.SPSProjectBackend.model.PcesthttId;
import com.example.SPSProjectBackend.repository.PcesthttRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PcesthttService {

    @Autowired
    private PcesthttRepository repository;

    public Optional<Pcesthtt> findById(PcesthttId id) {
        return repository.findById(id);
    }

    public List<Pcesthtt> findByEstimateNo(String estimateNo) {
        return repository.findByEstimateNo(estimateNo);
    }

    public Pcesthtt save(Pcesthtt entity) {
        return repository.save(entity);
    }

    public void deleteById(PcesthttId id) {
        repository.deleteById(id);
    }
}
