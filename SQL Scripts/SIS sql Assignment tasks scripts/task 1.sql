create database sisdb;
use sisdb;
show tables;
desc students;

CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE,
    email VARCHAR(100) UNIQUE,
    phone_number VARCHAR(15)
);

CREATE TABLE Teacher (
    teacher_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE Courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100),
    credits INT,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id)
);

CREATE TABLE Enrollments (
    enrollment_id INT PRIMARY KEY,
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

CREATE TABLE Payments (
    payment_id INT PRIMARY KEY,
    student_id INT,
    amount DECIMAL(10 , 2 ),
    payment_date DATE,
    FOREIGN KEY (student_id)
        REFERENCES Students (student_id)
);

-- 5. Insert Sample Records
-- Students
INSERT INTO Students (student_id, first_name, last_name, date_of_birth, email, phone_number) VALUES
(101, 'John', 'Doe', '1995-08-15', 'john.doe@example.com', '1234567890'),
(102, 'Emma', 'Watson', '1998-04-15', 'emma.w@example.com', '1234509876'),
(103, 'Liam', 'Smith', '2000-12-02', 'liam.s@example.com', '9876543210'),
(104, 'Olivia', 'Johnson', '1997-06-09', 'olivia.j@example.com', '8765432109'),
(105, 'Noah', 'Brown', '2002-03-19', 'noah.b@example.com', '7894561230'),
(106, 'Ava', 'Davis', '1999-01-25', 'ava.d@example.com', '9012345678'),
(107, 'William', 'Wilson', '2001-11-10', 'will.w@example.com', '8889997776'),
(108, 'Sophia', 'Moore', '1996-07-04', 'sophia.m@example.com', '7776665554'),
(109, 'James', 'Taylor', '1998-09-23', 'james.t@example.com', '6665554443'),
(110, 'Isabella', 'Anderson', '2000-05-14', 'isabella.a@example.com', '5554443332');

-- Teacher
INSERT INTO Teacher (teacher_id, first_name, last_name, email) VALUES
(1, 'Alan', 'Turing', 'alan.turing@example.com'),
(2, 'Grace', 'Hopper', 'grace.hopper@example.com'),
(3, 'Ada', 'Lovelace', 'ada.l@example.com'),
(4, 'Marie', 'Curie', 'marie.curie@example.com'),
(5, 'Nikola', 'Tesla', 'nikola.t@example.com'),
(6, 'Albert', 'Einstein', 'albert.e@example.com'),
(7, 'Katherine', 'Johnson', 'katherine.j@example.com'),
(8, 'Stephen', 'Hawking', 'stephen.h@example.com'),
(9, 'Carl', 'Sagan', 'carl.sagan@example.com'),
(10, 'Jane', 'Goodall', 'jane.g@example.com');


-- Courses
INSERT INTO Courses (course_id, course_name, credits, teacher_id) VALUES
(201, 'Mathematics', 4, 1),
(202, 'Physics', 3, 2),
(203, 'Computer Science', 5, 3),
(204, 'Chemistry', 3, 4),
(205, 'Electrical Engineering', 4, 5),
(206, 'Artificial Intelligence', 5, 6),
(207, 'Astronomy', 3, 7),
(208, 'Quantum Mechanics', 4, 8),
(209, 'Philosophy of Science', 2, 9),
(210, 'Environmental Science', 3, 10);

-- Enrollments
INSERT INTO Enrollments (enrollment_id, student_id, course_id, enrollment_date) VALUES
(301, 101, 201, '2024-01-10'),
(302, 102, 202, '2024-01-12'),
(303, 103, 203, '2024-01-15'),
(304, 104, 204, '2024-01-17'),
(305, 105, 205, '2024-01-19'),
(306, 106, 206, '2024-01-21'),
(307, 107, 207, '2024-01-23'),
(308, 108, 208, '2024-01-25'),
(309, 109, 209, '2024-01-27'),
(310, 110, 210, '2024-01-29');


-- Payments
INSERT INTO Payments (payment_id, student_id, amount, payment_date) VALUES
(401, 101, 1500.00, '2024-02-01'),
(402, 102, 1600.00, '2024-02-03'),
(403, 103, 1700.00, '2024-02-05'),
(404, 104, 1800.00, '2024-02-07'),
(405, 105, 1900.00, '2024-02-09'),
(406, 106, 2000.00, '2024-02-11'),
(407, 107, 2100.00, '2024-02-13'),
(408, 108, 2200.00, '2024-02-15'),
(409, 109, 2300.00, '2024-02-17'),
(410, 110, 2400.00, '2024-02-19');


desc teacher;
desc courses;
desc students;
desc enrollments;
desc payments;


