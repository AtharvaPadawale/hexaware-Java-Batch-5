package main;

import java.io.File;
import java.io.IOException;

import dao.JobBoardDAO;

import dao.JobBoardDAOImpl;
import entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import exception.InvalidEmailException;
import exception.NegativeSalaryException;
import exception.FileUploadException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainModule {
    private static final JobBoardDAO dao = new JobBoardDAOImpl();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        dao.initializeDatabase(); 

        while (true) {
            System.out.println("\n===== CareerHub Job Board =====");
            System.out.println("1. Insert Company");
            System.out.println("2. Insert Applicant");
            System.out.println("3. Insert Job Listing");
            System.out.println("4. Insert Job Application");
            System.out.println("5. View All Companies");
            System.out.println("6. View All Applicants");
            System.out.println("7. View All Job Listings");
            System.out.println("8. View Applications for a Job");
            System.out.println("9. Exit");
            System.out.println("10. View Job Listings with Company & Salary");
            System.out.println("11. Search Jobs by Salary Range");
            System.out.print("\nChoose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> insertCompany();
                case 2 -> insertApplicant();
                case 3 -> insertJobListing();
                case 4 -> insertJobApplication();
                case 5 -> viewCompanies();
                case 6 -> viewApplicants();
                case 7 -> viewJobListings();
                case 8 -> viewApplicationsForJob();
                case 9 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                case 10 -> viewJobListingsWithDetails();
                case 11 -> searchJobsBySalaryRange();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void insertCompany() {
        System.out.print("Company ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Company Name: ");
        String name = sc.nextLine();
        System.out.print("Location: ");
        String loc = sc.nextLine();

        dao.insertCompany(new Company(id, name, loc));
        System.out.println("Company inserted.");
    }

    private static void insertApplicant() {
        System.out.print("Applicant ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("First Name: ");
        String fname = sc.nextLine();
        System.out.print("Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
//        System.out.print("Resume Text: ");
//        String resume = sc.nextLine();
        System.out.print("Resume File Path: ");
        String resume = sc.nextLine(); // file path instead of raw text
        
        try {
            validateEmail(email);            // Validate email
            uploadResume(resume);        // Validate file upload
            
            dao.insertApplicant(new Applicant(id, fname, lname, email, phone, resume));
            System.out.println("Applicant inserted successfully.");
        } catch (InvalidEmailException | FileUploadException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertJobListing() {
        System.out.print("Job ID: ");
        int jobId = sc.nextInt(); sc.nextLine();
        System.out.print("Company ID: ");
        int companyId = sc.nextInt(); sc.nextLine();
        System.out.print("Job Title: ");
        String title = sc.nextLine();
        System.out.print("Description: ");
        String desc = sc.nextLine();
        System.out.print("Location: ");
        String loc = sc.nextLine();
        System.out.print("Salary: ");
        double salary = sc.nextDouble(); sc.nextLine();
        System.out.print("Job Type (Full-time/Part-time): ");
        String type = sc.nextLine();
        
        try {
            validateSalary(salary); // validate salary
            dao.insertJobListing(new JobListing(jobId, companyId, title, desc, loc, salary, type, LocalDateTime.now()));
            System.out.println("Job Listing inserted.");
        } catch (NegativeSalaryException e) {
            System.out.println(e.getMessage()); // show salary error
        }
    }

    private static void insertJobApplication() {
        System.out.print("Application ID: ");
        int appId = sc.nextInt(); sc.nextLine();
        System.out.print("Job ID: ");
        int jobId = sc.nextInt(); sc.nextLine();
        System.out.print("Applicant ID: ");
        int applicantId = sc.nextInt(); sc.nextLine();
        System.out.print("Cover Letter: ");
        String cover = sc.nextLine();

        dao.insertJobApplication(new JobApplication(appId, jobId, applicantId, LocalDateTime.now(), cover));
        System.out.println("Job Application submitted.");
    }

    private static void viewCompanies() {
        List<Company> list = dao.getCompanies();
        System.out.println("\n--- All Companies ---");
        for (Company c : list) {
            System.out.println(c.getCompanyID() + ": " + c.getCompanyName() + " - " + c.getLocation());
        }
    }

    private static void viewApplicants() {
        List<Applicant> list = dao.getApplicants();
        System.out.println("\n--- All Applicants ---");
        for (Applicant a : list) {
            System.out.println(a.getApplicantID() + ": " + a.getFirstName() + " " + a.getLastName() + ", " + a.getEmail());
        }
    }

    private static void viewJobListings() {
        List<JobListing> list = dao.getJobListings();
        System.out.println("\n--- All Job Listings ---");
        for (JobListing j : list) {
            System.out.println(j.getJobID() + ": " + j.getJobTitle() + " at " + j.getJobLocation());
        }
    }

    private static void viewApplicationsForJob() {
        System.out.print("Enter Job ID: ");
        int jobId = sc.nextInt();
        sc.nextLine();

        List<JobApplication> list = dao.getApplicationsForJob(jobId);
        System.out.println("\n--- Applications for Job ID " + jobId + " ---");
        for (JobApplication a : list) {
            System.out.println("ApplicationID: " + a.getApplicationID() + ", ApplicantID: " + a.getApplicantID());
        }
    }
    
    // Task 4 Q1
    private static void viewJobListingsWithDetails() {
        String query = "SELECT j.JobTitle, c.CompanyName, j.Salary " +
                       "FROM JobListing j " +
                       "JOIN Company c ON j.CompanyID = c.CompanyID";

        try (Connection con = util.DBConnUtil.getConnection(); // or just DBConnUtil.getConnection() if imported
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- Job Listings with Company and Salary ---");
            while (rs.next()) {
                System.out.println("Job: " + rs.getString("JobTitle") +
                        ", Company: " + rs.getString("CompanyName") +
                        ", Salary: ₹" + rs.getDouble("Salary"));
            }

        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.out.println("❌ Error retrieving job listings: " + e.getMessage());
        }
    }

    //Task4 Q5
    private static void searchJobsBySalaryRange() {
        System.out.print("Enter min salary: ");
        double min = sc.nextDouble();
        System.out.print("Enter max salary: ");
        double max = sc.nextDouble();
        sc.nextLine(); // clear buffer

        String query = "SELECT j.JobTitle, c.CompanyName, j.Salary " +
                       "FROM JobListing j JOIN Company c ON j.CompanyID = c.CompanyID " +
                       "WHERE j.Salary BETWEEN ? AND ?";

        try (Connection con = util.DBConnUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            ResultSet rs = ps.executeQuery();
            System.out.println("\n--- Jobs in Salary Range ---");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("Job: " + rs.getString("JobTitle") +
                        ", Company: " + rs.getString("CompanyName") +
                        ", Salary: ₹" + rs.getDouble("Salary"));
            }
            if (!found) {
                System.out.println("No jobs found in this range.");
            }

        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.out.println("Error retrieving jobs: " + e.getMessage());
        }
    }

    
    // exception methods
    // 1
    public static void validateEmail(String email) throws InvalidEmailException {
        if (!email.contains("@") || !email.matches(".+@.+\\..+")) {
            throw new InvalidEmailException("Invalid email format: " + email);
        }
    }
    
    // 2
    public static void validateSalary(double salary) throws NegativeSalaryException {
        if (salary < 0) {
            throw new NegativeSalaryException("Salary cannot be negative: " + salary);
        }
    }
    
    // 3
    public static void uploadResume(String filePath) throws FileUploadException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileUploadException("Resume file not found: " + filePath);
        }

        if (file.length() > 5 * 1024 * 1024) { // 5 MB
            throw new FileUploadException("Resume file size exceeds 5MB.");
        }

        if (!(filePath.endsWith(".pdf") || filePath.endsWith(".docx"))) {
            throw new FileUploadException("Unsupported file format. Use PDF or DOCX.");
        }

        System.out.println("Resume uploaded successfully.");
    }

    // 4
    // 5 
    
     

}
