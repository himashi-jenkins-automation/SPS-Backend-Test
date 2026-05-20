package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.SppegInformation;
import com.example.SPSProjectBackend.model.SppegInformationId;
import com.example.SPSProjectBackend.service.SppegInformationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/sppeg")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class SppegInformationController {

    private static final Logger logger = LoggerFactory.getLogger(SppegInformationController.class);

    @Autowired
    private SppegInformationService service;

    @GetMapping
    public List<SppegInformation> getAll() {
        logger.info("Fetching all SppegInformation records");
        return service.getAll();
    }

    @GetMapping("/{sppeg_id}/{parent_id}/{department_id}")
    public Optional<SppegInformation> getById(@PathVariable("sppeg_id") String sppegId,
                                              @PathVariable("parent_id") String parentId,
                                              @PathVariable("department_id") String departmentId) {
        SppegInformationId key = new SppegInformationId(sppegId, parentId, departmentId);
        return service.getById(key);
    }

    @GetMapping("/department/{deptId}")
    public List<SppegInformation> getByDepartment(@PathVariable String deptId) {
        logger.info("Fetching all records for department: {}", deptId);
        return service.getByDeptId(deptId);
    }

    @PostMapping
    public SppegInformation create(@RequestBody SppegInformation info) {
        return service.save(info);
    }

    @PutMapping
    public SppegInformation update(@RequestBody SppegInformation info) {
        return service.save(info);
    }

    @DeleteMapping("/{sppeg_id}/{parent_id}/{department_id}")
    public void delete(@PathVariable("sppeg_id") String sppegId,
                       @PathVariable("parent_id") String parentId,
                       @PathVariable("department_id") String departmentId) {
        SppegInformationId key = new SppegInformationId(sppegId, parentId, departmentId);
        service.deleteById(key);
    }

    @GetMapping("/filter")
    public List<SppegInformation> getPeggingTreeNodes(@RequestParam("department_id") String departmentId,
                                                      @RequestParam("parent_id") String parentId) {
        return service.getByParentId(departmentId, parentId);
    }

     // NEW: Add child node endpoint
    @PostMapping("/add-child")
    public ResponseEntity<?> addChildNode(@RequestBody Map<String, String> request) {
        try {
            String parentId = request.get("parentId");
            String deptId = request.get("deptId");
            String newNodeId = request.get("newNodeId");
            String newNodeName = request.get("newNodeName");
            String description = request.get("description");
            
            logger.info("Adding child node: {} to parent: {} in dept: {}", newNodeName, parentId, deptId);
            
            // Check if node already exists
            SppegInformationId newId = new SppegInformationId(newNodeId, parentId, deptId);
            if (service.nodeExists(newId)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Node with this ID already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            }
            
            SppegInformation savedNode = service.addChildNode(parentId, deptId, newNodeId, newNodeName, description);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Node added successfully");
            response.put("node", savedNode);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            logger.error("Error adding child node", e);
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // NEW: Delete node endpoint
    @DeleteMapping("/delete/{sppeg_id}/{parent_id}/{department_id}")
    public ResponseEntity<?> deleteNode(@PathVariable("sppeg_id") String sppegId,
                                       @PathVariable("parent_id") String parentId,
                                       @PathVariable("department_id") String departmentId) {
        try {
            SppegInformationId key = new SppegInformationId(sppegId, parentId, departmentId);
            
            // Check if node exists
            if (!service.nodeExists(key)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Node not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            
            service.deleteNodeAndChildren(key);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Node and its children deleted successfully");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Error deleting node", e);
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }


     // UPDATE: Update node name only endpoint
    @PutMapping("/update-name/{sppeg_id}/{parent_id}/{department_id}")
    public ResponseEntity<?> updateNodeName(@PathVariable("sppeg_id") String sppegId,
                                           @PathVariable("parent_id") String parentId,
                                           @PathVariable("department_id") String departmentId,
                                           @RequestBody Map<String, String> request) {
        try {
            SppegInformationId key = new SppegInformationId(sppegId, parentId, departmentId);
            
            // Check if node exists
            if (!service.nodeExists(key)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Node not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            
            String newName = request.get("name");
            
            // Validate name
            if (newName == null || newName.trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Node name cannot be empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            
            logger.info("Updating node name: {} to new name: {}", sppegId, newName);
            
            SppegInformation updatedNode = service.updateNodeName(key, newName);
            
            if (updatedNode == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Failed to update node name");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Node name updated successfully");
            response.put("node", updatedNode);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Error updating node name", e);
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }


}
