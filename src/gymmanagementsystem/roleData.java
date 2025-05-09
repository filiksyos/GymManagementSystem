package gymmanagementsystem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class roleData {
    private Integer id;
    private String roleName;
    private String description;
    private String permissions;
    
    // Constructor
    public roleData(Integer id, String roleName, String description, String permissions) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.permissions = permissions;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getPermissions() {
        return permissions;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    
    // Helper methods
    
    // Check if this role has a specific permission
    public boolean hasPermission(String permission) {
        if (permissions == null) {
            return false;
        }
        
        if (permissions.equals("ALL")) {
            return true;
        }
        
        Set<String> permissionSet = new HashSet<>(Arrays.asList(permissions.split(",")));
        return permissionSet.contains(permission);
    }
    
    // Add a permission to this role
    public void addPermission(String permission) {
        if (permissions == null || permissions.isEmpty()) {
            permissions = permission;
        } else if (!hasPermission(permission) && !permissions.equals("ALL")) {
            permissions += "," + permission;
        }
    }
    
    // Remove a permission from this role
    public void removePermission(String permission) {
        if (permissions != null && !permissions.isEmpty() && !permissions.equals("ALL")) {
            Set<String> permissionSet = new HashSet<>(Arrays.asList(permissions.split(",")));
            permissionSet.remove(permission);
            permissions = String.join(",", permissionSet);
        }
    }
    
    // Convert permissions string to array
    public String[] getPermissionsArray() {
        if (permissions == null || permissions.isEmpty()) {
            return new String[0];
        }
        
        if (permissions.equals("ALL")) {
            return new String[]{"ALL"};
        }
        
        return permissions.split(",");
    }
    
    @Override
    public String toString() {
        return roleName;
    }
} 