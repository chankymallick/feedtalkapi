package com.feedtalk.feedtalkapi.utility;

public class UtilityHelper {

	public enum FeedCatagory {
		TOPSTORY, TECH,CRICKET, POLITICS,MOVIES
	}

	public enum CommentType {
		MAIN, REPLY
	}

	public static String NDTVRssFeed_TrendingNews = "http://feeds.feedburner.com/ndtvnews-trending-news";
	public static String NDTVRssFeed_Technology = "http://feeds.feedburner.com/gadgets360-latest";
	public static String NDTVRssFeed_Cricket = "http://feeds.feedburner.com/ndtvsports-cricket";
	public static String TimesOfIndiaTopTrending = "https://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms";
	public static String TimesOfIndiaEntertainment = "https://timesofindia.indiatimes.com/rssfeeds/1081479906.cms";

}
