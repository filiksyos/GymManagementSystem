package gymmanagementsystem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class passwordUtil {
    
    private static final int SALT_LENGTH = 16;
    
    // Generate a secure hash for a password
    public static String hashPassword(String plainTextPassword) {
        try {
            // Generate a random salt
            byte[] salt = generateSalt();
            
            // Hash the password with the salt
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(plainTextPassword.getBytes());
            
            // Store both the salt and the hashed password
            return Base64.getEncoder().encodeToString(salt) + ":" + 
                   Base64.getEncoder().encodeToString(hashedPassword);
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Check if a plain text password matches a stored hash
    public static boolean checkPassword(String plainTextPassword, String storedHash) {
        try {
            // Split the stored hash into salt and hash parts
            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                return false;
            }
            
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] hash = Base64.getDecoder().decode(parts[1]);
            
            // Hash the provided password with the same salt
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] newHash = md.digest(plainTextPassword.getBytes());
            
            // Compare the hashes
            return MessageDigest.isEqual(hash, newHash);
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Generate a cryptographically secure random salt
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }
    
    // Generate a random password of specified length
    public static String generateRandomPassword(int length) {
        if (length < 8) {
            length = 8; // Enforce minimum length for security
        }
        
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()_-+=<>?";
        String allChars = lowercase + uppercase + digits + specialChars;
        
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        // Ensure at least one of each character type
        password.append(lowercase.charAt(random.nextInt(lowercase.length())));
        password.append(uppercase.charAt(random.nextInt(uppercase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        
        // Fill the rest with random characters
        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }
        
        // Shuffle the password for better security
        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int j = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }
        
        return new String(passwordArray);
    }
    
    // Validate password strength
    public static boolean isStrongPassword(String password) {
        // Password must be at least 8 characters
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasLowercase = false;
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        
        // Strong password must have at least 3 of the 4 character types
        int count = 0;
        if (hasLowercase) count++;
        if (hasUppercase) count++;
        if (hasDigit) count++;
        if (hasSpecial) count++;
        
        return count >= 3;
    }
    
    // Get password strength description
    public static String getPasswordStrength(String password) {
        if (password == null || password.isEmpty()) {
            return "Empty";
        }
        
        if (password.length() < 8) {
            return "Too Short";
        }
        
        int score = 0;
        
        // Length score
        if (password.length() >= 12) {
            score += 2;
        } else if (password.length() >= 8) {
            score += 1;
        }
        
        // Character type score
        boolean hasLowercase = false;
        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        
        if (hasLowercase) score++;
        if (hasUppercase) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;
        
        // Return strength description based on score
        if (score >= 6) {
            return "Strong";
        } else if (score >= 4) {
            return "Medium";
        } else {
            return "Weak";
        }
    }
} 