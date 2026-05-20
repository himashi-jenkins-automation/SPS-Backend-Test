//package com.example.SPSProjectBackend.controller;
//
//import com.example.SPSProjectBackend.dto.ApprovalHistoryDTO;
//import com.example.SPSProjectBackend.service.ApprovalService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/approval-history")
//public class ApprovalHistoryController {
//
//    @Autowired
//    private ApprovalService approvalService;
//
//    @GetMapping("/{estimateNo}")
//    public ResponseEntity<List<ApprovalHistoryDTO>> getApprovalHistory(
//            @PathVariable String estimateNo) {
//        List<ApprovalHistoryDTO> history = approvalService.getApprovalHistoryByEstimateNo(estimateNo);
//        return ResponseEntity.ok(history);
//    }
//}

//new code
package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.ApprovalHistoryDTO;
import com.example.SPSProjectBackend.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for fetching approval history (Read operations)
 * This remains separate for clear separation of concerns
 */
@RestController
@RequestMapping("/api/approval-history")
public class ApprovalHistoryController {

    @Autowired
    private ApprovalService approvalService;

    /**
     * Get approval history by estimate/reference number
     * GET: /api/approval-history/{estimateNo}
     */
    @GetMapping("/{estimateNo}")
    public ResponseEntity<List<ApprovalHistoryDTO>> getApprovalHistory(
            @PathVariable String estimateNo) {
        List<ApprovalHistoryDTO> history = approvalService.getApprovalHistoryByEstimateNo(estimateNo);

        if (history.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(history);
    }

    /**
     * Get approval history with optional filtering
     * GET: /api/approval-history/filter?referenceNo=...&fromStatus=...&toStatus=...
     */
    @GetMapping("/filter")
    public ResponseEntity<List<ApprovalHistoryDTO>> getFilteredApprovalHistory(
            @RequestParam(required = false) String referenceNo,
            @RequestParam(required = false) String fromStatus,
            @RequestParam(required = false) String toStatus) {

        // For now, returning all for the reference number
        // You can enhance this method to include more filters
        if (referenceNo == null || referenceNo.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ApprovalHistoryDTO> history = approvalService.getApprovalHistoryByEstimateNo(referenceNo);

        if (history.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(history);
    }
}