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
	@Column(length=1000,nullable=false,unique=true,name="linkUrl")
	private String linkUrl;
	@Column(length=1500)
	private String ContentImageUrl;	
	@Column(length=1500)
	private String headlineImageUrl;
	@Column(length=1500)
	private String headline;
	@Column(length=1200)
	private String contentInitial;
	@Column(length=1020)
	private String sourceAgency;
	private String catagory;	
	private int clicked;	
	@Column(nullable=false)	
	@Temporal(TemporalType.TIMESTAMP)
	private Date linkDate;
	private boolean isPublished;

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
	public String getContentImageUrl() {
		return ContentImageUrl;
	}
	public void setContentImageUrl(String contentImageUrl) {
		ContentImageUrl = contentImageUrl;
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
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
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
