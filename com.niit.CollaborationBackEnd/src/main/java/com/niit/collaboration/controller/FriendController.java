package com.niit.collaboration.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.Friend;

public class FriendController {

	private static final Logger logger=LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	Friend friend;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value="/myFriends",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(){
		String loggedInUserID=(String)session.getAttribute("loggedInUserID");
		List<Friend> myFriends=new ArrayList<Friend>();
		if(loggedInUserID==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please login to know your friend");
			myFriend.add(friend);
			return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
		}
	}
	
	
}
