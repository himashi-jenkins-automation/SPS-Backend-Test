package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.CommissionDTO;
import com.example.SPSProjectBackend.service.PcesthmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commission")
public class CommissionController {

    private final PcesthmtService pcesthmtService;

    @Autowired
    public CommissionController(PcesthmtService pcesthmtService) {
        this.pcesthmtService = pcesthmtService;
    }

    // @GetMapping("/details")
    // public ResponseEntity<List<CommissionDTO>> getCommissionDetails() {
    // List<CommissionDTO> commissionDetails =
    // pcesthmtService.getCommissionDetails();
    // return ResponseEntity.ok(commissionDetails);
    // }
    // //this is get new api for get data related to create ordercard
    // @GetMapping("/details/user")
    // public ResponseEntity<List<CommissionDTO>> getCommissionDetailsByUser(
    // @RequestParam String userId) {
    // List<CommissionDTO> commissionDetails =
    // pcesthmtService.getCommissionDetailsByUserId(userId);
    // return ResponseEntity.ok(commissionDetails);
    // }
    //
    //
    // //this is for get editing related details
    // @GetMapping("/details/edit")
    // public ResponseEntity<List<CommissionDTO>> getEditCommissionDetails() {
    // List<CommissionDTO> commissionDetails =
    // pcesthmtService.getEditCommissionDetails();
    // return ResponseEntity.ok(commissionDetails);
    // }
    //
    // //this is for get validate related details
    // @GetMapping("/details/validate")
    // public ResponseEntity<List<CommissionDTO>> getValidateCommissionDetails() {
    // List<CommissionDTO> commissionDetails =
    // pcesthmtService.getValidateCommissionDetails();
    // return ResponseEntity.ok(commissionDetails);
    // }

    // Unified endpoint for getting commission details with dynamic status
    @GetMapping("/details/user/create")
    public ResponseEntity<List<CommissionDTO>> getCommissionDetailsForCreate(
            @RequestParam String userId) {
        // For create operation,OLD pass status=4
        // NOW WE CHANGE TO status=1 because they say they need both physical closed
        // jobs (status=4)
        // soft closed jobs(status=1)
        List<CommissionDTO> commissionDetails = pcesthmtService.getCommissionDetailsByUserIdAndStatus(userId, 1);
        return ResponseEntity.ok(commissionDetails);
    }

    @GetMapping("/details/user/edit")
    public ResponseEntity<List<CommissionDTO>> getCommissionDetailsForEdit(
            @RequestParam String userId) {
        // For edit operation, pass status=10
        List<CommissionDTO> commissionDetails = pcesthmtService.getCommissionDetailsByUserIdAndStatus(userId, 10);
        return ResponseEntity.ok(commissionDetails);
    }

    @GetMapping("/details/user/validate")
    public ResponseEntity<List<CommissionDTO>> getCommissionDetailsForValidate(
            @RequestParam String userId) {
        // For validate operation, pass status=11
        List<CommissionDTO> commissionDetails = pcesthmtService.getCommissionDetailsByUserIdAndStatus(userId, 11);
        return ResponseEntity.ok(commissionDetails);
    }

    @GetMapping("/details/user/validateAreaEE")
    public ResponseEntity<List<CommissionDTO>> getCommissionDetailsForValidateAreaEE(
            @RequestParam String userId) {
        // For validateAreaEE operation, pass status=13
        List<CommissionDTO> commissionDetails = pcesthmtService.getCommissionDetailsByUserIdAndStatus(userId, 13);
        return ResponseEntity.ok(commissionDetails);
    }

    @GetMapping("/details/user/verify")
    public ResponseEntity<List<CommissionDTO>> getCommissionDetailsForVerify(
            @RequestParam String userId) {
        // For verify operation, pass status=12
        List<CommissionDTO> commissionDetails = pcesthmtService.getCommissionDetailsByUserIdAndStatus(userId, 12);
        return ResponseEntity.ok(commissionDetails);
    }

}