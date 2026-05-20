package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.ApplicantDTO;
// import com.example.SPSProjectBackend.repository.ApplicantRepository;
// import com.example.SPSProjectBackend.model.Applicant;
// import com.example.SPSProjectBackend.service.ApplicantService;
// import com.example.SPSProjectBackend.service.SaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
// import com.example.SPSProjectBackend.dto.ApplicantDTO;
import com.example.SPSProjectBackend.service.ApplicantService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;
@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;
    private static final Logger logger = LoggerFactory.getLogger(ApplicantController.class);

    @GetMapping
    public List<ApplicantDTO> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchApplicantByIdNo(@RequestParam String idNo) {
        // Optional<ApplicantDTO> applicantDTO = applicantService.getApplicantById(idNo);
        //UPDATED: for function name
        Optional<ApplicantDTO> applicantDTO = applicantService.getApplicantByIdNo(idNo);
        if (applicantDTO.isPresent()) {
            return ResponseEntity.ok(applicantDTO.get());
        } else {
            return ResponseEntity.status(404)
                    .body("Applicant with ID " + idNo + " not found.");
        }
    }

    @GetMapping("/test")
    public String test() {
        try {
            System.out.println("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @PatchMapping("/{idNo}")
    public ResponseEntity<ApplicantDTO> updateApplicant(@PathVariable String idNo,
            @RequestBody ApplicantDTO updatedApplicantDTO) {
        try {
            ApplicantDTO updated = applicantService.updateApplicant(idNo, updatedApplicantDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Return 404 if not found
        }
    }

    @PostMapping("/save")
    public ApplicantDTO createApplicant(@RequestBody ApplicantDTO applicantDTO) {

        return applicantService.saveApplicant(applicantDTO);
    }

    @DeleteMapping("/{idNo}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable String idNo) {
        if (applicantService.getApplicantById(idNo).isPresent()) {
            applicantService.deleteApplicant(idNo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // this is for testing the commission applicant
    // this is old endpoint for by get estimate number for applicant
    // @GetMapping("/by-estimate")
    // public ResponseEntity<List<ApplicantDTO>>
    // getApplicantsByEstimateNo(@RequestParam String estimateNo) {
    // List<ApplicantDTO> applicants =
    // applicantService.getApplicantsByEstimateNo(estimateNo);
    // if (applicants.isEmpty()) {
    // return
    // ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    // }
    // return ResponseEntity.ok(applicants);
    // }

    // UPDATED: Better error handling for by-estimate endpoint
    @GetMapping("/by-estimate")
    public ResponseEntity<?> getApplicantsByEstimateNo(@RequestParam String estimateNo) {
        try {
            System.out.println("🔍 Received request for estimateNo: " + estimateNo);
            logger.info("Received request for estimateNo: {}", estimateNo); // Use {} for params
            logger.debug("Received request for estimateNo: {}", estimateNo); // Or logger.error for issues

            if (estimateNo == null || estimateNo.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("error", "Estimate number is required"));
            }

            List<ApplicantDTO> applicants = applicantService.getApplicantsByEstimateNo(estimateNo.trim());

            if (applicants.isEmpty()) {
                System.out.println("❌ No applicants found for estimate: " + estimateNo);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message",
                                "No applicant found for estimate number: " + estimateNo));
            }

            System.out.println("✅ Found " + applicants.size() + " applicant(s)");
            return ResponseEntity.ok(applicants);

        } catch (Exception e) {
            System.err.println("💥 Error processing estimateNo: " + estimateNo);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error",
                            "Internal server error while processing estimate number"));
        }
    }

//    @GetMapping("/{idNo}")
//    public ResponseEntity<ApplicantDTO> getApplicantById(@PathVariable String idNo) {
//        Optional<ApplicantDTO> applicant = applicantService.getApplicantById(idNo);
//        return applicant.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/{idNo}")
    public ResponseEntity<ApplicantDTO> getApplicantById(@PathVariable String idNo) {
        Optional<ApplicantDTO> applicant = applicantService.getApplicantByIdNo(idNo);
        return applicant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @GetMapping("/by-estimate/{estimateNo}")
    // public ResponseEntity<List<ApplicantDTO>>
    // getApplicantsByEstimateNo(@PathVariable String estimateNo) {
    // List<ApplicantDTO> applicants =
    // applicantService.getApplicantsByEstimateNo(estimateNo);
    // return ResponseEntity.ok(applicants);
    // }
}
