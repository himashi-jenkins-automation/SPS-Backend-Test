package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.SpPeggingDmt;
import com.example.SPSProjectBackend.model.SpPeggingDmtId;
import com.example.SPSProjectBackend.service.SpPeggingDmtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/spPeggingDmt")
public class SpPeggingDmtController {

    private static final Logger logger = LoggerFactory.getLogger(SpPeggingDmtController.class);
    private final SpPeggingDmtService service;

    public SpPeggingDmtController(SpPeggingDmtService service) {
        this.service = service;
    }

    @GetMapping
    public List<SpPeggingDmt> getAll() {
        return service.findAll();
    }

    @GetMapping("/{lineSectionTypeId}/{resCd}/{deptId}")
    public ResponseEntity<SpPeggingDmt> getById(
            @PathVariable String lineSectionTypeId,
            @PathVariable String resCd,
            @PathVariable String deptId) {
        SpPeggingDmtId id = new SpPeggingDmtId();
        id.setLineSectionTypeId(lineSectionTypeId);
        id.setResCd(resCd);
        id.setDeptId(deptId);
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lineSectionType/{lineSectionTypeId}")
    public List<SpPeggingDmt> getByLineSectionTypeId(@PathVariable String lineSectionTypeId) {
        try {
            logger.info("Fetching SpPeggingDmt for lineSectionTypeId: {}", lineSectionTypeId);
            List<SpPeggingDmt> result = service.findByLineSectionTypeId(lineSectionTypeId);
            logger.info("Found {} records for lineSectionTypeId: {}", result.size(), lineSectionTypeId);
            return result;
        } catch (Exception e) {
            logger.error("Error fetching SpPeggingDmt for lineSectionTypeId: {}", lineSectionTypeId, e);
            throw e;
        }
    }

    @PostMapping
    public SpPeggingDmt create(@RequestBody SpPeggingDmt spPeggingDmt) {
        return service.save(spPeggingDmt);
    }

    @PutMapping
    public SpPeggingDmt update(@RequestBody SpPeggingDmt spPeggingDmt) {
        return service.save(spPeggingDmt);
    }

    @DeleteMapping("/{lineSectionTypeId}/{resCd}/{deptId}")
    public void delete(
            @PathVariable String lineSectionTypeId,
            @PathVariable String resCd,
            @PathVariable String deptId) {
        SpPeggingDmtId id = new SpPeggingDmtId();
        id.setLineSectionTypeId(lineSectionTypeId);
        id.setResCd(resCd);
        id.setDeptId(deptId);
        service.deleteById(id);
    }

    // ✅ FIXED endpoint to update/modify estimate records
    @PutMapping("/estimate")
    public ResponseEntity<?> updateByEstimateNo(
            @RequestParam String estimateNo,
            @RequestBody List<SpPeggingDmt> updatedData) {

        // Basic validation
        if (updatedData == null || updatedData.isEmpty()) {
            return ResponseEntity.badRequest().body("No data provided for update");
        }

        // Validate that all records belong to the same estimateNo
        for (SpPeggingDmt item : updatedData) {
            if (item.getId() == null) {
                return ResponseEntity.badRequest().body("Record ID cannot be null");
            }
            if (!estimateNo.equals(item.getId().getLineSectionTypeId())) {
                return ResponseEntity.badRequest()
                        .body("All records must belong to estimate number: " + estimateNo);
            }
        }

        List<SpPeggingDmt> updatedRecords = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();

        // Update each record
        for (SpPeggingDmt updatedItem : updatedData) {
            SpPeggingDmtId id = updatedItem.getId();

            try {
                // Find the existing record
                var existingRecordOpt = service.findById(id);

                if (existingRecordOpt.isPresent()) {
                    SpPeggingDmt existingRecord = existingRecordOpt.get();

                    // Update all fields from the modified record
                    existingRecord.setPoleTypeId(updatedItem.getPoleTypeId());
                    existingRecord.setPointTypeId(updatedItem.getPointTypeId());
                    existingRecord.setResType(updatedItem.getResType());
                    existingRecord.setResCat(updatedItem.getResCat());
                    existingRecord.setResName(updatedItem.getResName());
                    existingRecord.setUom(updatedItem.getUom());
                    existingRecord.setEstimateQtyOld(updatedItem.getEstimateQtyOld());
                    existingRecord.setTolerance(updatedItem.getTolerance());
                    existingRecord.setUnitPrice(updatedItem.getUnitPrice());
                    existingRecord.setEstimateQty(updatedItem.getEstimateQty());

                    // Save the updated record
                    SpPeggingDmt savedRecord = service.save(existingRecord);
                    updatedRecords.add(savedRecord);

                } else {
                    errorMessages.add("Record not found: " + id.getResCd() + "/" + id.getDeptId());
                }
            } catch (Exception e) {
                errorMessages.add("Error updating record " + id.getResCd() + ": " + e.getMessage());
            }
        }

        // Prepare response based on update results
        if (updatedRecords.isEmpty() && !errorMessages.isEmpty()) {
            // All updates failed
            return ResponseEntity.badRequest()
                    .body("Failed to update any records. Errors: " + String.join(", ", errorMessages));
        } else if (!errorMessages.isEmpty()) {
            // Partial success - some updated, some failed
            UpdateResponse response = new UpdateResponse(
                    "Partially updated. " + updatedRecords.size() + " records updated successfully.",
                    updatedRecords,
                    errorMessages);
            return ResponseEntity.ok(response);
        }

        // All updates successful
        return ResponseEntity.ok(updatedRecords);
    }

    // ✅ NEW endpoint for bulk creation of estimate records
    @PostMapping("/estimate/bulk")
    public ResponseEntity<?> createEstimate(
            @RequestParam String estimateNo,
            @RequestBody List<SpPeggingDmt> estimateData) {

        if (estimateData == null || estimateData.isEmpty()) {
            return ResponseEntity.badRequest().body("No estimate data provided");
        }

        // Set estimate number for all records
        for (SpPeggingDmt record : estimateData) {
            if (record.getId() == null) {
                record.setId(new SpPeggingDmtId());
            }
            record.getId().setLineSectionTypeId(estimateNo);
        }

        List<SpPeggingDmt> savedRecords = new ArrayList<>();

        try {
            for (SpPeggingDmt record : estimateData) {
                SpPeggingDmt savedRecord = service.save(record);
                savedRecords.add(savedRecord);
            }

            return ResponseEntity.ok(savedRecords);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error saving estimate: " + e.getMessage());
        }
    }

    // Response wrapper for partial updates
    private static class UpdateResponse {
        private String message;
        private List<SpPeggingDmt> updatedRecords;
        private List<String> errors;

        public UpdateResponse(String message, List<SpPeggingDmt> updatedRecords, List<String> errors) {
            this.message = message;
            this.updatedRecords = updatedRecords;
            this.errors = errors;
        }

        // Getters
        public String getMessage() {
            return message;
        }

        public List<SpPeggingDmt> getUpdatedRecords() {
            return updatedRecords;
        }

        public List<String> getErrors() {
            return errors;
        }
    }
}