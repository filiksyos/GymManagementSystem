-- Equipment table
CREATE TABLE IF NOT EXISTS `equipment` (
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

-- Sample data for equipment table
INSERT INTO `equipment` (`equipmentId`, `name`, `type`, `purchaseDate`, `lastMaintenance`, `nextMaintenance`, `status`) VALUES
('EQ-101', 'Treadmill Pro 500', 'Cardio', '2022-05-15', '2022-12-10', '2023-06-10', 'Working'),
('EQ-102', 'Bench Press Standard', 'Strength', '2022-03-22', '2022-11-15', '2023-05-15', 'Working'),
('EQ-103', 'Leg Press Machine', 'Strength', '2022-04-10', '2022-10-20', '2023-04-20', 'Maintenance'); 