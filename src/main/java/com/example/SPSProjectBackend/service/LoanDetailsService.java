package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.LoanDetailsDTO;
import com.example.SPSProjectBackend.repository.LoanDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanDetailsService {

    @Autowired
    private LoanDetailsRepository loanDetailsRepository;

    public List<LoanDetailsDTO> getLoanDetailsByEstimateNo(String estimateNo) {
        try {
            System.out.println("=== Starting loan details search for estimateNo: " + estimateNo + " ===");

            List<LoanDetailsRepository.LoanDetailsProjection> projections =
                    loanDetailsRepository.findLoanDetailsByEstimateNo(estimateNo);

            if (!projections.isEmpty()) {
                System.out.println("✅ Found " + projections.size() + " loan detail record(s)");

                // Convert projections to DTOs
                return projections.stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
            }

            System.out.println("❌ No loan details found for estimate: " + estimateNo);
            return Collections.emptyList();

        } catch (Exception e) {
            System.err.println("💥 Error in getLoanDetailsByEstimateNo for estimate: " + estimateNo);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private LoanDetailsDTO convertToDTO(LoanDetailsRepository.LoanDetailsProjection projection) {
        LoanDetailsDTO dto = new LoanDetailsDTO();
        dto.setIsLoanApp(projection.getIsLoanApp());
        dto.setLoanAmount(projection.getLoanAmount() != null ?
                BigDecimal.valueOf(projection.getLoanAmount()) : null);
        dto.setApplicationNo(projection.getApplicationNo());
        return dto;
    }
}