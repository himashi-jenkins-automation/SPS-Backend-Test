//package com.example.SPSProjectBackend;
//
//import util.common.Encryption;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.text.DecimalFormat;
//
//public class PasswordGenerator {
//
//    public static void main(String[] args) {
//        Encryption encryption = new Encryption();
//
//        String userId = "TESTUSER";
//        String password = "test123";
//
//        System.out.println("User ID: " + userId);
//        System.out.println("Password: " + password);
//
//        // Use the fixed calculation method
//        String encryptedPassword = encryption.calculateEncryptedPassword(userId, password);
//
//        System.out.println("Encrypted Password to store in DB: " + encryptedPassword);
//        System.out.println("Password for TESTUSER should be: " + encryptedPassword);
//
//        // Test validation
//        boolean isValid = encryption.validateLogin(userId, password, encryptedPassword);
//        System.out.println("Validation test result: " + isValid);
//    }
//}


//2nd code
//
package com.example.SPSProjectBackend;

import util.common.Encryption;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PasswordGenerator {

    public static void main(String[] args) {
        try {
            Encryption encryption = new Encryption();

            // Test with your original working user first
            String userId = "48020DEO1";
            String password = "test123";

            System.out.println("=== Testing with Original Working User ===");
            System.out.println("User ID: " + userId);
            System.out.println("Password: " + password);

            String encryptedPassword = calculateEncryptedPasswordWithBigDecimal(encryption, userId, password);

            System.out.println("\n=== RESULTS ===");
            System.out.println("Generated Encrypted Password: " + encryptedPassword);
            System.out.println("Original DB Password: 50880181868037732730257157801485403.8469");
            System.out.println("Match: " + encryptedPassword.equals("50880181868037732730257157801485403.8469"));

            // Test validation
            boolean isValid = encryption.validateLogin(userId, password, encryptedPassword);
            System.out.println("Validation test result: " + isValid);

            // Now generate for your new user
            System.out.println("\n\n=== Generating for New User ===");
            userId = "380EE";
            password = "test123";

            System.out.println("User ID: " + userId);
            System.out.println("Password: " + password);

            encryptedPassword = calculateEncryptedPasswordWithBigDecimal(encryption, userId, password);

            System.out.println("\n=== RESULTS ===");
            System.out.println("Encrypted Password to store in DB: " + encryptedPassword);

            // Test validation
            isValid = encryption.validateLogin(userId, password, encryptedPassword);
            System.out.println("Validation test result: " + isValid);

            if (isValid) {
                System.out.println("✅ SUCCESS: The encrypted password is correct!");
            } else {
                System.out.println("❌ FAILED: The encrypted password doesn't match!");
            }

            System.out.println("\n=== SQL UPDATE COMMAND ===");
            System.out.println("RUN THIS SQL IN YOUR DATABASE:");
            System.out.println("UPDATE SAUSERM SET PSWRD = '" + encryptedPassword + "' WHERE USER_ID = '" + userId + "';");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Uses BigDecimal to maintain precision with large numbers
     */
    public static String calculateEncryptedPasswordWithBigDecimal(Encryption encryption, String userId, String password) throws Exception {
        // Get the encrypted strings
        String encryptedPasswordStr = encryption.checkPass(password);
        String encryptedUsernameStr = encryption.checkPass(userId.trim().toUpperCase());

        System.out.println("\n=== ENCRYPTION DETAILS ===");
        System.out.println("Encrypted username string: " + encryptedUsernameStr);
        System.out.println("Encrypted password string: " + encryptedPasswordStr);

        // Use BigDecimal for precision
        BigDecimal bigM_PS = new BigDecimal(encryptedPasswordStr);
        BigDecimal bigM_PU = new BigDecimal(encryptedUsernameStr);

        System.out.println("M_PU (username numeric): " + bigM_PU);
        System.out.println("M_PS (password numeric): " + bigM_PS);

        // Perform the calculation with BigDecimal
        BigDecimal sum = bigM_PU.add(bigM_PS);
        BigDecimal divisor = new BigDecimal("100005600000.9987");
        BigDecimal M_P = sum.divide(divisor, 34, RoundingMode.HALF_UP); // Use more decimal places

        System.out.println("Raw calculated value: " + M_P);

        // Format to match the original pattern (with 4 decimal places)
        // The original has about 34 digits before decimal and 4 after
        DecimalFormat df = new DecimalFormat("#.#################################.####");
        String formattedResult = df.format(M_P);

        System.out.println("Formatted result: " + formattedResult);

        return formattedResult;
    }

    /**
     * Debug method to understand the current Double-based calculation
     */
    public static void debugDoubleCalculation(Encryption encryption, String userId, String password) throws Exception {
        System.out.println("\n=== DOUBLE PRECISION DEBUG ===");

        String encryptedPasswordStr = encryption.checkPass(password);
        String encryptedUsernameStr = encryption.checkPass(userId.trim().toUpperCase());

        Double M_PS = Double.parseDouble(encryptedPasswordStr);
        Double M_PU = Double.parseDouble(encryptedUsernameStr);

        System.out.println("As Double - M_PU: " + M_PU);
        System.out.println("As Double - M_PS: " + M_PS);
        System.out.println("Notice how Double truncates the large numbers!");

        Double M_P = ((M_PU + M_PS) / 100005600000.9987);
        DecimalFormat df = new DecimalFormat("#.########");
        String doubleResult = df.format(M_P);

        System.out.println("Double-based result: " + doubleResult);
        System.out.println("This explains why we get zeros instead of the actual digits!");
    }
}




///this is new one with debugger support for find the issue
//package com.example.SPSProjectBackend;
//
//import util.common.Encryption;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//public class PasswordGenerator {
//
//    public static void main(String[] args) {
//        try {
//            Encryption encryption = new Encryption();
//
//            // Compare working vs non-working 9-character user IDs
//            String[][] testUsers = {
//                    {"EE1COMSAB", "test123", "798417496258042500839631391132765.3135"}, // Working 9-char
//                    {"DEO1COMCP", "test123", "798417396263611194291322482889724.9149"}  // Not working 9-char
//            };
//
//            for (String[] user : testUsers) {
//                String userId = user[0];
//                String password = user[1];
//                String storedPassword = user[2];
//
//                System.out.println("\n" + "=".repeat(60));
//                System.out.println("ANALYZING: " + userId);
//                System.out.println("=".repeat(60));
//
//                analyzeCharacterByCharacter(encryption, userId, password, storedPassword);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void analyzeCharacterByCharacter(Encryption encryption, String userId, String password, String storedEncryptedPassword) throws Exception {
//        System.out.println("UserID: " + userId);
//        System.out.println("Length: " + userId.length());
//
//        // Analyze each character's encryption value
//        System.out.println("\nCHARACTER ENCRYPTION VALUES:");
//        long totalUsernameValue = 0;
//        for (int i = 0; i < userId.length(); i++) {
//            char c = userId.charAt(i);
//            String charStr = String.valueOf(c);
//            long charValue = getCharacterValue(charStr);
//            totalUsernameValue += charValue;
//            System.out.println("  Position " + (i+1) + ": '" + c + "' -> " + charValue);
//        }
//        System.out.println("Total username char value: " + totalUsernameValue);
//
//        // Get the full encrypted strings
//        String encryptedUsername = encryption.checkPass(userId);
//        String encryptedPassword = encryption.checkPass(password);
//
//        System.out.println("\nENCRYPTED STRINGS:");
//        System.out.println("Encrypted username: " + encryptedUsername);
//        System.out.println("Encrypted password: " + encryptedPassword);
//        System.out.println("Username encrypted length: " + encryptedUsername.length());
//        System.out.println("Password encrypted length: " + encryptedPassword.length());
//
//        // Test the validation with detailed logging
//        System.out.println("\nVALIDATION TEST:");
//        testValidationWithLogging(encryption, userId, password, storedEncryptedPassword);
//
//        // Check for any special characters or patterns
//        System.out.println("\nSPECIAL ANALYSIS:");
//        checkForSpecialPatterns(userId);
//    }
//
//    private static long getCharacterValue(String str) {
//        // Same mapping as in your Encryption.decrypt method
//        switch (str) {
//            case "A": return 2457L; case "B": return 2459L; case "C": return 2460L; case "D": return 2461L;
//            case "E": return 2462L; case "F": return 2463L; case "G": return 2464L; case "H": return 2465L;
//            case "I": return 2466L; case "J": return 2468L; case "K": return 2469L; case "L": return 2470L;
//            case "M": return 2471L; case "N": return 2472L; case "O": return 2472L; case "P": return 2473L;
//            case "Q": return 2473L; case "R": return 2474L; case "S": return 2475L; case "T": return 2476L;
//            case "U": return 2478L; case "V": return 2478L; case "W": return 2479L; case "X": return 2481L;
//            case "Y": return 2482L; case "Z": return 2483L;
//            case "0": return 2502L; case "1": return 2503L; case "2": return 2504L; case "3": return 2505L;
//            case "4": return 2506L; case "5": return 2507L; case "6": return 2508L; case "7": return 2509L;
//            case "8": return 2561L; case "9": return 2562L;
//            default: return 0L;
//        }
//    }
//
//    public static void testValidationWithLogging(Encryption encryption, String userId, String password, String storedEncryptedPassword) throws Exception {
//        // Simulate the exact validation logic with detailed logging
//        String encryptedPasswordStr = encryption.checkPass(password);
//        String encryptedUsernameStr = encryption.checkPass(userId.trim().toUpperCase());
//
//        System.out.println("  Step 1 - Encrypted values:");
//        System.out.println("    Username: " + encryptedUsernameStr);
//        System.out.println("    Password: " + encryptedPasswordStr);
//
//        Double M_PS = Double.parseDouble(encryptedPasswordStr);
//        Double M_PU = Double.parseDouble(encryptedUsernameStr);
//
//        System.out.println("  Step 2 - Double values:");
//        System.out.println("    M_PS (password): " + M_PS);
//        System.out.println("    M_PU (username): " + M_PU);
//
//        Double M_P = ((M_PU + M_PS) / 100005600000.9987);
//
//        System.out.println("  Step 3 - Calculation:");
//        System.out.println("    Sum: " + (M_PU + M_PS));
//        System.out.println("    M_P (raw): " + M_P);
//
//        java.text.DecimalFormat df = new java.text.DecimalFormat("#.########");
//        double calculatedVal = Double.parseDouble(df.format(M_P));
//        double storedVal = Double.parseDouble(df.format(Double.parseDouble(storedEncryptedPassword)));
//
//        System.out.println("  Step 4 - Formatted comparison:");
//        System.out.println("    Calculated (8 decimal): " + calculatedVal);
//        System.out.println("    Stored (8 decimal): " + storedVal);
//        System.out.println("    Ratio: " + (calculatedVal / storedVal));
//        System.out.println("    Match: " + (calculatedVal / storedVal == 1.0));
//
//        boolean result = encryption.validateLogin(userId, password, storedEncryptedPassword);
//        System.out.println("  Final validation result: " + result);
//    }
//
//    public static void checkForSpecialPatterns(String userId) {
//        // Check for repeated characters
//        System.out.println("  Repeated characters analysis:");
//        for (int i = 0; i < userId.length(); i++) {
//            char c = userId.charAt(i);
//            int count = 0;
//            for (int j = 0; j < userId.length(); j++) {
//                if (userId.charAt(j) == c) {
//                    count++;
//                }
//            }
//            if (count > 1) {
//                System.out.println("    '" + c + "' appears " + count + " times");
//            }
//        }
//
//        // Check character patterns
//        System.out.println("  Character sequence analysis:");
//        for (int i = 0; i < userId.length() - 1; i++) {
//            char current = userId.charAt(i);
//            char next = userId.charAt(i + 1);
//            System.out.println("    '" + current + "' -> '" + next + "'");
//        }
//    }
//}




//this is commented because i check above things
//package com.example.SPSProjectBackend;
//
//import util.common.Encryption;
//
//public class PasswordGenerator {
//    public static void main(String[] args) {
//        try {
//            Encryption encryption = new Encryption();
//
//            String userId = "DEO1COMCP";
//            String password = "test123";
//            String storedPassword = "798417396263611194291322482889724.9148";
//
//            System.out.println("=== DIRECT ENCRYPTION TEST ===");
//            System.out.println("User: " + userId);
//            System.out.println("Password: " + password);
//            System.out.println("Stored: " + storedPassword);
//
//            boolean result = encryption.validateLogin(userId, password, storedPassword);
//            System.out.println("Validation result: " + result);
//
//            // Also test the individual components
//            String encryptedUser = encryption.checkPass(userId);
//            String encryptedPass = encryption.checkPass(password);
//            System.out.println("Encrypted user: " + encryptedUser);
//            System.out.println("Encrypted pass: " + encryptedPass);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}