package niit.collaboration.backend.dao;

import java.util.List;

import niit.collaboration.backend.model.Job;
import niit.collaboration.backend.model.JobApplication;

public interface JobDAO {

	public List<Job> getAllOpenedJobs();
    public Job getJobDetails(Long id);
    public boolean updateJob(Job job);
    public boolean updateJob(JobApplication jobApplication);
    public boolean save(JobApplication jobApplication);
    public boolean save(Job job);
    public List<Job> getMyAppliedJobs(String userId);
    public JobApplication getJobApplication(String userID,String jobID);
    public JobApplication getJobApplication(String jobID);
}
