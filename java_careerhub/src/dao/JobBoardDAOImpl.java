package dao;

import entity.*;
import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class JobBoardDAOImpl implements JobBoardDAO {

    private Connection getConnection() throws SQLException,ClassNotFoundException, IOException{
        return DBConnUtil.getConnection();
    }

    public void initializeDatabase() {
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Company (" +
                    "CompanyID INT PRIMARY KEY," +
                    "CompanyName VARCHAR(100)," +
                    "Location VARCHAR(100))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Applicant (" +
                    "ApplicantID INT PRIMARY KEY," +
                    "FirstName VARCHAR(50)," +
                    "LastName VARCHAR(50)," +
                    "Email VARCHAR(100)," +
                    "Phone VARCHAR(15)," +
                    "Resume TEXT)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS JobListing (" +
                    "JobID INT PRIMARY KEY," +
                    "CompanyID INT," +
                    "JobTitle VARCHAR(100)," +
                    "JobDescription TEXT," +
                    "JobLocation VARCHAR(100)," +
                    "Salary DECIMAL(10,2)," +
                    "JobType VARCHAR(50)," +
                    "PostedDate DATETIME," +
                    "FOREIGN KEY (CompanyID) REFERENCES Company(CompanyID))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS JobApplication (" +
                    "ApplicationID INT PRIMARY KEY," +
                    "JobID INT," +
                    "ApplicantID INT," +
                    "ApplicationDate DATETIME," +
                    "CoverLetter TEXT," +
                    "FOREIGN KEY (JobID) REFERENCES JobListing(JobID)," +
                    "FOREIGN KEY (ApplicantID) REFERENCES Applicant(ApplicantID))");

            System.out.println("Database schema initialized successfully.");

        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    public void insertCompany(Company company) {
        String sql = "INSERT INTO Company VALUES (?, ?, ?)";
        
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, company.getCompanyID());
            ps.setString(2, company.getCompanyName());
            ps.setString(3, company.getLocation());
            ps.executeUpdate();
        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.err.println("Failed to insert company: " + e.getMessage());
        }
    }

    public void insertApplicant(Applicant applicant) {
        String sql = "INSERT INTO Applicant VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, applicant.getApplicantID());
            ps.setString(2, applicant.getFirstName());
            ps.setString(3, applicant.getLastName());
            ps.setString(4, applicant.getEmail());
            ps.setString(5, applicant.getPhone());
            ps.setString(6, applicant.getResume());
            ps.executeUpdate();
        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.err.println("Failed to insert applicant: " + e.getMessage());
        }
    }

    public void insertJobListing(JobListing job) {
        String sql = "INSERT INTO JobListing VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, job.getJobID());
            ps.setInt(2, job.getCompanyID());
            ps.setString(3, job.getJobTitle());
            ps.setString(4, job.getJobDescription());
            ps.setString(5, job.getJobLocation());
            ps.setDouble(6, job.getSalary());
            ps.setString(7, job.getJobType());
            ps.setTimestamp(8, Timestamp.valueOf(job.getPostedDate()));
            ps.executeUpdate();
        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.err.println("Failed to insert job listing: " + e.getMessage());
        }
    }

    public void insertJobApplication(JobApplication app) {
        String sql = "INSERT INTO JobApplication VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, app.getApplicationID());
            ps.setInt(2, app.getJobID());
            ps.setInt(3, app.getApplicantID());
            ps.setTimestamp(4, Timestamp.valueOf(app.getApplicationDate()));
            ps.setString(5, app.getCoverLetter());
            ps.executeUpdate();
        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.err.println("Failed to insert job application: " + e.getMessage());
        }
    }

    public List<Company> getCompanies() {
        List<Company> list = new ArrayList<>();
        
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Company");
            while (rs.next()) {
                list.add(new Company(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.err.println("Failed to fetch companies: " + e.getMessage());
        }
        return list;
    }

    public List<Applicant> getApplicants() {
        List<Applicant> list = new ArrayList<>();
        
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Applicant");
            while (rs.next()) {
                list.add(new Applicant(
                        rs.getInt("ApplicantID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Resume")
                ));
            }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.err.println("Failed to fetch applicants: " + e.getMessage());
        }
        return list;
    }

    public List<JobListing> getJobListings() {
        List<JobListing> list = new ArrayList<>();
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM JobListing");
            while (rs.next()) {
                list.add(new JobListing(
                        rs.getInt("JobID"),
                        rs.getInt("CompanyID"),
                        rs.getString("JobTitle"),
                        rs.getString("JobDescription"),
                        rs.getString("JobLocation"),
                        rs.getDouble("Salary"),
                        rs.getString("JobType"),
                        rs.getTimestamp("PostedDate").toLocalDateTime()
                ));
            }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.err.println("Failed to fetch job listings: " + e.getMessage());
        }
        return list;
    }

    public List<JobApplication> getApplicationsForJob(int jobID) {
        List<JobApplication> list = new ArrayList<>();
        String sql = "SELECT * FROM JobApplication WHERE JobID = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, jobID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new JobApplication(
                        rs.getInt("ApplicationID"),
                        rs.getInt("JobID"),
                        rs.getInt("ApplicantID"),
                        rs.getTimestamp("ApplicationDate").toLocalDateTime(),
                        rs.getString("CoverLetter")
                ));
            }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            System.err.println("Failed to fetch job applications: " + e.getMessage());
        }
        return list;
    }
}



//package dao;
//
//import entity.*;
//import util.DBConnUtil;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JobBoardDAOImpl implements JobBoardDAO {
//
//    private Connection getConnection() throws SQLException {
//        return DBConnUtil.getConnection();
//    }
//
//    // 1. Initialize DB schema and tables
//    public void initializeDatabase() {
//        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
//
//            // Create Company table
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Company (" +
//                    "CompanyID INT PRIMARY KEY," +
//                    "CompanyName VARCHAR(100)," +
//                    "Location VARCHAR(100))");
//
//            // Create Applicant table
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Applicant (" +
//                    "ApplicantID INT PRIMARY KEY," +
//                    "FirstName VARCHAR(50)," +
//                    "LastName VARCHAR(50)," +
//                    "Email VARCHAR(100)," +
//                    "Phone VARCHAR(15)," +
//                    "Resume TEXT)");
//
//            // Create JobListing table
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS JobListing (" +
//                    "JobID INT PRIMARY KEY," +
//                    "CompanyID INT," +
//                    "JobTitle VARCHAR(100)," +
//                    "JobDescription TEXT," +
//                    "JobLocation VARCHAR(100)," +
//                    "Salary DECIMAL(10,2)," +
//                    "JobType VARCHAR(50)," +
//                    "PostedDate DATETIME," +
//                    "FOREIGN KEY (CompanyID) REFERENCES Company(CompanyID))");
//
//            // Create JobApplication table
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS JobApplication (" +
//                    "ApplicationID INT PRIMARY KEY," +
//                    "JobID INT," +
//                    "ApplicantID INT," +
//                    "ApplicationDate DATETIME," +
//                    "CoverLetter TEXT," +
//                    "FOREIGN KEY (JobID) REFERENCES JobListing(JobID)," +
//                    "FOREIGN KEY (ApplicantID) REFERENCES Applicant(ApplicantID))");
//
//            System.out.println("Database schema initialized successfully.");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 2. Insert Company
//    public void insertCompany(Company company) {
//        String sql = "INSERT INTO Company VALUES (?, ?, ?)";
//        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, company.getCompanyID());
//            ps.setString(2, company.getCompanyName());
//            ps.setString(3, company.getLocation());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 3. Insert Applicant
//    public void insertApplicant(Applicant applicant) {
//        String sql = "INSERT INTO Applicant VALUES (?, ?, ?, ?, ?, ?)";
//        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, applicant.getApplicantID());
//            ps.setString(2, applicant.getFirstName());
//            ps.setString(3, applicant.getLastName());
//            ps.setString(4, applicant.getEmail());
//            ps.setString(5, applicant.getPhone());
//            ps.setString(6, applicant.getResume());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 4. Insert JobListing
//    public void insertJobListing(JobListing job) {
//        String sql = "INSERT INTO JobListing VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, job.getJobID());
//            ps.setInt(2, job.getCompanyID());
//            ps.setString(3, job.getJobTitle());
//            ps.setString(4, job.getJobDescription());
//            ps.setString(5, job.getJobLocation());
//            ps.setDouble(6, job.getSalary());
//            ps.setString(7, job.getJobType());
//            ps.setTimestamp(8, Timestamp.valueOf(job.getPostedDate()));
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 5. Insert JobApplication
//    public void insertJobApplication(JobApplication app) {
//        String sql = "INSERT INTO JobApplication VALUES (?, ?, ?, ?, ?)";
//        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, app.getApplicationID());
//            ps.setInt(2, app.getJobID());
//            ps.setInt(3, app.getApplicantID());
//            ps.setTimestamp(4, Timestamp.valueOf(app.getApplicationDate()));
//            ps.setString(5, app.getCoverLetter());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 6. Get all Companies
//    public List<Company> getCompanies() {
//        List<Company> list = new ArrayList<>();
//        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery("SELECT * FROM Company");
//            while (rs.next()) {
//                list.add(new Company(rs.getInt(1), rs.getString(2), rs.getString(3)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // 7. Get all Applicants
//    public List<Applicant> getApplicants() {
//        List<Applicant> list = new ArrayList<>();
//        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery("SELECT * FROM Applicant");
//            while (rs.next()) {
//                list.add(new Applicant(
//                        rs.getInt("ApplicantID"),
//                        rs.getString("FirstName"),
//                        rs.getString("LastName"),
//                        rs.getString("Email"),
//                        rs.getString("Phone"),
//                        rs.getString("Resume")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // 8. Get all Job Listings
//    public List<JobListing> getJobListings() {
//        List<JobListing> list = new ArrayList<>();
//        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
//            ResultSet rs = stmt.executeQuery("SELECT * FROM JobListing");
//            while (rs.next()) {
//                list.add(new JobListing(
//                        rs.getInt("JobID"),
//                        rs.getInt("CompanyID"),
//                        rs.getString("JobTitle"),
//                        rs.getString("JobDescription"),
//                        rs.getString("JobLocation"),
//                        rs.getDouble("Salary"),
//                        rs.getString("JobType"),
//                        rs.getTimestamp("PostedDate").toLocalDateTime()
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // 9. Get Applications for a specific Job
//    public List<JobApplication> getApplicationsForJob(int jobID) {
//        List<JobApplication> list = new ArrayList<>();
//        String sql = "SELECT * FROM JobApplication WHERE JobID = ?";
//        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, jobID);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new JobApplication(
//                        rs.getInt("ApplicationID"),
//                        rs.getInt("JobID"),
//                        rs.getInt("ApplicantID"),
//                        rs.getTimestamp("ApplicationDate").toLocalDateTime(),
//                        rs.getString("CoverLetter")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//}