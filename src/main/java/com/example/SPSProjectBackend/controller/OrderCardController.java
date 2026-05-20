package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.JobTypeCountDTO;
import com.example.SPSProjectBackend.dto.OrderCardDTO;
import com.example.SPSProjectBackend.service.OrderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-cards")
public class OrderCardController {

    @Autowired
    private OrderCardService orderCardService;

//    @GetMapping("/next-order-card-no")
//    public ResponseEntity<?> getNextOrderCardNo(@RequestParam String projectNo) {
//        try {
//            String nextOrderCardNo = orderCardService.generateNextOrderCardNo(projectNo);
//            return ResponseEntity.ok(Collections.singletonMap("nextOrderCardNo", nextOrderCardNo));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Collections.singletonMap("error", "Failed to generate order card number: " + e.getMessage()));
//        }
//    }

//    this is new controller method to get ordercard new strcture using
//    deptId
//    CHANGED: Simplified to only accept deptId parameter
    @GetMapping("/next-order-card-no")
    public ResponseEntity<?> getNextOrderCardNo(@RequestParam String deptId) {
        try {
            // CHANGED: Directly use deptId for new format
            String nextOrderCardNo = orderCardService.generateNextOrderCardNo(deptId);
            return ResponseEntity.ok(Collections.singletonMap("nextOrderCardNo", nextOrderCardNo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to generate order card number: " + e.getMessage()));
        }
    }



    @GetMapping
    public List<OrderCardDTO> getAllOrderCards() {
        return orderCardService.getAllOrderCards();
    }

    @GetMapping("/by-project")
    public ResponseEntity<?> getOrderCardsByProject(@RequestParam String projectNo) {
        try {
            List<OrderCardDTO> orderCards = orderCardService.getOrderCardsByProject(projectNo);
            return ResponseEntity.ok(orderCards);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to fetch order cards: " + e.getMessage()));
        }
    }

    @GetMapping("/by-project-dept")
    public ResponseEntity<?> getOrderCardByProjectAndDept(
            @RequestParam String projectNo,
            @RequestParam String deptId) {
        try {
            Optional<OrderCardDTO> orderCard = orderCardService.getOrderCardByProjectAndDept(projectNo, deptId);
            if (orderCard.isPresent()) {
                return ResponseEntity.ok(orderCard.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message", "Order card not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to fetch order card: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrderCard(@RequestBody OrderCardDTO orderCardDTO) {
        try {
            OrderCardDTO created = orderCardService.createOrderCard(orderCardDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to create order card: " + e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateOrderCard(
            @RequestParam String projectNo,
            @RequestParam String deptId,
            @RequestBody OrderCardDTO orderCardDTO) {
        try {
            OrderCardDTO updated = orderCardService.updateOrderCard(projectNo, deptId, orderCardDTO);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to update order card: " + e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOrderCard(
            @RequestParam String projectNo,
            @RequestParam String deptId) {
        try {
            orderCardService.deleteOrderCard(projectNo, deptId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to delete order card: " + e.getMessage()));
        }
    }

    @GetMapping("/job-type-counts")
    public ResponseEntity<?> getJobTypeCounts() {
        try {
            List<JobTypeCountDTO> jobTypeCounts = orderCardService.getJobTypeCounts();
            return ResponseEntity.ok(jobTypeCounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to fetch job type counts: " + e.getMessage()));
        }
    }
}