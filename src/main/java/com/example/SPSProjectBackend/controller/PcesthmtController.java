package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.service.EstimateResponse;
import com.example.SPSProjectBackend.service.PcesthmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pcesthmt")
public class PcesthmtController {

    @Autowired
    private PcesthmtService pcesthmtService;

    @GetMapping("/estimateNumbers")
    public ResponseEntity<List<String>> getAllEstimateNumbers() {
        List<String> estimateNumbers = pcesthmtService.getAllEstimateNumbers();
        return ResponseEntity.ok(estimateNumbers);
    }

    @GetMapping("/{estimateNo}")
    public ResponseEntity<EstimateResponse> checkEstimate(@PathVariable String estimateNo) {
        EstimateResponse response = pcesthmtService.checkEstimateAndGetDate(estimateNo);
        return ResponseEntity.ok(response);
    }

    // NEW CODE - Added PUT mapping for updating status
    // @PutMapping("/update-status")
    // public ResponseEntity<String> updateStatus(
    // @RequestParam String estimateNo,
    // @RequestParam String deptId) {
    // try {
    // pcesthmtService.updateStatusTo10(estimateNo, deptId);
    // return ResponseEntity.ok("Status updated successfully to 10 for estimate: " +
    // estimateNo + " and department: " + deptId);
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body("Error updating status: " +
    // e.getMessage());
    // }
    // }
    //
    // //this is use for update status to 11 relevent to forward order card function
    // @PutMapping("/update-status-to-11")
    // public ResponseEntity<String> updateStatusTo11(
    // @RequestParam String estimateNo,
    // @RequestParam String deptId) {
    // try {
    // pcesthmtService.updateStatusTo11(estimateNo, deptId);
    // return ResponseEntity.ok("Status updated successfully to 11 for estimate: " +
    // estimateNo + " and department: " + deptId);
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body("Error updating status: " +
    // e.getMessage());
    // }
    // }

    // @PutMapping("/update-status-to-10")
    // public ResponseEntity<String> updateStatusTo10(
    // @RequestParam String estimateNo,
    // @RequestParam String deptId) {
    // return updateStatus(estimateNo, deptId, (short) 10);
    // }
    //
    // @PutMapping("/update-status-to-11")
    // public ResponseEntity<String> updateStatusTo11(
    // @RequestParam String estimateNo,
    // @RequestParam String deptId) {
    // return updateStatus(estimateNo, deptId, (short) 11);
    // }

    // For status 10
    @PutMapping("/update-status-to-10")
    public ResponseEntity<String> updateStatus(
            @RequestParam String estimateNo,
            @RequestParam String deptId) {
        try {
            pcesthmtService.updateStatus(estimateNo, deptId, (short) 10);
            return ResponseEntity
                    .ok("Status updated successfully to 10 for estimate: " + estimateNo + " and department: " + deptId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating status: " + e.getMessage());
        }
    }

    // For status 11
    @PutMapping("/update-status-to-11")
    public ResponseEntity<String> updateStatusTo11(
            @RequestParam String estimateNo,
            @RequestParam String deptId) {
        try {
            pcesthmtService.updateStatus(estimateNo, deptId, (short) 11);
            return ResponseEntity
                    .ok("Status updated successfully to 11 for estimate: " + estimateNo + " and department: " + deptId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating status: " + e.getMessage());
        }
    }

    // For status 12
    @PutMapping("/update-status-to-12")
    public ResponseEntity<String> updateStatusTo12(
            @RequestParam String estimateNo,
            @RequestParam String deptId) {
        try {
            pcesthmtService.updateStatus(estimateNo, deptId, (short) 12);
            return ResponseEntity
                    .ok("Status updated successfully to 11 for estimate: " + estimateNo + " and department: " + deptId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating status: " + e.getMessage());
        }
    }

    // For status 13
    @PutMapping("/update-status-to-13")
    public ResponseEntity<String> updateStatusTo13(
            @RequestParam String estimateNo,
            @RequestParam String deptId) {
        try {
            pcesthmtService.updateStatus(estimateNo, deptId, (short) 13);
            return ResponseEntity
                    .ok("Status updated successfully to 13 for estimate: " + estimateNo + " and department: " + deptId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating status: " + e.getMessage());
        }
    }

    // For status 14
    @PutMapping("/update-status-to-14")
    public ResponseEntity<String> updateStatusTo14(
            @RequestParam String estimateNo,
            @RequestParam String deptId) {
        try {
            pcesthmtService.updateStatus(estimateNo, deptId, (short) 14);
            return ResponseEntity
                    .ok("Status updated successfully to 14 for estimate: " + estimateNo + " and department: " + deptId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating status: " + e.getMessage());
        }
    }

}
