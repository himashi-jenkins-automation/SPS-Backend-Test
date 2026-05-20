// EncryptionImporterMain.java
// This is a simple Java class that can be run in IntelliJ.
// It imports the existing Encryption class from util.common (assuming your project structure has it).
// If you get compilation errors about package, place this file in your src root or adjust packages.
// Hardcode your userId and password below in the main method.
// When run, it will output the encrypted value to store in the database password column.
// Note: If your project doesn't have SLF4J configured for logging, you might see warnings, but the computation should still work as logging is only for errors.

package com.example.SPSProjectBackend;
import util.common.Encryption;

public class EncryptionImporterMain {

    // Method to compute the encrypted value to store in DB (using checkPass from Encryption)
    private static String computeEncryptedPassword(String userId, String password) {
        Encryption encryption = new Encryption();  // Instantiate the existing Encryption class
        String mUser = encryption.checkPass(userId.trim().toUpperCase());
        String mPass = encryption.checkPass(password);

        if (mUser.trim().isEmpty() || mPass.trim().isEmpty()) {
            return "Invalid input (contains unsupported characters)";
        }

        try {
            double mPu = Double.parseDouble(mUser);
            double mPs = Double.parseDouble(mPass);
            double constant = 100005600000.9987;
            double mP = (mPu + mPs) / constant;
            return String.valueOf(mP);  // This is the value to store in DB password column
        } catch (NumberFormatException e) {
            return "Error computing value: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        // Hardcoded values (change these as needed)
        String userId = "DEO1CONWPN";
        String password = "test123";

        String encryptedValue = computeEncryptedPassword(userId, password);
        System.out.println("The encrypted value to put in the database password column for correct login: " + encryptedValue);
    }
}