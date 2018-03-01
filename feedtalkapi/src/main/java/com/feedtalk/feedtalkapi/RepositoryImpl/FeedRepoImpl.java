package com.feedtalk.feedtalkapi.RepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedtalk.feedtalkapi.Models.Comment;
import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Models.Reply;
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
	
	public boolean newComment(int feedId,Comment comment){
		Feed feed = feedRepository.findOne(feedId);
		Set<Comment> hsh = feed.getComments();
		hsh.add(comment);		
		feed.setComments(hsh);
		feedRepository.save(feed);
		return true;
		
	}

	public Comment getComment(Feed feed , int commentId){
		for(Comment com :feed.getComments()){
			if(com.getCommentId() == commentId){
				return com;
			}			
		}
		return null;
	}
	public Set<Comment> getAllComment(int FeedId){
		return feedRepository.findOne(FeedId).getComments();
	}
	public Reply getReply(int feedId , int commentId,int replyId){
		for(Comment com : feedRepository.findOne(feedId).getComments()){
			if(com.getCommentId() == commentId){
				for(Reply rep : com.getReply()){
					if(rep.getReplyId() == replyId){
						return rep;
					}
				}
			}			
		}
		return null;
	}
	public int likeComment(int feedId,int commentId){
		Feed feed = feedRepository.findOne(feedId);
		Comment com = this.getComment(feed, commentId);
		int like = com.getLike()+1;
		com.setLike(like);
		feedRepository.save(feed);
		return like;
	}
	public int dislikeComment(int feedId,int commentId){
		Feed feed = feedRepository.findOne(feedId);
		Comment com = this.getComment(feed, commentId);
		int dislike = com.getDislike()+1;
		com.setDislike(dislike);
		feedRepository.save(feed);
		return dislike;
	}
	public boolean newReply(int FeedId , int CommentId, Reply reply ){
		Feed feed = feedRepository.findOne(FeedId);		
		Comment targetComment = null;
	    for(Comment com:feed.getComments()){
	    	if(com.getCommentId()==CommentId){
	    		targetComment = com;
	    		break;
	    	}
	    }
		Set <Reply> newreply = targetComment.getReply();
		newreply.add(reply);
	    targetComment.setReply(newreply);
	    feedRepository.save(feed);
		return true;
		
	}
	public boolean reportComment(int feedId,int commentId){
		Feed feed = feedRepository.findOne(feedId);
		Comment com = this.getComment(feed, commentId);
		int report = com.getReport()+1;
		com.setReport(report);
		feedRepository.save(feed);
		return true;
	}
}
