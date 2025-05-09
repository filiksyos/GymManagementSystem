-- Gym Management System - Complete Database Setup
-- This script combines all necessary steps to set up the entire database in one go
-- It includes original tables and the Phase 1 expansion tables with proper foreign keys

-- Set SQL mode and start transaction
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- Drop tables if they exist (in correct order to preserve foreign key constraints)
DROP TABLE IF EXISTS `payment`;
DROP TABLE IF EXISTS `schedule`;
DROP TABLE IF EXISTS `equipment`;
DROP TABLE IF EXISTS `member`;
DROP TABLE IF EXISTS `coach`;
DROP TABLE IF EXISTS `admin`;

-- Create admin table
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert sample admin data
INSERT INTO `admin` (`id`, `email`, `username`, `password`) VALUES
(1, 'admin@marcoman.com', 'admin', 'admin123'),
(2, 'test@marcoman.com', 'test', 'test1234'),
(3, 'test@marcoman.com', 'test', 'admin123'),
(4, 'testsignup@marcoman.com', 'signup', 'admin123');

-- Create coach table with unique coachId
CREATE TABLE `coach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coachId` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(500) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `phoneNum` int(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_coach_coachId` (`coachId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert sample coach data
INSERT INTO `coach` (`id`, `coachId`, `name`, `address`, `gender`, `phoneNum`, `status`) VALUES
(2, 'CID-101', 'Marco Man', 'Test (1) Address', 'Male', 1231248125, 'Active'),
(3, 'CID-102', 'Trial (2)', 'Test (2) Address', 'Male', 2139239284, 'Active'),
(4, 'CID-103', 'Trial (3)', 'Trial (3)', 'Female', 1236125262, 'Active');

-- Create member table with unique memberId
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberId` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(500) NOT NULL,
  `phoneNum` int(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `schedule` varchar(100) NOT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `price` double NOT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_member_memberId` (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert sample member data
INSERT INTO `member` (`id`, `memberId`, `name`, `address`, `phoneNum`, `gender`, `schedule`, `startDate`, `endDate`, `price`, `status`) VALUES
(8, 'MID-101', 'Marco', 'Test (1) Address', 1261361, 'Male', '11AM - 1PM', '2023-01-02', '2023-04-12', 5000, 'Paid'),
(9, 'MID-102', 'Test', 'Test (2) Address', 12323123, 'Female', '3PM - 5PM', '2023-01-03', '2023-03-21', 3850, 'Paid'),
(10, 'MID-103', 'Test (3)', 'Test (3) Address', 1253633, 'Male', '3PM - 5PM', '2023-01-04', '2023-02-13', 2000, 'Paid'),
(11, 'MID-104', 'Test (4)', 'Test (4) Address', 1246365324, 'Others', '5PM - 7PM', '2023-01-05', '2023-03-19', 3650, 'Paid');

-- Create equipment table
CREATE TABLE `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equipmentId` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `purchaseDate` date DEFAULT NULL,
  `lastMaintenance` date DEFAULT NULL,
  `nextMaintenance` date DEFAULT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create schedule table with foreign key to coach
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scheduleId` varchar(100) NOT NULL,
  `className` varchar(100) NOT NULL,
  `coachId` varchar(100) NOT NULL,
  `dayOfWeek` varchar(20) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `maxCapacity` int(11) NOT NULL,
  `currentEnrollment` int(11) DEFAULT 0,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`coachId`) REFERENCES `coach`(`coachId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create payment table with foreign key to member
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paymentId` varchar(100) NOT NULL,
  `memberId` varchar(100) NOT NULL,
  `amount` double NOT NULL,
  `paymentDate` date NOT NULL,
  `paymentMethod` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`memberId`) REFERENCES `member`(`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Set auto-increment values
ALTER TABLE `admin` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
ALTER TABLE `coach` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
ALTER TABLE `member` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
ALTER TABLE `equipment` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `schedule` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `payment` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

COMMIT; 