//package com.example.SPSProjectBackend.controller;
//
//import com.example.SPSProjectBackend.dto.LoginRequest;
//import com.example.SPSProjectBackend.model.SaUser;
//import com.example.SPSProjectBackend.service.SaUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
////@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@RequestMapping("/api/v1/auth")
//public class SaUserController {
//
//    private final SaUserService saUserService;
//
//
//    @Autowired
//    public SaUserController(SaUserService saUserService) {
//        this.saUserService = saUserService;
//    }
//
////    @PostMapping("/login")
////    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
////        try {
////            SaUser user = saUserService.login(loginRequest.getUserId(), loginRequest.getPassword());
////
////            Map<String, Object> response = new HashMap<>();
////            response.put("message", "Login successful");
////            response.put("userId", user.getUserId());
////            response.put("userName", user.getUserName());
////            response.put("userLevel", user.getUserLevel());
////
////            return ResponseEntity.ok(response);
////        } catch (RuntimeException e) {
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
////        }
////    }
//
//    //@PostMapping("/login")
////    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
////        String userId = loginRequest.getUserId();
////        String password = loginRequest.getPassword();
////
////        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "User ID and password must not be empty"));
////        }
////
////        try {
////            SaUser user = saUserService.login(userId, password);
////
////            // Set session attributes
////            request.getSession().setAttribute("loggedUser", user.getUserId().toUpperCase());
////            request.getSession().setAttribute("loggedUserRole", user.getUserLevel());
////
////            Map<String, Object> response = new HashMap<>();
////            response.put("message", "Login successful");
////            response.put("userId", user.getUserId());
////            response.put("userName", user.getUserName());
////            response.put("userLevel", user.getUserLevel());
////
////            return ResponseEntity.ok(response);
////        } catch (RuntimeException e) {
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
////        }
////    }
//
////
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
//        String userId = loginRequest.getUserId();
//        String password = loginRequest.getPassword();
//
//        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "User ID and password must not be empty"));
//        }
//
//        try {
//            SaUser user = saUserService.login(userId, password);
//
//            request.getSession().setAttribute("loggedUser", user.getUserId().toUpperCase());
//            request.getSession().setAttribute("loggedUserRole", user.getUserLevel());
//            request.getSession().setAttribute("loggedusercostcenter", user.getRptUser());
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "Login successful");
//            response.put("userId", user.getUserId());
//            response.put("userName", user.getUserName());
//            response.put("userLevel", user.getUserLevel());
//            response.put("costcenter", user.getRptUser());
//
//            return ResponseEntity.ok(response);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
//        }
//    }
//
//
//}

////NEW UPDATED CODES
//package com.example.SPSProjectBackend.controller;
//
//import com.example.SPSProjectBackend.dto.LoginRequest;
//import com.example.SPSProjectBackend.model.SaUser;
//import com.example.SPSProjectBackend.service.SaUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class SaUserController {
//
//    private final SaUserService saUserService;
//
//    @Autowired
//    public SaUserController(SaUserService saUserService) {
//        this.saUserService = saUserService;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
//        String userId = loginRequest.getUserId();
//        String password = loginRequest.getPassword();
//
//        // CHANGED: Added more detailed validation
//        if (userId == null || userId.trim().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "User ID must not be empty"));
//        }
//        if (password == null || password.trim().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Password must not be empty"));
//        }
//
//        try {
//            SaUser user = saUserService.login(userId, password);
//
//            // Set session attributes
//            request.getSession().setAttribute("loggedUser", user.getUserId().toUpperCase());
//            request.getSession().setAttribute("loggedUserRole", user.getUserLevel());
//            request.getSession().setAttribute("loggedusercostcenter", user.getRptUser());
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "Login successful");
//            response.put("userId", user.getUserId());
//            response.put("userName", user.getUserName());
//            response.put("userLevel", user.getUserLevel());
//            response.put("costcenter", user.getRptUser());
//
//            return ResponseEntity.ok(response);
//        } catch (RuntimeException e) {
//            // CHANGED: Return more specific error messages
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
//        } catch (Exception e) {
//            // CHANGED: Added general exception handling
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal server error during login"));
//        }
//    }
//}

//NEW CODES 2
package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.LoginRequest;
import com.example.SPSProjectBackend.dto.UserLocationUpdateRequest;
import com.example.SPSProjectBackend.model.SaUser;
import com.example.SPSProjectBackend.service.SaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/v1/auth")
public class SaUserController {

    private static final Logger logger = LoggerFactory.getLogger(SaUserController.class);

    private final SaUserService saUserService;

    @Autowired
    public SaUserController(SaUserService saUserService) {
        this.saUserService = saUserService;
    }

    @Autowired
    private RestTemplate restTemplate;

    // @PostMapping("/login")
    // public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest
    // loginRequest) {
    // try {
    // SaUser user = saUserService.login(loginRequest.getUserId(),
    // loginRequest.getPassword());
    //
    // Map<String, Object> response = new HashMap<>();
    // response.put("message", "Login successful");
    // response.put("userId", user.getUserId());
    // response.put("userName", user.getUserName());
    // response.put("userLevel", user.getUserLevel());
    //
    // return ResponseEntity.ok(response);
    // } catch (RuntimeException e) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error",
    // e.getMessage()));
    // }
    // }

    // @PostMapping("/login")
    // public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest
    // loginRequest, HttpServletRequest request) {
    // String userId = loginRequest.getUserId();
    // String password = loginRequest.getPassword();
    //
    // if (userId == null || userId.isEmpty() || password == null ||
    // password.isEmpty()) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
    // "User ID and password must not be empty"));
    // }
    //
    // try {
    // SaUser user = saUserService.login(userId, password);
    //
    // // Set session attributes
    // request.getSession().setAttribute("loggedUser",
    // user.getUserId().toUpperCase());
    // request.getSession().setAttribute("loggedUserRole", user.getUserLevel());
    //
    // Map<String, Object> response = new HashMap<>();
    // response.put("message", "Login successful");
    // response.put("userId", user.getUserId());
    // response.put("userName", user.getUserName());
    // response.put("userLevel", user.getUserLevel());
    //
    // return ResponseEntity.ok(response);
    // } catch (RuntimeException e) {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error",
    // e.getMessage()));
    // }
    // }

    //


    //this is previous current working login without AD login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest,
            HttpServletRequest request) {
        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();

        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "User ID and password must not be empty"));
        }

        try {
            SaUser user = saUserService.login(userId, password);

            request.getSession().setAttribute("loggedUser", user.getUserId().toUpperCase());
            request.getSession().setAttribute("loggedUserRole", user.getUserLevel());
            request.getSession().setAttribute("loggedusercostcenter", user.getRptUser());


            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("userId", user.getUserId());
            response.put("userName", user.getUserName());
            response.put("userLevel", user.getUserLevel());
            response.put("costcenter", user.getRptUser());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            logger.error("Login error for user {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Login internal error: " + e.getMessage()));
        }
    }

    // TODO: Uncomment when location columns are added to SAUSERM table
    /*
    @PutMapping("/location")
    public ResponseEntity<Map<String, Object>> updateLocation(@RequestBody UserLocationUpdateRequest request) {
        if (request == null || request.getUserId() == null || request.getUserId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "User ID is required"));
        }
        if (request.getLatitude() == null || request.getLongitude() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Latitude and longitude are required"));
        }

        try {
            SaUser updated = saUserService.updateUserLocation(
                    request.getUserId(),
                    request.getLatitude(),
                    request.getLongitude(),
                    request.getAccuracy());

            return ResponseEntity.ok(Map.of(
                    "message", "Location updated",
                    "userId", updated.getUserId(),
                    "latitude", updated.getLatitude(),
                    "longitude", updated.getLongitude(),
                    "accuracy", updated.getLocationAccuracy(),
                    "updatedAt", updated.getLocationUpdatedAt()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            logger.error("Failed to update location for {}: {}", request.getUserId(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unable to update location"));
        }
    }
    */

    //this is old loginwith ad method but not compare with epf numbers

//    @PostMapping("/loginWthAD")
//    public ResponseEntity<Map<String, Object>> loginWIthAD(@RequestBody LoginRequest loginRequest,
//                                                     HttpServletRequest request) {
//        String userId = loginRequest.getUserId();
//        String password = loginRequest.getPassword();
//
//        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(Map.of("error", "User ID and password must not be empty"));
//        }
//
//        try {
//            // Step 1: Check if user exists in LDAP
//            String checkUrl = "http://smartceb.ceb:81/SMART_API/api/UserManagement/IsLDAPUserAvailable?user_name=" + userId;
//            ResponseEntity<Map> checkResponse = restTemplate.getForEntity(checkUrl, Map.class);
//
//            Map<String, Object> checkBody = checkResponse.getBody();
//            if (checkBody == null || !Boolean.TRUE.equals(checkBody.get("IsSuccess"))) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body(Map.of("error", "User not found or invalid"));
//            }
//
//            // Step 2: Validate credentials via AD login
//            String authUrl = "http://smartceb.ceb:81/SMART_API/api/UserManagement/ValidateADLogin";
//            Map<String, String> authRequest = new HashMap<>();
//            authRequest.put("ad_user_name", userId);
//            authRequest.put("ad_password", password);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<Map<String, String>> entity = new HttpEntity<>(authRequest, headers);
//
//            ResponseEntity<Map> authResponse = restTemplate.exchange(
//                    authUrl,
//                    HttpMethod.POST,
//                    entity,
//                    Map.class
//            );
//
//            Map<String, Object> authBody = authResponse.getBody();
//            if (authBody == null || !Boolean.TRUE.equals(authBody.get("isSuccess"))) {
//                String errorMsg = authBody != null && authBody.containsKey("message")
//                        ? (String) authBody.get("message")
//                        : "Invalid credentials";
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body(Map.of("error", errorMsg));
//            }
//
//            // Extract user details from the nested "SmartCEBUser" object
//            Map<String, Object> smartUser = (Map<String, Object>) authBody.get("SmartCEBUser");
//            if (smartUser == null) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(Map.of("error", "Invalid response from authentication server"));
//            }
//
//            // Map fields to match frontend expectations
//            String adUsername = (String) smartUser.get("ad_username");
//            String firstName = (String) smartUser.get("first_name");
//            String lastName = (String) smartUser.get("last_name");
//            String costcenter = (String) smartUser.get("mitfin_cost_center");
//
//            // Construct userName (fallback to ad_username if names missing)
//            String userName = (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
//            userName = userName.trim().isEmpty() ? adUsername : userName;
//
//            // Set userLevel – default to "DEO" (adjust as needed)
//            String userLevel = "DEO";
//
//            // Prepare response for frontend
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "Login successful");
//            response.put("userId", adUsername);
//            response.put("userName", userName);
//            response.put("userLevel", userLevel);
//            response.put("costcenter", costcenter);
//
//            // Optional: Set session attributes (if your app relies on them)
//            request.getSession().setAttribute("loggedUser", adUsername.toUpperCase());
//            request.getSession().setAttribute("loggedUserRole", userLevel);
//            request.getSession().setAttribute("loggedusercostcenter", costcenter);
//
//            return ResponseEntity.ok(response);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("error", "Login service unavailable: " + e.getMessage()));
//        }
//    }


    @PostMapping("/loginWithADNew")  // Fixed method name (optional, keep mapping consistent)
    public ResponseEntity<Map<String, Object>> loginWithAD(@RequestBody LoginRequest loginRequest,
                                                           HttpServletRequest request) {
        String userId = loginRequest.getUserId();  // This is the EPF number entered by user
        String password = loginRequest.getPassword();

        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "User ID and password must not be empty"));
        }

        try {
            // Step 1: Check if user exists in LDAP
            String checkUrl = "http://smartceb.ceb:81/SMART_API/api/UserManagement/IsLDAPUserAvailable?user_name=" + userId;
            ResponseEntity<Map> checkResponse = restTemplate.getForEntity(checkUrl, Map.class);

            Map<String, Object> checkBody = checkResponse.getBody();
            if (checkBody == null || !Boolean.TRUE.equals(checkBody.get("IsSuccess"))) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "User not found or invalid"));
            }

            // Step 2: Validate credentials via AD login
            String authUrl = "http://smartceb.ceb:81/SMART_API/api/UserManagement/ValidateADLogin";
            Map<String, String> authRequest = new HashMap<>();
            authRequest.put("ad_user_name", userId);
            authRequest.put("ad_password", password);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(authRequest, headers);

            ResponseEntity<Map> authResponse = restTemplate.exchange(
                    authUrl,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            Map<String, Object> authBody = authResponse.getBody();
            if (authBody == null || !Boolean.TRUE.equals(authBody.get("isSuccess"))) {
                String errorMsg = authBody != null && authBody.containsKey("message")
                        ? (String) authBody.get("message")
                        : "Invalid credentials";
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", errorMsg));
            }

            // Extract user details from the nested "SmartCEBUser" object
            Map<String, Object> smartUser = (Map<String, Object>) authBody.get("SmartCEBUser");
            if (smartUser == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("error", "Invalid response from authentication server"));
            }

            String adUsername = (String) smartUser.get("ad_username"); // This is the EPF number

            // Step 3: Fetch the local user from SAUSERM table using EPF number
            SaUser localUser;
            try {
                localUser = saUserService.getUserByEpfNo(adUsername);
            } catch (RuntimeException e) {
                // User not found or inactive in local DB
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Local user account not found or inactive: " + e.getMessage()));
            }

            // Step 4: Build response using local user data
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
//            response.put("userId", adUsername);               // Keep entered EPF as userId for frontend
            response.put("userId",localUser.getUserId());
            response.put("userName", localUser.getUserName()); // Use name from DB
            response.put("userLevel", localUser.getUserLevel());
            response.put("costcenter", localUser.getRptUser()); // Use rptUser from DB as costcenter

            // Step 5: Set session attributes using local user's actual userId, role, and costcenter
            request.getSession().setAttribute("loggedUser", localUser.getUserId().toUpperCase());
            request.getSession().setAttribute("loggedUserRole", localUser.getUserLevel());
            request.getSession().setAttribute("loggedusercostcenter", localUser.getRptUser());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Login service unavailable: " + e.getMessage()));
        }
    }

    @GetMapping("/users/dept/{deptId}")
    public ResponseEntity<?> getUsersByDept(@PathVariable String deptId) {
        try {
            List<String> users = saUserService.getUserIdsByDeptPrefix(deptId);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

}