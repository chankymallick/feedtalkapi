package com.feedtalk.feedtalkapi.APIGateways;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Models.FeedLinks;
import com.feedtalk.feedtalkapi.Models.User;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedLinksRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.UserRepoImpl;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;
import com.feedtalk.feedtalkapi.utility.UtilityHelper.FeedCatagory;

//@CrossOrigin(origins ={ "http://localhost:8100","http://localhost:8090"})

@CrossOrigin
@RestController
public class PublicAPI {

	@Autowired
	FeedRepoImpl feedRepoImplementation;
	@Autowired
	UserRepoImpl userRepoImplementation;
	@Autowired
	FeedLinksRepoImpl feedLinksRepoImplementation;

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

	@RequestMapping(method = RequestMethod.GET, value = "/feed/latest")
	public List<Feed> getTop20Feed() {
		return feedRepoImplementation.getTop30Feed();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/feed/Top20FeedsByCatagory/{catagory}/")
	public List<Feed> getTop20FeedByCatagory(@PathVariable UtilityHelper.FeedCatagory catagory) {
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
	
	@RequestMapping(method = RequestMethod.PUT , value = "/feed/likes/{id}/")
	public boolean setFeedLikes(@PathVariable int id){
		return feedRepoImplementation.setLikeImpl(id);
	}
	@RequestMapping(method = RequestMethod.GET , value = "/feed/likes/{id}/")
	public int getFeedLikes(@PathVariable int id){
		return feedRepoImplementation.getLikes(id);
	}
	@RequestMapping(method = RequestMethod.PUT , value = "/feed/dislikes/{id}/")
	public boolean setFeedDislikes(@PathVariable int id){
		return feedRepoImplementation.setDislikeImpl(id);
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/feed/dislikes/{id}/")
	public int getFeedDislikes(@PathVariable int id){
		return feedRepoImplementation.getDislikes(id);
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/feedlinks/extract")
	public String extractLinks(){
		return feedLinksRepoImplementation.mineNDTVLinks();
	}
	@RequestMapping(method = RequestMethod.GET , value = "/feedlinks")
	public List<FeedLinks> getFeedLinks(){
		return feedLinksRepoImplementation.getFeedLinksRepoImpl();
	}
	
	
}
