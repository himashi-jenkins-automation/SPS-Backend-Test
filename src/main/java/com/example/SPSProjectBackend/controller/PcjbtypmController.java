package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.Pcjbtypm;
import com.example.SPSProjectBackend.model.PcjbtypmId;
import com.example.SPSProjectBackend.service.PcjbtypmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pcjbtypm")
public class PcjbtypmController {

    private final PcjbtypmService pcjbtypmService;

    public PcjbtypmController(PcjbtypmService pcjbtypmService) {
        this.pcjbtypmService = pcjbtypmService;
    }

    @PostMapping
    public ResponseEntity<Pcjbtypm> createPcjbtypm(@RequestBody Pcjbtypm pcjbtypm) {
        Pcjbtypm savedPcjbtypm = pcjbtypmService.save(pcjbtypm);
        return ResponseEntity.ok(savedPcjbtypm);
    }

    @GetMapping("/{catCd}/{deptId}")
    public ResponseEntity<Pcjbtypm> getPcjbtypm(@PathVariable String catCd, @PathVariable String deptId) {
        PcjbtypmId id = new PcjbtypmId();
        id.setCatCd(catCd);
        id.setDeptId(deptId);
        return pcjbtypmService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pcjbtypm>> getAllPcjbtypm() {
        List<Pcjbtypm> pcjbtypmList = pcjbtypmService.findAll();
        return ResponseEntity.ok(pcjbtypmList);
    }

    @GetMapping("/by-dept/{deptId}")
    public ResponseEntity<List<Pcjbtypm>> getPcjbtypmByDeptId(@PathVariable String deptId) {
        List<Pcjbtypm> pcjbtypmList = pcjbtypmService.findByDeptId(deptId);
        return ResponseEntity.ok(pcjbtypmList);
    }

    @PutMapping("/{catCd}/{deptId}")
    public ResponseEntity<Pcjbtypm> updatePcjbtypm(@PathVariable String catCd, @PathVariable String deptId,
                                                   @RequestBody Pcjbtypm pcjbtypm) {
        PcjbtypmId id = new PcjbtypmId();
        id.setCatCd(catCd);
        id.setDeptId(deptId);
        if (!pcjbtypmService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pcjbtypm.setId(id);
        Pcjbtypm updatedPcjbtypm = pcjbtypmService.save(pcjbtypm);
        return ResponseEntity.ok(updatedPcjbtypm);
    }

    @DeleteMapping("/{catCd}/{deptId}")
    public ResponseEntity<Void> deletePcjbtypm(@PathVariable String catCd, @PathVariable String deptId) {
        PcjbtypmId id = new PcjbtypmId();
        id.setCatCd(catCd);
        id.setDeptId(deptId);
        if (pcjbtypmService.existsById(id)) {
            pcjbtypmService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}