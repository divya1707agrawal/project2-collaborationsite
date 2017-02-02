package niit.collaboration.backend.dao;

import java.util.List;

import niit.collaboration.backend.model.Blog;

public interface BlogDAO {

	public List<Blog> list();
	public Blog get(String userID, Integer blogID);
	public boolean update(Blog blog);
	public void delete(String userID, Integer blogID);
	public boolean save(Blog blog);
	
}
