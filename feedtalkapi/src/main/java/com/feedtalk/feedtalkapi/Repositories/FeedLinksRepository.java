package com.feedtalk.feedtalkapi.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.feedtalk.feedtalkapi.Models.FeedLinks;

@RepositoryRestResource(exported = false)
public interface FeedLinksRepository extends CrudRepository<FeedLinks,Integer>{
	
	
	
	public FeedLinks findByLinkIdAndIsPublishedTrue(int linkId);
	public FeedLinks findByLinkUrlAndIsPublishedTrue(String UrlLink);
	public List<FeedLinks> findTop100ByIsPublishedTrueOrderByLinkIdAsc();	
	public List<FeedLinks> findTop40ByIsPublishedTrueOrderByLinkDateDesc();	
	public List<FeedLinks> findTop40ByCatagoryAndIsPublishedTrueOrderByLinkDateDesc(String Catagory);
	public List<FeedLinks> findTop12ByCatagoryAndIsPublishedTrueOrderByLinkDateDesc(String Catagory);
	public List<FeedLinks> findTop4ByCatagoryAndIsPublishedTrueOrderByLinkDateDesc(String Catagory);
}
