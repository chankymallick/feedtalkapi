package com.feedtalk.feedtalkapi.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;
import com.feedtalk.feedtalkapi.utility.UtilityHelper.FeedCatagory;

public interface FeedRepository extends CrudRepository<Feed,Integer> {
			
	public Feed findByUrlLinkAndPublishedTrue(String urllink);
	
	public Feed findByFeedIdAndPublishedTrue(int id);
	
	public List<Feed> findTop20ByPublishedTrueOrderByPublishingDateDesc();
	
	public List<Feed> findTop20ByCatagoryAndPublishedTrueOrderByPublishingDateDesc(UtilityHelper.FeedCatagory catagory);
	
	
	
	
}
