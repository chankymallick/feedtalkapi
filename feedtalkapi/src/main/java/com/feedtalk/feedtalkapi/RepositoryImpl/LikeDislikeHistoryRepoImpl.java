package com.feedtalk.feedtalkapi.RepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.feedtalk.feedtalkapi.Models.LikeDislikeHistory;
import com.feedtalk.feedtalkapi.Repositories.LikeDislikeHistoryRepository;

public class LikeDislikeHistoryRepoImpl {
	@Autowired
	LikeDislikeHistoryRepository likeDislikeHistoryRepo;
	
	public LikeDislikeHistory findByUserIdAndFeedIdAndCommentIdImpl(String userId,int feedId,int CommentId){
		return likeDislikeHistoryRepo.findByUserIdAndFeedIdAndCommentId(userId, feedId, CommentId);
		
	}
	public LikeDislikeHistory updateLikeDislikeStatus(String userId,int feedId,int CommentId){
		LikeDislikeHistory lkdHistory = new LikeDislikeHistory();
		lkdHistory.setFeedId(feedId);
		lkdHistory.setCommentId(CommentId);
		lkdHistory.setUserId(userId);
		return likeDislikeHistoryRepo.save(lkdHistory);	
	}

}
