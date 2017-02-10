package niit.collaboration.backend.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "c_forum_comment")
@Component
public class Comment extends BaseDomain{

	@Id
	private BigDecimal id;
	
	@Column(name="forum_id")
	private String forumId;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="forum_comment")
	private String description;
	
	@Column(name="commented_date")
	private Date commented_date;
	
	private int rating;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getForumId() {
		return forumId;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getCommented_date() {
		return commented_date;
	}

	public void setCommented_date(Date commented_date) {
		this.commented_date = commented_date;
	}	
	
	
}
