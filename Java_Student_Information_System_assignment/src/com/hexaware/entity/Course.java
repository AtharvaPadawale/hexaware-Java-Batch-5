package com.hexaware.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseId;
    private String courseName;
    private String courseCode;
    private String instructorName;
    private Teacher teacher;
    private List<Enrollment> enrollments;

    // Constructor
    public Course(int courseId, String courseName, String courseCode, String instructorName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructorName = instructorName;
        this.enrollments = new ArrayList<>();
    }

    // Assign a teacher
    public void assignTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    // Update course info
    public void updateCourseInfo(String courseCode, String courseName, String instructorName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructorName = instructorName;
    }

    // Display course info
    public void displayCourseInfo() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Course Name: " + courseName);
        System.out.println("Course Code: " + courseCode);
        System.out.println("Instructor Name: " + instructorName);
    }

    // Get list of enrollments
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    // Get assigned teacher
    public Teacher getTeacher() {
        return teacher;
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
}
