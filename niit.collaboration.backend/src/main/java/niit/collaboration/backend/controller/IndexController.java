package niit.collaboration.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class IndexController {

	
	  @RequestMapping(value="/" ,method = RequestMethod.GET)
	    public String getIndexPage() {
	        return "/homepage";
	    }

	  @RequestMapping(value="viewusers")
	public  ModelAndView getusers()
	{
		  return new ModelAndView("viewusers");
	}
	
	  @RequestMapping(value="acceptrejectrequest")
		public  ModelAndView getacceptrequest()
		{
			  return new ModelAndView("acceptrejectrequest");
		}  
	  
	  @RequestMapping(value="unfriend")
		public  ModelAndView getunfriend()
		{
			  return new ModelAndView("unfriend");
		}  
	  
	  @RequestMapping(value="updateprofile")
		public  ModelAndView getupdateprofile()
		{
			  return new ModelAndView("updateprofile");
		}  
	  
	  
	  @RequestMapping(value="applyforjob")
		public  ModelAndView jobapplication()
		{
			  return new ModelAndView("applyforjob");
		}  
	  
	  @RequestMapping(value="viewappliedprofile")
		public  ModelAndView appliedprofiles()
		{
			  return new ModelAndView("viewappliedprofile");
		}  
	  
	  
}