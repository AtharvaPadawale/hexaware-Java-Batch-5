/* SQL coding challenge: CarrerHub The Job Board 
   (SQL Script)
   
   Name: Atharva Padawale
*/

CREATE DATABASE IF NOT EXISTS CareerHub;
USE CareerHub;

CREATE TABLE IF NOT EXISTS Companies (
    CompanyID INT PRIMARY KEY,
    CompanyName VARCHAR(50),
    Location VARCHAR(100)
);

INSERT INTO Companies (CompanyID, CompanyName, Location) VALUES
(1, 'Google', 'Bangalore'),
(2, 'Amazon', 'Hyderabad'),
(3, 'TechMahindra', 'Pune'),
(4, 'Hexaware', 'Chennai'),
(5, 'TCS', 'Pune');

CREATE TABLE IF NOT EXISTS Jobs (
    JobID INT PRIMARY KEY,
    CompanyID INT,
    JobTitle VARCHAR(50),
    JobDescription TEXT,
    JobLocation VARCHAR(50),
    Salary DECIMAL(10,2),
    JobType VARCHAR(50),
    PostedDate DATETIME,
    FOREIGN KEY (CompanyID) REFERENCES Companies(CompanyID)
);

INSERT INTO Jobs (JobID, CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) VALUES
(11, 1, 'Software Tester', 'test and maintain software applications.', 'Bangalore', 100000, 'Full-time', '2024-07-01 10:00:00'),
(12, 2, 'Data Analyst', 'Analyze data.', 'Hyderabad', 200000, 'Full-time', '2024-07-02 11:00:00'),
(13, 3, 'Cloud Architect', 'Manage cloud architecture.', 'Pune', 300000, 'Full-time', '2024-07-03 12:00:00'),
(14, 4, 'Tableu Analyst', 'Analyzing data and creating dashboards.', 'Chennai', 4000000, 'Contract', '2024-07-04 09:30:00'),
(15, 5, 'AIML Engineer', 'Develop AI-based applications.', 'Pune', 700000, 'Full-time', '2025-07-05 08:45:00'),
(16, 2, 'Data Analyst', 'Analyze data.', 'Hyderabad', NULL, 'internship', '2024-07-02 11:00:00'),
(17, 1, 'IT Support ', 'desk support', 'Bangalore', 65000, 'Full-time', '2024-07-01 10:00:00');


CREATE TABLE IF NOT EXISTS Applicants (
    ApplicantID INT PRIMARY KEY,
    FirstName VARCHAR(100),
    LastName VARCHAR(100),
    Email VARCHAR(255),
    Phone VARCHAR(20),
    Resume TEXT
);


INSERT INTO Applicants (ApplicantID, FirstName, LastName, Email, Phone, Resume) VALUES
(101, 'atharva', 'patil', 'patil@email.com', '9877673210', 'Resume link 1'),
(102, 'Tejsinh', 'Bhosale', 'tejsinh@email.com', '9866661470', 'Resume link 2'),
(103, 'patil', 'Deshpande', 'patil@email.com', '9854444698', 'Resume link 3'),
(104, 'aditya', 'sarate', 'patil@email.com', '989888856', 'Resume link 4'),
(105, 'utkarsh', 'chou', 'utkarsh@email.com', '9786544460', 'Resume link 5');


CREATE TABLE IF NOT EXISTS Applications (
    ApplicationID INT PRIMARY KEY,
    JobID INT,
    ApplicantID INT,
    ApplicationDate DATETIME,
    CoverLetter TEXT,
    FOREIGN KEY (JobID) REFERENCES Jobs(JobID),
    FOREIGN KEY (ApplicantID) REFERENCES Applicants(ApplicantID)
);

INSERT INTO Applications (ApplicationID, JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES
(111, 11, 101,'2024-07-06 14:00:00', 'I am excited to apply for this role at Google.'),
(112, 12, 102,'2024-07-07 15:30:00', 'I have strong experience in data science and machine learning.'),
(113, 13, 103, '2024-07-08 16:00:00', 'I am a certified AWS cloud architect with 4 years of experience.'),
(114, 14, 104, '2024-07-09 10:00:00', 'Passionate about analyzing and predicting data with hands-on experience.'),
(115, 15, 105, '2024-07-10 11:30:00', 'Interested in AI applications and deep learning.');


-- Q 5
SELECT j.JobTitle, COUNT(a.ApplicationID) AS ApplicationCount
FROM Jobs j
LEFT JOIN Applications a ON j.JobID = a.JobID
GROUP BY j.JobID, j.JobTitle;

-- Q 6
SELECT j.JobTitle, c.CompanyName, j.JobLocation, j.Salary
FROM Jobs j
JOIN Companies c ON j.CompanyID = c.CompanyID
WHERE j.Salary BETWEEN 100000 AND 400000;


-- Q 7
SELECT j.JobTitle, c.CompanyName, a.ApplicationDate
FROM Applications a
JOIN Jobs j ON a.JobID = j.JobID
JOIN Companies c ON j.CompanyID = c.CompanyID
WHERE a.ApplicantID = 104; 


-- Q8
SELECT AVG(Salary) AS Average_Salary
FROM Jobs
WHERE Salary > 0;

-- Q9
SELECT c.CompanyName, COUNT(j.JobID) AS JobCount
FROM Companies c
JOIN Jobs j ON c.CompanyID = j.CompanyID
GROUP BY c.CompanyID
HAVING JobCount = (
    SELECT MAX(JobCount) FROM (
        SELECT COUNT(JobID) AS JobCount
        FROM Jobs
        GROUP BY CompanyID
    ) AS SubQuery
);

-- Q10
SELECT a.firstname, a.lastname, j.jobTitle, c.location, a.experience
FROM Applicants a 
JOIN Applications ap ON a.ApplicantID = ap.ApplicantID
JOIN Jobs j ON ap.JobID = j.JobID 
JOIN Companies c ON j.CompanyID = c.CompanyID
WHERE c.Location = 'chennai' AND a.experience >= 3;


-- Q11
SELECT DISTINCT JobTitle
FROM Jobs
WHERE Salary BETWEEN 60000 AND 80000;

-- Q12
SELECT JobTitle
FROM Jobs j
LEFT JOIN Applications a ON j.JobID = a.JobID
WHERE a.ApplicationID IS NULL;

-- Q13
SELECT a.FirstName, a.LastName, c.CompanyName, j.JobTitle
FROM Applications ap
JOIN Applicants a ON ap.ApplicantID = a.ApplicantID
JOIN Jobs j ON ap.JobID = j.JobID
JOIN Companies c ON j.CompanyID = c.CompanyID;

-- Q14
SELECT c.CompanyName, COUNT(j.JobID) AS JobCount
FROM Companies c
LEFT JOIN Jobs j ON c.CompanyID = j.CompanyID
GROUP BY c.CompanyID;

-- Q15
SELECT a.FirstName, a.LastName, c.CompanyName, j.JobTitle
FROM Applicants a
LEFT JOIN Applications ap ON a.ApplicantID = ap.ApplicantID
LEFT JOIN Jobs j ON ap.JobID = j.JobID
LEFT JOIN Companies c ON j.CompanyID = c.CompanyID;

-- Q16
SELECT DISTINCT c.CompanyName
FROM Companies c
JOIN Jobs j ON c.CompanyID = j.CompanyID
WHERE j.Salary > (
    SELECT AVG(Salary)
    FROM Jobs
    WHERE Salary > 0 );

-- Q17

SELECT FirstName, LastName, CONCAT(City, ', ', State) AS Location 
FROM Applicants;

-- Q18
SELECT * FROM Jobs
WHERE JobTitle LIKE '%Developer%' OR JobTitle LIKE '%Engineer%';

-- Q19
SELECT a.FirstName, a.LastName, j.JobTitle
FROM Applicants a
LEFT JOIN Applications ap ON a.ApplicantID = ap.ApplicantID
LEFT JOIN Jobs j ON ap.JobID = j.JobID
UNION
SELECT a.FirstName, a.LastName, j.JobTitle
FROM Jobs j
LEFT JOIN Applications ap ON j.JobID = ap.JobID
LEFT JOIN Applicants a ON ap.ApplicantID = a.ApplicantID;

-- Q20
SELECT a.FirstName, a.LastName, c.CompanyName, c.Location
FROM Applicants a
CROSS JOIN Companies c
WHERE c.Location = 'Chennai'
  AND a.Experience > 2;
/*
SELECT a.FirstName, a.LastName, c.CompanyName, c.Location
FROM Applicants a
JOIN Applications app ON a.ApplicantID = app.ApplicantID
JOIN Jobs j ON app.JobID = j.JobID
JOIN Companies c ON j.CompanyID = c.CompanyID
WHERE c.Location = 'Chennai'
  AND a.Experience > 2;
*/




-- for Experiance columns and City and state columns in applicant table

-- alter table applicants 
-- ADD experience int;
-- ADD city VARCHAR(100),
-- ADD state VARCHAR(100);

-- UPDATE Applicants
-- SET Experience = 3
-- WHERE ApplicantID = 101;

-- UPDATE Applicants
-- SET Experience = 5
-- WHERE ApplicantID = 102;

-- UPDATE Applicants
-- SET Experience = 2
-- WHERE ApplicantID = 103;

-- UPDATE Applicants
-- SET Experience = 4
-- WHERE ApplicantID = 104;

-- UPDATE Applicants
-- SET Experience = 1
-- WHERE ApplicantID = 105;

-- UPDATE Applicants
-- SET City = 'Mumbai', State = 'Maharashtra'
-- WHERE ApplicantID = 101;

-- UPDATE Applicants
-- SET City = 'Pune', State = 'Maharashtra'
-- WHERE ApplicantID = 102;

-- UPDATE Applicants
-- SET City = 'Bengaluru', State = 'Karnataka'
-- WHERE ApplicantID = 103;

-- UPDATE Applicants
-- SET City = 'Hyderabad', State = 'Telangana'
-- WHERE ApplicantID = 104;

-- UPDATE Applicants
-- SET City = 'Chennai', State = 'Tamil Nadu'
-- WHERE ApplicantID = 105;
