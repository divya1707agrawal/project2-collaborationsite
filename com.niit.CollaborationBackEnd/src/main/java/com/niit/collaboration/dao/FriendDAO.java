package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Friend;

public interface FriendDAO {

	public List<Friend> getMyFriends(String userID);
	public Friend get(String userID,String friendID);
	public boolean update(Friend friend);
	public void delete(String userID,String friendID);
	public List<Friend> getNewFriendRequests(String userID);
	public void setOnline(String friendID);
	public void setOffLine(String friendID);
}
