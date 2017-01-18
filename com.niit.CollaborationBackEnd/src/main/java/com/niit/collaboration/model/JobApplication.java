package com.niit.collaboration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "c_job_applied")
@Component
public class JobApplication extends BaseDomain{

	@Id
	private int id;
	
	private String user_id;
	
	private String job_id;
	
	private Date date_applied;
	
	private char status;
	
	private String remarks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public Date getDate_applied() {
		return date_applied;
	}

	public void setDate_applied(Date date_applied) {
		this.date_applied = date_applied;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
