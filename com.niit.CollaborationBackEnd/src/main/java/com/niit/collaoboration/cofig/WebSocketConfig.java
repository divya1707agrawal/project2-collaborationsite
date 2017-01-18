package com.niit.collaoboration.cofig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.niit.collaboration.controller.ChatForumController;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan("com.niit.collaboration")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
	
	private static final Logger logger=LoggerFactory.getLogger(WebSocketConfig.class);
	

	 public void configureMessageBroker(MessageBrokerRegistry config){
		logger.debug("starting method configureMessageBroker");
		config.enableSimpleBroker("/topic","/queue");
		config.setApplicationDestinationPrefixes("/app");
		logger.debug("Ending method configureMessagebroker");
	}
	
	public void registerStompEndpoints(StompEndpointRegistry registry){
		logger.debug("starting method registerStompEndpoints");
		registry.addEndpoint("/chat").withSockJS();
		registry.addEndpoint("/chat_forum").withSockJS();
		logger.debug("ending method registerstompEndpoints");
	}

}
