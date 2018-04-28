package com.feedtalk.feedtalkapi.RepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.feedtalk.feedtalkapi.Models.Comment;
import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Models.LikeDislikeHistory;
import com.feedtalk.feedtalkapi.Models.Reply;
import com.feedtalk.feedtalkapi.Repositories.FeedRepository;
import com.feedtalk.feedtalkapi.Repositories.UserRepository;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;
import com.feedtalk.feedtalkapi.utility.UtilityHelper.FeedCatagory;

public class FeedRepoImpl {

	@Autowired
	FeedRepository feedRepository;

	@Autowired
	LikeDislikeHistoryRepoImpl likeDislikeHistoryImpl;

	public Feed addNewFeedImpl(Feed feed) {
		System.out.println(feed.getContent());

		feed.setContent(StringEscapeUtils.unescapeHtml4(feed.getContent()));

		System.out.println(feed.getContent());
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

	public List<Feed> getTop30MostRecentFeed() {
		return feedRepository.findTop30ByPublishedTrueOrderByPublishingDateDesc();
	}

	public List<Feed> getTop6TopStories() {
		return feedRepository.findTop6ByPublishedTrueOrderByViewOrderDesc();
	}

	public List<Feed> getTop14TopStories() {
		List<Feed> top6 = feedRepository.findTop6ByPublishedTrueOrderByViewOrderDesc();
		List<Feed> top20 = feedRepository.findTop20ByPublishedTrueOrderByViewOrderDesc();
		top20.removeAll(top6);
		return top20;
	}

	public List<Feed> getTop30MostReadFeed() {
		return feedRepository.findTop30ByPublishedTrueOrderByViewsDesc();
	}

	public List<Feed> getTop6MostReadFeed() {
		return feedRepository.findTop6ByPublishedTrueOrderByViewsDesc();
	}

	public Feed getNextFeed(int curentfeed) {
		return feedRepository.findTop1ByFeedIdGreaterThanAndPublishedTrue(curentfeed);
	}

	public List<Feed> getNonPoliticsFeed() {
		return feedRepository.findTop6NotByCatagoryAndPublishedTrueOrderByViewsDescQuery(
				UtilityHelper.FeedCatagory.POLITICS, new PageRequest(0, 6));

	}

	public List<Feed> getRelatedFeeds(int feedId) {
		List<Feed> top4matchedResults = new ArrayList<Feed>();
		String tags = feedRepository.findByFeedIdAndPublishedTrue(feedId).getTags();
		Map<String, Integer> searchMatchingRecordMap = new HashMap<String, Integer>();
		String alltags[] = tags.split(",");
		for (String tag : alltags) {
			List<Feed> matchedFeed = feedRepository.getRelatedFeedsQuery(tag);
			for (Feed feed : matchedFeed) {
				if (searchMatchingRecordMap.containsKey(feed.getUrlLink())) {
					int matchNumber = searchMatchingRecordMap.get(feed.getUrlLink());
					searchMatchingRecordMap.put(feed.getUrlLink(), ++matchNumber);
				} else {
					searchMatchingRecordMap.put(feed.getUrlLink(), 1);
				}
			}
		}

		if (searchMatchingRecordMap.size() < 4) {
			
			for(Feed feed : feedRepository.findTop20ByPublishedTrueOrderByViewOrderDesc()){
				if(top4matchedResults.size()>=4){
					return top4matchedResults;
				}
				top4matchedResults.add(feed);
			}
			
			

		} else {
			Map<String, Integer> sortedMap = this.sortMap(searchMatchingRecordMap);
		
			for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
				if(top4matchedResults.size()>=4){
					return top4matchedResults;
				}
				top4matchedResults.add(feedRepository.findByUrlLinkAndPublishedTrue(entry.getKey()));		
			}

		}

		return top4matchedResults;

	}

	public List<Feed> getTop20FeedByCatagory(String catagory) {
		UtilityHelper.FeedCatagory catagoryEnum = UtilityHelper.FeedCatagory.valueOf(catagory);
		return feedRepository.findTop20ByCatagoryAndPublishedTrueOrderByPublishingDateDesc(catagoryEnum);
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

	// public boolean updateFeedPublish(int id,boolean publish) {
	// Feed oldfeed = feedRepository.findOne(id);
	// if (oldfeed != null) {
	// oldfeed.setPublished(publish);
	// feedRepository.save(oldfeed);
	// return true;
	// } else {
	// return false;
	// }
	// }
	public boolean updateFeedPublish(int id, Feed feed) {
		Feed oldfeed = feedRepository.findOne(id);
		if (oldfeed != null) {
			oldfeed.setHeadline(feed.getHeadline());
			oldfeed.setUrlLink(feed.getUrlLink());
			oldfeed.setHeadLineImage(feed.getHeadLineImage());
			oldfeed.setCatagory(feed.getCatagory());
			oldfeed.setContent(feed.getContent());
			oldfeed.setAuthourName(feed.getAuthourName());
			oldfeed.setContentPreview(feed.getContentPreview());
			oldfeed.setTags(feed.getTags());
			oldfeed.setPublished(feed.isPublished());
			oldfeed.setViewOrder(feed.getViewOrder());
			feedRepository.save(oldfeed);
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

	public boolean newComment(int feedId, Comment comment) {
		Feed feed = feedRepository.findOne(feedId);
		Set<Comment> hsh = feed.getComments();
		hsh.add(comment);
		feed.setComments(hsh);
		feedRepository.save(feed);
		return true;

	}

	public Comment getComment(Feed feed, int commentId) {
		for (Comment com : feed.getComments()) {
			if (com.getCommentId() == commentId) {
				return com;
			}
		}
		return null;
	}

	public Set<Comment> getAllComment(int FeedId) {
		return feedRepository.findOne(FeedId).getComments();
	}

	public Reply getReply(int feedId, int commentId, int replyId) {
		for (Comment com : feedRepository.findOne(feedId).getComments()) {
			if (com.getCommentId() == commentId) {
				for (Reply rep : com.getReply()) {
					if (rep.getReplyId() == replyId) {
						return rep;
					}
				}
			}
		}
		return null;
	}

	public int likeComment(int feedId, int commentId, String UserId) {

		Feed feed = feedRepository.findOne(feedId);
		Comment com = this.getComment(feed, commentId);

		LikeDislikeHistory likeDislike = likeDislikeHistoryImpl.findByUserIdAndFeedIdAndCommentIdImpl(UserId, feedId,
				commentId);
		int like = com.getLike();
		if (likeDislike == null) {
			like++;
			com.setLike(like);
			feedRepository.save(feed);
			likeDislikeHistoryImpl.updateLikeDislikeStatus(UserId, feedId, commentId);

		}

		return like;
	}

	public int dislikeComment(int feedId, int commentId, String UserId) {
		Feed feed = feedRepository.findOne(feedId);
		Comment com = this.getComment(feed, commentId);
		LikeDislikeHistory likeDislike = likeDislikeHistoryImpl.findByUserIdAndFeedIdAndCommentIdImpl(UserId, feedId,
				commentId);
		int dislike = com.getDislike();

		if (likeDislike == null) {
			dislike++;
			com.setDislike(dislike);
			feedRepository.save(feed);
			likeDislikeHistoryImpl.updateLikeDislikeStatus(UserId, feedId, commentId);
			dislike++;
		}

		return dislike;
	}

	public boolean newReply(int FeedId, int CommentId, Reply reply) {
		Feed feed = feedRepository.findOne(FeedId);
		Comment targetComment = null;
		for (Comment com : feed.getComments()) {
			if (com.getCommentId() == CommentId) {
				targetComment = com;
				break;
			}
		}
		Set<Reply> newreply = targetComment.getReply();
		newreply.add(reply);
		targetComment.setReply(newreply);
		feedRepository.save(feed);
		return true;

	}

	public boolean reportComment(int feedId, int commentId) {
		Feed feed = feedRepository.findOne(feedId);
		Comment com = this.getComment(feed, commentId);
		int report = com.getReport() + 1;
		com.setReport(report);
		feedRepository.save(feed);
		return true;
	}

	public Map<String, Integer> sortMap(Map<String, Integer> map) {
		Set<Entry<String, Integer>> set = map.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		Map<String, Integer> returnMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			returnMap.put(entry.getKey(), entry.getValue());
			System.out.println(entry.getKey() + " ==== " + entry.getValue());
		}
		return returnMap;
	}
}
