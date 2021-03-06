package com.feedtalk.feedtalkapi.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.feedtalk.feedtalkapi.Models.User;

@RepositoryRestResource(exported = false)
public interface UserRepository  extends CrudRepository <User , Integer> {
	
	public User findByEmailAndActiveTrue(String username);
	public User findByEmailAndPasswordAndUsertypeAndActiveTrue(String username,String password,String Usertype);
	
	public User findByEmailAndPasswordAndActiveTrue(String username,String password);

}
