//
////this is working code of encription with all related to userlevels
//package util.common;
//
//import org.springframework.stereotype.Component;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
//@Component
//public class Encryption {
//
//    public boolean validateLogin(String userName, String password, String storedEncryptedPassword) throws Exception {
//        Double M_PS = Double.parseDouble(checkPass(password));
//        Double M_PU = Double.parseDouble(checkPass(userName.trim().toUpperCase()));
//
//        Double M_P = ((M_PU + M_PS) / 100005600000.9987);
//
//        // Format both values to 8 decimal places
//        DecimalFormat df = new DecimalFormat("#.########");
//        double calculatedVal = Double.parseDouble(df.format(M_P));
//        double storedVal = Double.parseDouble(df.format(Double.parseDouble(storedEncryptedPassword)));
//
//        return calculatedVal / storedVal == 1.0;
//    }
//
//    public String checkPass(String literal) {
//        int PAS_LEN = 1;
//        String M_PASS = "";
//        char[] cArray = literal.trim().toCharArray();
//        ArrayList<String> strList = new ArrayList<>();
//
//        for (char c : cArray) strList.add(Character.toString(c));
//
//        int cArrayLength = cArray.length;
//        long LEN_TOT = 0L;
//
//        while (PAS_LEN <= 10) {
//            long PAS_CHAR, POS_CHAR;
//
//            if (cArrayLength < PAS_LEN) {
//                PAS_CHAR = (PAS_LEN == 0) ? 9999L : LEN_TOT;
//            } else {
//                PAS_CHAR = decrypt(strList.get(PAS_LEN - 1));
//            }
//
//            if (PAS_CHAR == 0L) return "";
//
//            POS_CHAR = decrypt(String.valueOf(PAS_LEN));
//            long ENC_CHAR = PAS_CHAR + POS_CHAR;
//            LEN_TOT += ENC_CHAR;
//
//            M_PASS = M_PASS.isEmpty() ? String.valueOf(ENC_CHAR) : M_PASS + ENC_CHAR;
//            PAS_LEN++;
//        }
//
//        return M_PASS;
//    }
//
//    private long decrypt(String str) {
//        switch (str) {
//            case "A": return 2457L; case "B": return 2459L; case "C": return 2460L; case "D": return 2461L;
//            case "E": return 2462L; case "F": return 2463L; case "G": return 2464L; case "H": return 2465L;
//            case "I": return 2466L; case "J": return 2468L; case "K": return 2469L; case "L": return 2470L;
//            case "M": return 2471L; case "N": return 2472L; case "O": return 2472L; case "P": return 2473L;
//            case "Q": return 2473L; case "R": return 2474L; case "S": return 2475L; case "T": return 2476L;
//            case "U": return 2478L; case "V": return 2478L; case "W": return 2479L; case "X": return 2481L;
//            case "Y": return 2482L; case "Z": return 2483L;
//            case "a": return 4055L; case "b": return 4056L; case "c": return 4057L; case "d": return 4060L;
//            case "e": return 4061L; case "f": return 4063L; case "g": return 4064L; case "h": return 4065L;
//            case "i": return 4066L; case "j": return 4068L; case "k": return 4069L; case "l": return 5470L;
//            case "m": return 5471L; case "n": return 5472L; case "o": return 5472L; case "p": return 5473L;
//            case "q": return 5473L; case "r": return 5474L; case "s": return 5475L; case "t": return 5476L;
//            case "u": return 5478L; case "v": return 5478L; case "w": return 5479L; case "x": return 5481L;
//            case "y": return 5482L; case "z": return 9999L;
//            case "0": return 2502L; case "1": return 2503L; case "2": return 2504L; case "3": return 2505L;
//            case "4": return 2506L; case "5": return 2507L; case "6": return 2508L; case "7": return 2509L;
//            case "8": return 2561L; case "9": return 2562L; case "10": return 2563L;
//            default: return 0L;
//        }
//    }
//}

//
//
// NEW UPDATED CODES 3 - Encryption.java
// Changes:
// 1. In validateLogin: Added checks for empty m_User or m_Pass strings (from checkPass). If empty, return false. This prevents NumberFormatException when parsing "" to Double, which would occur if invalid characters are present (as in original, it returns false gracefully).
// 2. Changed comparison logic to exactly match original: Compute M_P as Double, then compute ratio = M_P / dbVal, then format the *ratio* to "#.########", parse back, and check if == 1.0. Previously, it formatted M_P and stored separately before dividing, which could lead to precision mismatches due to floating-point issues.
// 3. Removed unnecessary DecimalFormat in the old comparison; now uses the format only on the ratio as per original.
// 4. In checkPass: Added trim() to literal at the beginning for consistency.
// 5. In decrypt: No changes, as it matches original.
// 6. Added try-catch around Double.parseDouble to handle potential NumberFormatException from large numbers or invalid formats, logging and returning false to prevent crashes.
package util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.text.DecimalFormat;
import java.util.ArrayList;

@Component
public class Encryption {

    private static final Logger logger = LoggerFactory.getLogger(Encryption.class); // ADDED: Logger for debugging
                                                                                    // encryption issues

    public boolean validateLogin(String userName, String password, String storedPassword) throws Exception {
        String m_User = checkPass(userName.trim().toUpperCase());
        String m_Pass = checkPass(password);

        if (m_User.trim().isEmpty() || m_Pass.trim().isEmpty()) { // ADDED: Check for empty results from checkPass
                                                                  // (e.g., invalid chars), return false as in original
            logger.debug("Empty checkPass result for user or pass, invalid");
            return false;
        }

        try {
            Double M_PU = Double.parseDouble(m_User);
            Double M_PS = Double.parseDouble(m_Pass);

            Double M_P = (M_PU + M_PS) / 100005600000.9987;

            if (storedPassword == null || storedPassword.trim().isEmpty()) { // ADDED: Handle null/empty stored password
                return false;
            }

            Double dbVal = Double.parseDouble(storedPassword);
            Double enteredVal = M_P;

            Double ratio = enteredVal / dbVal;

            DecimalFormat twoDForm = new DecimalFormat("#.########");
            Double roundUp = Double.valueOf(twoDForm.format(ratio));

            return roundUp.equals(1.00000000d); // CHANGED: Now format the ratio *after* division, matching original
                                                // logic exactly
        } catch (NumberFormatException e) { // ADDED: Catch parse exceptions (e.g., very large numbers exceeding double
                                            // precision)
            logger.error("Number format error during validation: {}", e.getMessage());
            return false;
        }
    }

    public String checkPass(String literal) {
        literal = literal.trim(); // ADDED: Trim literal for consistency
        int PAS_LEN = 1;
        String M_PASS = "";
        char[] cArray = literal.toCharArray(); // CHANGED: Removed unnecessary trim() since we trimmed literal
        ArrayList<String> strList = new ArrayList<>();

        for (char c : cArray)
            strList.add(Character.toString(c));

        int cArrayLength = cArray.length;
        long LEN_TOT = 0L;

        while (PAS_LEN <= 10) {
            long PAS_CHAR, POS_CHAR;

            if (cArrayLength < PAS_LEN) {
                PAS_CHAR = (PAS_LEN == 0) ? 9999L : LEN_TOT; // Matches original (though PAS_LEN !=0)
            } else {
                PAS_CHAR = decrypt(strList.get(PAS_LEN - 1));
            }

            if (PAS_CHAR == 0L)
                return "";

            POS_CHAR = decrypt(String.valueOf(PAS_LEN));
            long ENC_CHAR = PAS_CHAR + POS_CHAR;
            LEN_TOT += ENC_CHAR;

            M_PASS = M_PASS.isEmpty() ? String.valueOf(ENC_CHAR) : M_PASS + ENC_CHAR;
            PAS_LEN++;
        }

        return M_PASS;
    }

    private long decrypt(String str) {
        switch (str) {
            case "A":
                return 2457L;
            case "B":
                return 2459L;
            case "C":
                return 2460L;
            case "D":
                return 2461L;
            case "E":
                return 2462L;
            case "F":
                return 2463L;
            case "G":
                return 2464L;
            case "H":
                return 2465L;
            case "I":
                return 2466L;
            case "J":
                return 2468L;
            case "K":
                return 2469L;
            case "L":
                return 2470L;
            case "M":
                return 2471L;
            case "N":
                return 2472L;
            case "O":
                return 2472L;
            case "P":
                return 2473L;
            case "Q":
                return 2473L;
            case "R":
                return 2474L;
            case "S":
                return 2475L;
            case "T":
                return 2476L;
            case "U":
                return 2478L;
            case "V":
                return 2478L;
            case "W":
                return 2479L;
            case "X":
                return 2481L;
            case "Y":
                return 2482L;
            case "Z":
                return 2483L;
            case "a":
                return 4055L;
            case "b":
                return 4056L;
            case "c":
                return 4057L;
            case "d":
                return 4060L;
            case "e":
                return 4061L;
            case "f":
                return 4063L;
            case "g":
                return 4064L;
            case "h":
                return 4065L;
            case "i":
                return 4066L;
            case "j":
                return 4068L;
            case "k":
                return 4069L;
            case "l":
                return 5470L;
            case "m":
                return 5471L;
            case "n":
                return 5472L;
            case "o":
                return 5472L;
            case "p":
                return 5473L;
            case "q":
                return 5473L;
            case "r":
                return 5474L;
            case "s":
                return 5475L;
            case "t":
                return 5476L;
            case "u":
                return 5478L;
            case "v":
                return 5478L;
            case "w":
                return 5479L;
            case "x":
                return 5481L;
            case "y":
                return 5482L;
            case "z":
                return 9999L;
            case "0":
                return 2502L;
            case "1":
                return 2503L;
            case "2":
                return 2504L;
            case "3":
                return 2505L;
            case "4":
                return 2506L;
            case "5":
                return 2507L;
            case "6":
                return 2508L;
            case "7":
                return 2509L;
            case "8":
                return 2561L;
            case "9":
                return 2562L;
            case "10":
                return 2563L;
            default:
                return 0L;
        }
    }
}

// under below all codes not working correctly
//// UPDATED NEW CODES
//
// package util.common;
//
// import org.springframework.stereotype.Component;
// import java.math.BigDecimal;
// import java.math.RoundingMode;
// import java.text.DecimalFormat;
// import java.util.ArrayList;
//
// @Component
// public class Encryption {
//
// public boolean validateLogin(String userName, String password, String
// storedEncryptedPassword) {
// System.out.println("=== Encryption Debug ===");
// System.out.println("Username: " + userName);
// System.out.println("Password: " + password);
// System.out.println("Stored encrypted: " + storedEncryptedPassword);
//
// try {
// // Use the correct calculation method
// String calculatedPassword = calculateEncryptedPassword(userName, password);
// double calculatedVal = Double.parseDouble(calculatedPassword);
// double storedVal = Double.parseDouble(storedEncryptedPassword);
//
// System.out.println("Calculated value: " + calculatedVal);
// System.out.println("Stored value: " + storedVal);
// System.out.println("Validation result: " + (Math.abs(calculatedVal -
// storedVal) < 0.00000001));
// System.out.println("=== End Debug ===");
//
// return Math.abs(calculatedVal - storedVal) < 0.00000001;
// } catch (Exception e) {
// System.err.println("Error in validateLogin: " + e.getMessage());
// e.printStackTrace();
// return false;
// }
// }
//
// public String calculateEncryptedPassword(String userName, String password) {
// String encryptedPasswordStr = checkPass(password);
// String encryptedUsernameStr = checkPass(userName.trim().toUpperCase());
//
// System.out.println("Encrypted password string: " + encryptedPasswordStr);
// System.out.println("Encrypted username string: " + encryptedUsernameStr);
//
// // FIXED: Use the correct calculation
// BigDecimal M_PS = new BigDecimal(encryptedPasswordStr);
// BigDecimal M_PU = new BigDecimal(encryptedUsernameStr);
//
// // The key fix: Use the correct divisor and calculation
// BigDecimal sum = M_PU.add(M_PS);
// BigDecimal divisor = new BigDecimal("100005600000.9987");
// BigDecimal result = sum.divide(divisor, 8, RoundingMode.HALF_UP);
//
// DecimalFormat df = new DecimalFormat("#.########");
// return df.format(result.doubleValue());
// }
//
// public String checkPass(String literal) {
// if (literal == null || literal.trim().isEmpty()) {
// return "0";
// }
//
// StringBuilder M_PASS = new StringBuilder();
// char[] cArray = literal.trim().toCharArray();
//
// for (int PAS_LEN = 1; PAS_LEN <= 10; PAS_LEN++) {
// long PAS_CHAR;
//
// if (PAS_LEN > cArray.length) {
// // If we've processed all characters, use the total so far
// PAS_CHAR = getTotalFromString(M_PASS.toString());
// } else {
// String currentChar = String.valueOf(cArray[PAS_LEN - 1]);
// PAS_CHAR = decrypt(currentChar);
// }
//
// if (PAS_CHAR == 0L) {
// PAS_CHAR = 9999L;
// }
//
// long POS_CHAR = decrypt(String.valueOf(PAS_LEN));
// long ENC_CHAR = PAS_CHAR + POS_CHAR;
//
// if (M_PASS.length() == 0) {
// M_PASS.append(ENC_CHAR);
// } else {
// M_PASS.append(ENC_CHAR);
// }
// }
//
// return M_PASS.toString();
// }
//
// private long getTotalFromString(String str) {
// if (str == null || str.isEmpty()) return 0L;
// try {
// // For long strings, take the last few digits to avoid huge numbers
// if (str.length() > 10) {
// String lastPart = str.substring(str.length() - 6);
// return Long.parseLong(lastPart);
// }
// return Long.parseLong(str);
// } catch (NumberFormatException e) {
// return 0L;
// }
// }
//
// private long decrypt(String str) {
// switch (str) {
// // Uppercase letters
// case "A": return 2457L; case "B": return 2459L; case "C": return 2460L; case
// "D": return 2461L;
// case "E": return 2462L; case "F": return 2463L; case "G": return 2464L; case
// "H": return 2465L;
// case "I": return 2466L; case "J": return 2468L; case "K": return 2469L; case
// "L": return 2470L;
// case "M": return 2471L; case "N": return 2472L; case "O": return 2472L; case
// "P": return 2473L;
// case "Q": return 2473L; case "R": return 2474L; case "S": return 2475L; case
// "T": return 2476L;
// case "U": return 2478L; case "V": return 2478L; case "W": return 2479L; case
// "X": return 2481L;
// case "Y": return 2482L; case "Z": return 2483L;
//
// // Lowercase letters
// case "a": return 4055L; case "b": return 4056L; case "c": return 4057L; case
// "d": return 4060L;
// case "e": return 4061L; case "f": return 4063L; case "g": return 4064L; case
// "h": return 4065L;
// case "i": return 4066L; case "j": return 4068L; case "k": return 4069L; case
// "l": return 5470L;
// case "m": return 5471L; case "n": return 5472L; case "o": return 5472L; case
// "p": return 5473L;
// case "q": return 5473L; case "r": return 5474L; case "s": return 5475L; case
// "t": return 5476L;
// case "u": return 5478L; case "v": return 5478L; case "w": return 5479L; case
// "x": return 5481L;
// case "y": return 5482L; case "z": return 9999L;
//
// // Numbers
// case "0": return 2502L; case "1": return 2503L; case "2": return 2504L; case
// "3": return 2505L;
// case "4": return 2506L; case "5": return 2507L; case "6": return 2508L; case
// "7": return 2509L;
// case "8": return 2561L; case "9": return 2562L;
//
// // Position numbers (for PAS_LEN)
// case "10": return 2563L;
//
// default:
// System.out.println("Unknown character in decrypt: " + str);
// return 0L;
// }
// }
// }

//// NEW CODES2 this is working with username=ADMIN and password=admin123
// package util.common;
//
// import org.springframework.stereotype.Component;
// import java.text.DecimalFormat;
// import java.util.ArrayList;
//
// @Component
// public class Encryption {
//
// public boolean validateLogin(String userName, String password, String
//// storedEncryptedPassword) throws Exception {
// Double M_PS = Double.parseDouble(checkPass(password));
// Double M_PU = Double.parseDouble(checkPass(userName.trim().toUpperCase()));
//
// Double M_P = ((M_PU + M_PS) / 100005600000.9987);
//
// // Format both values to 8 decimal places
// DecimalFormat df = new DecimalFormat("#.########");
// double calculatedVal = Double.parseDouble(df.format(M_P));
// double storedVal =
//// Double.parseDouble(df.format(Double.parseDouble(storedEncryptedPassword)));
//
// return calculatedVal / storedVal == 1.0;
// }
//
// public String checkPass(String literal) {
// int PAS_LEN = 1;
// String M_PASS = "";
// char[] cArray = literal.trim().toCharArray();
// ArrayList<String> strList = new ArrayList<>();
//
// for (char c : cArray) strList.add(Character.toString(c));
//
// int cArrayLength = cArray.length;
// long LEN_TOT = 0L;
//
// while (PAS_LEN <= 10) {
// long PAS_CHAR, POS_CHAR;
//
// if (cArrayLength < PAS_LEN) {
// PAS_CHAR = (PAS_LEN == 0) ? 9999L : LEN_TOT;
// } else {
// PAS_CHAR = decrypt(strList.get(PAS_LEN - 1));
// }
//
// if (PAS_CHAR == 0L) return "";
//
// POS_CHAR = decrypt(String.valueOf(PAS_LEN));
// long ENC_CHAR = PAS_CHAR + POS_CHAR;
// LEN_TOT += ENC_CHAR;
//
// M_PASS = M_PASS.isEmpty() ? String.valueOf(ENC_CHAR) : M_PASS + ENC_CHAR;
// PAS_LEN++;
// }
//
// return M_PASS;
// }
//
// private long decrypt(String str) {
// switch (str) {
// case "A": return 2457L; case "B": return 2459L; case "C": return 2460L; case
//// "D": return 2461L;
// case "E": return 2462L; case "F": return 2463L; case "G": return 2464L; case
//// "H": return 2465L;
// case "I": return 2466L; case "J": return 2468L; case "K": return 2469L; case
//// "L": return 2470L;
// case "M": return 2471L; case "N": return 2472L; case "O": return 2472L; case
//// "P": return 2473L;
// case "Q": return 2473L; case "R": return 2474L; case "S": return 2475L; case
//// "T": return 2476L;
// case "U": return 2478L; case "V": return 2478L; case "W": return 2479L; case
//// "X": return 2481L;
// case "Y": return 2482L; case "Z": return 2483L;
// case "a": return 4055L; case "b": return 4056L; case "c": return 4057L; case
//// "d": return 4060L;
// case "e": return 4061L; case "f": return 4063L; case "g": return 4064L; case
//// "h": return 4065L;
// case "i": return 4066L; case "j": return 4068L; case "k": return 4069L; case
//// "l": return 5470L;
// case "m": return 5471L; case "n": return 5472L; case "o": return 5472L; case
//// "p": return 5473L;
// case "q": return 5473L; case "r": return 5474L; case "s": return 5475L; case
//// "t": return 5476L;
// case "u": return 5478L; case "v": return 5478L; case "w": return 5479L; case
//// "x": return 5481L;
// case "y": return 5482L; case "z": return 9999L;
// case "0": return 2502L; case "1": return 2503L; case "2": return 2504L; case
//// "3": return 2505L;
// case "4": return 2506L; case "5": return 2507L; case "6": return 2508L; case
//// "7": return 2509L;
// case "8": return 2561L; case "9": return 2562L; case "10": return 2563L;
// default: return 0L;
// }
// }
// }