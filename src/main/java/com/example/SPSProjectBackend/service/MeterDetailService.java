package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.MeterDetailDTO;
import com.example.SPSProjectBackend.model.MeterDetail;
import com.example.SPSProjectBackend.model.MeterDetailId;
import com.example.SPSProjectBackend.repository.MeterDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeterDetailService {

    @Autowired
    private MeterDetailRepository meterDetailRepository;

    public List<MeterDetailDTO> getAllMeterDetails() {
        return meterDetailRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MeterDetailDTO> getMeterDetailsByOrderCardNo(String orderCardNo) {
        return meterDetailRepository.findByOrderCardNo(orderCardNo)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MeterDetailDTO> getMeterDetailByOrderCardNoAndMtrType(String orderCardNo, String mtrType) {
        return meterDetailRepository.findByOrderCardNoAndMtrType(orderCardNo, mtrType)
                .map(this::convertToDTO);
    }

    @Transactional
    public MeterDetailDTO createMeterDetail(MeterDetailDTO meterDetailDTO) {
        MeterDetail meterDetail = convertToEntity(meterDetailDTO);

        // Set timestamps
        meterDetail.setEnteredDtime(LocalDateTime.now());
        meterDetail.setEditedDtime(LocalDateTime.now());

        MeterDetail saved = meterDetailRepository.save(meterDetail);
        return convertToDTO(saved);
    }

    @Transactional
    public List<MeterDetailDTO> createMeterDetailsBatch(List<MeterDetailDTO> meterDetailDTOs) {

        System.out.println("=== DEBUG: Received meter details batch ===");

        List<MeterDetail> meterDetails = meterDetailDTOs.stream()
                .map(dto -> {

                    // Debug logging
//                    System.out.println("Processing meter type: " + dto.getMtrType());
//                    System.out.println("mFactor value: " + dto.getMFactor());
//                    System.out.println("effctDate value: " + dto.getEffctDate());
//                    System.out.println("---");

                    MeterDetail entity = convertToEntity(dto);
                    entity.setEnteredDtime(LocalDateTime.now());
                    entity.setEditedDtime(LocalDateTime.now());
                    return entity;
                })
                .collect(Collectors.toList());

        List<MeterDetail> saved = meterDetailRepository.saveAll(meterDetails);


        // Log saved entities
//        System.out.println("=== DEBUG: Saved entities ===");
//        saved.forEach(entity -> {
//            System.out.println("Saved - mtrType: " + entity.getMtrType() +
//                    ", mFactor: " + entity.getMFactor() +
//                    ", effctDate: " + entity.getEffctDate());
//        });

        return saved.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //delete previous data and replace new mtr values
    @Transactional
    public List<MeterDetailDTO> replaceMeterDetailsBatch(
            List<MeterDetailDTO> meterDetailDTOs) {

        if (meterDetailDTOs == null || meterDetailDTOs.isEmpty()) {
            return Collections.emptyList();
        }

        //  Decide delete scope (VERY IMPORTANT)
        String orderCardNo = meterDetailDTOs.get(0).getOrderCardNo();

        //  DELETE existing records for this order card
        meterDetailRepository.deleteByOrderCardNo(orderCardNo);

        //  INSERT fresh data
        List<MeterDetail> meterDetails = meterDetailDTOs.stream()
                .map(dto -> {
                    MeterDetail entity = convertToEntity(dto);
                    entity.setEnteredDtime(LocalDateTime.now());
                    entity.setEditedDtime(LocalDateTime.now());
                    return entity;
                })
                .collect(Collectors.toList());

        List<MeterDetail> saved = meterDetailRepository.saveAll(meterDetails);

        //  Return DTOs
        return saved.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Transactional
    public MeterDetailDTO updateMeterDetail(String orderCardNo, String mtrType, MeterDetailDTO meterDetailDTO) {
        MeterDetailId id = new MeterDetailId(orderCardNo, mtrType);
        Optional<MeterDetail> existingOpt = meterDetailRepository.findById(id);

        if (existingOpt.isPresent()) {
            MeterDetail existing = existingOpt.get();
            updateEntityFromDTO(existing, meterDetailDTO);
            existing.setEditedDtime(LocalDateTime.now());

            MeterDetail updated = meterDetailRepository.save(existing);
            return convertToDTO(updated);
        }
        throw new RuntimeException("Meter detail not found with order card: " + orderCardNo + " and meter type: " + mtrType);
    }

    @Transactional
    public List<MeterDetailDTO> updateMeterDetailsBatch(List<MeterDetailDTO> meterDetailDTOs) {
        List<MeterDetail> updatedMeterDetails = new ArrayList<>();

        for (MeterDetailDTO dto : meterDetailDTOs) {
            // Use the composite key from each DTO to find the existing record
            MeterDetailId id = new MeterDetailId(dto.getOrderCardNo(), dto.getMtrType());
            Optional<MeterDetail> existingOpt = meterDetailRepository.findById(id);

            if (existingOpt.isPresent()) {
                MeterDetail existing = existingOpt.get();
                updateEntityFromDTO(existing, dto);
                existing.setEditedDtime(LocalDateTime.now());

                MeterDetail updated = meterDetailRepository.save(existing);
                updatedMeterDetails.add(updated);
            } else {
                // If not found, you can choose to create new or throw exception
                // For now, let's throw exception
                throw new RuntimeException("Meter detail not found with order card: " +
                        dto.getOrderCardNo() + " and meter type: " + dto.getMtrType());
            }
        }

        return updatedMeterDetails.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMeterDetail(String orderCardNo, String mtrType) {
        MeterDetailId id = new MeterDetailId(orderCardNo, mtrType);
        if (meterDetailRepository.existsById(id)) {
            meterDetailRepository.deleteById(id);
        } else {
            throw new RuntimeException("Meter detail not found with order card: " + orderCardNo + " and meter type: " + mtrType);
        }
    }

    @Transactional
    public void deleteMeterDetailsByOrderCardNo(String orderCardNo) {
        meterDetailRepository.deleteByOrderCardNo(orderCardNo);
    }

    // Conversion methods
    private MeterDetailDTO convertToDTO(MeterDetail meterDetail) {
        MeterDetailDTO dto = new MeterDetailDTO();
        dto.setOrderCardNo(meterDetail.getOrderCardNo());
        dto.setMtrType(meterDetail.getMtrType());
        dto.setAccNbr(meterDetail.getAccNbr());
        dto.setAddedBlcy(meterDetail.getAddedBlcy());
        dto.setAreaCd(meterDetail.getAreaCd());
        dto.setBrCode(meterDetail.getBrCode());
        dto.setCnctTrnpan(meterDetail.getCnctTrnpan());
        dto.setCtRatio(meterDetail.getCtRatio());
        dto.setDpCode(meterDetail.getDpCode());
        dto.setEditedDtime(meterDetail.getEditedDtime());
        dto.setEditedUserId(meterDetail.getEditedUserId());
        dto.setEffctBlcy(meterDetail.getEffctBlcy());
        dto.setEffctDate(meterDetail.getEffctDate());
        dto.setEnteredDtime(meterDetail.getEnteredDtime());
        dto.setJobNbr(meterDetail.getJobNbr());
        dto.setMtr1set(meterDetail.getMtr1set());
        dto.setMtr2set(meterDetail.getMtr2set());
        dto.setMtr3set(meterDetail.getMtr3set());
        dto.setMtrNbr(meterDetail.getMtrNbr());
        dto.setMtrOrder(meterDetail.getMtrOrder());
        dto.setMtrRatio(meterDetail.getMtrRatio());
        dto.setMtrSeq(meterDetail.getMtrSeq());
//        dto.setMFactor(meterDetail.getMFactor());
        dto.setMtrFactor(meterDetail.getMtrFactor());
        dto.setNoMtrSets(meterDetail.getNoMtrSets());
        dto.setNoOfPhases(meterDetail.getNoOfPhases());
        dto.setPrsntRdn(meterDetail.getPrsntRdn());
        dto.setSetType(meterDetail.getSetType());
        dto.setTrCb(meterDetail.getTrCb());
        dto.setTrnsfAmps(meterDetail.getTrnsfAmps());
        dto.setTrnsfVolt(meterDetail.getTrnsfVolt());
        dto.setUserId(meterDetail.getUserId());
        return dto;
    }

    private MeterDetail convertToEntity(MeterDetailDTO dto) {
        MeterDetail meterDetail = new MeterDetail();
        updateEntityFromDTO(meterDetail, dto);
        return meterDetail;
    }

    private void updateEntityFromDTO(MeterDetail meterDetail, MeterDetailDTO dto) {
        meterDetail.setOrderCardNo(dto.getOrderCardNo());
        meterDetail.setMtrType(dto.getMtrType());
        meterDetail.setAccNbr(dto.getAccNbr());
        meterDetail.setAddedBlcy(dto.getAddedBlcy());
        meterDetail.setAreaCd(dto.getAreaCd());
        meterDetail.setBrCode(dto.getBrCode());
        meterDetail.setCnctTrnpan(dto.getCnctTrnpan());
        meterDetail.setCtRatio(dto.getCtRatio());
        meterDetail.setDpCode(dto.getDpCode());
        meterDetail.setEditedDtime(dto.getEditedDtime());
        meterDetail.setEditedUserId(dto.getEditedUserId());
        meterDetail.setEffctBlcy(dto.getEffctBlcy());
        meterDetail.setEffctDate(dto.getEffctDate());
        meterDetail.setEnteredDtime(dto.getEnteredDtime());
        meterDetail.setJobNbr(dto.getJobNbr());
        meterDetail.setMtr1set(dto.getMtr1set());
        meterDetail.setMtr2set(dto.getMtr2set());
        meterDetail.setMtr3set(dto.getMtr3set());
        meterDetail.setMtrNbr(dto.getMtrNbr());
        meterDetail.setMtrOrder(dto.getMtrOrder());
        meterDetail.setMtrRatio(dto.getMtrRatio());
        meterDetail.setMtrSeq(dto.getMtrSeq());
//        meterDetail.setMFactor(dto.getMFactor());
        meterDetail.setMtrFactor(dto.getMtrFactor());
        meterDetail.setNoMtrSets(dto.getNoMtrSets());
        meterDetail.setNoOfPhases(dto.getNoOfPhases());
        meterDetail.setPrsntRdn(dto.getPrsntRdn());
        meterDetail.setSetType(dto.getSetType());
        meterDetail.setTrCb(dto.getTrCb());
        meterDetail.setTrnsfAmps(dto.getTrnsfAmps());
        meterDetail.setTrnsfVolt(dto.getTrnsfVolt());
        meterDetail.setUserId(dto.getUserId());
    }
}