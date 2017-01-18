package com.niit.collaboration.controller;


import static org.hibernate.annotations.common.util.impl.Log_$logger.*;

//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;

public class UserController {
    
	@Autowired
	UserDAO userDAO;
	
	@Autowired 
	FriendDAO friendDAO;
	
	@Autowired 
	User user;
	
	@RequestMapping(value="/makeAdmin/{id}",method=RequestMethod.PUT)
	public ResponseEntity<User> makeAdmin(@PathVariable("id") String empID){
		user=userDAO.get(empID);
		if(user==null){
			user=new User();
			user.setErrorCode("404");
			user.setErrorMessage("Employee does not exist");
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		user.setRole("Admin");
		userDAO.update(user);
		user.setErrorCode("200");
		user.setErrorMessage("successfully assign Admin role to the employee:"+user.getName());
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/myProfile",method=RequestMethod.GET)
	public ResponseEntity<User> myProfile(HttpSession session){
		String loggedInUserID=(String)session.getAttribute("loggedInUserID");
		User user=userDAO.get(loggedInUserID);
		if(user==null){
		user=new User();
		user.setErrorCode("404");
		user.setErrorMessage("user does not exist");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/user/authenticate/",method=RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user,HttpSession session){
		//logger.debug("---calling method authenticate");
		user=userDAO.authenticate(user.getId(),user.getPassword());
	  		if(user==null){
			user=new User();//Do we need to create ne wuser?
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credential.Please enter valid credentials");
			//logger.debug("-------invalid credential")
		}else
		{
			user.setErrorCode("200");
			user.setErrorMessage("you have successfully logged in.");
			user.setIsOnline('Y');
			session.setAttribute("loggedInUser",user);
			session.setAttribute("loggedInUserID",user.getId());
					
		     friendDAO.setOnline(user.getId());
		     userDAO.setOnline(user.getId());
		}
	  		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/register/",method=RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user){
		
		if(UserDAO.save(user)==false){
			user.setErrorCode("404");
			user.setErrorMessage("the registration is not success.Please try again");
		}else{
			user.setErrorCode("200");
			user.setErrorMessage("thanku you for registration");
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers(){
		//logger.debug("------calling method listAllUsers");
		List<User> users=userDAO.list();
		
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	/*@RequestMapping("/Users/{id}/")
	public ResponseEntity<User> getUserByID(@PathVariable("id") String user){
		User user=userDAO.get(userId);
		if(user==null){
			user=new User();  //to avoid NLP
			user.setErrorCode("404");
			user.setErrorMessage("User does not found with id "+userID);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@RequestMapping("/Users")
	public ResponseEntity<List<User>> getAllUserDetails(){
		List<User> users=UserDAO.list();
		if(users.isEmpty()){
		users.setErrorCode("404");
		user.setErrorMessage("no users are available");
		users.add(user);
	}*/
	
	@RequestMapping(value="/accept/{id}/",method=RequestMethod.GET)
	public ResponseEntity<User> accept(@PathVariable("id") String id){
		user=updateStatus(id,'A',"");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/reject/{id}/{reason}",method=RequestMethod.GET)
	public ResponseEntity<User> reject(@PathVariable("id") String id,@PathVariable("reason")String reason){
		user=updateStatus(id,'R',reason);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/user/",method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user){
		//logger.debug("------calling method createUser");
		if(userDAO.get(user.getId())==null)
		  {
			user.setIsOnline('N');
			user.setStatus('N');
					if(userDAO.save(user)==true)
					{
						user.setErrorCode("200");
					    user.setErrorMessage("Thanku for registration.You havesuccessfully registered");
					 }
					else
					{
						user.setErrorCode("404");
					    user.setErrorMessage("could not complete the operation");
					}
					return new ResponseEntity<User>(user,HttpStatus.OK);
		     }
		user.setErrorCode("404");
		user.setErrorMessage("user already exist eith id:"+user.getId());
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	private User updateStatus(String id,char status,String reason){
		user=userDAO.get(id);
		if(user==null){
			user=new User();
			user.setErrorCode("404");
			user.setErrorMessage("could not update the status to "+status);
		}
		else{
			user.setStatus(status);
			user.setReason(reason);
			userDAO.update(user);
			user.setErrorCode("200");
			user.setErrorMessage("update the status the successfully");
		}
		return user;
	}
}
