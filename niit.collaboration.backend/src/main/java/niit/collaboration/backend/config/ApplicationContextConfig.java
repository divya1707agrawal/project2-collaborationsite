package niit.collaboration.backend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import niit.collaboration.backend.model.Blog;
import niit.collaboration.backend.model.Chat;
import niit.collaboration.backend.model.ChatForum;
import niit.collaboration.backend.model.Comment;
import niit.collaboration.backend.model.Friend;
import niit.collaboration.backend.model.Job;
import niit.collaboration.backend.model.JobApplication;
import niit.collaboration.backend.model.MessageForum;
import niit.collaboration.backend.model.User;

@Configuration
@ComponentScan("com.niit.collaboration.dao,com.niit.collaboration.dao.impl,com.niit.collaboration.model,com.niit.collaboration.controller")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	//private static final Logger logger=LoggerFactory.getLogger(ApplicationContextConfig.class);
	
	
	
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
	    DriverManagerDataSource dataSource = new  DriverManagerDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    dataSource.setUsername("hr");
	    dataSource.setPassword("oracle");
	    
	    Properties connectionProperties=new  Properties();
	    connectionProperties.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle11gDialect");
	  
	    connectionProperties.setProperty("hibernate.hbm2ddl.auto","create-drop");// connectionProperties.setProperty("hibernate.id.new_generator_mappings","false");
	    dataSource.setConnectionProperties(connectionProperties);
	    
	    return dataSource;
	}	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(20971520);   // 20MB
	    multipartResolver.setMaxInMemorySize(1048576);  // 1MB
	    return multipartResolver;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 
	    sessionBuilder.addAnnotatedClasses(User.class);
	    sessionBuilder.addAnnotatedClasses(Blog.class);
	    sessionBuilder.addAnnotatedClasses(Chat.class);
	    sessionBuilder.addAnnotatedClasses(ChatForum.class);
	    sessionBuilder.addAnnotatedClasses(Comment.class);
	   // sessionBuilder.addAnnotatedClasses(Event.class);
	    sessionBuilder.addAnnotatedClasses(Friend.class);
	    sessionBuilder.addAnnotatedClasses(MessageForum.class);
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
