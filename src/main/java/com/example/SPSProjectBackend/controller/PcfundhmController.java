package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.Pcfundhm;
import com.example.SPSProjectBackend.service.PcfundhmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funds")
//@CrossOrigin(origins = "3000") // Allow requests from any frontend origin
public class PcfundhmController {

    @Autowired
    private PcfundhmService service;

    // Get full fund details
    @GetMapping
    public List<Pcfundhm> getAllFunds() {
        return service.getAllFunds();
    }

    // Get only fund sources
    @GetMapping("/sources")
    public List<String> getAllFundSources() {
        return service.getAllFundSources();
    }
}
