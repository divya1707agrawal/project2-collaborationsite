package com.niit.collaboration.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import oracle.net.ns.Message;

@Controller
public class ChatForumController {

	private static final Logger logger=LoggerFactory.getLogger(ChatForumController.class);
	
	@MessageMapping("/chat_forum")  //send message
	@SendTo("/topic/message")     //recieveMessage
	public OutputMessage sendMessage(Message message){
		logger.debug("calling the method sendMessage");
		logger.debug("Message:",message.getMessage());
		return new OutputMessage(message,new Date());
	}
}
