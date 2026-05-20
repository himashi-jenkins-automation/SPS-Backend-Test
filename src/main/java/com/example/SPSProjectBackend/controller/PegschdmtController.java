package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.Pegschdmt;
import com.example.SPSProjectBackend.model.PegschdmtId;
import com.example.SPSProjectBackend.service.PegschdmtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pegschdmt")
@CrossOrigin
public class PegschdmtController {

    private final PegschdmtService service;

    public PegschdmtController(PegschdmtService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pegschdmt> getAll() {
        return service.getAll();
    }

    @GetMapping("/{estimateNo}/{nodeId}")
    public Optional<Pegschdmt> getById(@PathVariable String estimateNo, @PathVariable String nodeId) {
        return service.getById(new PegschdmtId(estimateNo, nodeId));
    }

    @PostMapping
    public Pegschdmt create(@RequestBody Pegschdmt data) {
        // Validate required fields (ESTIMATENO and NODEID cannot be null)
        if (data.getEstimateNo() == null || data.getNodeId() == null) {
            throw new IllegalArgumentException("Estimate No and Node ID are required.");
        }
        return service.save(data);
    }

    @DeleteMapping("/{estimateNo}/{nodeId}")
    public void delete(@PathVariable String estimateNo, @PathVariable String nodeId) {
        service.delete(new PegschdmtId(estimateNo, nodeId));
    }
}