package com.feedtalk.feedtalkapi.Models;

import com.feedtalk.feedtalkapi.utility.UtilityHelper.FeedCatagory;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.feedtalk.feedtalkapi.utility.UtilityHelper;

@Entity
@Table(name = "FEED")
public class Feed {
	public Feed() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int feedId;

	@Column(unique = true, name = "UrlLink")
	private String urlLink;

	@Column(nullable = false)
	private String headline;

	@Column(length = 3500, nullable = false)
	private String content;

	@Column(length = 130, nullable = false)
	private String contentPreview;

	@Column(length = 4000, nullable = false)
	private String headLineImage;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private UtilityHelper.FeedCatagory catagory;

	@Column(nullable = false, length = 25)
	private String authourName;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(nullable = false)
	private Date publishingDate;

	@Column(nullable = false)
	private boolean published;

	private int views;
	private int shared;
	private int likes;
	private int dislikes;

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

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentPreview() {
		return contentPreview;
	}

	public void setContentPreview(String contentPreview) {
		this.contentPreview = contentPreview;
	}

	public String getHeadLineImage() {
		return headLineImage;
	}

	public void setHeadLineImage(String headLineImage) {
		this.headLineImage = headLineImage;
	}

	public UtilityHelper.FeedCatagory getCatagory() {
		return catagory;
	}

	public void setCatagory(UtilityHelper.FeedCatagory catagory) {
		this.catagory = catagory;
	}

	public String getAuthourName() {
		return authourName;
	}

	public void setAuthourName(String authourName) {
		this.authourName = authourName;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getShared() {
		return shared;
	}

	public void setShared(int shared) {
		this.shared = shared;
	}

	@PrePersist
	@PreUpdate
	public void setDefault() {
		
	}
	
    @PostLoad
    public void changeObjectData(){
    
    }
   
}
