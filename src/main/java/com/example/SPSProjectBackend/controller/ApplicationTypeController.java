package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.ApplicationTypeDto;
import com.example.SPSProjectBackend.service.ApplicationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*", "http://10.128.1.59:8096"})
// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/application")
public class ApplicationTypeController {

    @Autowired
    private ApplicationTypeService service;

    @GetMapping("/type")
    public List<ApplicationTypeDto> getAllTypes() {
        return service.getAllApplicationTypes();
    }
}
