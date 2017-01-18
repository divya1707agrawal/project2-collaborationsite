package com.niit.collaoboration.cofig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.niit.collaboration.model.*;

@Configuration
@ComponentScan("com.niit.collaboration")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	private static final Logger logger=LoggerFactory.getLogger(ApplicationContextConfig.class);
	
	
	
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
	    DriverManagerDataSource dataSource = new  DriverManagerDataSource();
	    dataSource.setDriverClassName("");
	    dataSource.setUrl("");
	    dataSource.setUsername("hr");
	    dataSource.setPassword("oracle");
	    
	    Properties connectionProperties=new  Properties();
	    connectionProperties.setProperty("","");
	    dataSource.setConnectionProperties(connectionProperties);
	    return dataSource;
	}	
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 
	    sessionBuilder.addAnnotatedClasses(User.class);
	    sessionBuilder.addAnnotatedClasses(Blog.class);
	    //sessionBuilder.addAnnotatedClasses(Chat.class);
	    //sessionBuilder.addAnnotatedClasses(ChatForum.class);
	    //sessionBuilder.addAnnotatedClasses(ChatForumComment.class);
	   // sessionBuilder.addAnnotatedClasses(Event.class);
	    sessionBuilder.addAnnotatedClasses(Friend.class);
	    sessionBuilder.addAnnotatedClasses(Job.class);
	    sessionBuilder.addAnnotatedClasses(JobApplication.class);
	    
	    return sessionBuilder.buildSessionFactory();
}
	

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	 
	    return transactionManager;
	}
}
