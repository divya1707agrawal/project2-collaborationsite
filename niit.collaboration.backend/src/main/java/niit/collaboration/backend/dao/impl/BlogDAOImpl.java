package niit.collaboration.backend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import niit.collaboration.backend.dao.BlogDAO;
import niit.collaboration.backend.model.Blog;

@Repository
public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Transactional
	private Integer getMaxId()
	{
		String hql="select c_blog_sequence.nextval from dual";
	Query query=sessionFactory.getCurrentSession().createSQLQuery(hql);
	Integer maxID;
     try{
			maxID=(Integer) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return 100;
		}return maxID+1;
	}
	
	@Transactional
	public List<Blog> list(){
		String hql="from Blog";
				Query query=sessionFactory.openSession().createQuery(hql);
		return (List)query.uniqueResult();
		
	}
	
	
	@Transactional
	public Blog get(String userID, Integer blogID) {
		String hql="from Blog where userID="+"'"+userID+"'and blogID='"+blogID+"'";
		Query query=sessionFactory.openSession().createQuery(hql);
		return (Blog)query.uniqueResult();
	}

	@Transactional
	public boolean update(Blog blog) {
		try{
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}catch(Exception e){
		e.printStackTrace();	
		return false;
		}
	}
		
	@Transactional	
	public void delete(String userID, Integer blogID) {
		Blog blog=new Blog();
		blog.setId(blogID);
		blog.setUserID(userID);
		sessionFactory.openSession().delete(blog);
	}
	
	@Transactional
	public boolean save(Blog blog){
		try{
			blog.setId(getMaxId());
			Session session=sessionFactory.getCurrentSession();
			session.save(blog);
			session.flush();
		    return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
