package com.feedtalk.feedtalkapi.RepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Models.User;
import com.feedtalk.feedtalkapi.Repositories.UserRepository;

public class UserRepoImpl {

	@Autowired
	UserRepository userRepository;
	
	public User addNewUserImpl(User user){		
		return userRepository.save(user);
	}	
	
	public User getUserByUserName(String username){		
		return userRepository.findByEmailAndActiveTrue(username);
	}
}
