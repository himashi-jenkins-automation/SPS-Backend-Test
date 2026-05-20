package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.ApprovalRequestDTO;
import com.example.SPSProjectBackend.dto.ApprovalResponseDTO;
import com.example.SPSProjectBackend.model.Approval;
import com.example.SPSProjectBackend.service.ApprovalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing approval log entries (Create operations)
 */
@RestController
@RequestMapping("/api/approval-log")
public class ApprovalLogController {

    @Autowired
    private ApprovalService approvalService;

    /**
     * Create a new approval log entry
     * POST: /api/approval-log/create
     *
     * @param requestDTO Contains all required fields for approval log
     * @return Created approval log details
     */
    @PostMapping("/create")
    public ResponseEntity<ApprovalResponseDTO> createApprovalLog(@Valid @RequestBody ApprovalRequestDTO requestDTO) {
        try {
            // Validate required fields
            if (requestDTO.getReferenceNo() == null || requestDTO.getReferenceNo().isEmpty() ||
                    requestDTO.getDeptId() == null || requestDTO.getDeptId().isEmpty() ||
                    requestDTO.getApprovalType() == null || requestDTO.getApprovalType().isEmpty() ||
                    requestDTO.getApprovedLevel() == null || requestDTO.getApprovedLevel().isEmpty() ||
                    requestDTO.getFromStatus() == null || requestDTO.getFromStatus().isEmpty() ||
                    requestDTO.getToStatus() == null || requestDTO.getToStatus().isEmpty() ||
                    requestDTO.getApprovedBy() == null || requestDTO.getApprovedBy().isEmpty()) {

                ApprovalResponseDTO errorResponse = new ApprovalResponseDTO();
                errorResponse.setMessage("Missing required fields");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            // Create the approval log
            ApprovalResponseDTO response = approvalService.createApprovalLog(requestDTO);

            if (response.getMessage().contains("Error")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            ApprovalResponseDTO errorResponse = new ApprovalResponseDTO();
            errorResponse.setMessage("Server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get approval log by ID
     * GET: /api/approval-log/{approvalId}
     */
    @GetMapping("/{approvalId}")
    public ResponseEntity<Approval> getApprovalLogById(@PathVariable String approvalId) {
        Approval approval = approvalService.getApprovalById(approvalId);

        if (approval == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(approval);
    }

    /**
     * Get all approval logs for a reference number
     * GET: /api/approval-log/reference/{referenceNo}
     */
    @GetMapping("/reference/{referenceNo}")
    public ResponseEntity<List<Approval>> getApprovalsByReference(@PathVariable String referenceNo) {
        List<Approval> approvals = approvalService.getApprovalsByReferenceNo(referenceNo);

        if (approvals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(approvals);
    }
}