package niit.collaboration.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import niit.collaboration.backend.dao.JobDAO;
import niit.collaboration.backend.model.Job;
import niit.collaboration.backend.model.JobApplication;

@RestController
public class JobController {
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired 
	Job job;
	
	@Autowired 
	JobApplication jobApplication;
	
	@Autowired
	HttpSession httpSession;
	
	//crossOrigin(origins="http://localhost:8088")
	@RequestMapping(value="/getAllJobs",method=RequestMethod.GET)
    public ResponseEntity<List<Job>> getAllOpenedJobs(){
		List<Job> jobs=jobDAO.getAllOpenedJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getJobdetails/{jobID}",method=RequestMethod.GET)
	public ResponseEntity<Job> getJobDetails(@PathVariable("jobID") String jobID){
		Job job=jobDAO.getJobDetails(Long.parseLong(jobID.trim()));
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getMyAppliedJobs",method=RequestMethod.GET)
	public ResponseEntity<List<Job>> getMyAplliedJobs(HttpSession httpSession){
		String loggedInUserID=(String) httpSession.getAttribute("loggedInUserID");
		List<Job> jobs=new ArrayList<Job>();
		
		if(loggedInUserID==null||loggedInUserID.isEmpty()){
			job.setErrorCode("404");
			job.setErrorMessage("you have to login to seee you applied jobs");
			jobs.add(job);
		}else{
			jobs=jobDAO.getMyAppliedJobs(loggedInUserID);
		}
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/selectUser/{userID}/{jobID}/{remarks}",method=RequestMethod.PUT)
    public ResponseEntity<JobApplication> selectUser(@PathVariable("userID") String userID,
    		@PathVariable("jobID") String jobID,@PathVariable("remarks") String remarks){
		jobApplication=updateJobApplicationStatus(userID,jobID,'S',remarks);
		
		return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
	}
	
	@RequestMapping(value="/callForInterview/{userID}/{jobID}/{remarks}",method=RequestMethod.PUT)
    public ResponseEntity<JobApplication> callForInterview(@PathVariable("userID") String userID,
    		@PathVariable("jobID") String jobID,@PathVariable("remarks") String remarks){
		jobApplication=updateJobApplicationStatus(userID,jobID,'C',remarks);
		
		return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
	}
	
	@RequestMapping(value="/rejectJobApplication/{userID}/{jobID}/{remarks}",method=RequestMethod.PUT)
    public ResponseEntity<JobApplication> rejectJobApplication(@PathVariable("userID") String userID,
    		@PathVariable("jobID") String jobID,@PathVariable("remarks") String remarks){
		jobApplication=updateJobApplicationStatus(userID,jobID,'R',remarks);
		
		return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
	}
	
	public boolean isUserAppliedForTheJob(String userID,String jobID)
	{
		    List<Job> list=jobDAO.getMyAppliedJobs(userID);
		    for(Job j:list)
		    {
		    	Long i=j.getId();
		    	String str=i.toString();
		    	if(j.equals(str))
		    	{
		    		return true;
		    	}
		    		
		    }
		return false;
	}
	private JobApplication updateJobApplicationStatus(String userID,String jobID,char status,String remarks){
		if(isUserAppliedForTheJob(userID,jobID)==false){
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage(userID+" not applied for the job"+jobID);
			return jobApplication;
		}
		String loggedInUserRole=(String) httpSession.getAttribute("loggedInUserRole");
		if(loggedInUserRole==null||loggedInUserRole.isEmpty())
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("you are not logged in");
					return jobApplication;
					
		}
		if(!loggedInUserRole.equalsIgnoreCase("admin"))
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("you are notadmin.you can not do this operation");
			return jobApplication;
		}
		jobApplication=jobDAO.getJobApplication(userID,jobID);
		
		jobApplication.setStatus(status);
		jobApplication.setRemarks(remarks);
		if(jobDAO.updateJob(jobApplication)){
			jobApplication.setErrorCode("200");
			jobApplication.setErrorMessage("successfully updated the status as "+status);
		}
		return jobApplication;
	}
	
	@RequestMapping(value="applyForJob/{jobID}",method=RequestMethod.POST)
	public ResponseEntity<JobApplication> applyForJob(@PathVariable("jobID") String jobID,HttpSession httpSession){
	String loggedInUserID=(String) httpSession.getAttribute("loggedInUSerID");
	
	if(loggedInUserID==null||loggedInUserID.isEmpty()){
		jobApplication.setErrorCode("404");
		jobApplication.setErrorMessage("you have login to apply for a job" );
	}else{
		if(jobDAO.getJobApplication(loggedInUserID,jobID)==null)
		{
			jobApplication.setJobID(jobID);
			jobApplication.setUserID(loggedInUserID);
			jobApplication.setStatus('N'); //N-newly applied,C-call for interview,S-selcted
			jobApplication.setDate_applied(new Date(System.currentTimeMillis()));
			
			if(jobDAO.save(jobApplication)){
				jobApplication.setErrorCode("200");
				jobApplication.setErrorMessage("you have successfully applied for the job"+jobID);
			}
		}
		else   //if the user already applied for the job
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("you already applied for the job"+jobID);
		}
	}
	return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
	}
	
	@RequestMapping(value="/postAJob",method=RequestMethod.POST)
	public ResponseEntity<Job> postAJob(@RequestBody Job job ){
		job.setStatus('V');  //V-vacant,F-filled,p-pending
		if(jobDAO.save(job)==false){
			job.setErrorCode("404");
			job.setErrorMessage("not able to post a job");
		}else{
	}
		return null;
		
	}
}
