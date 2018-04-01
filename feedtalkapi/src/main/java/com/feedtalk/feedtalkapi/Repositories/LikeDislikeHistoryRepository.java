package com.feedtalk.feedtalkapi.Repositories;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.feedtalk.feedtalkapi.Models.LikeDislikeHistory;

@RepositoryRestResource(exported = false)
public interface LikeDislikeHistoryRepository extends CrudRepository<LikeDislikeHistory, Integer> {

		public LikeDislikeHistory findByUserIdAndFeedIdAndCommentId(String userId,int feedId,int CommentId);

}
