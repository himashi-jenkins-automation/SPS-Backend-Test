// Add this new repository: WiringLandDetailConRepository.java
package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.WiringLandDetailCon;
import com.example.SPSProjectBackend.model.WiringLandDetailConId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WiringLandDetailConRepository extends JpaRepository<WiringLandDetailCon, WiringLandDetailConId> {
}