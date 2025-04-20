package com.hexaware.dao;

import com.hexaware.entity.*;
import com.hexaware.util.DBConnUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SisDaoImpl implements SisDao {

    private Connection conn;

    public SisDaoImpl() throws SQLException, IOException, ClassNotFoundException {
        conn = DBConnUtil.getConnection();
    }

    // ========== STUDENT METHODS ==========
    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO Student (first_name, last_name, date_of_birth, email, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, Date.valueOf(student.getDateOfBirth()));
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhoneNumber());
            ps.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE Student SET first_name=?, last_name=?, date_of_birth=?, email=?, phone_number=? WHERE student_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, Date.valueOf(student.getDateOfBirth()));
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhoneNumber());
            ps.setInt(6, student.getStudentId());
            ps.executeUpdate();
            System.out.println("Student updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int studentId) {
        Student student = null;
        String sql = "SELECT * FROM Student WHERE student_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student(
                    rs.getInt("student_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("date_of_birth").toLocalDate(),
                    rs.getString("email"),
                    rs.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Student student = new Student(
                    rs.getInt("student_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("date_of_birth").toLocalDate(),
                    rs.getString("email"),
                    rs.getString("phone_number")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM Student WHERE student_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.executeUpdate();
            System.out.println("Student deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ========== COURSE METHODS ==========
    @Override
    public void addCourse(Course course) {
        String sql = "INSERT INTO Course (course_name, course_code, instructor_name) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setString(3, course.getInstructorName());
            ps.executeUpdate();
            System.out.println("Course added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCourse(Course course) {
        String sql = "UPDATE Course SET course_name=?, course_code=?, instructor_name=? WHERE course_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setString(3, course.getInstructorName());
            ps.setInt(4, course.getCourseId());
            ps.executeUpdate();
            System.out.println("Course updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course getCourseById(int courseId) {
        Course course = null;
        String sql = "SELECT * FROM Course WHERE course_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                course = new Course(
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getString("course_code"),
                    rs.getString("instructor_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Course";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getString("course_code"),
                    rs.getString("instructor_name")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM Course WHERE course_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ps.executeUpdate();
            System.out.println("Course deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ========== TEACHER METHODS ==========
    @Override
    public void addTeacher(Teacher teacher) {
        String sql = "INSERT INTO Teacher (first_name, last_name, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getEmail());
            ps.executeUpdate();
            System.out.println("Teacher added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        String sql = "UPDATE Teacher SET first_name=?, last_name=?, email=? WHERE teacher_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getEmail());
            ps.setInt(4, teacher.getTeacherId());
            ps.executeUpdate();
            System.out.println("Teacher updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        Teacher teacher = null;
        String sql = "SELECT * FROM Teacher WHERE teacher_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher(
                    rs.getInt("teacher_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM Teacher";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Teacher teacher = new Teacher(
                    rs.getInt("teacher_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email")
                );
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public void assignTeacherToCourse(int courseId, int teacherId) {
        String sql = "UPDATE Course SET instructor_name=(SELECT CONCAT(first_name, ' ', last_name) FROM Teacher WHERE teacher_id=?) WHERE course_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teacherId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
            System.out.println("Teacher assigned to course successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ========== ENROLLMENT METHODS ==========
    @Override
    public void enrollStudent(Enrollment enrollment) {
        String sql = "INSERT INTO Enrollment (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));
            ps.executeUpdate();
            System.out.println("Student enrolled successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollment WHERE student_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                    rs.getInt("enrollment_id"),
                    rs.getInt("student_id"),
                    rs.getInt("course_id"),
                    rs.getDate("enrollment_date").toLocalDate()
                );
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollment WHERE course_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                    rs.getInt("enrollment_id"),
                    rs.getInt("student_id"),
                    rs.getInt("course_id"),
                    rs.getDate("enrollment_date").toLocalDate()
                );
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    @Override
    public void deleteEnrollment(int enrollmentId) {
        String sql = "DELETE FROM Enrollment WHERE enrollment_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, enrollmentId);
            ps.executeUpdate();
            System.out.println("Enrollment deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ========== PAYMENT METHODS ==========
    @Override
    public void makePayment(Payment payment) {
        String sql = "INSERT INTO Payment (student_id, amount, payment_date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, payment.getStudentId());
            ps.setDouble(2, payment.getAmount());
            ps.setDate(3, Date.valueOf(payment.getPaymentDate()));
            ps.executeUpdate();
            System.out.println("Payment recorded successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payment> getPaymentsByStudentId(int studentId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payment WHERE student_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("student_id"),
                    rs.getDouble("amount"),
                    rs.getDate("payment_date").toLocalDate()
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    // ========== REPORTS ==========
    @Override
    public void generateEnrollmentReport(int courseId) {
        String sql = "SELECT s.student_id, s.first_name, s.last_name, c.course_name " +
                     "FROM Student s " +
                     "INNER JOIN Enrollment e ON s.student_id = e.student_id " +
                     "INNER JOIN Course c ON e.course_id = c.course_id " +
                     "WHERE c.course_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            String courseName = "";

            while (rs.next()) {
                if (!found) {
                    courseName = rs.getString("course_name");
                    System.out.println("\n===== Enrollment Report =====");
                    System.out.println("Course Name: " + courseName);
                    System.out.println("\nEnrolled Students:");
                }
                found = true;
                System.out.println("Student ID: " + rs.getInt("student_id"));
                System.out.println("Name       : " + rs.getString("first_name") + " " + rs.getString("last_name"));
                System.out.println("---------------------------------------");
            }

            if (!found) {
                System.out.println("No students enrolled in this course.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void generatePaymentReport(int studentId) {
        List<Payment> payments = getPaymentsByStudentId(studentId);
        if (payments.isEmpty()) {
            System.out.println("No payments made by this student.");
        } else {
            System.out.println("Payment Report for Student ID: " + studentId);
            for (Payment payment : payments) {
                System.out.println("Payment ID: " + payment.getPaymentId() + ", Amount: " + payment.getAmount() + ", Date: " + payment.getPaymentDate());
            }
        }
    }
}
