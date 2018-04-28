package com.feedtalk.feedtalkapi.Repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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

	public List<Feed> findTop30ByPublishedTrueOrderByViewsDesc();
	
	public List<Feed> findTop6ByPublishedTrueOrderByViewsDesc();
	
	public List<Feed> findTop30ByPublishedTrueOrderByPublishingDateDesc();
	
	public List<Feed> findTop6ByPublishedTrueOrderByViewOrderDesc();
	
	public List<Feed> findTop20ByPublishedTrueOrderByViewOrderDesc();
	
	public List<Feed> findTop20ByCatagoryAndPublishedTrueOrderByPublishingDateDesc(UtilityHelper.FeedCatagory catagory);
	
	public Feed findByUrlLink(String urllink);
	
	public Feed findTop1ByFeedIdGreaterThanAndPublishedTrue(int currentfeed);
	
	@Query("select f from Feed f where f.published=1 and f.catagory <> ?1 order by f.views desc")
	public List<Feed> findTop6NotByCatagoryAndPublishedTrueOrderByViewsDescQuery(UtilityHelper.FeedCatagory catagory,Pageable page); 
	
	
	@Query("select f from Feed f where f.published=1 and f.tags like %?1% order by f.publishingDate desc")
	public List<Feed> getRelatedFeedsQuery(String tag);
		
	
	
}
