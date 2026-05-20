package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.Pcesthtt;
import com.example.SPSProjectBackend.model.PcesthttId;
import com.example.SPSProjectBackend.service.PcesthttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pcesthtt")
public class PcesthttController {

    @Autowired
    private PcesthttService service;

    @GetMapping("/estimate")
    public ResponseEntity<?> getByEstimateNo(@RequestParam String estimateNo) {
        String decodedEstimateNo = URLDecoder.decode(estimateNo, StandardCharsets.UTF_8);
        List<Pcesthtt> list = service.findByEstimateNo(decodedEstimateNo);

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{estimateNo}/{revNo}/{deptId}")
    public ResponseEntity<Pcesthtt> getById(
            @PathVariable String estimateNo,
            @PathVariable Short revNo,
            @PathVariable String deptId) {

        PcesthttId id = new PcesthttId();
        id.setEstimateNo(estimateNo);
        id.setRevNo(revNo);
        id.setDeptId(deptId);

        Optional<Pcesthtt> entity = service.findById(id);
        return entity.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pcesthtt create(@RequestBody Pcesthtt entity) {
        return service.save(entity);
    }

    @PutMapping("/{estimateNo}/{revNo}/{deptId}")
    public ResponseEntity<Pcesthtt> update(
            @PathVariable String estimateNo,
            @PathVariable Short revNo,
            @PathVariable String deptId,
            @RequestBody Pcesthtt entity) {

        PcesthttId id = new PcesthttId();
        id.setEstimateNo(estimateNo);
        id.setRevNo(revNo);
        id.setDeptId(deptId);

        Optional<Pcesthtt> existingEntity = service.findById(id);
        if (!existingEntity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        entity.setId(id);
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{estimateNo}/{revNo}/{deptId}")
    public ResponseEntity<Void> delete(
            @PathVariable String estimateNo,
            @PathVariable Short revNo,
            @PathVariable String deptId) {

        PcesthttId id = new PcesthttId();
        id.setEstimateNo(estimateNo);
        id.setRevNo(revNo);
        id.setDeptId(deptId);

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
