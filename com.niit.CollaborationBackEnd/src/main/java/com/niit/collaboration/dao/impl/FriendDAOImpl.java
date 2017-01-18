package com.niit.collaboration.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;

@Repository
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public List<Friend> getMyFriends(String userID) {
		
		return null;
	}

	public Friend get(String userID, String friendID) {
		
		return null;
	}

	public boolean update(Friend friend) {
		
		return false;
	}

	public void delete(String userID, String friendID) {
		
		
	}

	public List<Friend> getNewFriendRequests(String userID) {
		
		return null;
	}

	public void setOnline(String friendID) {
		//log.debug("Startinh of the method setOnline");
		String hql="UPDATE Friend SET isOnline='Y' where friendID='"+friendID+"'";
		//log.debug("hql:"+hql);
		 Query query=sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
			//log.debug("Ending of the method method setOnline");
		
	}

	public void setOffLine(String friendID) {
		//log.debug("Startinh of the method setOffline");
				String hql="UPDATE Friend SET isOnline='N' where friendID='"+friendID+"'";
				//log.debug("hql:"+hql);
				 Query query=sessionFactory.getCurrentSession().createQuery(hql);
					query.executeUpdate();
					//log.debug("Ending of the method method setOffline");		
	}

}
