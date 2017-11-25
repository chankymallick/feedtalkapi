package com.feedtalk.feedtalkapi.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "REPLY")
public class Reply {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int replyId;
	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(length=500,nullable=false)
	private String replyText;
	
	@Column(length=30,nullable=false)
	private String userName;
	
	private int likes;
	private int dislikes;
	private int report;
	private boolean hidden; 
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date date;
	

}
