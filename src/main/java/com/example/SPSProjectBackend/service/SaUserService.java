package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.SaUser;
import com.example.SPSProjectBackend.repository.SaUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.common.Encryption;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class SaUserService {

    private static final Logger logger = LoggerFactory.getLogger(SaUserService.class);
    private final SaUserRepository saUserRepository;
    private final Encryption encryption;

    @Autowired
    public SaUserService(SaUserRepository saUserRepository, Encryption encryption) {
        this.saUserRepository = saUserRepository;
        this.encryption = encryption;
    }

    @Transactional
    public SaUser login(String userId, String password) {
        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException("User ID and password must not be empty");
        }

        String normalizedUserId = userId.trim().toUpperCase();
        logger.info("Attempting login for userId: {}", normalizedUserId); // FIXED: Log normalized userId instead of
                                                                          // userOptional

        Optional<SaUser> userOptional = saUserRepository.findByUserId(normalizedUserId);
        logger.debug("User optional: {}", userOptional); // Existing debug log

        if (userOptional.isPresent()) {
            SaUser user = userOptional.get();

            if (user.getStatus() != null && user.getStatus() == 1) {
                throw new RuntimeException("User is inactive");
            }

            // Check expiry date
            if (user.getExpiryDate() != null) {
                try {
                    LocalDate expiryDate = user.getExpiryDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    LocalDate currentDate = LocalDate.now();

                    if (currentDate.isAfter(expiryDate)) {
                        throw new RuntimeException("User account has expired on " + expiryDate);
                    }
                } catch (Exception dateException) {
                    logger.warn("Warning: Could not parse expiry date for user {}, allowing login. Error: {}",
                            normalizedUserId, dateException.getMessage()); // CHANGED: Log warning and allow login
                                                                           // instead of throwing
                    logger.debug("Raw expiry date: {}", user.getExpiryDate());
                }
            }

            try {
                boolean isValid = encryption.validateLogin(normalizedUserId, password, user.getPassword()); // CHANGED:
                                                                                                            // Pass
                                                                                                            // normalizedUserId
                                                                                                            // for
                                                                                                            // consistency
                if (!isValid) {
                    throw new RuntimeException("Invalid password");
                }
            } catch (Exception e) {
                logger.error("Error validating password for user {}: {}", normalizedUserId, e.getMessage()); // ADDED:
                                                                                                             // More
                                                                                                             // logging
                                                                                                             // for
                                                                                                             // errors
                throw new RuntimeException("Error validating password: " + e.getMessage());
            }

            logger.info("Successful login for user: {}", normalizedUserId); // ADDED: Log successful login
            return user;

        } else {
            throw new RuntimeException("Invalid user ID");
        }
    }

    public SaUser getUserByUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new RuntimeException("User ID is required");
        }

        return saUserRepository.findByUserId(userId.trim().toUpperCase())
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
    }

    // TODO: Uncomment when location columns are added to SAUSERM table
    /*
    @Transactional
    public SaUser updateUserLocation(String userId, BigDecimal latitude, BigDecimal longitude, BigDecimal accuracy) {
        SaUser user = getUserByUserId(userId);

        user.setLatitude(latitude);
        user.setLongitude(longitude);
        user.setLocationAccuracy(accuracy);
        user.setLocationUpdatedAt(new Date());

        return saUserRepository.save(user);
    }
    */

    // Add this method to SaUserService.java
    public SaUser getUserByEpfNo(String epfno) {
        if (epfno == null || epfno.trim().isEmpty()) {
            throw new RuntimeException("EPF number must not be empty");
        }

        String normalizedEpfno = epfno.trim().toUpperCase();
        logger.info("Fetching user by EPF: {}", normalizedEpfno);

        Optional<SaUser> userOpt = saUserRepository.findByEpfNo(normalizedEpfno);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with EPF number: " + epfno);
        }

        SaUser user = userOpt.get();

        // Check if user is inactive
        if (user.getStatus() != null && user.getStatus() == 1) {
            throw new RuntimeException("User account is inactive");
        }

        // Check expiry date (if present)
        if (user.getExpiryDate() != null) {
            try {
                LocalDate expiryDate = user.getExpiryDate().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                if (LocalDate.now().isAfter(expiryDate)) {
                    throw new RuntimeException("User account has expired on " + expiryDate);
                }
            } catch (Exception e) {
                logger.warn("Could not parse expiry date for EPF {}: {}", normalizedEpfno, e.getMessage());
                // Allow login if date parsing fails (as in normal login)
            }
        }

        return user;
    }

    public List<String> getUserIdsByDeptPrefix(String deptId) {

        if (deptId == null || deptId.isEmpty()) {
            throw new RuntimeException("DeptId cannot be empty");
        }

        // Extract prefix (424 from 424.00)
        String prefix = deptId.split("\\.")[0];

        logger.info("Fetching users for prefix: {}", prefix);

        return saUserRepository.findUserIdsByRptUserPrefix(prefix);
    }
}
