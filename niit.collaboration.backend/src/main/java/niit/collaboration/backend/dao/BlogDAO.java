package niit.collaboration.backend.dao;

import java.math.BigDecimal;
import java.util.List;

import niit.collaboration.backend.model.Blog;

public interface BlogDAO {

	public List<Blog> list();
	public Blog get(String userID, Integer blogID);
	public boolean update(Blog blog);
	public boolean delete(String userID, BigDecimal blogID);
	public boolean save(Blog blog);
	
}
