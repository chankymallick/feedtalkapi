package com.feedtalk.feedtalkapi.Scrapper;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.LinkedHashMap;

import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.ricecode.similarity.LevenshteinDistanceStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

public class NDTVLinksExtractor {
	private static final String NDTV = "https://www.ndtv.com";
	public Map<String, String[]> NDTVLinks() {
		try {
			int i = 0;
			Map<String, String[]> validLinks = new LinkedHashMap<String, String[]>();
			Document doc = Jsoup.connect(NDTVLinksExtractor.NDTV).get();
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				if (link.attr("href").length() > 80 && (!link.attr("href").contains("navigation"))
						&& (!link.attr("href").contains("home-footer"))) {
					String title = Jsoup.connect(link.attr("href")).get().title();
					validLinks.put(link.attr("href"), new String[] { title, NDTVImageExtractor(link.attr("href")) });
					if (i >= 20) {
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
	public Map<String, String[]> IndianExpressLinks() {
		try {
			int i = 0;
			Map<String, String[]> validLinks = new LinkedHashMap<String, String[]>();
			Document doc = Jsoup.connect(NDTVLinksExtractor.NDTV).get();
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				if (link.attr("href").length() > 80 && (!link.attr("href").contains("navigation"))
						&& (!link.attr("href").contains("home-footer"))) {
					String title = Jsoup.connect(link.attr("href")).get().title();
					validLinks.put(link.attr("href"), new String[] { title, NDTVImageExtractor(link.attr("href")) });
					if (i >= 20) {
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
	public static void main(String... s) {
		new NDTVLinksExtractor().NDTVImageExtractor("https://www.ndtv.com/india-news/following-right-economic-policy-not-populism-arun-jaitley-to-ndtv-1763097?pfrom=home-topstories");
	}

	public String NDTVImageExtractor(String link) {
		try {
			Document doc = Jsoup.connect(link).get();
			Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
			String imageLink = "";
			int rank = 0;
			for (Element image : images) {
					int size = this.getUrLImageSize(image.attr("src"));
					if(size>rank){
						rank = size;
						imageLink = image.attr("src");
					}
			}
			return imageLink;
		} catch (Exception e) {
			return null;
		}

	}
	
	public int getUrLImageSize(String URL) {
		URL url;
		try {
			url = new URL(URL);
			BufferedImage image = ImageIO.read(url);
			int height = image.getHeight();
			int width = image.getWidth();			
			return height * width;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public double StringMatcher(String source, String target) {
		SimilarityStrategy strategy = new LevenshteinDistanceStrategy();
		StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
		double score = service.score(source, target);
		return score;
	}
	
}
