package niit.collaboration.backend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import niit.collaboration.backend.dao.FriendDAO;
import niit.collaboration.backend.model.Friend;

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
	
	private Integer getMaxId()
	{
		String hql="select max(id) from Friend";
	Query query=sessionFactory.getCurrentSession().createQuery(hql);
	Integer maxID;
     try{
			maxID=(Integer) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return 100;
		}return maxID+1;
	}
	
	@Transactional
	public List<Friend> getMyFriends(String userID) {
		String hql1="select friendIDfrom Friend where userID='"+userID+"'and status='A'";
		String hql2="select userID from Friend where friendID='"+userID+"'and status='A'";
		Query query1=sessionFactory.openSession().createQuery(hql1);
		Query query2=sessionFactory.openSession().createQuery(hql2);
		List<Friend> list1=(List<Friend>)query1.list();
		List<Friend> list2=(List<Friend>)query2.list();
		list1.addAll(list2);
		return list1;
	}
	

	@Transactional
	public Friend get(String userID, String friendID) {
		String hql="from Friend where userID="+"'"+userID+"'and friendID='"+friendID+"'";
		Query query=sessionFactory.openSession().createQuery(hql);
		return (Friend)query.uniqueResult();
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
		String hql="select friendID from Friend where userID="+"'"+userID+"'and status='"+"N'";
		Query query=sessionFactory.openSession().createQuery(hql);
		List<Friend> list=(List<Friend>)query.list();
		return list;
	}

	@Transactional
	public void setOnline(String friendID) {
		//log.debug("Startinh of the method setOnline");
		String hql="UPDATE Friend SET isOnline='Y' where friendID='"+friendID+"'";
		//log.debug("hql:"+hql);
		 Query query=sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
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
			friend.setId(getMaxId()+1);
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