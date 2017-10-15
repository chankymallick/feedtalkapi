package com.feedtalk.feedtalkapi.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.feedtalk.feedtalkapi.Models.FeedLinks;

public interface FeedLinksRepository extends CrudRepository<FeedLinks,Integer>{
	
	public FeedLinks findByLinkUrlAndIsPublishedTrue(String UrlLink);
	public List<FeedLinks> findTop40ByIsPublishedTrueOrderByLinkDateDesc();
}
