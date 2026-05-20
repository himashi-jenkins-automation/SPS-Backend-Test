package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.SpestcndDTO;
import com.example.SPSProjectBackend.model.Spestcnd;
import com.example.SPSProjectBackend.service.SpestcndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*", "http://10.128.1.59:8096"}, allowCredentials = "true")
// @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/spestcnd")
public class SpestcndController {

    @Autowired
    private SpestcndService spestcndService;

    @PostMapping("/save")
    public ResponseEntity<Spestcnd> saveBasicInfo(@RequestBody SpestcndDTO dto) {
        Spestcnd saved = spestcndService.saveBasicInfo(dto);
        return ResponseEntity.ok(saved);
    }
}
