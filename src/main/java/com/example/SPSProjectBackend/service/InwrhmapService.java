package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.Inwrhmap;
import com.example.SPSProjectBackend.model.InwrhmapId;
import com.example.SPSProjectBackend.repository.InwrhmapRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InwrhmapService {
    private final InwrhmapRepository repository;

    public InwrhmapService(InwrhmapRepository repository) {
        this.repository = repository;
    }

    public List<Inwrhmap> getAll() {
        return repository.findAll();
    }

    public Optional<Inwrhmap> getById(InwrhmapId id) {
        return repository.findById(id);
    }

    public List<Inwrhmap> getWarehousesByDeptId(String deptId) {
        return repository.findByDeptId(deptId);
    }

    public Inwrhmap save(Inwrhmap entity) {
        return repository.save(entity);
    }

    public void delete(InwrhmapId id) {
        repository.deleteById(id);
    }

}
