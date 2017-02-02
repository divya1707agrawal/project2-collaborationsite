package niit.collaboration.backend.controller;

import java.util.Date;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import niit.collaboration.backend.model.Message;
import niit.collaboration.backend.model.OutputMessage;

@Controller
public class ChatForumController {

	//private static final Logger logger=LoggerFactory.getLogger(ChatForumController.class);
	
	@MessageMapping("/chat_forum")  //send message
	@SendTo("/topic/message")     //recieveMessage
	public OutputMessage sendMessage(Message message){
	//logger.debug("calling the method sendMessage");
		//logger.debug("Message:",message.getMessage());
		return new OutputMessage(message,new Date());
	}
}
