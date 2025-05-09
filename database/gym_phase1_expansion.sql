-- Gym Management System Database Expansion
-- Phase 1: New Tables

-- Table structure for table `equipment`
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

-- Table structure for table `schedule`
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

-- Table structure for table `payment`
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

-- Auto increment settings
ALTER TABLE `equipment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT; 