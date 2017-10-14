package com.feedtalk.feedtalkapi.Scrapper;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.ricecode.similarity.LevenshteinDistanceStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

public class LinksExtractor {
	private static final String NDTV = "https://www.ndtv.com";
	
	public Map<String,String[]> NDTVLinks() {
		try {
			int i=0;
			Map<String,String[]> validLinks = new LinkedHashMap<String,String[]>();
			Document doc = Jsoup.connect(LinksExtractor.NDTV).get();
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				if (link.attr("href").length() > 80 && (!link.attr("href").contains("navigation"))
						&& (!link.attr("href").contains("home-footer"))) {	
					String title = Jsoup.connect(link.attr("href")).get().title();
					validLinks.put(link.attr("href"),new String[]{title,NDTVImageExtractor(link.attr("href"))});	
					if(i>=20){
						return validLinks;
					}
					i++;
				}
			}
			return validLinks;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public String NDTVImageExtractor(String link) {
		try {			
			Document doc = Jsoup.connect(link).get();
			Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
			String imageLink = "";
			double rank = 0;
			for (Element image : images) {
				if (image.attr("src").contains("i.ndtvimg.com")) {
					double tempRank = StringMatcher(link, image.attr("src"));
					if( tempRank > rank){
						rank = tempRank;
						imageLink = image.attr("src");
					}
					
				}
			}
			return imageLink;
		} catch (Exception e) {
			return null;
		}

	}

	public double StringMatcher(String source, String target) {
		SimilarityStrategy strategy = new LevenshteinDistanceStrategy();
		StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
		double score = service.score(source, target);
		return score;
	}

}
