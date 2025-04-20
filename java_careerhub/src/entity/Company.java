package entity;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int companyID;
    private String companyName;
    private String location;
    
    private List<JobListing> jobs = new ArrayList<>();

    public Company() {}

    public Company(int companyID, String companyName, String location) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.location = location;
    }

    public void postJob(String jobTitle, String jobDescription, String jobLocation, double salary, String jobType) {
        JobListing job = new JobListing(0, this.companyID, jobTitle, jobDescription, jobLocation, salary, jobType, java.time.LocalDateTime.now());
        jobs.add(job);
    }

    public List<JobListing> getJobs() {
        return jobs;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}