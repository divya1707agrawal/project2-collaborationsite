package niit.collaboration.backend.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import niit.collaboration.backend.dao.BlogDAO;
import niit.collaboration.backend.model.Blog;

@RestController
public class BlogController {
	
	@Autowired
	BlogDAO blogDAO; 
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/blog")
	public ModelAndView getBlog()
	{
		ModelAndView obj=new ModelAndView("blog");
		
	return obj;
	}
	

	@RequestMapping("/viewblog")
	public ModelAndView getviewBlog()
	{
		ModelAndView obj=new ModelAndView("viewblogs");
		
	return obj;
	}
	

	
	
	@RequestMapping(value="/addblog",method=RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog)
	{
		System.out.println("blog started");
		System.out.println("blog title:"+blog.getTitle());
		System.out.println("blog description:"+blog.getDescription());
		System.out.println("blog id:"+blog.getId());
 String id=(String)session.getAttribute("loggedInUserID");
  blog.setUserID(id);
  blog.setDateTime(new java.util.Date());

		if( blog.getId()==null)
		{
			blogDAO.save(blog);
		}
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);//"blog added success fully";
	}
	
	@RequestMapping(value="/list_blog",method=RequestMethod.GET)
	public ResponseEntity<java.util.List>  listBlog(Model model){
	   
	
		    
	   return new ResponseEntity(blogDAO.list(),HttpStatus.OK); 
	}
	

	//edit page
    @RequestMapping(value="manage_blog",method=RequestMethod.POST)
    public ResponseEntity<Blog> editb(@RequestBody Blog blog)
    {
   	System.out.println("edit started");
    	
   	 if(blogDAO.update(blog)==true){
 			
		blog.setErrorCode("200");
		blog.setErrorMessage("updated successfully");
   	}
   	 else {
 			
	     blog.setErrorCode("404");
	     blog.setErrorMessage("cannot be updated");
 		} 
   	  return new ResponseEntity<Blog>(blog,HttpStatus.OK);
    }

    
       
    
    //for going to edit page
   	@RequestMapping(value="/editblog", method=RequestMethod.GET)
   	public ModelAndView editBlog(@RequestParam("id")String id)
   	{
   		
   String uid=(String)session.getAttribute("loggedInUserID");	
   	Blog b=blogDAO.get(uid,Integer.parseInt(id));
    session.setAttribute("editable", b);
   	ModelAndView obj=new ModelAndView("editblog");
   	obj.addObject("blog",b);
   	return obj;
   	} 
   	
	@RequestMapping("/getblog")
	public ResponseEntity getBlogEditable() 
	{
		 Blog b=(Blog)session.getAttribute("editable");
		 return new ResponseEntity(b,HttpStatus.OK);
	}
	
	  @RequestMapping(value="/manage_blog_remove",method=RequestMethod.GET)
	 	public ModelAndView delete(@RequestParam("id")String id)throws Exception
	 	{	
	 		String uid=(String)	session.getAttribute("loggedInUserID");	
	 		System.out.println("blogid=>"+id);
	 		System.out.println("userid=>"+uid);
	 	   	Blog blog=blogDAO.get(uid,Integer.parseInt(id));

	 	BigDecimal obj=new BigDecimal(id);
	 	 if(blogDAO.delete(uid,obj))
	 	 {
	
	 	   blog.setErrorMessage("blog deleted successfully");
	 	   blog.setErrorCode("200");
	 	 }
	 	 else
	 	 {
	 		 blog.setErrorMessage("blog cannot be deleted");
	 	     blog.setErrorCode("500");
	 	 }
 		  return new ModelAndView("viewblogs");
       }
	}
