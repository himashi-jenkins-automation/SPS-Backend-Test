// Updated WiringLandDetailController.java
package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.ServiceDetailsDTO;
import com.example.SPSProjectBackend.model.WiringLandDetail;
import com.example.SPSProjectBackend.model.WiringLandDetailCon;
import com.example.SPSProjectBackend.model.WiringLandDetailConId;
// import com.example.SPSProjectBackend.model.WiringLandDetailId;
import com.example.SPSProjectBackend.repository.PcesthmtRepository;
import com.example.SPSProjectBackend.repository.WiringLandDetailConRepository;
import com.example.SPSProjectBackend.repository.WiringLandDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wiring-land-details")
public class WiringLandDetailController {

    @Autowired
    private WiringLandDetailRepository wiringLandDetailRepository;

    @Autowired
    private WiringLandDetailConRepository wiringLandDetailConRepository;

    @Autowired
    private PcesthmtRepository pcesthmtRepository;

    @GetMapping("/by-estimate/{estimateNo}")
    public ResponseEntity<ServiceDetailsDTO> getServiceDetailsByEstimate(
            @PathVariable String estimateNo) {

        // Step 1: Dynamically get deptId from Pcesthmt based on estimateNo (latest
        // revision)
        List<String> deptIds = pcesthmtRepository.findDeptIdsByEstimateNo(estimateNo);
        if (deptIds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        String deptId = deptIds.get(0); // Take the first (should be the same for all revisions)

        // Step 2: Find WiringLandDetail by deptId (assume one per deptId; if multiple,
        // take first)
        List<WiringLandDetail> wiringLandDetails = wiringLandDetailRepository.findByIdDeptId(deptId);
        if (wiringLandDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        WiringLandDetail detail = wiringLandDetails.get(0); // Take the first one

        // Step 3: Get applicationId from the found WiringLandDetail
        String applicationId = detail.getId().getApplicationId();

        // Step 4: Find WiringLandDetailCon by applicationId and deptId
        WiringLandDetailConId conId = new WiringLandDetailConId();
        conId.setApplicationId(applicationId);
        conId.setDeptId(deptId);
        Optional<WiringLandDetailCon> conOpt = wiringLandDetailConRepository.findById(conId);
        String area = conOpt.isPresent() ? conOpt.get().getAreaCode() : null;

        // Step 5: Create DTO and set area
        ServiceDetailsDTO dto = new ServiceDetailsDTO(
                detail.getServiceStreetAddress(),
                detail.getServiceSuburb(),
                detail.getServiceCity(),
                detail.getServicePostalCode(),
                detail.getServiceDistrict(),
                detail.getDemand(),
                detail.getNeighboursAccNo(),
                area // Include area in constructor
        );

        return ResponseEntity.ok(dto);
    }
}