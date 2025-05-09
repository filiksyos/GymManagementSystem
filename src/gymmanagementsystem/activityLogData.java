package gymmanagementsystem;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class activityLogData {
    private Integer id;
    private String username;
    private String action;
    private String module;
    private String details;
    private Timestamp timestamp;
    private String ipAddress;
    
    // Constructor
    public activityLogData(Integer id, String username, String action,
                         String module, String details, Timestamp timestamp, 
                         String ipAddress) {
        this.id = id;
        this.username = username;
        this.action = action;
        this.module = module;
        this.details = details;
        this.timestamp = timestamp;
        this.ipAddress = ipAddress;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getAction() {
        return action;
    }
    
    public String getModule() {
        return module;
    }
    
    public String getDetails() {
        return details;
    }
    
    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    public void setModule(String module) {
        this.module = module;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    // Helper methods
    
    // Get formatted timestamp (for display)
    public String getFormattedTimestamp() {
        if (timestamp == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(timestamp);
    }
    
    // Get short details (for display in tables)
    public String getShortDetails() {
        if (details == null || details.length() <= 50) {
            return details;
        }
        return details.substring(0, 47) + "...";
    }
    
    @Override
    public String toString() {
        return "Activity: " + action + " (" + getFormattedTimestamp() + ")";
    }
} 