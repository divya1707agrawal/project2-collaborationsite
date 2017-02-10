package niit.collaboration.backend.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import niit.collaboration.backend.dao.ChatForumDAO;
import niit.collaboration.backend.model.ChatForum;
import niit.collaboration.backend.model.Comment;
import niit.collaboration.backend.model.MessageForum;
import niit.collaboration.backend.model.OutputMessage;

@Controller
public class ChatForumController {

	//private static final Logger logger=LoggerFactory.getLogger(ChatForumController.class);
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ChatForumDAO chatForumDAO;
	
	@MessageMapping("/chat_forum")  //send message
	@SendTo("/topic/message")     //recieveMessage
	public OutputMessage sendMessage(MessageForum message){
	//logger.debug("calling the method sendMessage");
		//logger.debug("Message:",message.getMessage());
		return new OutputMessage(message,new Date());
	}
	
	 @RequestMapping(value="chatforum")
		public  ModelAndView getusers()
		{
		 String uid=(String)session.getAttribute("loggedInUserID");
			if(uid==null)
	 		{
	 			return new ModelAndView("login");
	 		}
			  return new ModelAndView("chatforum");
		}
		
	 @RequestMapping(value="/addforum",method=RequestMethod.POST)
		public ResponseEntity<ChatForum> createforum(@RequestBody ChatForum chatforum)
		{
			
	 String id=(String)session.getAttribute("loggedInUserID");
	  chatforum.setUserID(id);
	 
	  chatforum.setCreatedDate(new java.util.Date());

			if( chatforum.getId()==null)
			{
				chatForumDAO.save(chatforum);
			}
			
			return new ResponseEntity<ChatForum>(chatforum,HttpStatus.OK);
		}
	 
	 @RequestMapping(value="/list_chat_forum",method=RequestMethod.GET)
		public ResponseEntity<java.util.List>  listchatforum(Model model){
		   return new ResponseEntity(chatForumDAO.list(),HttpStatus.OK); 
		}
		
		 @RequestMapping(value="/manage_chat_forum",method=RequestMethod.POST)
		    public ResponseEntity<ChatForum> editb(@RequestBody ChatForum chatforum)
		    {
		   	System.out.println("edit started");
		    	
		   	 if(chatForumDAO.update(chatforum)==true){
		 			
				chatforum.setErrorCode("200");
				chatforum.setErrorMessage("updated successfully");
		   	}
		   	 else {
		 			
			     chatforum.setErrorCode("404");
			     chatforum.setErrorMessage("cannot be updated");
		 		} 
		   	  return new ResponseEntity<ChatForum>(chatforum,HttpStatus.OK);
		    }

		
		  @RequestMapping(value="/manage_chat_forum_remove",method=RequestMethod.GET)
		 	public ModelAndView delete(@RequestParam("id")String id)throws Exception
		 	{	
		 		String uid=(String)	session.getAttribute("loggedInUserID");	
		 		
		 	   	ChatForum chatforum=chatForumDAO.get(uid,Integer.parseInt(id));

		 	BigDecimal obj=new BigDecimal(id);
		 	 if(chatForumDAO.delete(uid,obj))
		 	 {
		
		 		chatforum.setErrorMessage("blog deleted successfully");
		 		chatforum.setErrorCode("200");
		 	 }
		 	 else
		 	 {
		 		chatforum.setErrorMessage("blog cannot be deleted");
		 		chatforum.setErrorCode("500");
		 	 }
			  return new ModelAndView("viewchatforums");
	     }
		  
		  @RequestMapping(value="viewchatforums")
			public  ModelAndView getforums()
			{
			  String uid=(String)session.getAttribute("loggedInUserID");
				if(uid==null)
		 		{
		 			return new ModelAndView("login");
		 		}
				  return new ModelAndView("viewchatforums");
			}
		  @RequestMapping(value="personalchatting")
			public  ModelAndView getchatting()
			{
			  String uid=(String)session.getAttribute("loggedInUserID");
				if(uid==null)
		 		{
		 			return new ModelAndView("login");
		 		}
				  return new ModelAndView("personalchatting");
			}
		
		  
	
}
