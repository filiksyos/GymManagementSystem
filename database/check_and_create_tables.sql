-- Gym Management System Database Expansion - Safe Version
-- This script checks if tables exist before creating them

-- Check and create equipment table
DROP PROCEDURE IF EXISTS create_equipment_table;
DELIMITER //
CREATE PROCEDURE create_equipment_table()
BEGIN
    IF NOT EXISTS (
        SELECT * FROM information_schema.tables 
        WHERE table_schema = DATABASE() 
        AND table_name = 'equipment'
    ) THEN
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
        
        SELECT 'Equipment table created successfully.' AS message;
    ELSE
        SELECT 'Equipment table already exists.' AS message;
    END IF;
END //
DELIMITER ;

CALL create_equipment_table();
DROP PROCEDURE IF EXISTS create_equipment_table;

-- Check and create schedule table
DROP PROCEDURE IF EXISTS create_schedule_table;
DELIMITER //
CREATE PROCEDURE create_schedule_table()
BEGIN
    IF NOT EXISTS (
        SELECT * FROM information_schema.tables 
        WHERE table_schema = DATABASE() 
        AND table_name = 'schedule'
    ) THEN
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
        
        SELECT 'Schedule table created successfully.' AS message;
    ELSE
        SELECT 'Schedule table already exists.' AS message;
    END IF;
END //
DELIMITER ;

CALL create_schedule_table();
DROP PROCEDURE IF EXISTS create_schedule_table;

-- Check and create payment table
DROP PROCEDURE IF EXISTS create_payment_table;
DELIMITER //
CREATE PROCEDURE create_payment_table()
BEGIN
    IF NOT EXISTS (
        SELECT * FROM information_schema.tables 
        WHERE table_schema = DATABASE() 
        AND table_name = 'payment'
    ) THEN
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
        
        SELECT 'Payment table created successfully.' AS message;
    ELSE
        SELECT 'Payment table already exists.' AS message;
    END IF;
END //
DELIMITER ;

CALL create_payment_table();
DROP PROCEDURE IF EXISTS create_payment_table; 