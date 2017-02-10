package niit.collaboration.backend.dao;

import java.math.BigDecimal;
import java.util.List;

import niit.collaboration.backend.model.MessageForum;

public interface MessageForumDAO {

	public List<MessageForum> list(int fid);
	public MessageForum get(String userID, Integer messageforumID);
	public boolean delete(String userID,BigDecimal messageforumID);
	public boolean save(MessageForum messageForum);
	
}
