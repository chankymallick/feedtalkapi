package com.feedtalk.feedtalkapi.Models;
import com.feedtalk.feedtalkapi.utility.UtilityHelper.FeedCatagory;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="FEED")
public class Feed {
	public Feed(){		
	}
	private int FEED_ID;
	@Id
	private String URL_LINK;
	private String HEADLINE;
	private String CONTENT;
	private String HEADLINE_IMAGE;	
	private String CATAGORY;
	private String AUTHOUR_NAME;
	private String PUBLISHING_DATE;	
	private String PUBLISHED;	
	private int VIEWS;
	private int SHARED;
	
	
	
	public int getFEED_ID() {
		return FEED_ID;
	}
	public void setFEED_ID(int fEED_ID) {
		FEED_ID = fEED_ID;
	}
	public String getURL_LINK() {
		return URL_LINK;
	}
	public void setURL_LINK(String uRL_LINK) {
		URL_LINK = uRL_LINK;
	}
	public String getHEADLINE() {
		return HEADLINE;
	}
	public void setHEADLINE(String hEADLINE) {
		HEADLINE = hEADLINE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getCATAGORY() {
		return CATAGORY;
	}
	public void setCATAGORY(String cATAGORY) {
		CATAGORY = cATAGORY;
	}
	public String getAUTHOUR_NAME() {
		return AUTHOUR_NAME;
	}
	public void setAUTHOUR_NAME(String aUTHOUR_NAME) {
		AUTHOUR_NAME = aUTHOUR_NAME;
	}
	public String getPUBLISHING_DATE() {
		return PUBLISHING_DATE;
	}
	public void setPUBLISHING_DATE(String pUBLISHING_DATE) {
		PUBLISHING_DATE = pUBLISHING_DATE;
	}
	public String getPUBLISHED() {
		return PUBLISHED;
	}
	public void setPUBLISHED(String pUBLISHED) {
		PUBLISHED = pUBLISHED;
	}
	public int getVIEWS() {
		return VIEWS;
	}
	public void setVIEWS(int vIEWS) {
		VIEWS = vIEWS;
	}
	public int getSHARED() {
		return SHARED;
	}
	public void setSHARED(int sHARED) {
		SHARED = sHARED;
	}
	public String getHEADLINE_IMAGE() {
		return HEADLINE_IMAGE;
	}

	public void setHEADLINE_IMAGE(String hEADLINE_IMAGE) {
		HEADLINE_IMAGE = hEADLINE_IMAGE;
	}

	@PrePersist
	public void initializeFeedId() {
	    Random random = new Random(System.currentTimeMillis());
	    this.FEED_ID = random.nextInt(1000000000);
	}
	


}
