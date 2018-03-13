package com.feedtalk.feedtalkapi.Models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.feedtalk.feedtalkapi.utility.UtilityHelper;


@Entity
@Table(name="COMMENT")
public class Comment {
	
	public Comment() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;

	
	@Column(length = 500, nullable = false)	
	private String commentText;
	
	@Column(length = 30, nullable = false)	//Email
	private String userName;
	
	private int likes;
	private int dislike;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(nullable = false)
	private Date commentTime;
	
	private int report;
	private boolean hiden;
	
	
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	@Column(length = 30)	
	private String displayName;
	@Column(length = 100)	
	private String photoURL;
	
	
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public Set<Reply> getReply() {
		return reply;
	}
	public void setReply(Set<Reply> reply) {
		this.reply = reply;
	}
	@OrderBy("date DESC")
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Reply> reply; 
	
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getLike() {
		return likes;
	}
	public void setLike(int like) {
		this.likes = like;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public int getReport() {
		return report;
	}
	public void setReport(int report) {
		this.report = report;
	}
	public boolean isHiden() {
		return hiden;
	}
	public void setHiden(boolean hiden) {
		this.hiden = hiden;
	}
	

}
