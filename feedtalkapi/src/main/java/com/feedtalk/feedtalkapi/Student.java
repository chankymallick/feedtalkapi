package com.feedtalk.feedtalkapi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	
	
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getBranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public long getMobile() {
		return Mobile;
	}
	public void setMobile(long mobile) {
		Mobile = mobile;
	}
	
	public Student(int studentId, String studentName, String branch, long mobile) {
		super();
		StudentId = studentId;
		StudentName = studentName;
		Branch = branch;
		Mobile = mobile;
	}

	@Id
	private int StudentId;
	@Column
	private String StudentName;
	@Column
	private String Branch;
	@Column
	private long Mobile;
	public Student() {
		super();
	}
	

}
