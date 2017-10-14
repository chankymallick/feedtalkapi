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
@Table(name = "FEEDLinks")
public class FeedLinks {
	
	public FeedLinks(){
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int linkId;
	@Column(length=600,nullable=false,unique=true,name="linkUrl")
	private String linkUrl;
	@Column(length=500,nullable=false)
	private String headlineImageUrl;
	@Column(length=500,nullable=false)
	private String headline;
	@Column(length=200,nullable=false)
	private String contentInitial;
	@Column(length=20,nullable=false)
	private String sourceAgency;
	private int clicked;
	@CreationTimestamp
	@Column(nullable=false)	
	@Temporal(TemporalType.TIMESTAMP)
	private Date linkDate;
	private boolean isPublished;

	
	public FeedLinks(String linkUrl, String headlineImageUrl, String headline, String contentInitial,
			String sourceAgency, int clicked, Date linkDate, boolean isPublished) {
		super();
		this.linkUrl = linkUrl;
		this.headlineImageUrl = headlineImageUrl;
		this.headline = headline;
		this.contentInitial = contentInitial;
		this.sourceAgency = sourceAgency;
		this.clicked = clicked;
		this.linkDate = linkDate;
		this.isPublished = isPublished;
	}
	public int getLinkId() {
		return linkId;
	}
	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getHeadlineImageUrl() {
		return headlineImageUrl;
	}
	public void setHeadlineImageUrl(String headlineImageUrl) {
		this.headlineImageUrl = headlineImageUrl;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getContentInitial() {
		return contentInitial;
	}
	public void setContentInitial(String contentInitial) {
		this.contentInitial = contentInitial;
	}
	public String getSourceAgency() {
		return sourceAgency;
	}
	public void setSourceAgency(String sourceAgency) {
		this.sourceAgency = sourceAgency;
	}
	public int getClicked() {
		return clicked;
	}
	public void setClicked(int clicked) {
		this.clicked = clicked;
	}
	public Date getLinkDate() {
		return linkDate;
	}
	public void setLinkDate(Date linkDate) {
		this.linkDate = linkDate;
	}
	public boolean isPublished() {
		return isPublished;
	}
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

}
