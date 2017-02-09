package niit.collaboration.backend.dao;

import java.math.BigDecimal;
import java.util.List;

import niit.collaboration.backend.model.ChatForum;

public interface ChatForumDAO {

	public List<ChatForum> list();
	public ChatForum get(String userID, Integer chatforumID);
	public boolean update(ChatForum chatForum) ;
	public boolean delete(String userID,BigDecimal commentID);
	public boolean save(ChatForum chatForum);
}
