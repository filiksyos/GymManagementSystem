-- Gym Management System Database - Verification and Modification Script
-- This script checks if tables exist and adds missing columns if needed

-- Alternative approach: Use DROP TABLE IF EXISTS to recreate tables
-- Uncomment these lines if you want to recreate the tables from scratch
-- WARNING: This will delete all data in these tables!
/*
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS equipment;

-- Then run your original creation script
*/

-- Function to check if a column exists in a table
DROP PROCEDURE IF EXISTS column_exists;
DELIMITER //
CREATE PROCEDURE column_exists(IN table_name VARCHAR(100), IN column_name VARCHAR(100), OUT exists_var BOOLEAN)
BEGIN
    SET exists_var = EXISTS (
        SELECT * FROM information_schema.columns 
        WHERE table_schema = DATABASE() 
        AND table_name = table_name
        AND column_name = column_name
    );
END //
DELIMITER ;

-- Verify equipment table structure
DROP PROCEDURE IF EXISTS verify_equipment_table;
DELIMITER //
CREATE PROCEDURE verify_equipment_table()
BEGIN
    DECLARE column_exists_var BOOLEAN;
    
    -- Check if table exists
    IF EXISTS (
        SELECT * FROM information_schema.tables 
        WHERE table_schema = DATABASE() 
        AND table_name = 'equipment'
    ) THEN
        -- Check for each required column and add if missing
        CALL column_exists('equipment', 'equipmentId', column_exists_var);
        IF NOT column_exists_var THEN
            ALTER TABLE `equipment` ADD `equipmentId` varchar(100) NOT NULL AFTER `id`;
            SELECT 'Added missing column equipmentId to equipment table' AS message;
        END IF;
        
        CALL column_exists('equipment', 'name', column_exists_var);
        IF NOT column_exists_var THEN
            ALTER TABLE `equipment` ADD `name` varchar(100) NOT NULL;
            SELECT 'Added missing column name to equipment table' AS message;
        END IF;
        
        CALL column_exists('equipment', 'type', column_exists_var);
        IF NOT column_exists_var THEN
            ALTER TABLE `equipment` ADD `type` varchar(100) NOT NULL;
            SELECT 'Added missing column type to equipment table' AS message;
        END IF;
        
        CALL column_exists('equipment', 'purchaseDate', column_exists_var);
        IF NOT column_exists_var THEN
            ALTER TABLE `equipment` ADD `purchaseDate` date DEFAULT NULL;
            SELECT 'Added missing column purchaseDate to equipment table' AS message;
        END IF;
        
        CALL column_exists('equipment', 'lastMaintenance', column_exists_var);
        IF NOT column_exists_var THEN
            ALTER TABLE `equipment` ADD `lastMaintenance` date DEFAULT NULL;
            SELECT 'Added missing column lastMaintenance to equipment table' AS message;
        END IF;
        
        CALL column_exists('equipment', 'nextMaintenance', column_exists_var);
        IF NOT column_exists_var THEN
            ALTER TABLE `equipment` ADD `nextMaintenance` date DEFAULT NULL;
            SELECT 'Added missing column nextMaintenance to equipment table' AS message;
        END IF;
        
        CALL column_exists('equipment', 'status', column_exists_var);
        IF NOT column_exists_var THEN
            ALTER TABLE `equipment` ADD `status` varchar(100) NOT NULL;
            SELECT 'Added missing column status to equipment table' AS message;
        END IF;
        
        SELECT 'Equipment table structure verified.' AS message;
    ELSE
        -- Create the table if it doesn't exist
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
        
        SELECT 'Equipment table created.' AS message;
    END IF;
END //
DELIMITER ;

CALL verify_equipment_table();
DROP PROCEDURE IF EXISTS verify_equipment_table;

-- Simple diagnostic query to show table structure
SELECT 
    TABLE_NAME, 
    COLUMN_NAME, 
    COLUMN_TYPE, 
    IS_NULLABLE, 
    COLUMN_KEY, 
    COLUMN_DEFAULT
FROM 
    information_schema.columns 
WHERE 
    table_schema = DATABASE() 
    AND table_name IN ('equipment', 'schedule', 'payment')
ORDER BY 
    table_name, 
    ordinal_position; 