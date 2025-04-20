use sisdb;

select * from students;
select * from enrollments;
select * from courses;
select * from teacher;
select * from payments;

-- 1
SELECT AVG(enroll_count) AS avg_students_per_course
FROM (
    SELECT COUNT(student_id) AS enroll_count
    FROM Enrollments
    GROUP BY course_id
) AS course_enrollments;
-- 2 
SELECT S.first_name, S.last_name, P.amount
FROM Payments P
JOIN Students S ON S.student_id = P.student_id
WHERE P.amount = (
    SELECT MAX(amount) FROM Payments
);
-- 3
SELECT C.course_name, COUNT(E.student_id) AS enrollments
FROM Courses C
JOIN Enrollments E ON C.course_id = E.course_id
GROUP BY C.course_id, C.course_name
HAVING COUNT(E.student_id) = (
    SELECT MAX(enroll_count) FROM (
        SELECT COUNT(student_id) AS enroll_count
        FROM Enrollments
        GROUP BY course_id
    ) AS sub
);

-- 4
SELECT T.first_name, T.last_name, SUM(P.amount) AS total_course_payment
FROM Teacher T
JOIN Courses C ON T.teacher_id = C.teacher_id
JOIN Enrollments E ON C.course_id = E.course_id
JOIN Payments P ON E.student_id = P.student_id
GROUP BY T.teacher_id, T.first_name, T.last_name;
-- 5
SELECT student_id
FROM Enrollments
GROUP BY student_id
HAVING COUNT(DISTINCT course_id) = (SELECT COUNT(*) FROM Courses);

SELECT first_name, last_name
FROM Teacher
WHERE teacher_id NOT IN (
    SELECT DISTINCT teacher_id FROM Courses WHERE teacher_id IS NOT NULL
);
-- 7
SELECT AVG(age) AS avg_age
FROM (
    SELECT TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) AS age
    FROM Students
) AS sub;
-- 8
SELECT course_name
FROM Courses
WHERE course_id NOT IN (
    SELECT DISTINCT course_id FROM Enrollments
);
-- 9
SELECT E.student_id, E.course_id, SUM(P.amount) AS total_payment
FROM Enrollments E
JOIN Payments P ON E.student_id = P.student_id
GROUP BY E.student_id, E.course_id;

SELECT student_id, COUNT(payment_id) AS num_payments
FROM Payments
GROUP BY student_id
HAVING COUNT(payment_id) > 1;

SELECT S.first_name, S.last_name, SUM(P.amount) AS total_payment
FROM Students S
JOIN Payments P ON S.student_id = P.student_id
GROUP BY S.student_id, S.first_name, S.last_name;
-- 12
SELECT C.course_name, COUNT(E.student_id) AS enrolled_students
FROM Courses C
LEFT JOIN Enrollments E ON C.course_id = E.course_id
GROUP BY C.course_name;
-- 13
SELECT S.student_id, S.first_name, S.last_name, AVG(P.amount) AS avg_payment
FROM Students S
JOIN Payments P ON S.student_id = P.student_id
GROUP BY S.student_id, S.first_name, S.last_name;