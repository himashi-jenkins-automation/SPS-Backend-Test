package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.SpestedyCon;
import com.example.SPSProjectBackend.model.SpestedyConId;
import com.example.SPSProjectBackend.repository.SpestedyConRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpestedyConService {

    private final SpestedyConRepository repository;

    @Autowired
    public SpestedyConService(SpestedyConRepository repository) {
        this.repository = repository;
    }

    public List<SpestedyCon> getAll() {
        return repository.findAll();
    }

    public List<SpestedyCon> getByDeptId(String deptId) {
        return repository.findByIdDeptId(deptId);
    }

    public List<Object[]> getByAllocatedTo(String allocatedTo) {
        return repository.findWestimateNoAndDescriptionByAllocatedTo(allocatedTo);
    }

    // Update allocatedTo only
    public SpestedyCon updateAllocatedTo(SpestedyConId id, String allocatedTo) {
        Optional<SpestedyCon> optional = repository.findById(id);
        if (optional.isPresent()) {
            SpestedyCon record = optional.get();
            record.setAllocatedTo(allocatedTo);
            return repository.save(record);
        } else {
            throw new RuntimeException("Record not found for ID: " + id);
        }
    }
    // public List<Object[]> getByReferenceNo(String allocatedTo, String referenceNo) {
    //     return repository.findByReferenceNo(allocatedTo, referenceNo);
    // }
}
