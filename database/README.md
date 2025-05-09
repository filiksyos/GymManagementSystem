# Gym Management System Database Setup

This folder contains SQL scripts for setting up and extending the Gym Management System database.

## Table of Contents
- [Overview](#overview)
- [Database Structure](#database-structure)
- [Setup Instructions](#setup-instructions)
- [Understanding the Scripts](#understanding-the-scripts)
- [Troubleshooting](#troubleshooting)

## Overview

The Gym Management System uses a MySQL database to store information about members, coaches, equipment, schedules, and payments. This README explains how to set up the database and which scripts to use.

## Database Structure

The database consists of the following tables:

1. `admin` - System administrators
2. `coach` - Fitness coaches/trainers
3. `member` - Gym members
4. `equipment` - Gym equipment inventory
5. `schedule` - Class schedules
6. `payment` - Member payment records
7. `role` - User roles for access control
8. `activity_log` - User activity tracking

## Setup Instructions

### First-Time Setup

If you're setting up the database for the first time:

1. Create a new database named `gym` in MySQL/XAMPP
2. Run the script `gym.sql` to create the base tables (admin, coach, member)
3. Run the script `fix_foreign_key_simplified.sql` to add the extended tables (equipment, schedule, payment)

### Single-Step Setup

For convenience, you can use `complete_setup.sql` which combines all necessary steps into a single script.

## Understanding the Scripts

### Main Scripts

- **gym.sql** - Creates the base database with admin, coach, and member tables
- **fix_foreign_key_simplified.sql** - Creates the equipment, schedule, and payment tables with proper relationships

### Alternative/Specialized Scripts

- **gym_phase1_expansion.sql** - Basic script to add new tables (may cause errors if tables already exist)
- **check_and_create_tables.sql** - Checks if tables exist before creating them (safer but more complex)
- **verify_and_modify_tables.sql** - Advanced script that checks and adds missing columns to existing tables

## Troubleshooting

### "Table already exists" Error

If you see this error when running scripts:
1. Use `fix_foreign_key_simplified.sql` instead, which drops tables before recreating them

### "Foreign key constraint is incorrectly formed" Error

This happens because foreign key columns need unique indexes. The `fix_foreign_key_simplified.sql` script handles this by:
1. Adding unique indexes to the `coachId` and `memberId` columns
2. Creating the tables with proper foreign key relationships

### Permission Errors

If you see permission errors when accessing information_schema:
1. Use `fix_foreign_key_simplified.sql` which doesn't rely on information_schema

## Admin Management System

### New Admin Management Features

The latest database update includes enhanced admin management capabilities:

1. **Enhanced Admin Table** - Extended with role-based access control
2. **Role Management** - New role table for granular permissions
3. **Activity Logging** - Comprehensive activity tracking

### Admin Management Scripts

- **admin_management_tables.sql** - Adds role-based access control and activity logging
- **verify_admin_tables.sql** - Checks that admin management tables are properly set up
- **migrate_admin_passwords.sql** - Prepares existing accounts for password security upgrade

### Implementing Admin Management

To add admin management to your system:

1. Run `admin_management_tables.sql` to update database structure
2. Run `verify_admin_tables.sql` to confirm tables are correctly set up
3. Use `migrate_admin_passwords.sql` to prepare for password security updates
4. Implement the Java components (adminData, roleData, activityLogData, passwordUtil)
5. Update the UI to include admin management screens

## Next Steps

After setting up the database, proceed to implement the Java data models and UI components as specified in the project implementation plan. 