package com.feedtalk.feedtalkapi.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Repositories.FeedRepository;
import com.feedtalk.feedtalkapi.Repositories.UserRepository;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;
import com.feedtalk.feedtalkapi.utility.UtilityHelper.FeedCatagory;

public class FeedRepoImpl {

	@Autowired
	FeedRepository feedRepository;

	public Feed addNewFeedImpl(Feed feed) {
		return feedRepository.save(feed);
	}

	public Feed getFeedByIdImpl(int feedId) {
		return feedRepository.findByFeedIdAndPublishedTrue(feedId);
	}

	public Feed getFeedByUrlLinkImpl(String urllink) {
		return feedRepository.findByUrlLinkAndPublishedTrue(urllink);

	}

	public boolean isUrlExistImpl(String urllink) {
		Feed feed = feedRepository.findByUrlLink(urllink);
		if (feed == null) {
			return false;
		} else {
			return true;
		}

	}

	public List<Feed> getAllFeedImpl() {
		List<Feed> feedlist = new ArrayList<>();
		feedRepository.findAll().forEach(feedlist::add);
		return feedlist;
	}

	public List<Feed> getTop30Feed() {
		return feedRepository.findTop30ByPublishedTrueOrderByPublishingDateDesc();
	}

	public List<Feed> getTop20FeedByCatagory(UtilityHelper.FeedCatagory catagory) {
		return feedRepository.findTop20ByCatagoryAndPublishedTrueOrderByPublishingDateDesc(catagory);
	}

	public int getLikes(int id) {
		Feed feed = feedRepository.findOne(id);
		return feed.getLikes();
	}

	public int getDislikes(int id) {
		Feed feed = feedRepository.findOne(id);
		return feed.getDislikes();
	}

	public boolean setLikeImpl(int id) {
		Feed feed = feedRepository.findOne(id);
		if (feed != null) {
			int likes = feed.getLikes();
			feed.setLikes(++likes);
			feedRepository.save(feed);
			return true;
		} else {
			return false;
		}
	}
	public boolean setDislikeImpl(int id) {
		Feed feed = feedRepository.findOne(id);
		if (feed != null) {
			int dislikes = feed.getDislikes();
			feed.setDislikes(++dislikes);
			feedRepository.save(feed);
			return true;
		} else {
			return false;
		}
	}
}
