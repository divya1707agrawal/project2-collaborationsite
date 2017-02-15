package niit.collaboration.backend.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
@Controller

public class ImageController {

    @Autowired
    HttpSession session;

	@RequestMapping(value = "/Upload", method = RequestMethod.POST)
	public ModelAndView handleFileUpload(@RequestParam CommonsMultipartFile[] fileUpload) throws ServletException,IOException {
		
		String loggedInUserID=(String)session.getAttribute("registered");
		FileOutputStream fout=new FileOutputStream("D:\\demo project\\collaborationapplication\\niit.collaboration.frontend\\src\\main\\webapp\\static\\images\\"+loggedInUserID+".jpeg");
		
		for (CommonsMultipartFile aFile : fileUpload){
              
	            System.out.println("Saving file: " + aFile.getOriginalFilename());
	             
	         byte[] bt=aFile.getBytes();
	         fout.write(bt);
	         fout.flush();
	        }
	  
	    return new ModelAndView ("homepage");
	} 
	
	 @RequestMapping(value="imageupload")
		public  ModelAndView imageupload()
		{
		  
			  return new ModelAndView("imageupload");
		}  
}
