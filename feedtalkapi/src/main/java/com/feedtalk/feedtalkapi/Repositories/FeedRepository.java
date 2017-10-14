package com.feedtalk.feedtalkapi.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;
import com.feedtalk.feedtalkapi.utility.UtilityHelper.FeedCatagory;

@RepositoryRestResource(exported = false)
public interface FeedRepository extends CrudRepository<Feed,Integer> {
			
	public Feed findByUrlLinkAndPublishedTrue(String urllink);
	
	public Feed findByFeedIdAndPublishedTrue(int id);	

	public List<Feed> findTop30ByPublishedTrueOrderByPublishingDateDesc();
	
	public List<Feed> findTop20ByCatagoryAndPublishedTrueOrderByPublishingDateDesc(UtilityHelper.FeedCatagory catagory);
	
	public Feed findByUrlLink(String urllink);
	
	
	
}
