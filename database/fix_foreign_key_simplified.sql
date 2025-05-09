-- Fix Foreign Key Constraint Issue - Simplified Version
-- This script adds unique indexes to enable foreign key relationships
-- without needing access to information_schema

-- Add unique index to coachId in coach table (with error handling)
ALTER TABLE `coach` 
ADD UNIQUE INDEX `idx_coach_coachId` (`coachId`);

-- Create the schedule table
DROP TABLE IF EXISTS `schedule`;
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

-- Add unique index to memberId in member table (with error handling)
ALTER TABLE `member` 
ADD UNIQUE INDEX `idx_member_memberId` (`memberId`);

-- Create the payment table
DROP TABLE IF EXISTS `payment`;
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