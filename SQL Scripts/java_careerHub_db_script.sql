-- create database if not exists java_careerhub;
use java_careerhub;

-- 1. Table: Company
CREATE TABLE Company (
    CompanyID INT PRIMARY KEY,
    CompanyName VARCHAR(100) NOT NULL,
    Location VARCHAR(100) NOT NULL
);

-- 2. Table: Applicant
CREATE TABLE Applicant (
    ApplicantID INT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Phone VARCHAR(15),
    Resume TEXT
);

-- 3. Table: JobListing
CREATE TABLE JobListing (
    JobID INT PRIMARY KEY,
    CompanyID INT NOT NULL,
    JobTitle VARCHAR(100) NOT NULL,
    JobDescription TEXT,
    JobLocation VARCHAR(100),
    Salary DECIMAL(10, 2) CHECK (Salary >= 0),
    JobType VARCHAR(50),
    PostedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CompanyID) REFERENCES Company(CompanyID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- 4. Table: JobApplication
CREATE TABLE JobApplication (
    ApplicationID INT PRIMARY KEY,
    JobID INT NOT NULL,
    ApplicantID INT NOT NULL,
    ApplicationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    CoverLetter TEXT,
    FOREIGN KEY (JobID) REFERENCES JobListing(JobID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ApplicantID) REFERENCES Applicant(ApplicantID)
        ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Company (CompanyID, CompanyName, Location) VALUES
(1, 'TechCorp', 'New York'),
(2, 'InnoWave', 'San Francisco'),
(3, 'DataNimbus', 'Chicago'),
(4, 'BrightMind', 'Boston'),
(5, 'SkyLine Solutions', 'Austin'),
(6, 'CyberVista', 'Seattle'),
(7, 'NovaCore', 'Denver');

-- Insert sample Applicants
INSERT INTO Applicant (ApplicantID, FirstName, LastName, Email, Phone, Resume) VALUES
(101, 'Alice', 'Walker', 'alice.walker@example.com', '1234567890', 'resume_alice.pdf'),
(102, 'Bob', 'Smith', 'bob.smith@example.com', '1234567891', 'resume_bob.pdf'),
(103, 'Charlie', 'Brown', 'charlie.brown@example.com', '1234567892', 'resume_charlie.pdf'),
(104, 'Diana', 'Prince', 'diana.prince@example.com', '1234567893', 'resume_diana.pdf'),
(105, 'Ethan', 'Clark', 'ethan.clark@example.com', '1234567894', 'resume_ethan.pdf'),
(106, 'Fiona', 'Evans', 'fiona.evans@example.com', '1234567895', 'resume_fiona.pdf'),
(107, 'George', 'Harrison', 'george.harrison@example.com', '1234567896', 'resume_george.pdf');

-- Insert sample Job Listings
INSERT INTO JobListing (JobID, CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES
(201, 1, 'Java Developer', 'Develop enterprise applications', 'New York', 75000.00, 'Full-time', '2025-04-01 10:00:00'),
(202, 2, 'Data Analyst', 'Analyze market data', 'San Francisco', 68000.00, 'Contract', '2025-04-02 11:00:00'),
(203, 3, 'DevOps Engineer', 'Manage CI/CD pipelines', 'Chicago', 80000.00, 'Full-time', '2025-04-03 12:00:00'),
(204, 4, 'QA Tester', 'Manual and automation testing', 'Boston', 60000.00, 'Part-time', '2025-04-04 09:30:00'),
(205, 5, 'UI/UX Designer', 'Design responsive UIs', 'Austin', 70000.00, 'Full-time', '2025-04-05 14:00:00'),
(206, 6, 'Project Manager', 'Handle agile projects', 'Seattle', 90000.00, 'Full-time', '2025-04-06 16:00:00'),
(207, 7, 'Cloud Engineer', 'Deploy on AWS', 'Denver', 85000.00, 'Contract', '2025-04-07 15:00:00');


-- Insert sample Job Applications
INSERT INTO JobApplication (ApplicationID, JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES
(301, 201, 101, '2025-04-08 10:15:00', 'Excited to contribute as Java Developer'),
(302, 202, 102, '2025-04-08 11:30:00', 'My analytics background suits this role'),
(303, 203, 103, '2025-04-08 12:00:00', 'Strong DevOps experience detailed in resume'),
(304, 204, 104, '2025-04-08 13:45:00', 'Skilled in Selenium and JIRA'),
(305, 205, 105, '2025-04-08 14:10:00', 'Creative UI/UX designer with Figma skills'),
(306, 206, 106, '2025-04-08 15:30:00', 'Proven agile project management expertise'),
(307, 207, 107, '2025-04-08 16:20:00', 'AWS certified engineer looking forward');