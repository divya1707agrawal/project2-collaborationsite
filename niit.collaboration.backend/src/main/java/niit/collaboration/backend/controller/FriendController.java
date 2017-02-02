package niit.collaboration.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import niit.collaboration.backend.dao.FriendDAO;
import niit.collaboration.backend.dao.UserDAO;
import niit.collaboration.backend.model.Friend;

@RestController
public class FriendController {

//	private static final Logger logger=LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	Friend friend;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserDAO userDAO;
	
	private boolean isUserExist(String id)
	{
		if(userDAO.get(id)==null)
			return false;
		else
			return true;
	}
	
	@RequestMapping(value="/myFriends",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(){
		String loggedInUserID=(String)session.getAttribute("loggedInUserID");
		List<Friend> myFriends=new ArrayList<Friend>();
		if(loggedInUserID==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please login to know your friend");
			myFriends.add(friend);
			return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
		}
		myFriends=friendDAO.getMyFriends(loggedInUserID);
		
		if(myFriends.isEmpty()){
			friend.setErrorCode("404");
			friend.setErrorMessage("you does not have any friends");
			myFriends.add(friend);
		}
		return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addFriend/{friendID}",method=RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendID") String friendID){
	 String loggedInUSerID=(String)session.getAttribute("loggedInUserID");
	 friend.setUserID(loggedInUSerID);
	 friend.setFriendID(friendID);
	 friend.setStatus('N');//N-New,A-Accepted,R-rejected
	 friend.setIsOnline('N');
	 //Is the user already sent the request previous?

	 if(friendDAO.get(loggedInUSerID, friendID)!=null){
		 friend.setErrorCode("404");
		 friend.setErrorMessage("you already sent the friend request");
	 }else{
		 friendDAO.save(friend);
		 friend.setErrorCode("200");
		 friend.setErrorMessage("friend request successfull..."+friendID);
	 }
	   return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	

	private Friend updateRequest(String friendID,String status){
		String loggedInUserID=(String)session.getAttribute("loggedInUserID");
		
		if(isFriendRequestAvailable(friendID)==false)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("the request does not exist,So you can not update to"+status);
		}
		if(status.equals("A")||status.equals("R"))
			friend=friendDAO.get(friendID, loggedInUserID);
		else
			friend=friendDAO.get(loggedInUserID, friendID);
		friend.setStatus(status.charAt(0));//N-new,R-rejected,A-accepted
		System.out.println(friend.getStatus());
		friendDAO.update(friend);
		
		friend.setErrorCode("200");
		friend.setErrorMessage("Request from"+friend.getUserID()+" to"+friend.getFriendID()+"has updated");
        return friend;
	}
	
	private boolean  isFriendRequestAvailable(String friendID)
	{
		String loggedInUserID=(String)session.getAttribute("loggedInUserID");
		if(friendDAO.get(loggedInUserID, friendID)==null)
			return false;
		else
			return true;
	}
	
	@RequestMapping(value="/unFriend/{friendID}",method=RequestMethod.GET)
	public ResponseEntity<Friend> unFriend(@PathVariable("friendID") String friendID){
		updateRequest(friendID,"U");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}

	@RequestMapping(value="/getMyFriendRequests/",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriendRequests(){
		String loggedInUserID=(String)session.getAttribute("loggedInUserID");
		List<Friend> myFriendRequests=friendDAO.getFriendsRequestsSendByMe(loggedInUserID);
		return new ResponseEntity<List<Friend>>(myFriendRequests,HttpStatus.OK);
	}
	
	@RequestMapping("/getRequestsSendByMe")
	public ResponseEntity<List<Friend>> getRequestsSendByMe(){
	String loggedInUserID=(String) session.getAttribute("loggedInUserID");
	List<Friend> myFriendRequests=friendDAO.getFriendsRequestsSendByMe(loggedInUserID);
	return new ResponseEntity<List<Friend>>(myFriendRequests,HttpStatus.OK);
   }
	
	@RequestMapping(value="/rejectFriend/{friendID}",method=RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriend(@PathVariable("friendID") String friendID){
		updateRequest(friendID,"R");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptFriend/{friendID}",method=RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendID") String friendID){
		friend=updateRequest(friendID,"A");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
}

