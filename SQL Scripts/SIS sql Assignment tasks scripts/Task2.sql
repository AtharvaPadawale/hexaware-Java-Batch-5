use sisdb;
show tables;
select * from students;
select * from enrollments;
select * from teacher;
select * from payments;
select * from courses;

delete from enrollments
where student_id = 101;
delete from payments
where student_id = 101;
Delete from students
where student_id = 101;

insert into students (student_id,first_name,last_name,date_of_birth,email,phone_number)
values (101,'John','Doe','1995-08-15','john.doe@example.com','1234567890 ');

INSERT INTO Enrollments (enrollment_id, student_id, course_id, enrollment_date)
VALUES (301, 101, 201, '2025-04-09');

INSERT INTO Enrollments (enrollment_id, student_id, course_id, enrollment_date)
VALUES (311, 103, 201, '2026-04-09');

update teacher 
set email = 'xyz@abc.com'
where teacher_id = 1;

DELETE FROM Enrollments
WHERE student_id = 101 AND course_id = 201;


DELETE FROM enrollments
WHERE student_id = 101;
DELETE FROM students
WHERE student_id = 101;


UPDATE Courses
SET teacher_id = 1
WHERE course_id = 204;


UPDATE Payments
SET amount = 9999
WHERE payment_id = 402;