package niit.collaboration.backend.model;

public class Message {

	private String message;
	
	private String friendID;
	
	private int id;
	
	public Message(){
	}
	
	public Message(int id,String message){
		this.id=id;
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFriendID() {
		return friendID;
	}

	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
