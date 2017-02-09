package niit.collaboration.backend.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import niit.collaboration.backend.dao.BlogDAO;
import niit.collaboration.backend.dao.CommentDAO;
import niit.collaboration.backend.model.Blog;
import niit.collaboration.backend.model.Comment;

@RestController
public class CommentController {

	@Autowired
	CommentDAO commentDAO; 
	
	@Autowired
	HttpSession session;
	
	
	@RequestMapping(value="/addforumcomment",method=RequestMethod.POST)
	public ResponseEntity<Comment> createcomment(@RequestBody Comment comment)
	{
		
 String id=(String)session.getAttribute("loggedInUserID");
  comment.setUserId(id);
  comment.setForumId(blogno);
  comment.setCommented_date(new java.util.Date());

		if( comment.getId()==null)
		{
			commentDAO.save(comment);
		}
		
		return new ResponseEntity<Comment>(comment,HttpStatus.OK);
	}
	
	@RequestMapping(value="/list_forum_comment",method=RequestMethod.GET)
	public ResponseEntity<java.util.List>  listforumcomment(Model model){
	   return new ResponseEntity(commentDAO.list(blogno),HttpStatus.OK); 
	}
	
	 @RequestMapping(value="/manage_forum_comment",method=RequestMethod.POST)
	    public ResponseEntity<Comment> editb(@RequestBody Comment comment)
	    {
	   	System.out.println("edit started");
	    	
	   	 if(commentDAO.update(comment)==true){
	 			
			comment.setErrorCode("200");
			comment.setErrorMessage("updated successfully");
	   	}
	   	 else {
	 			
		     comment.setErrorCode("404");
		     comment.setErrorMessage("cannot be updated");
	 		} 
	   	  return new ResponseEntity<Comment>(comment,HttpStatus.OK);
	    }

	
	  @RequestMapping(value="/manage_forum_comment_remove",method=RequestMethod.GET)
	 	public ModelAndView delete(@RequestParam("id")String id)throws Exception
	 	{	
	 		String uid=(String)	session.getAttribute("loggedInUserID");	
	 		
	 	   	Comment comment=commentDAO.get(uid,Integer.parseInt(id));

	 	BigDecimal obj=new BigDecimal(id);
	 	 if(commentDAO.delete(uid,obj))
	 	 {
	
	 		comment.setErrorMessage("blog deleted successfully");
	 		comment.setErrorCode("200");
	 	 }
	 	 else
	 	 {
	 		comment.setErrorMessage("blog cannot be deleted");
	 		comment.setErrorCode("500");
	 	 }
		  return new ModelAndView("viewblogs");
     }
	  
	    String blogno;
	  @RequestMapping(value="viewcomments")
		public  ModelAndView getcomments(@RequestParam("blogid") String blogid)
		{
		  blogno=blogid;
		  
		  
			  return new ModelAndView("viewcomments");
		}
		
}
