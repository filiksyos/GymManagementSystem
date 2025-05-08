-- Fix Foreign Key Constraint Issue
-- This script will add a unique index to the coachId column in the coach table
-- allowing it to be referenced by foreign keys

-- First, let's check if the coachId column already has a unique index
SELECT COUNT(*) AS has_unique_index
FROM information_schema.statistics
WHERE table_schema = DATABASE()
  AND table_name = 'coach'
  AND column_name = 'coachId'
  AND non_unique = 0;

-- Add unique index to coachId in coach table
-- This allows it to be referenced by foreign keys
ALTER TABLE `coach` ADD UNIQUE INDEX `idx_coach_coachId` (`coachId`);

-- Now let's try to create the schedule table again
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

-- Now let's do the same for memberId to prevent issues with the payment table
-- Add unique index to memberId in member table
ALTER TABLE `member` ADD UNIQUE INDEX `idx_member_memberId` (`memberId`);

-- Try to create the payment table again
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