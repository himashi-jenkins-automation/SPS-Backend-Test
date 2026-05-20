package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.CommissionDTO;
import com.example.SPSProjectBackend.repository.PcesthmtRepository;
// import com.example.SPSProjectBackend.service.EstimateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import java.text.SimpleDateFormat;
import java.util.Arrays;
// import java.util.Date;
import java.util.List;

@Service
public class PcesthmtService {

    @Autowired
    private PcesthmtRepository pcesthmtRepository;

    public List<String> getAllEstimateNumbers() {
        return pcesthmtRepository.findAllEstimateNumbers();
    }

    public EstimateResponse checkEstimateAndGetDate(String estimateNo) {
        Object[] result = pcesthmtRepository.findEstimateAndProjectDates(estimateNo);

        System.out.println("Raw Query Result: " + Arrays.toString(result)); // Debug log

        boolean exists = result != null && result.length > 0;
        String formattedEtimateDt = null;
        String formattedPrjAssDt = null;

        if (exists) {
            // Extract the inner array (first row of the result)
            Object[] row = (Object[]) result[0];

            // Process each element of the row
            if (row[0] instanceof String) {
                formattedEtimateDt = (String) row[0];
            }
            if (row.length > 1 && row[1] instanceof String) {
                formattedPrjAssDt = (String) row[1];
            }
        }

        System.out.println("Query Result: " + Arrays.toString(result));
        return new EstimateResponse(exists, formattedEtimateDt, formattedPrjAssDt);
    }
    // // Add this method to your service
    // public List<CommissionDTO> getCommissionDetails() {
    // return pcesthmtRepository.findCommissionDetails();
    // }
    //
    // public List<CommissionDTO> getCommissionDetailsByUserId(String userId) {
    // return pcesthmtRepository.findCommissionDetailsByUserId(userId);
    // }
    //
    //
    // //this is for get editing related data
    // public List<CommissionDTO> getEditCommissionDetails() {
    // return pcesthmtRepository.findEditCommissionDetails();
    // }
    //
    // //this is for get validate related data
    // public List<CommissionDTO> getValidateCommissionDetails() {
    // return pcesthmtRepository.findValidateCommissionDetails();
    // }

    // Unified method to get commission details by userId and status
    // This replaces multiple methods with a single flexible method
    public List<CommissionDTO> getCommissionDetailsByUserIdAndStatus(String userId, Integer status) {
        return pcesthmtRepository.findCommissionDetailsByUserIdAndStatus(userId, status);
    }

    // NEW CODE - Added method to update status to 10
    // @Transactional
    // public void updateStatusTo10(String estimateNo, String deptId) {
    // pcesthmtRepository.updateStatusByEstimateNoAndDeptId(estimateNo, deptId,
    // (short) 10);
    // }
    //
    // //update status to 11
    // @Transactional
    // public void updateStatusTo11(String estimateNo, String deptId) {
    // pcesthmtRepository.updateStatusByEstimateNoAndDeptId(estimateNo, deptId,
    // (short) 11);
    // }

    // Unified method to update status - accepts status as parameter
    @Transactional
    public void updateStatus(String estimateNo, String deptId, Short status) {
        pcesthmtRepository.updateStatusByEstimateNoAndDeptId(estimateNo, deptId, status);
    }

}
