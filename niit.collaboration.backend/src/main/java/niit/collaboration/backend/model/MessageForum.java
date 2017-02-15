package niit.collaboration.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "message_forum")
@Component
public class MessageForum extends BaseDomain {

	private String message;
	
	@Column(name="user_id")
	private String userID;
	
	@Id
	private int id;
	
	@Column(name="forum_id")
	private int forumID;
	
	//public Message(){
	//}
	
	//public Message(int id,String message){
	//	this.id=id;
	//	this.message=message;
	//}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getForumID() {
		return forumID;
	}

	public void setForumID(int forumID) {
		this.forumID = forumID;
	}
	
	
}