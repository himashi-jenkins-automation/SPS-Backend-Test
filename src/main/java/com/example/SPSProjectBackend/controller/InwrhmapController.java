package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.Inwrhmap;
import com.example.SPSProjectBackend.model.InwrhmapId;
import com.example.SPSProjectBackend.service.InwrhmapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inwrhmap")
//@CrossOrigin(origins = "http://localhost:3000" + "", allowCredentials = "true")

public class InwrhmapController {
    private final InwrhmapService service;

    public InwrhmapController(InwrhmapService service) {
        this.service = service;
    }

    @GetMapping
    public List<Inwrhmap> getAll() {
        return service.getAll();
    }

    @GetMapping("/{deptId}/{wrhDept}")
    public Optional<Inwrhmap> getById(@PathVariable String deptId, @PathVariable String wrhDept) {
        return service.getById(new InwrhmapId(deptId, wrhDept));
    }

    // Get all warehouses by deptId (returns full Inwrhmap objects)
    @GetMapping("/dept/{deptId}")
    public List<Inwrhmap> getWarehousesByDeptId(@PathVariable String deptId) {
        return service.getWarehousesByDeptId(deptId);
    }

    // New endpoint to get only wrhDept values for a given deptId
    @GetMapping("/dept/{deptId}/wrhDept")
    public List<String> getWrhDeptByDeptId(@PathVariable String deptId) {
        List<Inwrhmap> warehouses = service.getWarehousesByDeptId(deptId);
        return warehouses.stream()
                .map(Inwrhmap::getWrhDept)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Inwrhmap save(@RequestBody Inwrhmap entity) {
        return service.save(entity);
    }

    @DeleteMapping("/{deptId}/{wrhDept}")
    public void delete(@PathVariable String deptId, @PathVariable String wrhDept) {
        service.delete(new InwrhmapId(deptId, wrhDept));
    }

}
