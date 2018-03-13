package com.feedtalk.feedtalkapi.Repositories;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.repository.CrudRepository;

import com.feedtalk.feedtalkapi.Models.LikeDislikeHistory;

public interface LikeDislikeHistoryRepository extends CrudRepository<LikeDislikeHistory, Integer> {

		public LikeDislikeHistory findByUserIdAndFeedIdAndCommentId(String userId,int feedId,int CommentId);

}
