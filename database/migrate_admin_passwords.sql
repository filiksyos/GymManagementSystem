-- Migrate existing admin passwords to a secure format
-- This script is a PLACEHOLDER - In a real application, you would need a
-- custom application to read plain text passwords and hash them securely

-- NOTE: Since we can't create hashed passwords directly in SQL (we need the Java passwordUtil),
-- this script will set a temporary flag to indicate which passwords need updating

-- First, add a temporary column to track which passwords need updating
ALTER TABLE `admin` ADD COLUMN `password_needs_update` TINYINT(1) DEFAULT 1;

-- Now, update all existing users to require password updates
UPDATE `admin` SET `password_needs_update` = 1;

-- Output a list of users that need password updates
SELECT `id`, `username`, `email`, `password_needs_update` FROM `admin`;

-- IMPORTANT: After running this script, you'll need to:
-- 1. Use the Java application to update passwords with proper hashing
-- 2. Upon first login, force users to change their passwords
-- 3. When a user changes their password, set password_needs_update to 0

-- Sample SQL to use when a user's password is updated properly:
-- UPDATE `admin` SET `password` = 'SECURELY_HASHED_PASSWORD', `password_needs_update` = 0 WHERE `id` = USER_ID;

-- After all passwords are migrated, you can remove the temporary column:
-- ALTER TABLE `admin` DROP COLUMN `password_needs_update`; 