package com.niit.collaboration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "c_user_detail")
@Component
public class User extends BaseDomain{

	@Id
	private String id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String address;
	 
	private String mobile;
	
	private String role;
	
	private char status;
	
	private String reason;
	
	@Column(name="is_online")
	private char isOnline;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return email;
	}

	public void setMail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile= mobile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}
	
	
}