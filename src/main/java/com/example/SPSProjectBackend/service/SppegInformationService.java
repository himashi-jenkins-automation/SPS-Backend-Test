package com.example.SPSProjectBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SPSProjectBackend.model.SppegInformation;
import com.example.SPSProjectBackend.model.SppegInformationId;
import com.example.SPSProjectBackend.repository.SppegInformationRepository;
// import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class SppegInformationService {

    @Autowired
    private SppegInformationRepository repository;

    public List<SppegInformation> getAll() {
        return repository.findAll();
    }

    public List<SppegInformation> getByDeptId(String deptId) {
    return repository.findByDeptId(deptId);
}

    public Optional<SppegInformation> getById(SppegInformationId id) {
        return repository.findById(id);
    }

    public SppegInformation save(SppegInformation info) {
        return repository.save(info);
    }

    public void deleteById(SppegInformationId id) {
        repository.deleteById(id);
    }

    public List<SppegInformation> getByParentId(String deptId, String parentId) {
        return repository.findByParentId(deptId, parentId);
    }

    // NEW: Add child node
    public SppegInformation addChildNode(String parentId, String deptId, String newNodeId, String newNodeName, String description) {
        SppegInformation childNode = new SppegInformation();
        childNode.setId(newNodeId);
        childNode.setParentId(parentId);
        childNode.setDeptId(deptId);
        childNode.setName(newNodeName);
        childNode.setDescription(description);
        
        return repository.save(childNode);
    }

    // NEW: Delete node and optionally its children
    public void deleteNodeAndChildren(SppegInformationId id) {
        // First, find all children of this node
        Optional<SppegInformation> nodeOpt = repository.findById(id);
        if (nodeOpt.isPresent()) {
            SppegInformation node = nodeOpt.get();
            
            // Find and delete all children recursively
            List<SppegInformation> children = repository.findByParentId(node.getDeptId(), node.getId());
            for (SppegInformation child : children) {
                SppegInformationId childId = new SppegInformationId(
                    child.getId(), 
                    child.getParentId(), 
                    child.getDeptId()
                );
                deleteNodeAndChildren(childId); // Recursive delete
            }
            
            // Delete the node itself
            repository.deleteById(id);
        }
    }

    // NEW: Check if node exists
    public boolean nodeExists(SppegInformationId id) {
        return repository.existsById(id);
    }

    // UPDATE: Update node name only (removed description update)
    public SppegInformation updateNodeName(SppegInformationId id, String newName) {
        Optional<SppegInformation> nodeOpt = repository.findById(id);
        if (nodeOpt.isPresent()) {
            SppegInformation node = nodeOpt.get();
            if (newName != null && !newName.trim().isEmpty()) {
                node.setName(newName);
                return repository.save(node);
            }
        }
        return null;
    }
}
