package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Inwrhmap;
import com.example.SPSProjectBackend.model.InwrhmapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InwrhmapRepository extends JpaRepository<Inwrhmap, InwrhmapId> {
    List<Inwrhmap> findByDeptId(String deptId);
}
