package com.feedtalk.feedtalkapi.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.feedtalk.feedtalkapi.Models.Feed;

public interface FeedRepository extends CrudRepository<Feed,String> {
		
		
}
