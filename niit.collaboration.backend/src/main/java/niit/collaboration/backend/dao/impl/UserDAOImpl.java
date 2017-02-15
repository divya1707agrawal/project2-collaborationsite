package niit.collaboration.backend.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import niit.collaboration.backend.dao.UserDAO;
import niit.collaboration.backend.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	public User get(String id) {
		if(id==null)
		{
			return null;
		}
		
		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		
		return user;
	}

	@Transactional
	public List<User> list() {
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean save(User user) {
		try {
			System.out.println("inside save");
			System.out.println("id is "+user.getName());
			
			Query query=sessionFactory.getCurrentSession().createSQLQuery("select c_user_sequence.nextval from dual ");
String i=	query.list().get(0).toString();
		System.out.println("i is"+i);
		if(user==null)
		{
			System.out.println("user is null");
		}
		else
		{
			System.out.println(user.getId()+","+user.getName());
			
		}
		user.setId(user.getName());
		user.setRole("Student");
			Session session=sessionFactory.getCurrentSession();
			session.save(user);
			session.flush();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Transactional
	public void setOnline(String userID) {
		try{
		System.out.println("inside setonline1");
		String hql = "UPDATE c_user_detail SET is_online='Y' where id='" + userID + "'";
		
		System.out.println("inside setonline2");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		
        System.out.println("inside setonline3");}
		catch(Exception e)
        {
			System.out.println(e);
		}
		
	}

	@Transactional
	public void setOffLine(String userID) {
		// log.debug("Starting of the method setOffLine");
		String hql = "UPDATE c_user_detail  SET is_online='N' where id='" + userID + "'";
		// log.debug("hql:"+hql);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		query.executeUpdate();
		// log.debug("ending of the method setOffLine");
	}
	
	@Transactional
	public User validate(String id,String password){
		//String hql="select * from c_user_detail where id='"+id+"' and password='"+password+"'";
		String hql=" from User u " ;
				
		//User user=(User) sessionFactory.getCurrentSession().get(User.class, id);
		            /* sessionFactory.getCurrentSession().create
		List<User> list=query.list();
	if(list.size()==0)
	{
		return null;
	}*/System.out.println("inside validation1");
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
	List<User> users=query.list();
	for(User u : users)
	{
		System.out.println("id is "+u.getId()+"  pass word "+u.getPassword());
		
	}
		for(User u : users)
		{
			System.out.println("id is "+u.getId()+"  pass word "+u.getPassword());
			if(u.getId().equals(id) && u.getPassword().equals(password.trim()))
			{
				return u;
			}
		}
		    /*if(query.list().size()==0)
		    {
		    	System.out.println("inside validation2");
		    	return null;
		    }
		    else
		    {
		    	System.out.println("inside validation3");
		    	return (User)query.list().get(0); }
	}*/
		return null;
	}
		
}
