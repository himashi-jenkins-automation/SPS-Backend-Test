package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.ApplicationDTO;
import com.example.SPSProjectBackend.dto.ApplicationDetailsDTO;
// import com.example.SPSProjectBackend.dto.ApplicationTypeDto;
// import com.example.SPSProjectBackend.dto.CostCenterJobStatusDto;
import com.example.SPSProjectBackend.model.ApplicationModel;
// import com.example.SPSProjectBackend.repository.ApplicationRepository;
// import com.example.SPSProjectBackend.repository.PcesthmtRepository;
import com.example.SPSProjectBackend.service.ApplicationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
// import com.example.SPSProjectBackend.dto.ApplicationDetailsDTO;

import java.util.*;

@RestController
// @CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true")
@RequestMapping("/api/application")
public class ApplicationController {

    // // This one for application details in commission page
    // @Autowired
    // private PcesthmtRepository pcesthmtRepository;

    // @Autowired
    // private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/api/v1/application-details")
    public List<Object[]> getApplicationDetails(@RequestParam("applicationNo") String applicationNo) {
        return applicationService.getApplicationDetailsByApplicationNo(applicationNo);
    }

    @GetMapping("/all")
    public List<String> getAllApplicationNos() {
        return applicationService.getAllApplicationNos();
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateApplicationNo(@RequestParam String number) {
        boolean exists = applicationService.validateApplicationNo(number);
        return ResponseEntity.ok(exists);
    }

//    @PostMapping("/save")
//    public ResponseEntity<ApplicationModel> saveApplication(@RequestBody ApplicationDTO applicationDTO,
//            HttpSession session) {
//
//        // Retrieve user details from the session
//        String sessionUsername = (String) session.getAttribute("email");
//        System.out.println("Session username: " + sessionUsername);
//        if (sessionUsername == null) {
//            return ResponseEntity.status(401).build(); // Unauthorized if session expired
//        }
//
//        ApplicationModel savedApplication = applicationService.saveApplication(applicationDTO, sessionUsername);
//        return ResponseEntity.ok(savedApplication);
//    }

    @PostMapping("/save")
    public ResponseEntity<ApplicationModel> saveApplication(@RequestBody ApplicationDTO applicationDTO) {
        ApplicationModel savedApplication = applicationService.saveApplication(applicationDTO);
        return ResponseEntity.ok(savedApplication);
    }

    // @PutMapping("/update")
    // public ResponseEntity<?> updateApplication(
    // @RequestParam String applicationId,
    // @RequestBody ApplicationDTO applicationDTO,
    // HttpSession session) {
    //
    // String sessionUsername = (String) session.getAttribute("username");
    // if (sessionUsername == null) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not
    // logged in.");
    // }
    //
    // try {
    // ApplicationModel updatedApplication =
    // applicationService.updateApplication(applicationId, applicationDTO,
    // sessionUsername);
    // return ResponseEntity.ok(updatedApplication);
    // } catch (EntityNotFoundException e) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    // }
    // }

//    @PatchMapping("/update")
//    public ResponseEntity<?> updateApplication(
//            @RequestParam String applicationId,
//            @RequestBody ApplicationDTO applicationDTO,
//            HttpSession session) {
//
//        String sessionUsername = (String) session.getAttribute("email");
//        System.out.println("Session username: " + sessionUsername);
//        if (sessionUsername == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in.");
//        }
//
//        try {
//            ApplicationModel updatedApplication = applicationService.updateApplication(applicationId, applicationDTO,
//                    sessionUsername);
//            return ResponseEntity.ok(updatedApplication);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }


    @PatchMapping("/update")
    public ResponseEntity<?> updateApplication(
            @RequestParam String applicationId,
            @RequestBody ApplicationDTO applicationDTO) {
        try {
            ApplicationModel updatedApplication = applicationService.updateApplication(applicationId, applicationDTO);
            return ResponseEntity.ok(updatedApplication);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getApplicationById(@RequestParam String applicationId) {
        Optional<ApplicationModel> application = applicationService.getApplicationById(applicationId);
        return application.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{deptId}")
    public List<Map<String, Object>> getStatusSummaryByDept(@PathVariable String deptId) {
        return applicationService.getStatusCounts(deptId);
    }

    // This one for application details in commission page
    // ApplicationController.java

    // ApplicationController.java
    @GetMapping("/details")
    public ResponseEntity<List<ApplicationDetailsDTO>> getApplicationDetailsCommission(
            @RequestParam("estimateNo") String estimateNo) {
        try {
            System.out.println("Fetching application details for estimateNo: " + estimateNo);
            List<ApplicationDetailsDTO> details = applicationService.getApplicationDetailsByEstimate(estimateNo);
            System.out.println("Found " + details.size() + " application details");
            if (details.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.emptyList());
            }
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            System.err.println(
                    "Error fetching application details for estimateNo: " + estimateNo + ", Error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @GetMapping("/by-estimate/{estimateNo}")
    public ResponseEntity<List<ApplicationModel>> getApplicationsForEstimate(
            @PathVariable String estimateNo) {
        List<ApplicationModel> applications = applicationService.getApplicationsForEstimate(estimateNo);
        return ResponseEntity.ok(applications);
    }

    //this endpoint related to frontend application
//    tech detail document upload submission
    //currently this is not implemnetd
    @PostMapping("/upload-documents")
    public ResponseEntity<List<String>> uploadDocuments(@RequestParam("files") MultipartFile[] files, @RequestParam("applicationId") String applicationId) {
        // Implement file saving logic, save to a directory, return URLs
        // For example:
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            // Save file to disk or cloud, generate URL
            String url = "https://your-storage/" + applicationId + "/" + file.getOriginalFilename();
            urls.add(url);
            // Actual save code here
        }
        return ResponseEntity.ok(urls);
    }
}