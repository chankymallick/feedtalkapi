package com.feedtalk.feedtalkapi.APIGateways;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedRepoImpl;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;
import com.feedtalk.feedtalkapi.utility.UtilityHelper.FeedCatagory;


@RestController
public class PublicAPI {
	
	@Autowired
	FeedRepoImpl feedRepoImplementation;
	@RequestMapping(method=RequestMethod.GET,value="/feed/FeedById/{feedId}/")
	public Feed getFeedByURL(@PathVariable int feedId){		
		return feedRepoImplementation.getFeedByIdImpl(feedId);		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/feed/FeedByUrl/{Url_Link}/")
	public Feed getFeedByURL(@PathVariable String Url_Link){		
		return feedRepoImplementation.getFeedByUrlLinkImpl(Url_Link);			}
		
	@RequestMapping(method=RequestMethod.GET,value="/feed/AllFeeds")
	public List<Feed> getAllFeed(){
		return feedRepoImplementation.getAllFeedImpl();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/feed/Top20Feeds")
	public List<Feed> getTop20Feed(){
		return feedRepoImplementation.getTop20Feed();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/feed/Top20FeedsByCatagory/{catagory}/")
	public List<Feed> getTop20FeedByCatagory(@PathVariable UtilityHelper.FeedCatagory catagory){
		return feedRepoImplementation.getTop20FeedByCatagory(catagory);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/feed/NewFeed")
	public Feed addNewFeed(@RequestBody Feed feed){		
		return feedRepoImplementation.addNewFeedImpl(feed);		
	}
	
	
	
}
