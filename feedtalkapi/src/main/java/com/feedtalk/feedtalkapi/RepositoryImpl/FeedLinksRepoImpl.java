package com.feedtalk.feedtalkapi.RepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import com.feedtalk.feedtalkapi.utility.UtilityHelper;

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

	public List<FeedLinks> getFeedLinksRepoImpl() {
		List<FeedLinks> feedLinklist = new ArrayList<>();
		feedLinkRepository.findAll().forEach(feedLinklist::add);
		return feedLinklist;
	}

	public List<FeedLinks> getFeedLinksByTypeAllRepoImpl(String type) {

		return feedLinkRepository.findTop12ByCatagoryAndIsPublishedTrueOrderByLinkDateDesc(type);

	}

	public List<FeedLinks> getBreakingNews() {
		return feedLinkRepository.findTop4ByCatagoryAndIsPublishedTrueOrderByLinkDateDesc(
				UtilityHelper.FeedCatagory.TOPSTORY.toString());
	}

	public String mineLinks() {

		List<Set> linksList = new ArrayList<Set>();
		linksList.add(rssReader.getNDTVFeedLinks(UtilityHelper.NDTVRssFeed_TrendingNews,
				UtilityHelper.FeedCatagory.TOPSTORY));
		linksList
				.add(rssReader.getNDTVFeedLinks(UtilityHelper.NDTVRssFeed_Technology, UtilityHelper.FeedCatagory.TECH));
		linksList
				.add(rssReader.getNDTVFeedLinks(UtilityHelper.NDTVRssFeed_Cricket, UtilityHelper.FeedCatagory.CRICKET));
		linksList
				.add(rssReader.getTOILinks(UtilityHelper.TimesOfIndiaEntertainment, UtilityHelper.FeedCatagory.MOVIES));
		linksList
				.add(rssReader.getTOILinks(UtilityHelper.TimesOfIndiaTopTrending, UtilityHelper.FeedCatagory.TOPSTORY));
		int i = 1;
		String response = "";
		for (Set<FeedLinks> links : linksList) {
			for (FeedLinks fl : links) {
				if (feedLinkRepository.findByLinkUrlAndIsPublishedTrue(fl.getLinkUrl()) == null) {
					response += i + " == " + this.addLinks(fl);
					i++;
				}
			}
		}
		return response;
	}

	public String cleanUpOldLinks() {
		Long TotalLinks = feedLinkRepository.count();
		String Result = "";
		if (TotalLinks > 200) {
			Result += "Total Links Before CleanUp : " + TotalLinks;
			feedLinkRepository.delete(feedLinkRepository.findTop100ByIsPublishedTrueOrderByLinkIdAsc());
			Result += "Total Links After CleanUp : " + feedLinkRepository.count();

		} else {
			Result += "Total Links Less Than 200";
		}

		return Result;
	}	
    //(cron = "0 0 * * * *") For Hourly
	@Scheduled(cron = "0 0/5 * * * ?")
	public void scheduler() {
		System.out.println("Scheduled Mining Started : " + this.mineLinks());
		System.out.println("Delete Result : " + this.cleanUpOldLinks());
	}

	public FeedLinks getFeedlinkByURLImpl(String URLLink) {
		return feedLinkRepository.findByLinkUrlAndIsPublishedTrue(URLLink);
	}

	public FeedLinks getFeedlinkByIdImpl(int linkId) {
		return feedLinkRepository.findByLinkIdAndIsPublishedTrue(linkId);
	}
}
