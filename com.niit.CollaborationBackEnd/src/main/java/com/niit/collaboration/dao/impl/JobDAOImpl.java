package com.niit.collaboration.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;

@Repository
public class JobDAOImpl implements JobDAO {

	public List<Job> getAllOpenedJobs() {
		
		return null;
	}

	public Job getJobDetails(Long id) {
		
		return null;
	}

	public boolean updateJob(Job job) {
		
		return false;
	}

	public boolean updateJob(JobApplication jobApplication) {
		
		return false;
	}

	public boolean save(JobApplication jobApplication) {
		
		return false;
	}

	public boolean save(Job job) {
		
		return false;
	}

	public List<Job> getMyAppliedJobs(String userId) {
		
		return null;
	}

	public JobApplication getApllication(String userId, Long jobID) {
		
		return null;
	}

	public JobApplication getJobApplication(Long jobID) {
	
		return null;
	}

}
