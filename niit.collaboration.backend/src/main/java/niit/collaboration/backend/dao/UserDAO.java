package niit.collaboration.backend.dao;

import java.util.List;

import niit.collaboration.backend.model.User;

public interface UserDAO {

	public boolean update(User user);
	public User get(String id);
	public void setOnline(String userID);
	public void setOffLine(String userID);
	public boolean save(User user);
	public List<User> list();
	public User validate(String id,String password);
}
