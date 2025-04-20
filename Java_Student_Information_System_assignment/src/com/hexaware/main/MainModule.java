package com.hexaware.main;

import com.hexaware.dao.SisDao;
import com.hexaware.dao.SisDaoImpl;
import com.hexaware.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;
import java.sql.*;
public class MainModule {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException{
        Scanner sc = new Scanner(System.in);
        SisDao sisDao = new SisDaoImpl();
        int choice;

        do {
            System.out.println("\n===== Student Information System (SIS) =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Enroll Student to Course");
            System.out.println("4. Add Course");
            System.out.println("5. Add Teacher");
            System.out.println("6. Assign Teacher to Course");
            System.out.println("7. Make Payment");
            System.out.println("8. View Student Payment History");
            System.out.println("9. Generate Enrollment Report");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            try {
                switch (choice) {
                    case 1:
                        addStudent(sc, sisDao);
                        break;
                    case 2:
                        viewAllStudents(sisDao);
                        break;
                    case 3:
                        enrollStudent(sc, sisDao);
                        break;
                    case 4:
                        addCourse(sc, sisDao);
                        break;
                    case 5:
                        addTeacher(sc, sisDao);
                        break;
                    case 6:
                        assignTeacherToCourse(sc, sisDao);
                        break;
                    case 7:
                        makePayment(sc, sisDao);
                        break;
                    case 8:
                        viewStudentPayments(sc, sisDao);
                        break;
                    case 9:
                        generateEnrollmentReport(sc, sisDao);
                        break;
                    case 0:
                        System.out.println("Thank you! Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);

        sc.close();
    }

    private static void addStudent(Scanner sc, SisDao sisDao) {
        System.out.println("\n--- Add Student ---");
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
        LocalDate dob = LocalDate.parse(sc.nextLine());
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        Student student = new Student(0, firstName, lastName, dob, email, phone);
        sisDao.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void viewAllStudents(SisDao sisDao) {
        System.out.println("\n--- View All Students ---");
        List<Student> students = sisDao.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                s.displayStudentInfo();
                System.out.println("--------------------------------");
            }
        }
    }

    private static void enrollStudent(Scanner sc, SisDao sisDao) {
        System.out.println("\n--- Enroll Student to Course ---");
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        System.out.print("Enter Course ID: ");
        int courseId = sc.nextInt();
        System.out.print("Enter Enrollment Date (yyyy-mm-dd): ");
        sc.nextLine(); // consume newline
        LocalDate enrollmentDate = LocalDate.parse(sc.nextLine());

        Enrollment enrollment = new Enrollment(0, studentId, courseId, enrollmentDate);
        sisDao.enrollStudent(enrollment);
        System.out.println("Student enrolled successfully!");
    }

    private static void addCourse(Scanner sc, SisDao sisDao) {
        System.out.println("\n--- Add Course ---");
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine();
        System.out.print("Enter Instructor Name: ");
        String instructor = sc.nextLine();

        Course course = new Course(0, courseName, courseCode, instructor);
        sisDao.addCourse(course);
        System.out.println("Course added successfully!");
    }

    private static void addTeacher(Scanner sc, SisDao sisDao) {
        System.out.println("\n--- Add Teacher ---");
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        Teacher teacher = new Teacher(0, firstName, lastName, email);
        sisDao.addTeacher(teacher);
        System.out.println("Teacher added successfully!");
    }

    private static void assignTeacherToCourse(Scanner sc, SisDao sisDao) {
        System.out.println("\n--- Assign Teacher to Course ---");
        System.out.print("Enter Course ID: ");
        int courseId = sc.nextInt();
        System.out.print("Enter Teacher ID: ");
        int teacherId = sc.nextInt();

        sisDao.assignTeacherToCourse(courseId, teacherId);
        System.out.println("Teacher assigned to course successfully!");
    }

    private static void makePayment(Scanner sc, SisDao sisDao) {
        System.out.println("\n--- Make Payment ---");
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        System.out.print("Enter Payment Date (yyyy-mm-dd): ");
        sc.nextLine();
        LocalDate paymentDate = LocalDate.parse(sc.nextLine());

        Payment payment = new Payment(0, studentId, amount, paymentDate);
        sisDao.makePayment(payment);
        System.out.println("Payment recorded successfully!");
    }

    private static void viewStudentPayments(Scanner sc, SisDao sisDao) {
        System.out.println("\n--- View Student Payment History ---");
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        sisDao.generatePaymentReport(studentId);
    }

    private static void generateEnrollmentReport(Scanner sc, SisDao sisDao) {
        System.out.println("\n--- Generate Enrollment Report ---");
        System.out.print("Enter Course Id: ");
        int courseId = sc.nextInt();
        sisDao.generateEnrollmentReport(courseId);
    }
}
