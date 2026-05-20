package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.LoginResponseDTO;
import com.example.SPSProjectBackend.dto.MainMenuDTO;
import com.example.SPSProjectBackend.dto.TaskDTO;
import com.example.SPSProjectBackend.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
// import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/info")
    public LoginResponseDTO getLoginInfo(@RequestParam String userId) {
        return loginService.getLoginInfo(userId);
    }

    @GetMapping("/main-menus")
    public List<MainMenuDTO> getMainMenus(@RequestParam String userId) {
        return loginService.getMainMenusForUser(userId);
    }

    @GetMapping("/menu-tasks")
    public List<TaskDTO> getTasksForMenu(@RequestParam String userId, @RequestParam String menuCode) {
        return loginService.getTasksForMenuAndUser(userId, menuCode);
    }

    // Add this endpoint to LoginController.java
    @GetMapping("/dept-type")

    public ResponseEntity<?> getDeptTypeByDeptId(@RequestParam String deptId) {
        if (deptId == null || deptId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Department ID is required");
        }

        String deptType = loginService.getDeptTypeByDeptId(deptId);

        if (deptType == null) {
            return ResponseEntity.ok(Collections.singletonMap("message", "No department found for the given ID"));
        }

        return ResponseEntity.ok(Collections.singletonMap("deptType", deptType));
    }

}