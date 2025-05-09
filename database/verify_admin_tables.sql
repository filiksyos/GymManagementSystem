-- Verify Admin Management Tables
-- This script checks if the admin management tables are properly set up

-- Check admin table structure
DESCRIBE `admin`;

-- Check if the role table exists
SHOW TABLES LIKE 'role';

-- Check role table structure if it exists
DESCRIBE `role`;

-- Check if the activity_log table exists
SHOW TABLES LIKE 'activity_log';

-- Check activity_log table structure if it exists
DESCRIBE `activity_log`;

-- Check existing roles
SELECT * FROM `role`;

-- Check admin users
SELECT `id`, `username`, `email`, `fullName`, `role`, `status`, `lastLogin` FROM `admin`;

-- Check activity logs
SELECT * FROM `activity_log` LIMIT 10; 