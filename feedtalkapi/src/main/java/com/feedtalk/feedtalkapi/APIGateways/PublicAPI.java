package com.feedtalk.feedtalkapi.APIGateways;

import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feedtalk.feedtalkapi.Models.Comment;
import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Models.FeedLinks;
import com.feedtalk.feedtalkapi.Models.Reply;
import com.feedtalk.feedtalkapi.Models.User;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedLinksRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.LikeDislikeHistoryRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.UserRepoImpl;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;
import com.feedtalk.feedtalkapi.utility.UtilityHelper.FeedCatagory;

//@CrossOrigin(origins ={ "http://localhost:8100","http://localhost:4200"})

@CrossOrigin
@RestController
public class PublicAPI {

	@Autowired
	FeedRepoImpl feedRepoImplementation;
	@Autowired
	UserRepoImpl userRepoImplementation;
	@Autowired
	FeedLinksRepoImpl feedLinksRepoImplementation;
	@Autowired
	LikeDislikeHistoryRepoImpl likeDislikeHistoryImpl;

	@RequestMapping(method = RequestMethod.POST, value = "/feed/FeedById/{feedId}/")
	public Feed getFeedByURL(@PathVariable int feedId) {
		return feedRepoImplementation.getFeedByIdImpl(feedId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/feed/FeedByUrl/{Url_Link}/")
	public Feed getFeedByURL(@PathVariable String Url_Link) {
		return feedRepoImplementation.getFeedByUrlLinkImpl(Url_Link);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/feed/UrlVerify/{Url_Link}/")
	public boolean isUrlExist(@PathVariable String Url_Link) {
		return feedRepoImplementation.isUrlExistImpl(Url_Link);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/feed/AllFeeds")
	public List<Feed> getAllFeed() {
		return feedRepoImplementation.getAllFeedImpl();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/feed/mostrecent")
	public List<Feed> getTop30MostRecentFeed() {
		return feedRepoImplementation.getTop30MostRecentFeed();
	}
	@RequestMapping(method = RequestMethod.GET, value = "/feed/mostread")
	public List<Feed> getTop30MostReadFeed() {
		return feedRepoImplementation.getTop30MostReadFeed();
	}
	@RequestMapping(method = RequestMethod.GET, value = "/feed/Top20FeedsByCatagory/{catagory}/")
	public List<Feed> getTop20FeedByCatagory(@PathVariable String catagory) {
		return feedRepoImplementation.getTop20FeedByCatagory(catagory);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/feed/Top20FeedLinksByCatagory/{catagory}/")
	public List<Feed> getTop20FeedLinksByCatagory(@PathVariable String catagory) {
		return feedRepoImplementation.getTop20FeedByCatagory(catagory);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/feed/NewFeed")
	public Feed addNewFeed(@RequestBody Feed feed) {
		return feedRepoImplementation.addNewFeedImpl(feed);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/feed/NewUser")
	public User addNewUser(@RequestBody User user, Authentication auth) {
		return userRepoImplementation.addNewUserImpl(user);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/feed/likes/{id}/")
	public boolean setFeedLikes(@PathVariable int id) {
		return feedRepoImplementation.setLikeImpl(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/feed/likes/{id}/")
	public int getFeedLikes(@PathVariable int id) {
		return feedRepoImplementation.getLikes(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/feed/dislikes/{id}/")
	public boolean setFeedDislikes(@PathVariable int id) {
		return feedRepoImplementation.setDislikeImpl(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/feed/dislikes/{id}/")
	public int getFeedDislikes(@PathVariable int id) {
		return feedRepoImplementation.getDislikes(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/feedlinks/extract")
	public String extractLinks() {
		return feedLinksRepoImplementation.mineLinks();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/feedlinks")
	public List<FeedLinks> getFeedLinks() {
		return feedLinksRepoImplementation.getFeedLinksRepoImpl();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/feedlinks/{type}")
	public List<FeedLinks> getAllFeedLinks(@PathVariable String type) {
		return feedLinksRepoImplementation.getFeedLinksByTypeAllRepoImpl(type);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/feedlinks/url/{URL}/")
	public FeedLinks getFeedLinkByURL(@PathVariable String URL) {
		return feedLinksRepoImplementation.getFeedlinkByURLImpl(URL);
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

	@RequestMapping(method = RequestMethod.PUT, value = "/feed/comments/report/{FeedId}/{CommentId}/")
	public boolean reportComments(@PathVariable int FeedId, @PathVariable int CommentId) {
		return feedRepoImplementation.reportComment(FeedId, CommentId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/feed/comments/{FeedId}/")
	public Set<Comment> getCommentsByFeedId(@PathVariable int FeedId) {
		return feedRepoImplementation.getAllComment(FeedId);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/feed/reply/new/{FeedId}/{CommentId}/")
	public boolean newFeedReply(@PathVariable int FeedId, @PathVariable int CommentId, @RequestBody Reply reply) {
		return feedRepoImplementation.newReply(FeedId, CommentId, reply);
	}

}
