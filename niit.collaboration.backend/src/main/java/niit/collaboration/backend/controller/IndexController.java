package niit.collaboration.backend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class IndexController {

	@Autowired
	HttpSession session;
	
	  @RequestMapping(value="/" ,method = RequestMethod.GET)
	    public String getIndexPage() {
	        return "/homepage";
	    }

	  @RequestMapping(value="viewusers")
	public  ModelAndView getusers()
	{
		  String uid=(String)session.getAttribute("loggedInUserID");
			if(uid==null)
	 		{
	 			return new ModelAndView("login");
	 		}
		  return new ModelAndView("viewusers");
	}
	
	  @RequestMapping(value="acceptrejectrequest")
		public  ModelAndView getacceptrequest()
		{
		  String uid=(String)session.getAttribute("loggedInUserID");
			if(uid==null)
	 		{
	 			return new ModelAndView("login");
	 		}
			  return new ModelAndView("acceptrejectrequest");
		}  
	  
	  @RequestMapping(value="unfriend")
		public  ModelAndView getunfriend()
		{
		  String uid=(String)session.getAttribute("loggedInUserID");
			if(uid==null)
	 		{
	 			return new ModelAndView("login");
	 		}
			  return new ModelAndView("unfriend");
		}  
	  
	  @RequestMapping(value="updateprofile")
		public  ModelAndView getupdateprofile()
		{
		  String uid=(String)session.getAttribute("loggedInUserID");
			if(uid==null)
	 		{
	 			return new ModelAndView("login");
	 		}
			  return new ModelAndView("updateprofile");
		}  
	  
	  
	  @RequestMapping(value="applyforjob")
		public  ModelAndView jobapplication()
		{
		  String uid=(String)session.getAttribute("loggedInUserID");
			if(uid==null)
	 		{
	 			return new ModelAndView("login");
	 		}
			  return new ModelAndView("applyforjob");
		}  
	  
	  @RequestMapping(value="viewappliedprofile")
		public  ModelAndView appliedprofiles()
		{
		  String uid=(String)session.getAttribute("loggedInUserID");
			if(uid==null)
	 		{
	 			return new ModelAndView("login");
	 		}
			  return new ModelAndView("viewappliedprofile");
		}  
	  
	  
}