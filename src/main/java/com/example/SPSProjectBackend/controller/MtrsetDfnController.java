package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.MtrTypeDto;
import com.example.SPSProjectBackend.service.MtrsetDfnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mtrset")
public class MtrsetDfnController {

    @Autowired
    private MtrsetDfnService mtrsetDfnService;

    @GetMapping("/mtr-types")
    public ResponseEntity<List<MtrTypeDto>> getMtrTypes(
            @RequestParam String cusCat,
            @RequestParam Short mtrsetType) {

        List<MtrTypeDto> mtrTypes = mtrsetDfnService.getMtrTypesByCategoryAndSetType(cusCat, mtrsetType);
        return ResponseEntity.ok(mtrTypes);
    }
}