-- Gym Management System - Admin Management Tables
-- This script adds the necessary tables for admin management functionality

-- Modify the existing admin table
ALTER TABLE `admin` 
ADD COLUMN `fullName` varchar(100) DEFAULT NULL AFTER `password`,
ADD COLUMN `role` varchar(50) DEFAULT 'STAFF' AFTER `fullName`,
ADD COLUMN `status` varchar(20) DEFAULT 'active' AFTER `role`,
ADD COLUMN `lastLogin` datetime DEFAULT NULL AFTER `status`,
ADD COLUMN `createdBy` varchar(50) DEFAULT NULL AFTER `lastLogin`,
ADD COLUMN `createdAt` datetime DEFAULT CURRENT_TIMESTAMP AFTER `createdBy`,
MODIFY COLUMN `password` varchar(255) NOT NULL;

-- Create roles table
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `permissions` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleName_UNIQUE` (`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create activity log table
CREATE TABLE IF NOT EXISTS `activity_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `action` varchar(255) NOT NULL,
  `module` varchar(50) NOT NULL,
  `details` text,
  `timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
  `ipAddress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert default roles
INSERT INTO `role` (`roleName`, `description`, `permissions`) VALUES
('SUPER_ADMIN', 'Full system access with all permissions', 'ALL'),
('ADMIN', 'Administrative access with limited system settings', 'MANAGE_USERS,MANAGE_MEMBERS,MANAGE_COACHES,MANAGE_EQUIPMENT,MANAGE_SCHEDULE,VIEW_REPORTS'),
('MANAGER', 'Manage day-to-day operations', 'MANAGE_MEMBERS,MANAGE_COACHES,MANAGE_SCHEDULE,VIEW_REPORTS'),
('STAFF', 'Basic staff access', 'VIEW_MEMBERS,VIEW_COACHES,VIEW_SCHEDULE');

-- Update existing admin users with default values
UPDATE `admin` SET 
  `fullName` = CONCAT('Admin ', id), 
  `role` = 'SUPER_ADMIN', 
  `status` = 'active' 
WHERE `fullName` IS NULL;

-- Add a log entry for the database upgrade
INSERT INTO `activity_log` (`username`, `action`, `module`, `details`, `ipAddress`) VALUES
('system', 'Database Upgrade', 'Admin Management', 'Admin management tables created', '127.0.0.1'); 