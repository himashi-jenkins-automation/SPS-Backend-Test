package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.JobTypeCountDTO;
import com.example.SPSProjectBackend.dto.OrderCardDTO;
import com.example.SPSProjectBackend.model.OrderCard;
import com.example.SPSProjectBackend.model.OrderCardId;
import com.example.SPSProjectBackend.repository.OrderCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderCardService {

    @Autowired
    private OrderCardRepository orderCardRepository;

//    public String generateNextOrderCardNo(String projectNo) {
//        String prefix = "PROJECT/ORD/";
//
//        // Find the maximum existing order card number with the prefix
//        String maxOrderCardNo = orderCardRepository.findMaxOrderCardNoByPrefix(prefix);
//
//        int nextNumber = 1; // Default starting number
//
//        if (maxOrderCardNo != null && maxOrderCardNo.startsWith(prefix)) {
//            try {
//                // Extract the number part after the prefix
//                String numberStr = maxOrderCardNo.substring(prefix.length());
//                int currentNumber = Integer.parseInt(numberStr);
//                nextNumber = currentNumber + 1;
//            } catch (NumberFormatException e) {
//                // If parsing fails, start from 1
//                System.err.println("Error parsing order card number: " + maxOrderCardNo);
//            }
//        }
//
//        // Format the number with leading zeros (3 digits)
//        return String.format("%s%03d", prefix, nextNumber);
//    }

    //this is new method for generate new order card structure
//    using deptId


    // CHANGED: Simplified to only use deptId for generating new format: {deptId}/ORD/{2-digit-year}/{2-digit-sequence}
    public String generateNextOrderCardNo(String deptId) {
        // Get current year's last two digits (e.g., 25 for 2025)
        int currentYear = LocalDateTime.now().getYear() % 100;
        String yearPart = String.format("%02d", currentYear);

        // Create prefix for searching: {deptId}/ORD/{year}/
        String prefix = deptId + "/ORD/" + yearPart + "/";

        // Find the maximum existing order card number with this prefix
        String maxOrderCardNo = orderCardRepository.findMaxOrderCardNoByPrefix(prefix);

        int nextNumber = 1; // Default starting number

        if (maxOrderCardNo != null && maxOrderCardNo.startsWith(prefix)) {
            try {
                // Extract the sequence number part after the prefix
                String numberStr = maxOrderCardNo.substring(prefix.length());
                int currentNumber = Integer.parseInt(numberStr);
                nextNumber = currentNumber + 1;
            } catch (NumberFormatException e) {
                // If parsing fails, start from 1
                System.err.println("Error parsing order card number: " + maxOrderCardNo);
            }
        }

        // Format the number with leading zeros (2 digits) and return complete order card number
        return String.format("%s%02d", prefix, nextNumber);
    }

    public List<OrderCardDTO> getAllOrderCards() {
        return orderCardRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrderCardDTO> getOrderCardByProjectAndDept(String projectNo, String deptId) {
        return orderCardRepository.findByProjectNoAndDeptId(projectNo, deptId)
                .map(this::convertToDTO);
    }

    public List<OrderCardDTO> getOrderCardsByProject(String projectNo) {
        return orderCardRepository.findByProjectNo(projectNo)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderCardDTO createOrderCard(OrderCardDTO orderCardDTO) {
        OrderCard orderCard = convertToEntity(orderCardDTO);

        // Set update timestamp
        orderCard.setUpdDate(LocalDateTime.now()); //i will comment this for checking how time stamps behave

        OrderCard saved = orderCardRepository.save(orderCard);
        return convertToDTO(saved);
    }

    @Transactional
    public OrderCardDTO updateOrderCard(String projectNo, String deptId, OrderCardDTO orderCardDTO) {
        Optional<OrderCard> existingOpt = orderCardRepository.findByProjectNoAndDeptId(projectNo, deptId);

        if (existingOpt.isPresent()) {
            OrderCard existing = existingOpt.get();
            updateEntityFromDTO(existing, orderCardDTO);
            existing.setUpdDate(LocalDateTime.now());

            OrderCard updated = orderCardRepository.save(existing);
            return convertToDTO(updated);
        }
        throw new RuntimeException("Order card not found with project: " + projectNo + " and dept: " + deptId);
    }

    @Transactional
    public void deleteOrderCard(String projectNo, String deptId) {
        OrderCardId id = new OrderCardId(projectNo, deptId);
        if (orderCardRepository.existsById(id)) {
            orderCardRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order card not found with project: " + projectNo + " and dept: " + deptId);
        }
    }

    public List<JobTypeCountDTO> getJobTypeCounts() {
        return orderCardRepository.countByJobType();
    }

    // Conversion methods
    private OrderCardDTO convertToDTO(OrderCard orderCard) {
        OrderCardDTO dto = new OrderCardDTO();
        dto.setProjectNo(orderCard.getProjectNo());
        dto.setDeptId(orderCard.getDeptId());
        dto.setNoOfMeters(orderCard.getNoOfMeters());
        dto.setMeterNo1(orderCard.getMeterNo1());
        dto.setInitReading1(orderCard.getInitReading1());
        dto.setMeterType1(orderCard.getMeterType1());
        dto.setNoOfDigits1(orderCard.getNoOfDigits1());
        dto.setMeterNo2(orderCard.getMeterNo2());
        dto.setInitReading2(orderCard.getInitReading2());
        dto.setMeterType2(orderCard.getMeterType2());
        dto.setNoOfDigits2(orderCard.getNoOfDigits2());
        dto.setMeterNo3(orderCard.getMeterNo3());
        dto.setInitReading3(orderCard.getInitReading3());
        dto.setMeterType3(orderCard.getMeterType3());
        dto.setNoOfDigits3(orderCard.getNoOfDigits3());
        dto.setConnectedDate(orderCard.getConnectedDate());
        dto.setAvgConsump(orderCard.getAvgConsump());
        dto.setKvaRating(orderCard.getKvaRating());
        dto.setReaderCode(orderCard.getReaderCode());
        dto.setPackNo(orderCard.getPackNo());
        dto.setWalkSeq(orderCard.getWalkSeq());
        dto.setOldAccNo(orderCard.getOldAccNo());
        dto.setUpdBy(orderCard.getUpdBy());
        dto.setUpdDate(orderCard.getUpdDate());
        dto.setDepotCode(orderCard.getDepotCode());
        dto.setAreaCode(orderCard.getAreaCode());
        dto.setOrderCardNo(orderCard.getOrderCardNo());
        // ============ NEWLY ADDED FIELDS ============
        dto.setCustomerName(orderCard.getCustomerName());
        dto.setAddressOfSupply(orderCard.getAddressOfSupply());
        dto.setContractDemand(orderCard.getContractDemand());
        dto.setCustomerCategory(orderCard.getCustomerCategory());
        dto.setNatureOfSupplyCategory(orderCard.getNatureOfSupplyCategory());
        dto.setIsicNumber(orderCard.getIsicNumber());
        dto.setCustomerOwnershipType(orderCard.getCustomerOwnershipType());
        dto.setTariffType(orderCard.getTariffType());
        dto.setTransformerNumber(orderCard.getTransformerNumber());
        dto.setSinNumber(orderCard.getSinNumber());
        // ============ NEWLY ADDED MAPPINGS FOR 6 COLUMNS ============
        dto.setEstPivNumber(orderCard.getEstPivNumber());
        dto.setEstAmount(orderCard.getEstAmount());
        dto.setEstPayDate(orderCard.getEstPayDate());
        dto.setDepPivNumber(orderCard.getDepPivNumber());
        dto.setDepositAmount(orderCard.getDepositAmount());
        dto.setDepositDate(orderCard.getDepositDate());
        // ============ NEWLY ADDED MAPPINGS FOR 3 LOAN COLUMNS ============
        dto.setIsLoanApp(orderCard.getIsLoanApp());
        dto.setLoanAmount(orderCard.getLoanAmount());
        dto.setLoanType(orderCard.getLoanType());
        // ============ NEWLY ADDED MAPPINGS FOR 3  COLUMNS ============
        dto.setJobType(orderCard.getJobType());
        dto.setVoltageLevel(orderCard.getVoltageLevel());
        dto.setSectionId(orderCard.getSectionId());
        dto.setVatRegNo(orderCard.getVatRegNo());


        return dto;
    }

    private OrderCard convertToEntity(OrderCardDTO dto) {
        OrderCard orderCard = new OrderCard();
        updateEntityFromDTO(orderCard, dto);
        return orderCard;
    }

    private void updateEntityFromDTO(OrderCard orderCard, OrderCardDTO dto) {
        orderCard.setProjectNo(dto.getProjectNo());
        orderCard.setDeptId(dto.getDeptId());
        orderCard.setNoOfMeters(dto.getNoOfMeters());
        orderCard.setMeterNo1(dto.getMeterNo1());
        orderCard.setInitReading1(dto.getInitReading1());
        orderCard.setMeterType1(dto.getMeterType1());
        orderCard.setNoOfDigits1(dto.getNoOfDigits1());
        orderCard.setMeterNo2(dto.getMeterNo2());
        orderCard.setInitReading2(dto.getInitReading2());
        orderCard.setMeterType2(dto.getMeterType2());
        orderCard.setNoOfDigits2(dto.getNoOfDigits2());
        orderCard.setMeterNo3(dto.getMeterNo3());
        orderCard.setInitReading3(dto.getInitReading3());
        orderCard.setMeterType3(dto.getMeterType3());
        orderCard.setNoOfDigits3(dto.getNoOfDigits3());
        orderCard.setConnectedDate(dto.getConnectedDate());
        orderCard.setAvgConsump(dto.getAvgConsump());
        orderCard.setKvaRating(dto.getKvaRating());
        orderCard.setReaderCode(dto.getReaderCode());
        orderCard.setPackNo(dto.getPackNo());
        orderCard.setWalkSeq(dto.getWalkSeq());
        orderCard.setOldAccNo(dto.getOldAccNo());
        orderCard.setUpdBy(dto.getUpdBy());
        orderCard.setUpdDate(dto.getUpdDate());
        orderCard.setDepotCode(dto.getDepotCode());
        orderCard.setAreaCode(dto.getAreaCode());
        orderCard.setOrderCardNo(dto.getOrderCardNo());
        // ============ NEWLY ADDED FIELDS ============
        orderCard.setCustomerName(dto.getCustomerName());
        orderCard.setAddressOfSupply(dto.getAddressOfSupply());
        orderCard.setContractDemand(dto.getContractDemand());
        orderCard.setCustomerCategory(dto.getCustomerCategory());
        orderCard.setNatureOfSupplyCategory(dto.getNatureOfSupplyCategory());
        orderCard.setIsicNumber(dto.getIsicNumber());
        orderCard.setCustomerOwnershipType(dto.getCustomerOwnershipType());
        orderCard.setTariffType(dto.getTariffType());
        orderCard.setTransformerNumber(dto.getTransformerNumber());
        orderCard.setSinNumber(dto.getSinNumber());

        // ============ NEWLY ADDED MAPPINGS FOR 6 COLUMNS ============
        orderCard.setEstPivNumber(dto.getEstPivNumber());
        orderCard.setEstAmount(dto.getEstAmount());
        orderCard.setEstPayDate(dto.getEstPayDate());
        orderCard.setDepPivNumber(dto.getDepPivNumber());
        orderCard.setDepositAmount(dto.getDepositAmount());
        orderCard.setDepositDate(dto.getDepositDate());

        // ============ NEWLY ADDED MAPPINGS FOR 3 LOAN COLUMNS ============
        orderCard.setIsLoanApp(dto.getIsLoanApp());
        orderCard.setLoanAmount(dto.getLoanAmount());
        orderCard.setLoanType(dto.getLoanType());

        // ============ NEWLY ADDED MAPPINGS FOR 3  COLUMNS ============
        orderCard.setJobType(dto.getJobType());
        orderCard.setVoltageLevel(dto.getVoltageLevel());
        orderCard.setSectionId(dto.getSectionId());
        orderCard.setVatRegNo(dto.getVatRegNo());

    }
}