package niit.collaboration.backend.controller;

import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.*;

@ServerEndpoint(value="/websocket",configurator=ServletAwareConfig.class)
public class ChatServer {
	class  DataInfo
	{
		Session s;
		String user;
		DataInfo(Session s,String user)
		{
			this.s=s;
			this.user=user;
		}
	}
	static Set<DataInfo> sessionlist;//=new CopyOnWriteArraySet<Session>();
	static Set<String> userlist=new HashSet<String>();
  static  Thread t;
  private  EndpointConfig config;
  static void sendToAll(final int i)
  {
t=new Thread(){
		  
		  public void run()
		  {
			
				 for(DataInfo s:sessionlist)
				 {
					 System.out.println(s.s.getId());
					 try{
					s.s.getBasicRemote().sendText(i+"is connected");
					 }
					 catch(Exception e)
					 {
						 System.out.println(e);
					 }
				 }
				 try{
				 Thread.sleep(1000);
				 }
				 catch(Exception e)
				 {
					// System.out.println(e);
				 }
				 }
		  
		  
	  };
	  t.start();
  }
  static
  {
	  sessionlist=new CopyOnWriteArraySet<DataInfo>();
	  
  }

	@OnMessage
    public void onMessage(String message, Session session) throws IOException,
            InterruptedException {
		
		
		 System.out.println(message);
		  for(DataInfo s:sessionlist)
		  {
			  
			  System.out.println("hay");
		
			  s.s.getBasicRemote().sendText(message);
			  
			  }
    
      
      
        	
    }
 
    @OnOpen
    public void onOpen(Session session,EndpointConfig c) {
    	 this.config=c;
    	

    	 HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
         ServletContext servletContext = httpSession.getServletContext();
    	
         String user=(String)httpSession.getAttribute("loggedInUserID");
         userlist.add(user);
         System.out.println(userlist);
        DataInfo obj=new DataInfo(session,user);
        sessionlist.add(obj);
         for(DataInfo s:sessionlist)
         {
        	 try{
        		 
        	 s.s.getBasicRemote().sendText(userlist+" connected");
        	 }
        	 catch(Exception e)
        	 {
        		 System.out.println(e);
        	 }
        	 }
    	System.out.println("connected "+user);
    }
 
    @OnClose
    public void onClose(Session s) {
   
    	for(DataInfo obj:sessionlist)
    	{
    		if(s.getId().equals(obj.s.getId()))
    		{
    			System.out.println("math found");
    			sessionlist.remove(obj);
    			System.out.println(obj.user+" removed");
    			userlist.remove(obj.user);
    		}
    	}
    	
    	System.out.println(s.getId()+" is closed");
 
    }
}