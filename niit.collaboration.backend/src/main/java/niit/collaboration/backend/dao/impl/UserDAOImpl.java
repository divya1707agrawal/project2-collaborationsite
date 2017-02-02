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
		user.setId(i);
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
		// log.debug("Strating of the method setOnline");
		String hql = "UPDATE User SET isOnline='Y' where id='" + userID + "'";
		// log.debug("hql:"+hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		// log.debug("Ending of the method method setOnline");
	}

	@Transactional
	public void setOffLine(String userID) {
		// log.debug("Starting of the method setOffLine");
		String hql = "UPDATE User SET isOnline='N' where id='" + userID + "'";
		// log.debug("hql:"+hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		// log.debug("ending of the method setOffLine");
	}
	
	@Transactional
	public User validate(String id,String password){
		//String hql="select * from c_user_detail where id='"+id+"' and password='"+password+"'";
		String hql="from User";
		//User user=(User) sessionFactory.getCurrentSession().get(User.class, id);
		            /* sessionFactory.getCurrentSession().create
		List<User> list=query.list();
	if(list.size()==0)
	{
		return null;
	}*/System.out.println("inside validation");
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		    
		return (User)query.list().get(0); 
	}
}
