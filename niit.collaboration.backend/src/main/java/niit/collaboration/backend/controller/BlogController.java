package niit.collaboration.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import niit.collaboration.backend.dao.BlogDAO;
import niit.collaboration.backend.model.Blog;

@RestController
public class BlogController {
	
	@Autowired
	BlogDAO blogDAO; 
	
	@RequestMapping("/blog")
	public ModelAndView getBlog()
	{
		ModelAndView obj=new ModelAndView("blog");
		
	return obj;
	}
	@RequestMapping(value="/addblog",method=RequestMethod.POST)
	public String createBlog(@RequestBody Blog blog)
	{
		System.out.println("blog started");
		System.out.println("blog title:"+blog.getTitle());
		System.out.println("blog description:"+blog.getDescription());
		System.out.println("blog id:"+blog.getId());
		if(blog.getId()==0)
		{
			blogDAO.save(blog);
		}
		
		return "blog added success fully";
	}
	
}
