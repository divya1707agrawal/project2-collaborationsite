package niit.collaboration.backend.dao;

import java.util.List;

import niit.collaboration.backend.model.Friend;

public interface FriendDAO {

	public List<Friend> getMyFriends(String userID);
	public Friend get(String userID,String friendID);
	public boolean update(Friend friend);
	public void delete(String userID,String friendID);
	public List<Friend> getNewFriendRequests(String userID);
	public void setOnline(String friendID);
	public void setOffLine(String friendID);
	public boolean save(Friend friend);
	public List<Friend> getFriendsRequestsSendByMe(String friendid);
}
