-- Create schedule table with foreign key constraint
CREATE TABLE IF NOT EXISTS `schedule` (
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
  UNIQUE KEY `scheduleId` (`scheduleId`),
  CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`coachId`) REFERENCES `coach` (`coachId`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; 