package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.MtrTypeDto;
import com.example.SPSProjectBackend.repository.MtrsetDfnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MtrsetDfnService {

    @Autowired
    private MtrsetDfnRepository mtrsetDfnRepository;

    public List<MtrTypeDto> getMtrTypesByCategoryAndSetType(String cusCat, Short mtrsetType) {
        return mtrsetDfnRepository.findByCusCatAndMtrsetTypeOrderByMtrOrder(cusCat, mtrsetType);
    }
}