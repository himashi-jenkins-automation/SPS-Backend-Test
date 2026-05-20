package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.AreaCodeDTO;
import com.example.SPSProjectBackend.service.AreaCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/area-code")
public class AreaCodeController {

    @Autowired
    private AreaCodeService areaCodeService;

    @GetMapping
    public ResponseEntity<?> getAreaCodeByApplicationId(@RequestParam String applicationId) {
        try {
            System.out.println("🔍 Received request for applicationId: " + applicationId);

            if (applicationId == null || applicationId.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("error", "Application ID is required"));
            }

            AreaCodeDTO areaCodeDTO = areaCodeService.getAreaCodeByApplicationId(applicationId.trim());

            if (areaCodeDTO == null) {
                System.out.println("❌ No area code found for applicationId: " + applicationId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message",
                                "No area code found for application ID: " + applicationId));
            }

            System.out.println("✅ Found area code: " + areaCodeDTO.getAreaCode());
            return ResponseEntity.ok(areaCodeDTO);

        } catch (Exception e) {
            System.err.println("💥 Error processing applicationId: " + applicationId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error",
                            "Internal server error while processing application ID"));
        }
    }
}