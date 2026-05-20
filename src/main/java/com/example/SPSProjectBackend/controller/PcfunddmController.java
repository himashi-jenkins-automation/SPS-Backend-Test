package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.Pcfunddm;
import com.example.SPSProjectBackend.model.PcfunddmId;
import com.example.SPSProjectBackend.service.PcfunddmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pcfunddm")
public class PcfunddmController {

    private final PcfunddmService pcfunddmService;

    public PcfunddmController(PcfunddmService pcfunddmService) {
        this.pcfunddmService = pcfunddmService;
    }

    @PostMapping
    public ResponseEntity<Pcfunddm> createPcfunddm(@RequestBody Pcfunddm pcfunddm) {
        Pcfunddm savedPcfunddm = pcfunddmService.save(pcfunddm);
        return ResponseEntity.ok(savedPcfunddm);
    }

    @GetMapping("/{fundSource}/{fundId}/{deptId}")
    public ResponseEntity<Pcfunddm> getPcfunddm(@PathVariable String fundSource, @PathVariable String fundId, @PathVariable String deptId) {
        PcfunddmId id = new PcfunddmId(fundSource, fundId, deptId);
        return pcfunddmService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pcfunddm>> getAllPcfunddm() {
        List<Pcfunddm> pcfunddmList = pcfunddmService.findAll();
        return ResponseEntity.ok(pcfunddmList);
    }

    @GetMapping("/by-dept/{deptId}")
    public ResponseEntity<List<Pcfunddm>> getPcfunddmByDeptId(@PathVariable String deptId) {
        List<Pcfunddm> pcfunddmList = pcfunddmService.findByDeptId(deptId);
        return ResponseEntity.ok(pcfunddmList);
    }

    @GetMapping("/by-dept-and-source/{deptId}/{fundSource}")
    public ResponseEntity<List<Pcfunddm>> getPcfunddmByDeptIdAndFundSource(@PathVariable String deptId, @PathVariable String fundSource) {
        List<Pcfunddm> pcfunddmList = pcfunddmService.findByDeptIdAndFundSource(deptId, fundSource);
        return ResponseEntity.ok(pcfunddmList);
    }

    @PutMapping("/{fundSource}/{fundId}/{deptId}")
    public ResponseEntity<Pcfunddm> updatePcfunddm(@PathVariable String fundSource, @PathVariable String fundId, @PathVariable String deptId,
                                                   @RequestBody Pcfunddm pcfunddm) {
        PcfunddmId id = new PcfunddmId(fundSource, fundId, deptId);
        if (!pcfunddmService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pcfunddm.setId(id);
        Pcfunddm updatedPcfunddm = pcfunddmService.save(pcfunddm);
        return ResponseEntity.ok(updatedPcfunddm);
    }

    @DeleteMapping("/{fundSource}/{fundId}/{deptId}")
    public ResponseEntity<Void> deletePcfunddm(@PathVariable String fundSource, @PathVariable String fundId, @PathVariable String deptId) {
        PcfunddmId id = new PcfunddmId(fundSource, fundId, deptId);
        if (pcfunddmService.existsById(id)) {
            pcfunddmService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}