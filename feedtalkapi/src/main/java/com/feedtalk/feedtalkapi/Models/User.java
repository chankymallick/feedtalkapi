package com.feedtalk.feedtalkapi.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.feedtalk.feedtalkapi.utility.UtilityHelper;

@Entity(name="user_table")
public class User {
	
	public User(){
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userid_number")
	private int userIdNumber;	
	@Column(length=30,name="email",unique=true,nullable=false)
	private String email;
	
	@Column(length=30,name="twitterlink")
	private String twitterlink;	
	@Column(length=10,name="usertype")
	private String usertype;	
	
	@Column(length=30,name="username")
	private String username;
	@Column(length=100,name="password")
	private String password;
	@Column(name="active")
	private boolean active;
	
	
	
	public int getUserIdNumber() {
		return userIdNumber;
	}
	public void setUserIdNumber(int userIdNumber) {
		this.userIdNumber = userIdNumber;
	}
	public String getTwitterlink() {
		return twitterlink;
	}
	public void setTwitterlink(String twitterlink) {
		this.twitterlink = twitterlink;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}  
	
	@PrePersist
	@PreUpdate
	public void setDefault() {
		this.setPassword(UtilityHelper.encrypt(UtilityHelper.getBase64DecodedValue(this.getPassword()), UtilityHelper.getBase64DecodedValue(this.getPassword())));
	}
	
}
