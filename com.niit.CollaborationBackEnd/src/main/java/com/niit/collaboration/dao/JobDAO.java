package com.niit.collaboration.dao;

import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;
import java.util.List;
public interface JobDAO {

	public List<Job> getAllOpenedJobs();
    public Job getJobDetails(Long id);
    public boolean updateJob(Job job);
    public boolean updateJob(JobApplication jobApplication);
    public boolean save(JobApplication jobApplication);
    public boolean save(Job job);
    public List<Job> getMyAppliedJobs(String userId);
    public JobApplication getApllication(String userId,Long jobID);
    public JobApplication getJobApplication(Long jobID);
}
