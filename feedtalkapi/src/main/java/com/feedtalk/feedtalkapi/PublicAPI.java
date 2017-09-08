package com.feedtalk.feedtalkapi;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicAPI {
	
	@Autowired
	StudentDAO stdao;		

	@RequestMapping("/getHeadLines")
	public List<Student> getHeadLines(@RequestParam("ID") int id){		
		stdao.addStudent(new Student(id,"CHANKY","MCA",809878261));
		List<Student> m1 = new ArrayList<Student>();
		m1.add(new Student(1,"CHANKY","MCA",809878261));
		m1.add(new Student(2,"BIJAY","MCA",709791927));
		m1.add(new Student(3,"AYESA","BA",267021));
		return m1;
	}
	@RequestMapping("/getAllHeadLines")
	public List<Student> getAllHeadLines(){		
	return stdao.getStudent();
	}
	
	

}
