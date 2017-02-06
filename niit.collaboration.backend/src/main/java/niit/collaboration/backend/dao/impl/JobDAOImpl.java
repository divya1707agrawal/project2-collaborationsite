package niit.collaboration.backend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import niit.collaboration.backend.dao.JobDAO;
import niit.collaboration.backend.model.Job;
import niit.collaboration.backend.model.JobApplication;

@Repository
public class JobDAOImpl implements JobDAO {
	
	@Autowired 
	SessionFactory sessionFactory;
	
	
	
	private Long getMaxId()
	{
		Long maxID=100L;
		String hql="select max(id) from Job";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		try{
			
			maxID=(Long) query.uniqueResult();
		}catch(Exception e){
			maxID=100L;
			e.printStackTrace();
		}return maxID+1;
	}

	@Transactional
	public List<Job> getAllOpenedJobs() {
		String hql="from Job where status='"+"V'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Job> list=(List<Job>)query.list();
		return query.list();
	}

	@Transactional
	public Job getJobDetails(Long id) {
		return (Job) sessionFactory.getCurrentSession().get(Job.class,id);
	}

	@Transactional
	public boolean updateJob(Job job) {
		try{
			sessionFactory.getCurrentSession().update(job);
			return true;
		}catch(Exception e){
		e.printStackTrace();	
		return false;
		}
	}

	@Transactional
	public boolean updateJob(JobApplication jobApplication) {
		try{
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;
		}catch(Exception e){
		e.printStackTrace();	
		return false;
		}
	}

	@Transactional
	public boolean save(JobApplication jobApplied) {
		try{
			sessionFactory.getCurrentSession().save(jobApplied);
		}catch(Exception e){
		e.printStackTrace();	
		return false;
		}return true;
	}

	@Transactional
	public boolean save(Job job) {
		try{
			sessionFactory.getCurrentSession().save(job);
			
		}catch(Exception e){
		e.printStackTrace();	
		return false;
		}return true;
	}

	@Transactional
	public List<Job> getMyAppliedJobs(String userId) {
		String hql="from JobApplied where userID='"+"userID'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Job> list=(List<Job>)query.list();
		return query.list();
	}

	@Transactional
	public JobApplication getJobApplication(String userID, String jobID) {
		String hql="from JobApplication where userID='"+userID+"' and jobID='"+jobID+"'";
		return (JobApplication) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Transactional
	public JobApplication getJobApplication(String jobID) {
		return (JobApplication) sessionFactory.getCurrentSession().get(JobApplication.class,jobID);
	}

}
