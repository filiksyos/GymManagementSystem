package gymmanagementsystem;

import java.sql.Date;
import java.sql.Timestamp;

public class adminData {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String role;
    private String status;
    private Timestamp lastLogin;
    private String createdBy;
    private Timestamp createdAt;
    
    // Constructor
    public adminData(Integer id, String username, String password,
                    String fullName, String email, String role,
                    String status, Timestamp lastLogin, 
                    String createdBy, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.status = status;
        this.lastLogin = lastLogin;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
    
    // Constructor without password (for security)
    public adminData(Integer id, String username, String fullName,
                    String email, String role, String status,
                    Timestamp lastLogin, String createdBy, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = null; // Don't store password in this constructor
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.status = status;
        this.lastLogin = lastLogin;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    // Password getter is intentionally not provided for security
    
    public String getFullName() {
        return fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getRole() {
        return role;
    }
    
    public String getStatus() {
        return status;
    }
    
    public Timestamp getLastLogin() {
        return lastLogin;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    // Helper methods
    public boolean isActive() {
        return "active".equalsIgnoreCase(status);
    }
    
    public boolean isSuperAdmin() {
        return "SUPER_ADMIN".equals(role);
    }
    
    @Override
    public String toString() {
        return "User: " + username + ", Role: " + role;
    }
} 