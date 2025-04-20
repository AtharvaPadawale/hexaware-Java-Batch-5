use sisdb;

-- 1
SELECT S.first_name, S.last_name, SUM(P.amount) AS total_payment
FROM Students S
JOIN Payments P ON S.student_id = P.student_id
WHERE S.student_id = 102
GROUP BY S.first_name, S.last_name;

-- 2
SELECT C.course_name, COUNT(E.student_id) AS student_count
FROM Courses C
LEFT JOIN Enrollments E ON C.course_id = E.course_id
GROUP BY C.course_name;

-- 3
SELECT S.first_name, S.last_name
FROM Students S
LEFT JOIN Enrollments E ON S.student_id = E.student_id
WHERE E.enrollment_id IS NULL;

SELECT S.first_name, S.last_name, C.course_name
FROM Students S
JOIN Enrollments E ON S.student_id = E.student_id
JOIN Courses C ON E.course_id = C.course_id;

-- 5
SELECT T.first_name, T.last_name, C.course_name
FROM Teacher T
JOIN Courses C ON T.teacher_id = C.teacher_id;

-- 6
SELECT S.first_name, S.last_name, E.enrollment_date, C.course_name
FROM Students S
JOIN Enrollments E ON S.student_id = E.student_id
JOIN Courses C ON E.course_id = C.course_id
WHERE C.course_id = 201;

-- 7
SELECT S.first_name, S.last_name
FROM Students S
LEFT JOIN Payments P ON P.student_id = S.student_id
WHERE P.payment_id IS NULL;

SELECT C.course_name
FROM Courses C
LEFT JOIN Enrollments E ON C.course_id = E.course_id
WHERE E.enrollment_id IS NULL;

-- 9 
SELECT DISTINCT S.student_id, S.first_name, S.last_name
FROM Enrollments E1
JOIN Enrollments E2 ON E1.student_id = E2.student_id AND E1.course_id <> E2.course_id
JOIN Students S ON E1.student_id = S.student_id;


-- 10
Select t.first_name, t.last_name
From teacher t
left join courses c on c.teacher_id = t.teacher_id
where c.teacher_id IS NULL;