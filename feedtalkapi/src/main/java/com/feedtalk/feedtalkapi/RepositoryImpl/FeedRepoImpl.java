package com.feedtalk.feedtalkapi.RepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Repositories.FeedRepository;

public class FeedRepoImpl {
	
	
	@Autowired
	FeedRepository feedRepository;
	
	public Feed addNewFeedImpl(Feed feed){		
		return feedRepository.save(feed);
	}	
	public Feed getFeedByURL(String FeedUrl){
		return feedRepository.findOne(FeedUrl);		
	}
	
}
