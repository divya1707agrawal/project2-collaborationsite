package com.niit.collaboration.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public boolean update(User user){
		try{
			sessionFactory.getCurrentSession().update(user);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Transactional
	public User get(String id){
		User user=(User) sessionFactory.getCurrentSession().get(User.class, id);
		return user;
	}
	
	@Transactional
	public List<User> list(){
		String hql="from User";
		Query query=sessionFactory.getCurrentSession().create(user);
		return query.list();
	}
	
	@Transactional
	public boolean save(User user){
		try{
			sessionFactory.getCurrentSession().save(user);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	@Transactional
	public void setOnline(String userID){
		//log.debug("Strating of the method setOnline");
		String hql="UPDATE User SET isOnline='Y' where id='"+userID+"'";
		//log.debug("hql:"+hql);
		 Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		//log.debug("Ending of the method method setOnline");
	}
	
	@Transactional
	public void setOffLine(String userID){
		//log.debug("Starting of the method setOffLine");
		String hql="UPDATE User SETisOnline='N' where id='"+userID+"'";
	  // log.debug("hql:"+hql);
	   Query query=sessionFactory.getCurrentSession().createQuery(hql);
	   query.executeUpdate();
	  // log.debug("ending of the method setOffLine");
	}
}
