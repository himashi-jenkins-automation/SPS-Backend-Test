package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.SpPeggingDmt;
import com.example.SPSProjectBackend.model.SpPeggingDmtId;
import com.example.SPSProjectBackend.repository.SpPeggingDmtRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpPeggingDmtService {

    private final SpPeggingDmtRepository repository;

    public SpPeggingDmtService(SpPeggingDmtRepository repository) {
        this.repository = repository;
    }

    public List<SpPeggingDmt> findAll() {
        return repository.findAll();
    }

    public Optional<SpPeggingDmt> findById(SpPeggingDmtId id) {
        return repository.findById(id);
    }

    public List<SpPeggingDmt> findByLineSectionTypeId(String lineSectionTypeId) {
        return repository.findByIdLineSectionTypeId(lineSectionTypeId);
    }

    public SpPeggingDmt save(SpPeggingDmt spPeggingDmt) {
        return repository.save(spPeggingDmt);
    }

    public void deleteById(SpPeggingDmtId id) {
        repository.deleteById(id);
    }
}
