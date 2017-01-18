package com.niit.collaoboration.cofig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.niit.collaboration.controller.ChatForumController;

//web.xml-java based configuration
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	private static final Logger logger=LoggerFactory.getLogger(AppInitializer.class);
	
	@Override
	protected Class[] getRootConfigClasses() {
		logger.debug("Starting of the method getRootConfigClasses");
		return new Class[] { AppConfig.class,WebSocketConfig.class };
	}

	@Override
	protected Class[] getServletConfigClasses() {
		logger.debug("Starting of the method  getServletConfigClasses");
		return new Class[] { AppConfig.class,WebSocketConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		logger.debug("Starting of the method  getServletMappings");
		return new Class[] { AppConfig.class,WebSocketConfig.class };
	}

}
