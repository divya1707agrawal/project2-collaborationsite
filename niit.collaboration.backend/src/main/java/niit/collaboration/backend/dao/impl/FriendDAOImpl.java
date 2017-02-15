package niit.collaboration.backend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import niit.collaboration.backend.dao.FriendDAO;
import niit.collaboration.backend.model.Friend;
import java.math.BigDecimal;
@Repository
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;
	/*public FriendDAOImpl()
	{
		
	}
	@Autowired
	public FriendDAOImpl(SessionFactory sessionFactory)
	
	{
		this.sessionFactory=sessionFactory;
	}*/
	
	private BigDecimal getMaxId()
	{
		String hql="select c_friend_sequence.nextval from dual";
	Query query=sessionFactory.getCurrentSession().createSQLQuery(hql);
	BigDecimal maxID=null;
     try{
		maxID=(BigDecimal)query.list().get(0);
		System.out.println("maxid is "+maxID);
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
			
		}return maxID;
	}
	
	@Transactional
	public List<Friend> getMyFriends(String userID) {
		String hql1="select f from Friend f where f.userID='"+userID+"'and f.status='A'";
		String hql2="select f from Friend f where f.friendID='"+userID+"'and f.status='A'";
		Query query1=sessionFactory.openSession().createQuery(hql1);
		Query query2=sessionFactory.openSession().createQuery(hql2);
		List<Friend> list1=(List<Friend>)query1.list();
		List<Friend> list2=(List<Friend>)query2.list();
		list1.addAll(list2);
		return list1;
	}
	

	@Transactional
	public Friend get(String userID, String friendID) {
		System.out.println(userID);
		System.out.println(friendID);
		String hql="select f from Friend f where f.userID="+"'"+userID+"'and f.friendID='"+friendID+"'";
		Query query=sessionFactory.openSession().createQuery(hql);
		System.out.println("reached hear");
		if(query.list().size()==0)
		{
	  return null;
		}
		else
		{
		Friend f=(Friend)query.list().get(0);
		System.out.println(f.getId());
		return f;
	
		}
		
		
		
	}

	@Transactional
	public boolean update(Friend friend) {
		try{
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}catch(Exception e){
		e.printStackTrace();	
		return false;
		}
	}
		
	@Transactional	
	public void delete(String userID, String friendID) {
		Friend friend=new Friend();
		friend.setFriendID(friendID);
		friend.setUserID(userID);
		sessionFactory.openSession().delete(friend);
	}

	@Transactional
	public List<Friend> getNewFriendRequests(String userID) {
		String hql="select f.friendID from Friend f where f.userID="+"'"+userID+"'and f.status='"+"N'";
		Query query=sessionFactory.openSession().createQuery(hql);
		List<Friend> list=(List<Friend>)query.list();
		return list;
	}

	@Transactional
	public void setOnline(String friendID) {
		try{
			String hql="UPDATE Friend SET isOnline='Y' where friendID='"+friendID+"'";
			//log.debug("hql:"+hql);
			 Query query=sessionFactory.getCurrentSession().createQuery(hql);
				query.executeUpdate();
				
		}
		catch(Exception e)
		{
			
			System.out.println("Error  from friend "+e);
		}
		//log.debug("Startinh of the method setOnline");
			//log.debug("Ending of the method method setOnline");
		
	}

	@Transactional
	public void setOffLine(String friendID) {
		//log.debug("Startinh of the method setOffline");
				String hql="UPDATE Friend SET isOnline='N' where friendID='"+friendID+"'";
				//log.debug("hql:"+hql);
				 Query query=sessionFactory.getCurrentSession().createQuery(hql);
					query.executeUpdate();
					//log.debug("Ending of the method method setOffline");		
	}
	
	@Transactional
	public boolean save(Friend friend){
		try{
			friend.setId(getMaxId().intValue());
			sessionFactory.getCurrentSession().save(friend);
		    return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
public List<Friend> getFriendsRequestsSendByMe(String friendid)
{
	String hql="select f.status  from Friend f where friendID=' "+friendid+"'";
	Query query=sessionFactory.getCurrentSession().createQuery(hql);
	return query.list();
}
}