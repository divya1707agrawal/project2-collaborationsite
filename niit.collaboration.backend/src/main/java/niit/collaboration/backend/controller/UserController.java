package niit.collaboration.backend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import niit.collaboration.backend.dao.FriendDAO;
import niit.collaboration.backend.dao.UserDAO;
import niit.collaboration.backend.model.User;

@RestController
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
	
	@RequestMapping(value="/homepage")
	public ModelAndView getHomepage()
	{System.out.println("inside home page");
		return new ModelAndView("/homepage");
	}
	

	@RequestMapping(value="/login")
	public ModelAndView getLoginForm()
	{
		return new ModelAndView("login");
	}
	
	
	@RequestMapping(value="/user/authenticate/",method=RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User u,HttpSession session){
		System.out.println("inside login");
		//logger.debug("---calling method authenticate");
	User ur=userDAO.validate(u.getId(),u.getPassword());
	System.out.println(ur);
	//ModelAndView mv=new ModelAndView("/homepage");
	  		if(ur==null){
	  			
			ur=new User();//Do we need to create new user?
			ur.setErrorCode("404");
			ur.setErrorMessage("Invalid Credential.Please enter valid credentials");
			//logger.debug("-------invalid credential")
	System.out.println(ur.getErrorMessage());
	System.out.println("1");
	  		}else
		{
			ur.setErrorCode("200");
			ur.setErrorMessage("you have successfully logged in.");
			ur.setIsOnline('Y');
			session.setAttribute("loggedInUser",ur);
			session.setAttribute("loggedInUserID",ur.getId());
			System.out.println("userid="+ur.getId());
			session.setAttribute("userID",ur);
		    session.setAttribute("loggedInUserRole",ur.getRole() ); 
		    System.out.println(ur+ur.getRole());
		    		
			friendDAO.setOnline(ur.getId());
			  System.out.println("error code1");
		     userDAO.setOnline(ur.getId());
		     
		     System.out.println("error code");
		     System.out.println(ur.getErrorCode());
		     System.out.println(ur.getErrorMessage());
		     System.out.println("2");
		     
		}
	  		return new ResponseEntity<User>(ur,HttpStatus.OK);//new ResponseEntity<String>(headers,HttpStatus.FOUND);
	}
	
	@RequestMapping(value="/register/",method=RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user){
		
		if(userDAO.save(user)==false){
			user.setErrorCode("404");
			user.setErrorMessage("the registration is not success.Please try again");
		}else{
			user.setErrorCode("200");
			user.setErrorMessage("thanku you for registration");
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	/*@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers(){
		//logger.debug("------calling method listAllUsers");
		List<User> users=userDAO.list();
		
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}*/
	
	@RequestMapping("/Users/{id}/")
	public ResponseEntity<User> getUserByID(@PathVariable("id") String userId){
		User users=userDAO.get(userId);
		if(user==null){
			user=new User();  //to avoid NLP
			user.setErrorCode("404");
			user.setErrorMessage("User does not found with id "+userId);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUserDetails(){
		List<User> users=userDAO.list();
		if(users.isEmpty()){
		user.setErrorCode("404");
		user.setErrorMessage("no users are available");
		users.add(user);
		}
		return new ResponseEntity(users,HttpStatus.OK);
	}
	
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
	
	
	@RequestMapping(value="/user")
	public ModelAndView getUserForm()
	{
		return new ModelAndView("user");
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
					System.out.println(user.getErrorMessage());
					return new ResponseEntity<User>(user,HttpStatus.OK);
		     }
		user.setErrorCode("404");
		user.setErrorMessage("user already exist eith id:"+user.getId());
		System.out.println(user.getErrorMessage());
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

	
	@RequestMapping(value="/user/logout",method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session){
		String loggedInUserID=(String) session.getAttribute("loggedInUserID");
		friendDAO.setOffLine(loggedInUserID);
		userDAO.setOffLine(loggedInUserID);
		
		session.invalidate();
		
		user.setErrorCode("200");
		user.setErrorMessage("you have successfully logged out");
		return new ModelAndView("homepage");//new ResponseEntity<User>(user,HttpStatus.OK);
	}
}