package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.Pegschdmt;
import com.example.SPSProjectBackend.model.PegschdmtId;
import com.example.SPSProjectBackend.model.Pcestdtt;
import com.example.SPSProjectBackend.model.SpPeggingDmt;
import com.example.SPSProjectBackend.repository.PegschdmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PegschdmtService {

    // Dependencies on other services are added
    private final PegschdmtRepository repository;
    private final PcestdttService pcestdttService;
    private final SpPeggingDmtService spPeggingDmtService;

    /**
     * The constructor is updated to accept the new service dependencies.
     * Spring will automatically provide them (Dependency Injection).
     */
    @Autowired
    public PegschdmtService(PegschdmtRepository repository, PcestdttService pcestdttService,
            SpPeggingDmtService spPeggingDmtService) {
        this.repository = repository;
        this.pcestdttService = pcestdttService;
        this.spPeggingDmtService = spPeggingDmtService;
    }

    public List<Pegschdmt> getAll() {
        return repository.findAll();
    }

    public Optional<Pegschdmt> getById(PegschdmtId id) {
        return repository.findById(id);
    }

    /**
     * This is the updated save method with the new business logic.
     * It is marked as @Transactional to ensure data integrity.
     */
    @Transactional
    public Pegschdmt save(Pegschdmt data) {
        Pegschdmt savedPegschdmt = repository.save(data);

        String estimateNo = savedPegschdmt.getEstimateNo();
        String nodeId = savedPegschdmt.getNodeId();
        String deptId = savedPegschdmt.getDeptId();
        double parentItemQuantity = savedPegschdmt.getNoOfItem();

        List<SpPeggingDmt> materialTemplates = spPeggingDmtService.findByLineSectionTypeId(nodeId);

        if (materialTemplates == null || materialTemplates.isEmpty()) {
            System.out.println("No material templates found for nodeId: " + nodeId);
            return savedPegschdmt; // Still return successfully
        }

        for (SpPeggingDmt template : materialTemplates) {
            try {
                Pcestdtt newMaterialRecord = new Pcestdtt();
                newMaterialRecord.setEstimateNo(estimateNo);
                newMaterialRecord.setResCd(template.getId() != null ? template.getId().getResCd() : "UNKNOWN");
                newMaterialRecord.setDeptId(deptId);
                newMaterialRecord.setRevNo(BigDecimal.ONE);
                newMaterialRecord.setUom(template.getUom());
                newMaterialRecord.setResType(template.getResType());

                BigDecimal quantityPerUnit = template.getEstimateQty() != null ? template.getEstimateQty()
                        : BigDecimal.ZERO;
                BigDecimal totalQuantity = quantityPerUnit.multiply(BigDecimal.valueOf(parentItemQuantity));
                newMaterialRecord.setEstimateQty(totalQuantity);

                BigDecimal price = template.getUnitPrice() != null ? BigDecimal.valueOf(template.getUnitPrice())
                        : BigDecimal.ZERO;
                newMaterialRecord.setUnitPrice(price);

                pcestdttService.save(newMaterialRecord);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to save material for nodeId: " + nodeId);
            }
        }

        return savedPegschdmt;
    }

    public void delete(PegschdmtId id) {
        // Business logic to delete associated Pcestdtt records could be added here
        // later if needed.
        repository.deleteById(id);
    }
}