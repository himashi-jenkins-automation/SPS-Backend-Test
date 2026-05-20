//package com.example.SPSProjectBackend.service;
//
//import com.example.SPSProjectBackend.dto.ApprovalHistoryDTO;
//import com.example.SPSProjectBackend.model.Approval;
//import com.example.SPSProjectBackend.repository.ApprovalRepository;
//// import com.example.SPSProjectBackend.repository.PcesthmtRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ApprovalService {
//
//    @Autowired
//    private ApprovalRepository approvalRepository;
//
//    // @Autowired
//    // private PcesthmtRepository pcesthmtRepository;
//
//    public List<ApprovalHistoryDTO> getApprovalHistoryByEstimateNo(String estimateNo) {
//        List<Approval> approvals = approvalRepository.findByEstimateNo(estimateNo);
//
//        // Handle case where approvals is empty
//        if (approvals == null || approvals.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        return approvals.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    private ApprovalHistoryDTO convertToDTO(Approval approval) {
//        return new ApprovalHistoryDTO(
//                approval.getApprovedBy(),
//                approval.getApprovedDate(),
//                approval.getApprovedTime() != null ? approval.getApprovedTime() : "",
//                approval.getApprovedLevel(),
//                approval.getReason() != null ? approval.getReason() : "",
//                approval.getFromStatus(),
//                approval.getToStatus());
//    }
//}


//new code
package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.ApprovalHistoryDTO;
import com.example.SPSProjectBackend.dto.ApprovalRequestDTO;
import com.example.SPSProjectBackend.dto.ApprovalResponseDTO;
import com.example.SPSProjectBackend.model.Approval;
import com.example.SPSProjectBackend.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;

    /**
     * Get approval history by estimate/reference number
     */
    public List<ApprovalHistoryDTO> getApprovalHistoryByEstimateNo(String estimateNo) {
        List<Approval> approvals = approvalRepository.findByReferenceNo(estimateNo);

        if (approvals == null || approvals.isEmpty()) {
            return Collections.emptyList();
        }

        return approvals.stream()
                .map(this::convertToHistoryDTO)
                .collect(Collectors.toList());
    }

    /**
     * Create a new approval log entry
     */
    @Transactional
    public ApprovalResponseDTO createApprovalLog(ApprovalRequestDTO requestDTO) {
        try {
            // Generate the next approval ID
            String nextApprovalId = generateNextApprovalId(requestDTO.getReferenceNo());

            // Create new Approval entity
            Approval approval = new Approval(
                    requestDTO.getReferenceNo(),
                    requestDTO.getDeptId(),
                    requestDTO.getApprovalType(),
                    requestDTO.getApprovedLevel(),
                    requestDTO.getFromStatus(),
                    requestDTO.getToStatus(),
                    requestDTO.getApprovedBy(),
                    requestDTO.getReason(),
                    requestDTO.getStandardCost() != null ? requestDTO.getStandardCost() : BigDecimal.ZERO,
                    requestDTO.getDetailedCost() != null ? requestDTO.getDetailedCost() : BigDecimal.ZERO,
                    requestDTO.getSystemBy()
            );

            // Set the generated approval ID
            approval.setApprovalId(nextApprovalId);

            // Save to database
            Approval savedApproval = approvalRepository.save(approval);

            // Convert to response DTO
            return convertToResponseDTO(savedApproval, "Approval log created successfully");

        } catch (Exception e) {
            ApprovalResponseDTO errorResponse = new ApprovalResponseDTO();
            errorResponse.setMessage("Error creating approval log: " + e.getMessage());
            return errorResponse;
        }
    }

    /**
     * Generate next approval ID based on reference number
     * Format: REFERENCE_NO-XXXX (e.g., 423.10/EMT/25/0003-0001)
     */
    private String generateNextApprovalId(String referenceNo) {
        // Pattern to search for existing approval IDs for this reference
        String pattern = referenceNo + "-%";

        // Get the last approval ID for this reference
        List<String> lastApprovalIds = approvalRepository.findLastApprovalIdByPattern(pattern);

        String sequence;
        if (lastApprovalIds != null && !lastApprovalIds.isEmpty()) {
            // Get the last approval ID
            String lastApprovalId = lastApprovalIds.get(0);

            // Extract the sequence number (last 4 digits after the dash)
            String sequencePart = lastApprovalId.substring(lastApprovalId.lastIndexOf("-") + 1);

            // Parse to integer and increment
            Integer sequenceNumber = Integer.parseInt(sequencePart) + 1;
            sequence = sequenceNumber.toString();
        } else {
            // No existing records, start with 0001
            sequence = "0001";
        }

        // Format sequence to 4 digits with leading zeros
        switch (sequence.length()) {
            case 1:
                sequence = "000" + sequence;
                break;
            case 2:
                sequence = "00" + sequence;
                break;
            case 3:
                sequence = "0" + sequence;
                break;
            default:
                // Already 4 or more digits, take last 4
                if (sequence.length() > 4) {
                    sequence = sequence.substring(sequence.length() - 4);
                }
        }

        // Return the complete approval ID
        return referenceNo + "-" + sequence;
    }

    /**
     * Convert Approval entity to ApprovalHistoryDTO for history responses
     */
    private ApprovalHistoryDTO convertToHistoryDTO(Approval approval) {
        return new ApprovalHistoryDTO(
                approval.getApprovedBy(),
                approval.getApprovedDate() != null ?
                        java.sql.Date.valueOf(approval.getApprovedDate()) : null,
                approval.getApprovedTime() != null ? approval.getApprovedTime() : "",
                approval.getApprovedLevel(),
                approval.getReason() != null ? approval.getReason() : "",
                approval.getFromStatus(),
                approval.getToStatus()
        );
    }

    /**
     * Convert saved Approval entity to ApprovalResponseDTO
     */
    private ApprovalResponseDTO convertToResponseDTO(Approval approval, String message) {
        ApprovalResponseDTO responseDTO = new ApprovalResponseDTO();

        responseDTO.setApprovalId(approval.getApprovalId());
        responseDTO.setReferenceNo(approval.getReferenceNo());
        responseDTO.setDeptId(approval.getDeptId());
        responseDTO.setApprovalType(approval.getApprovalType());
        responseDTO.setApprovedLevel(approval.getApprovedLevel());
        responseDTO.setFromStatus(approval.getFromStatus());
        responseDTO.setToStatus(approval.getToStatus());
        responseDTO.setApprovedBy(approval.getApprovedBy());
        responseDTO.setReason(approval.getReason());
        responseDTO.setApprovedDate(approval.getApprovedDate());
        responseDTO.setApprovedTime(approval.getApprovedTime());
        responseDTO.setStandardCost(approval.getStandardCost());
        responseDTO.setDetailedCost(approval.getDetailedCost());
        responseDTO.setSystemBy(approval.getSystemBy());
        responseDTO.setMessage(message);

        return responseDTO;
    }

    /**
     * Get all approvals for a reference number
     */
    public List<Approval> getApprovalsByReferenceNo(String referenceNo) {
        return approvalRepository.findByReferenceNo(referenceNo);
    }

    /**
     * Get specific approval by ID
     */
    public Approval getApprovalById(String approvalId) {
        return approvalRepository.findByApprovalId(approvalId).orElse(null);
    }
}