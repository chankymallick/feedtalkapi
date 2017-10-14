package com.feedtalk.feedtalkapi.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Models.FeedLinks;
import com.feedtalk.feedtalkapi.Repositories.FeedLinksRepository;
import com.feedtalk.feedtalkapi.Repositories.FeedRepository;
import com.feedtalk.feedtalkapi.Scrapper.LinksExtractor;

public class FeedLinksRepoImpl {
	@Autowired
	FeedLinksRepository feedLinkRepository;

	@Autowired
	LinksExtractor linksExtractor;
	public boolean addLinks(FeedLinks fdl) {
		if (feedLinkRepository.save(fdl) != null) {
			return true;
		} else {
			return false;
		}
	}
	public List<FeedLinks> getFeedLinksRepoImpl(){
		List<FeedLinks> feedLinklist = new ArrayList<>();
		feedLinkRepository.findAll().forEach(feedLinklist::add);
		return feedLinklist;
	}
	public String mineNDTVLinks(){
		Map <String,String[]> ndtvLinks = linksExtractor.NDTVLinks();	
		int i=1;
		String response ="";
		for(String ss :ndtvLinks.keySet()){
			FeedLinks newlink = new FeedLinks();
			newlink.setLinkUrl(ss);
			newlink.setHeadline(ndtvLinks.get(ss)[0]);
			newlink.setHeadlineImageUrl(ndtvLinks.get(ss)[1]);
			newlink.setContentInitial("");
			newlink.setClicked(0);
			newlink.setPublished(false);
			newlink.setSourceAgency("NDTV");		
			response+= i+ " == " + this.addLinks(newlink);			
			i++;
		}
		return response;
	}

}
