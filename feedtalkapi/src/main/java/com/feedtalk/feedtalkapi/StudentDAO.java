package com.feedtalk.feedtalkapi;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

@Transactional
public class StudentDAO  {
	@Autowired
	SessionFactory sessionFactory;
	
	public void addStudent(Student s11){
		System.out.println(sessionFactory);
		Session s1 = sessionFactory.getCurrentSession();
		s1.save(s11);		
		System.out.println("Success");
	}
	public List<Student> getStudent(){
		
		Session s1 = sessionFactory.getCurrentSession();
		return s1.createCriteria(Student.class).list();
	}

}
