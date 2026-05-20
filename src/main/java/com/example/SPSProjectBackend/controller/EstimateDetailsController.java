package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.EstimateDetailsDTO;
import com.example.SPSProjectBackend.dto.EstimateItemDTO;
import com.example.SPSProjectBackend.repository.PcesthmtRepository;
import com.example.SPSProjectBackend.repository.SpstdestdmtRepository;
import com.example.SPSProjectBackend.repository.SpstdesthmtRepository;
import com.example.SPSProjectBackend.repository.WiringLandDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

@RestController
@RequestMapping("/api/estimate-details")
public class EstimateDetailsController {

    @Autowired
    private PcesthmtRepository pcesthmtRepository;

    @Autowired
    private SpstdesthmtRepository spstdesthmtRepository;

    @Autowired
    private SpstdestdmtRepository spstdestdmtRepository;

    @Autowired
    private WiringLandDetailRepository wiringLandDetailRepository;

    @GetMapping("/{estimateNo}")
    public ResponseEntity<EstimateDetailsDTO> getEstimateDetails(
            @PathVariable String estimateNo) {

        // Get deptId from Pcesthmt using estimateNo
        List<String> deptIds = pcesthmtRepository.findDeptIdsByEstimateNo(estimateNo);
        if (deptIds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        String deptId = deptIds.get(0);

        // Create DTO and populate data
        EstimateDetailsDTO dto = new EstimateDetailsDTO();

        // Get data from Spstdesthmt
        List<Object[]> spsData = spstdesthmtRepository.findEstimateDetailsByDeptId(deptId);
        if (!spsData.isEmpty()) {
            Object[] data = spsData.get(0);
            dto.setSecdeposit(((Number) data[0]).doubleValue());
            dto.setCebcost(((Number) data[1]).doubleValue());
            dto.setRebateCost(((Number) data[2]).doubleValue());
            dto.setVatcost(((Number) data[3]).doubleValue());
            dto.setNbtcost(((Number) data[4]).doubleValue());
            dto.setSctcost(((Number) data[5]).doubleValue());
            dto.setToconpay(((Number) data[6]).doubleValue());
        }

        // Get demand from WiringLandDetail
        BigDecimal demand = wiringLandDetailRepository.findDemandByDeptId(deptId);
        dto.setDemand(demand);

        return ResponseEntity.ok(dto);
    }

    // New endpoint for estimate items(this one for second table)
    @GetMapping("/{estimateNo}/items")
    public ResponseEntity<List<EstimateItemDTO>> getEstimateItems(
            @PathVariable String estimateNo) {

        // Get deptId from Pcesthmt using estimateNo
        List<String> deptIds = pcesthmtRepository.findDeptIdsByEstimateNo(estimateNo);
        if (deptIds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        String deptId = deptIds.get(0);

        // Get estimate items from Spstdestdmt
        List<Object[]> itemData = spstdestdmtRepository.findEstimateItemsByDeptId(deptId);
        List<EstimateItemDTO> items = new ArrayList<>();

        for (Object[] data : itemData) {
            EstimateItemDTO dto = new EstimateItemDTO();
            dto.setLineType((String) data[0]); // LINE_TYPE
            dto.setUom((String) data[1]); // UOM
            dto.setLinedes((String) data[2]); // LINEDES
            dto.setLength(((Number) data[3]).floatValue()); // LENGTH
            dto.setEstCost(((Number) data[4]).doubleValue()); // EST_COST
            dto.setLineCost(((Number) data[5]).doubleValue()); // LINE_COST
            // Get demand from WiringLandDetail
            BigDecimal demand = wiringLandDetailRepository.findDemandByDeptId(deptId);
            dto.setDemand(demand);
            items.add(dto);
        }

        return ResponseEntity.ok(items);
    }
}