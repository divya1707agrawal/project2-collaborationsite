package com.niit.collaboration.dao;

import com.niit.collaboration.model.User;

public interface UserDAO {

	public boolean update(User user);
	public User get(String id);
	public void setOnline(String userID);
	public void setOffLine(String userID);
}
