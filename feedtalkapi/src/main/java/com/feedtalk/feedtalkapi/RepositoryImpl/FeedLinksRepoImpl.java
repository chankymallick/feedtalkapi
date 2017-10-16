package com.feedtalk.feedtalkapi.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Models.FeedLinks;
import com.feedtalk.feedtalkapi.Repositories.FeedLinksRepository;
import com.feedtalk.feedtalkapi.Repositories.FeedRepository;
import com.feedtalk.feedtalkapi.Scrapper.NDTVLinksExtractor;
import com.feedtalk.feedtalkapi.Scrapper.RSSReader;

public class FeedLinksRepoImpl {
	@Autowired
	FeedLinksRepository feedLinkRepository;
	@Autowired
	RSSReader rssReader;
	
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
	public List<FeedLinks> getLatestFeedLinksRepoImpl(){
		return feedLinkRepository.findTop40ByIsPublishedTrueOrderByLinkDateDesc();
	}
	public String mineLinks(){
		Set<FeedLinks> linksSet = rssReader.getNDTVFeedLinks() ;
		Set<FeedLinks> linksSet2 = rssReader.getTOILinks();
		int i=1;
		String response ="";
		for(FeedLinks fl : linksSet){		
			if(feedLinkRepository.findByLinkUrlAndIsPublishedTrue(fl.getLinkUrl())==null){
			response+= i+ " == " + this.addLinks(fl);			
			i++;
			}
		}
		for(FeedLinks fl : linksSet2){		
			if(feedLinkRepository.findByLinkUrlAndIsPublishedTrue(fl.getLinkUrl())==null){
			response+= i+ " == " + this.addLinks(fl);			
			i++;
			}
		}
		return response;
	}
	@Scheduled(cron="0 0/5 * * * ?")
	public void scheduler(){
		System.out.println("Scheduled Mining Started : "+this.mineLinks());
	}
	public FeedLinks getFeedlinkByURLImpl(String URLLink){
		return feedLinkRepository.findByLinkUrlAndIsPublishedTrue(URLLink);
	}
}
