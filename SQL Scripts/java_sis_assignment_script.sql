Create Database if not exists java_sis_assignment;
use java_sis_assignment;

-- Create Student Table
CREATE TABLE Student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15)
);

-- Create Course Table
CREATE TABLE Course (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    course_code VARCHAR(20) UNIQUE NOT NULL,
    instructor_name VARCHAR(100) NOT NULL
);

-- Create Teacher Table
CREATE TABLE Teacher (
    teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Create Enrollment Table
CREATE TABLE Enrollment (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enrollment_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE CASCADE
);

-- Create Payment Table
CREATE TABLE Payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE
);

-- Insert records into Student Table
INSERT INTO Student (first_name, last_name, date_of_birth, email, phone_number) VALUES
('John', 'Doe', '2000-05-15', 'john.doe@example.com', '1234567890'),
('Jane', 'Smith', '1999-08-22', 'jane.smith@example.com', '2345678901'),
('Alice', 'Johnson', '2001-12-05', 'alice.johnson@example.com', '3456789012'),
('Bob', 'Brown', '1998-03-30', 'bob.brown@example.com', '4567890123'),
('Charlie', 'Davis', '2000-01-18', 'charlie.davis@example.com', '5678901234');

-- Insert records into Course Table
INSERT INTO course (course_name, course_code, instructor_name) VALUES
('Mathematics 101', 'MATH101', 'Dr. A. Taylor'),
('Physics 101', 'PHYS101', 'Dr. B. Robinson'),
('Computer Science 101', 'CS101', 'Dr. C. Lee'),
('Chemistry 101', 'CHEM101', 'Dr. D. Williams'),
('Biology 101', 'BIO101', 'Dr. E. Clark');

-- Insert records into Teacher Table
INSERT INTO teacher (first_name, last_name, email) VALUES
('Adam', 'Taylor', 'adam.taylor@university.com'),
('Beth', 'Robinson', 'beth.robinson@university.com'),
('Charles', 'Lee', 'charles.lee@university.com'),
('Dana', 'Williams', 'dana.williams@university.com'),
('Eve', 'Clark', 'eve.clark@university.com');

-- Insert records into Enrollment Table
INSERT INTO Enrollment (student_id, course_id, enrollment_date) VALUES
(1, 1, '2025-01-10'),
(2, 2, '2025-01-12'),
(3, 3, '2025-01-15'),
(4, 4, '2025-01-18'),
(5, 5, '2025-01-20');

-- Insert records into Payment Table
INSERT INTO Payment (student_id, amount, payment_date) VALUES
(1, 500.00, '2025-01-11'),
(2, 450.00, '2025-01-13'),
(3, 550.00, '2025-01-16'),
(4, 400.00, '2025-01-19'),
(5, 600.00, '2025-01-21');
