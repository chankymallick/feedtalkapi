package com.feedtalk.feedtalkapi.Scrapper;

import java.net.URL;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.feedtalk.feedtalkapi.Models.FeedLinks;

public class RSSReader {
	public static String NDTVRssFeed_TrendingNews = "http://feeds.feedburner.com/ndtvnews-trending-news";

	public Document RssXmlURLParser(String RSSUrl) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(RSSUrl).openStream());
			return doc;
		} catch (Exception e) {
			return null;
		}
	}

	public Set<FeedLinks> getNDTVFeedLinks() {
		try
		{
        LinkedHashSet <FeedLinks> linksSet = new LinkedHashSet<FeedLinks>();			
		Document doc = RssXmlURLParser(NDTVRssFeed_TrendingNews);		
		NodeList nList = doc.getElementsByTagName("item");	
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);	
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {				
				Element eElement = (Element) nNode;
				FeedLinks newLink = new FeedLinks();				
				newLink.setHeadline(eElement.getElementsByTagName("title").item(0).getTextContent());
				newLink.setLinkUrl(eElement.getElementsByTagName("link").item(0).getTextContent());
				newLink.setHeadlineImageUrl(eElement.getElementsByTagName("StoryImage").item(0).getTextContent());
				newLink.setLinkDate(new Date(eElement.getElementsByTagName("pubDate").item(0).getTextContent()));
				newLink.setCatagory(eElement.getElementsByTagName("category").item(0).getTextContent());
				newLink.setContentInitial(eElement.getElementsByTagName("description").item(0).getTextContent());
				newLink.setContentImageUrl(eElement.getElementsByTagName("fullimage").item(0).getTextContent());
				newLink.setSourceAgency(eElement.getElementsByTagName("source").item(0).getTextContent());			
				newLink.setPublished(true);
				linksSet.add(newLink);
			}
			
		}
		return linksSet;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return null;
	}

}

}