package niit.collaboration.backend.model;

import java.util.Date;
import niit.collaboration.backend.model.*;
public class OutputMessage extends MessageForum {
	 
	private Date time;
	
	public OutputMessage(MessageForum original,Date time){
		super();
		this.time=time;
	}
	
	public Date getTime(){
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
