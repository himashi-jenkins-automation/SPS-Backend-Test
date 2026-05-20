package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.MeterDetailDTO;
import com.example.SPSProjectBackend.service.MeterDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meter-details")
public class MeterDetailController {

    @Autowired
    private MeterDetailService meterDetailService;

    @GetMapping
    public List<MeterDetailDTO> getAllMeterDetails() {
        return meterDetailService.getAllMeterDetails();
    }

    @GetMapping("/by-order-card")
    public ResponseEntity<?> getMeterDetailsByOrderCardNo(@RequestParam String orderCardNo) {
        try {
            List<MeterDetailDTO> meterDetails = meterDetailService.getMeterDetailsByOrderCardNo(orderCardNo);
            return ResponseEntity.ok(meterDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to fetch meter details: " + e.getMessage()));
        }
    }

    @GetMapping("/by-order-card-and-type")
    public ResponseEntity<?> getMeterDetailByOrderCardNoAndMtrType(
            @RequestParam String orderCardNo,
            @RequestParam String mtrType) {
        try {
            Optional<MeterDetailDTO> meterDetail = meterDetailService.getMeterDetailByOrderCardNoAndMtrType(orderCardNo, mtrType);
            if (meterDetail.isPresent()) {
                return ResponseEntity.ok(meterDetail.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message", "Meter detail not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to fetch meter detail: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createMeterDetail(@RequestBody MeterDetailDTO meterDetailDTO) {
        try {
            MeterDetailDTO created = meterDetailService.createMeterDetail(meterDetailDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to create meter detail: " + e.getMessage()));
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<?> createMeterDetailsBatch(@RequestBody List<MeterDetailDTO> meterDetailDTOs) {
        try {
            List<MeterDetailDTO> created = meterDetailService.createMeterDetailsBatch(meterDetailDTOs);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to create meter details: " + e.getMessage()));
        }
    }

    //this is delete existent meter details and post fresh new mtr details
    @PostMapping("/batch-replace")
    public ResponseEntity<?> createOrReplaceMeterDetailsBatch(
            @RequestBody List<MeterDetailDTO> meterDetailDTOs) {
        try {
            List<MeterDetailDTO> saved =
                    meterDetailService.replaceMeterDetailsBatch(meterDetailDTOs);

            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap(
                            "error", "Failed to save meter details: " + e.getMessage()));
        }
    }


    //this  put mapping not supporting List<MeterDetailDTO>
    @PutMapping
    public ResponseEntity<?> updateMeterDetail(
            @RequestParam String orderCardNo,
            @RequestParam String mtrType,
            @RequestBody MeterDetailDTO meterDetailDTO) {
        try {
            MeterDetailDTO updated = meterDetailService.updateMeterDetail(orderCardNo, mtrType, meterDetailDTO);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to update meter detail: " + e.getMessage()));
        }
    }


    // Modified to accept List<MeterDetailDTO> instead of single MeterDetailDTO
    @PutMapping("/batch")
    public ResponseEntity<?> updateMeterDetailsBatch(@RequestBody List<MeterDetailDTO> meterDetailDTOs) {
        try {
            List<MeterDetailDTO> updated = meterDetailService.updateMeterDetailsBatch(meterDetailDTOs);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to update meter details: " + e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMeterDetail(
            @RequestParam String orderCardNo,
            @RequestParam String mtrType) {
        try {
            meterDetailService.deleteMeterDetail(orderCardNo, mtrType);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to delete meter detail: " + e.getMessage()));
        }
    }

    @DeleteMapping("/by-order-card")
    public ResponseEntity<?> deleteMeterDetailsByOrderCardNo(@RequestParam String orderCardNo) {
        try {
            meterDetailService.deleteMeterDetailsByOrderCardNo(orderCardNo);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to delete meter details: " + e.getMessage()));
        }
    }
}