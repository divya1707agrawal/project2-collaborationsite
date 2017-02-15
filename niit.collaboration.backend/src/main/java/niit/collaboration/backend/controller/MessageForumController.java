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

import niit.collaboration.backend.dao.MessageForumDAO;
import niit.collaboration.backend.model.ChatForum;
import niit.collaboration.backend.model.MessageForum;

@RestController
public class MessageForumController {

	@Autowired
	HttpSession session;
	
	@Autowired
	MessageForumDAO messageForumDAO; 
	
	 @RequestMapping(value="/addMessageforum",method=RequestMethod.POST)
		public ResponseEntity<MessageForum> createmessageforum(@RequestBody MessageForum messageforum)
		{
			
	 String id=(String)session.getAttribute("loggedInUserID");
	  messageforum.setUserID(id);
	 
			if( messageforum.getId()==0)
			{
				messageforum.setForumID(Integer.parseInt(fid));
				messageForumDAO.save(messageforum);
			}
			
			return new ResponseEntity<MessageForum>(messageforum,HttpStatus.OK);
		}
	 

	 @RequestMapping(value="/list_message_forum",method=RequestMethod.GET)
		public ResponseEntity<java.util.List>  listmessageforum(Model model){
		   return new ResponseEntity(messageForumDAO.list(Integer.parseInt(fid)),HttpStatus.OK); 
		}
	 
	 
	  @RequestMapping(value="/manage_message_forum_remove",method=RequestMethod.GET)
	 	public ModelAndView delete(@RequestParam("id")String id)throws Exception
	 	{	
	 		String uid=(String)	session.getAttribute("loggedInUserID");	
	 		
	 	   	MessageForum messageforum=messageForumDAO.get(uid,Integer.parseInt(id));

	 	BigDecimal obj=new BigDecimal(id);
	 	 if(messageForumDAO.delete(uid,obj))
	 	 {
	
	 		messageforum.setErrorMessage("blog deleted successfully");
	 		messageforum.setErrorCode("200");
	 	 }
	 	 else
	 	 {
	 		messageforum.setErrorMessage("blog cannot be deleted");
	 		messageforum.setErrorCode("500");
	 	 }
		  return new ModelAndView("viewchatforums");
    }
	  
	  String fid;
	  @RequestMapping(value="chattingpage")
		public  ModelAndView getchatting(@RequestParam("fid") String forumid)
		{
		    fid=forumid;
			  return new ModelAndView("forumchatting");
		}
}
