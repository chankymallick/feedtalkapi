package com.feedtalk.feedtalkapi.APIGateways;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedRepoImpl;


@RestController

public class PublicAPI {
	
	@Autowired
	FeedRepoImpl feedRepoImplementation;

	
	
	@RequestMapping(method=RequestMethod.GET,value="/feed/getFeedByURL/{URL}/")
	public Feed getFeedByURL(@PathVariable String URL){		
		return feedRepoImplementation.getFeedByURL(URL);		
	}
	@RequestMapping(method=RequestMethod.POST,value="/feed/addNewFeed")
	public Feed addNewFeed(@RequestBody Feed feed){		
		System.out.println(feed.getFEED_ID());
		return feedRepoImplementation.addNewFeedImpl(feed);		
	}

	public static  void main(String ...args){
		Feed feed = new Feed();
	    System.out.println(feed.getFEED_ID());
	}

}
