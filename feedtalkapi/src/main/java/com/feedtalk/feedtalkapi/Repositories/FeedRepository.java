package com.feedtalk.feedtalkapi.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;
import com.feedtalk.feedtalkapi.utility.UtilityHelper.FeedCatagory;

public interface FeedRepository extends CrudRepository<Feed,Integer> {
			
	public Feed findByUrlLink(String urllink);
	
	public List<Feed> findTop20ByOrderByPublishingDateDesc();
	
	public List<Feed> findTop20ByCatagoryAndPublishedTrueOrderByPublishingDateDesc(UtilityHelper.FeedCatagory catagory);
	
	
	
}
