package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.Pcestdtt;
import com.example.SPSProjectBackend.model.PcestdttId;
import com.example.SPSProjectBackend.service.PcestdttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pcestdtt")
public class PcestdttController {

    @Autowired
    private PcestdttService service;

    // ✅ Filter by estimateNo using query parameter (safe with slashes)
    @GetMapping("/estimate")
    public ResponseEntity<List<Pcestdtt>> getByEstimateNo(@RequestParam String estimateNo) {
        List<Pcestdtt> result = service.getByEstimateNo(estimateNo);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    // ✅ Get a single record by composite key
    @GetMapping("/{estimateNo}/{revNo}/{deptId}/{resCd}")
    public ResponseEntity<Pcestdtt> getById(
            @PathVariable String estimateNo,
            @PathVariable BigDecimal revNo,
            @PathVariable String deptId,
            @PathVariable String resCd) {
        PcestdttId id = new PcestdttId(estimateNo, revNo, deptId, resCd);
        Optional<Pcestdtt> data = service.getById(id);
        return data.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Create record
    @PostMapping
    public ResponseEntity<Pcestdtt> create(@RequestBody Pcestdtt pcestdtt) {
        return ResponseEntity.ok(service.save(pcestdtt));
    }

    // ✅ Delete record by composite key
    @DeleteMapping("/{estimateNo}/{revNo}/{deptId}/{resCd}")
    public ResponseEntity<?> delete(
            @PathVariable String estimateNo,
            @PathVariable BigDecimal revNo,
            @PathVariable String deptId,
            @PathVariable String resCd) {
        PcestdttId id = new PcestdttId(estimateNo, revNo, deptId, resCd);
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // ✅ Update record by composite key
    @PutMapping("/{estimateNo}/{revNo}/{deptId}/{resCd}")
    public ResponseEntity<Pcestdtt> update(
            @PathVariable String estimateNo,
            @PathVariable BigDecimal revNo,
            @PathVariable String deptId,
            @PathVariable String resCd,
            @RequestBody Pcestdtt updatedData) {
        PcestdttId id = new PcestdttId(estimateNo, revNo, deptId, resCd);
        Optional<Pcestdtt> existingOptional = service.getById(id);
        if (existingOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pcestdtt existing = existingOptional.get();
        if (updatedData.getResType() != null)
            existing.setResType(updatedData.getResType());
        if (updatedData.getUom() != null)
            existing.setUom(updatedData.getUom());
        if (updatedData.getUnitPrice() != null)
            existing.setUnitPrice(updatedData.getUnitPrice());
        if (updatedData.getEstimateQty() != null)
            existing.setEstimateQty(updatedData.getEstimateQty());
        if (updatedData.getEstimateCost() != null)
            existing.setEstimateCost(updatedData.getEstimateCost());
        if (updatedData.getReturnedQty() != null)
            existing.setReturnedQty(updatedData.getReturnedQty());
        if (updatedData.getReturnedCost() != null)
            existing.setReturnedCost(updatedData.getReturnedCost());
        if (updatedData.getApprovedQty() != null)
            existing.setApprovedQty(updatedData.getApprovedQty());
        if (updatedData.getDamageQty() != null)
            existing.setDamageQty(updatedData.getDamageQty());

        Pcestdtt saved = service.save(existing);
        return ResponseEntity.ok(saved);
    }

}
