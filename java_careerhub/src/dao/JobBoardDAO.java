package dao;

import entity.*;
import java.util.List;

public interface JobBoardDAO {
    void initializeDatabase();

    void insertCompany(Company company);
    void insertApplicant(Applicant applicant);
    void insertJobListing(JobListing job);
    void insertJobApplication(JobApplication application);

    List<Company> getCompanies();
    List<Applicant> getApplicants();
    List<JobListing> getJobListings();
    List<JobApplication> getApplicationsForJob(int jobID);
}
