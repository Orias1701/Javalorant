-- Test
CREATE TABLE IF NOT EXISTS HotelCustomers (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerName NVARCHAR(255) NOT NULL,
    Email NVARCHAR(255) NOT NULL
);

INSERT INTO HotelCustomers (CustomerName, Email) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com'),
('Alice Johnson', 'alice.johnson@example.com'),
('Bob Brown', 'bob.brown@example.com'),
('Charlie Davis', 'charlie.davis@example.com'),
('Diana Evans', 'diana.evans@example.com'),
('Frank Green', 'frank.green@example.com'),
('Grace Harris', 'grace.harris@example.com'),
('Henry Jackson', 'henry.jackson@example.com'),
('Ivy King', 'ivy.king@example.com');

SELECT * FROM HotelCustomers;