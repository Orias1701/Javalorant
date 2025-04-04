-- Active: 1743462407381@@127.0.0.1@3306@accounts

USE accounts;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    role VARCHAR(50) NOT NULL
) COMMENT 'Danh sách user';

INSERT INTO users (username, password, enabled, role) VALUES
('admin', 'admin123', TRUE, 'ROLE_ADMIN'),
('user1', 'user1', TRUE, 'ROLE_USER'),
('user2', 'user2', TRUE, 'ROLE_USER')

UPDATE users
SET username = 'a'
WHERE password = 'a';