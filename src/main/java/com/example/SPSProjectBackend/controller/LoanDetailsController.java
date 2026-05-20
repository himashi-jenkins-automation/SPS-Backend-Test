package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.LoanDetailsDTO;
import com.example.SPSProjectBackend.service.LoanDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/loan-details")
public class LoanDetailsController {

    @Autowired
    private LoanDetailsService loanDetailsService;

    @GetMapping("/by-estimate")
    public ResponseEntity<?> getLoanDetailsByEstimateNo(@RequestParam String estimateNo) {
        try {
            System.out.println("🔍 Received request for loan details with estimateNo: " + estimateNo);

            if (estimateNo == null || estimateNo.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("error", "Estimate number is required"));
            }

            List<LoanDetailsDTO> loanDetails = loanDetailsService.getLoanDetailsByEstimateNo(estimateNo.trim());

            if (loanDetails.isEmpty()) {
                System.out.println("❌ No loan details found for estimate: " + estimateNo);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message",
                                "No loan details found for estimate number: " + estimateNo));
            }

            System.out.println("✅ Found " + loanDetails.size() + " loan detail record(s)");
            return ResponseEntity.ok(loanDetails);

        } catch (Exception e) {
            System.err.println("💥 Error processing loan details for estimateNo: " + estimateNo);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error",
                            "Internal server error while processing estimate number"));
        }
    }
}