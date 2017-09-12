package com.feedtalk.feedtalkapi.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.feedtalk.feedtalkapi.Models.User;

public interface UserRepository  extends CrudRepository <User , Integer> {
	
	public User findByEmailAndActiveTrue(String username);

}
