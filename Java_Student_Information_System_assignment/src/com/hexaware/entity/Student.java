package com.hexaware.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private List<Course> enrolledCourses;
    private List<Payment> paymentHistory;

    // Constructor
    public Student(int studentId, String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enrolledCourses = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
    }

    // Enroll student in a course
    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    // Update student information
    public void updateStudentInfo(String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Record a payment
    public void makePayment(double amount, LocalDate paymentDate) {
        Payment payment = new Payment(paymentHistory.size() + 1, this.studentId, amount, paymentDate);
        paymentHistory.add(payment);
    }

    // Display detailed student information
    public void displayStudentInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("DOB: " + dateOfBirth);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    // Get list of enrolled courses
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Get payment history
    public List<Payment> getPaymentHistory() {
        return paymentHistory;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
