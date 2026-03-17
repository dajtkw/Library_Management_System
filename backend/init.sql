-- Initialize database for Library Management System
CREATE DATABASE IF NOT EXISTS library_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE library_management;

-- The tables will be created automatically by Hibernate (ddl-auto: update)
-- This file is optional and can be used to insert initial data

-- Example: Insert sample data (uncomment if needed)
-- INSERT INTO users (name, email, phone, created_at, updated_at) VALUES
-- ('Admin User', 'admin@library.com', '0123456789', NOW(), NOW()),
-- ('Test User', 'test@library.com', '0987654321', NOW(), NOW());

-- INSERT INTO books (title, author, isbn, quantity, available_quantity, created_at, updated_at) VALUES
-- ('Clean Code', 'Robert C. Martin', '9780132350884', 5, 5, NOW(), NOW()),
-- ('Design Patterns', 'Gang of Four', '9780201633610', 3, 3, NOW(), NOW()),
-- ('Spring in Action', 'Craig Walls', '9781617294945', 4, 4, NOW(), NOW());
