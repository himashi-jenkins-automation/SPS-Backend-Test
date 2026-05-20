package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.PivDetailDto;
import com.example.SPSProjectBackend.service.PivDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/piv-details")
public class PivDetailController {

    private final PivDetailService pivDetailService;

    @Autowired
    public PivDetailController(PivDetailService pivDetailService) {
        this.pivDetailService = pivDetailService;
    }

    // @GetMapping("/by-estimate/{estimateNo}")
    // public ResponseEntity<List<PivDetailDto>>
    // getPivDetailsForEstimate(@PathVariable String estimateNo) {
    // return
    // ResponseEntity.ok(pivDetailService.getPivDetailsForEstimate(estimateNo));
    // }
    @GetMapping("/by-estimate")
    public ResponseEntity<List<PivDetailDto>> getPivDetailsForEstimate(@RequestParam String estimateNo) {
        return ResponseEntity.ok(pivDetailService.getPivDetailsForEstimate(estimateNo));
    }
}
