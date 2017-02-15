package niit.collaboration.backend.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import niit.collaboration.backend.dao.MessageForumDAO;
import niit.collaboration.backend.model.ChatForum;

import niit.collaboration.backend.model.MessageForum;

@Repository
public class MessageForumDAOImpl implements MessageForumDAO {

	@Autowired 
	SessionFactory sessionFactory;
	
	@Transactional
	public List<MessageForum> list(int fid){
		String hql="from MessageForum c where forumID="+"'"+fid+"'";
				Query query=sessionFactory.openSession().createQuery(hql);
		return query.list();
		
	}
	
	@Transactional
	public MessageForum get(String userID, Integer messageforumID) {
		String hql="select m from MessageForum m where m.userID="+"'"+userID+"'and m.id='"+messageforumID+"'";
		Query query=sessionFactory.openSession().createQuery(hql);
		
		return (MessageForum)query.list().get(0);
	}
	
	
	@Transactional	
	public boolean delete(String userID,BigDecimal messageforumID) {
		MessageForum messageForum=new MessageForum();
		messageForum.setUserID(userID);
		messageForum.setForumID(messageforumID.intValue());
		Session session=sessionFactory.openSession();
		session.delete(messageForum);
		session.flush();
		return true;
	}
	
	@Transactional
	public boolean save(MessageForum messageForum){
		try{
			String hql="select message_forum_sequence.nextval from dual";
			Query query=sessionFactory.getCurrentSession().createSQLQuery(hql);
	BigDecimal	maxID=(BigDecimal) query.uniqueResult();
			messageForum.setId(maxID.intValue());
			Session session=sessionFactory.getCurrentSession();
			session.save(messageForum);
			session.flush();
		    return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
