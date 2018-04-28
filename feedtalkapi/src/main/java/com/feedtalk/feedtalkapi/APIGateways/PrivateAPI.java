package com.feedtalk.feedtalkapi.APIGateways;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feedtalk.feedtalkapi.Models.Comment;
import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Models.Reply;
import com.feedtalk.feedtalkapi.Models.User;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedLinksRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.LikeDislikeHistoryRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.UserRepoImpl;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;

@CrossOrigin
@RestController
public class PrivateAPI {

	@Autowired
	FeedRepoImpl feedRepoImplementation;
	@Autowired
	UserRepoImpl userRepoImplementation;
	@Autowired
	FeedLinksRepoImpl feedLinksRepoImplementation;
	@Autowired
	LikeDislikeHistoryRepoImpl likeDislikeHistoryImpl;

	@RequestMapping(method = RequestMethod.POST, value = "/user/authenticate/{dummydata1}/{password}/{dummydata2}/{username}")
	public Map<String,String> authenticateUser(@PathVariable String username, @PathVariable String password) {
		return userRepoImplementation.loginAndAuthourityDetails(username, password);		
	}

	@RequestMapping(method = RequestMethod.POST, value = "/feed/updatefeed/{feedid}/")
	public boolean updateFeedPublishStatus(@PathVariable int feedid,@RequestBody Feed feed) {
		return feedRepoImplementation.updateFeedPublish(feedid, feed);
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/feed/NewFeed")
	public Feed addNewFeed(@RequestBody Feed feed) {
		return feedRepoImplementation.addNewFeedImpl(feed);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/feed/editfeed")
	public Feed editFeed(@RequestBody Feed feed) {
		return feedRepoImplementation.addNewFeedImpl(feed);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/user/newuser/{dummydata1}/{password}/{dummydata2}/{username}")
	public Object addNewUser(@RequestBody User user, @PathVariable String username, @PathVariable String password) {
	
		if (userRepoImplementation.isUserAuthorized(username, password, UtilityHelper.UserAuthourities.ADMIN)) {
			return userRepoImplementation.addNewUserImpl(user);
		} else {
			return "You are not authourized to use this API";
		}

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/feed/likes/{id}/")
	public boolean setFeedLikes(@PathVariable int id) {
		return feedRepoImplementation.setLikeImpl(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/feed/dislikes/{id}/")
	public boolean setFeedDislikes(@PathVariable int id) {
		return feedRepoImplementation.setDislikeImpl(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/feedlinks/extract")
	public String extractLinks() {
		return feedLinksRepoImplementation.mineLinks();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/feed/comments/new/{Id}/")
	public boolean newFeedComment(@PathVariable int Id, @RequestBody Comment comment) {
		return feedRepoImplementation.newComment(Id, comment);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/feed/comments/like/{FeedId}/{CommentId}/{UserId}/")
	public int setCommentLike(@PathVariable int FeedId, @PathVariable int CommentId, @PathVariable String UserId) {

		return feedRepoImplementation.likeComment(FeedId, CommentId, UserId);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/feed/comments/dislike/{FeedId}/{CommentId}/{UserId}/")
	public int setCommentdisLike(@PathVariable int FeedId, @PathVariable int CommentId, @PathVariable String UserId) {
		return feedRepoImplementation.dislikeComment(FeedId, CommentId, UserId);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/feed/reply/new/{FeedId}/{CommentId}/")
	public boolean newFeedReply(@PathVariable int FeedId, @PathVariable int CommentId, @RequestBody Reply reply) {
		return feedRepoImplementation.newReply(FeedId, CommentId, reply);
	}

}
