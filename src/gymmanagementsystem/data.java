/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmanagementsystem;

/**
 *
 * @author MarcoMan
 * Subscribe our YouTube Channel: https://www.youtube.com/@marcomanchannel
 */
public class data {
    
    public static String username;
    public static String userRole;
    public static Integer userId;
    public static String userFullName;
    
    // Check if the current user has a specific permission
    public static boolean hasPermission(String permission) {
        if (userRole == null) {
            return false;
        }
        
        // Super admin has all permissions
        if (userRole.equals("SUPER_ADMIN")) {
            return true;
        }
        
        // Other roles need to be checked against the database
        // This is a placeholder - actual implementation will be in the dashboard controller
        return false;
    }
    
}
