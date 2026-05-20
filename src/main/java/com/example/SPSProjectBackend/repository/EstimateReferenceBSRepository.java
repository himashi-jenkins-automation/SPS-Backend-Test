// EstimateReferenceBSRepository.java
package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.EstimateReferenceBS;
import com.example.SPSProjectBackend.model.EstimateReferenceBSId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimateReferenceBSRepository extends JpaRepository<EstimateReferenceBS, EstimateReferenceBSId> {
    // You can add custom query methods here if needed
}