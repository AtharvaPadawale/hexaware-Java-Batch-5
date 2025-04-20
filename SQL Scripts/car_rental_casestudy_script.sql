create database if not exists car_rental_casestudy;
use car_rental_casestudy;

-- 1. Vehicle Table
CREATE TABLE Vehicle (
    vehicleID INT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    dailyRate DECIMAL(10,2) NOT NULL,
    status ENUM('available', 'notAvailable') DEFAULT 'available',
    passengerCapacity INT,
    engineCapacity DECIMAL(5,2)
);

-- 2. Customer Table
CREATE TABLE Customer (
    customerID INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL
);

-- 3. Lease Table
CREATE TABLE Lease (
    leaseID INT PRIMARY KEY AUTO_INCREMENT,
    vehicleID INT,
    customerID INT,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    type ENUM('Daily', 'Monthly') NOT NULL,
    FOREIGN KEY (vehicleID) REFERENCES Vehicle(vehicleID),
    FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);

-- 4. Payment Table
CREATE TABLE Payment (
    paymentID INT PRIMARY KEY AUTO_INCREMENT,
    leaseID INT,
    paymentDate DATE NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (leaseID) REFERENCES Lease(leaseID)
);


-- Insert into Vehicle table
INSERT INTO Vehicle (make, model, year, dailyRate, status, passengerCapacity, engineCapacity) VALUES
('Toyota', 'Camry', 2020, 45.00, 'available', 5, 2.5),
('Honda', 'Civic', 2019, 40.00, 'available', 5, 2.0),
('Ford', 'Explorer', 2021, 70.00, 'notAvailable', 7, 3.5),
('Tesla', 'Model 3', 2022, 90.00, 'available', 5, 0.0),
('Chevrolet', 'Malibu', 2018, 38.00, 'available', 5, 2.0);

-- Insert into Customer table
INSERT INTO Customer (firstName, lastName, email, phoneNumber) VALUES
('John', 'Doe', 'john.doe@example.com', '1234567890'),
('Jane', 'Smith', 'jane.smith@example.com', '0987654321'),
('Mike', 'Johnson', 'mike.johnson@example.com', '1122334455'),
('Emily', 'Davis', 'emily.davis@example.com', '2233445566');

-- Insert into Lease table
INSERT INTO Lease (vehicleID, customerID, startDate, endDate, type) VALUES
(1, 1, '2025-04-01', '2025-04-07', 'Daily'),
(2, 2, '2025-03-15', '2025-04-15', 'Monthly'),
(4, 3, '2025-04-10', '2025-04-17', 'Daily');

-- Insert into Payment table
INSERT INTO Payment (leaseID, paymentDate, amount) VALUES
(1, '2025-04-01', 315.00),
(2, '2025-03-15', 1200.00),
(3, '2025-04-10', 630.00);
