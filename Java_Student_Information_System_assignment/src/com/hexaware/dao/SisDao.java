package com.hexaware.dao;

import com.hexaware.entity.*;
import java.util.List;

public interface SisDao {
    
    // Student operations
    void addStudent(Student student);
    void updateStudent(Student student);
    Student getStudentById(int studentId);
    List<Student> getAllStudents();
    void deleteStudent(int studentId);

    // Course operations
    void addCourse(Course course);
    void updateCourse(Course course);
    Course getCourseById(int courseId);
    List<Course> getAllCourses();
    void deleteCourse(int courseId);

    // Teacher operations
    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    Teacher getTeacherById(int teacherId);
    List<Teacher> getAllTeachers();
    void assignTeacherToCourse(int courseId, int teacherId);

    // Enrollment operations
    void enrollStudent(Enrollment enrollment);
    List<Enrollment> getEnrollmentsByStudentId(int studentId);
    List<Enrollment> getEnrollmentsByCourseId(int courseId);
    void deleteEnrollment(int enrollmentId);

    // Payment operations
    void makePayment(Payment payment);
    List<Payment> getPaymentsByStudentId(int studentId);

    // Reports
    void generateEnrollmentReport(int CourseId);
    void generatePaymentReport(int studentId);
}
