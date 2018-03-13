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

import javax.speech.*;
import java.util.*;
import javax.speech.synthesis.*;
import com.feedtalk.feedtalkapi.Models.FeedLinks;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;

public class RSSReader {
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

	@SuppressWarnings({ "deprecation" })
	public Set<FeedLinks> getNDTVFeedLinks(String RssUrl, UtilityHelper.FeedCatagory Catagory) {
		try {
			LinkedHashSet<FeedLinks> linksSet = new LinkedHashSet<FeedLinks>();
			Document doc = RssXmlURLParser(RssUrl);
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
					newLink.setCatagory(Catagory.toString());
					newLink.setContentInitial(eElement.getElementsByTagName("description").item(0).getTextContent());
					newLink.setContentImageUrl(eElement.getElementsByTagName("fullimage").item(0).getTextContent());
					newLink.setSourceAgency(eElement.getElementsByTagName("source").item(0).getTextContent());
					newLink.setPublished(true);
					newLink.setComments(null);
					linksSet.add(newLink);
				}

			}
			return linksSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Set<FeedLinks> getTOILinks(String RssUrl, UtilityHelper.FeedCatagory Catagory) {
		try {
			LinkedHashSet<FeedLinks> linksSet = new LinkedHashSet<FeedLinks>();
			Document doc = RssXmlURLParser(RssUrl);
			NodeList nList = doc.getElementsByTagName("item");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String Desc = eElement.getElementsByTagName("description").item(0).getTextContent();
					if (Desc.contains("<img")) {
						try {
							FeedLinks newLink = new FeedLinks();
							newLink.setHeadline(eElement.getElementsByTagName("title").item(0).getTextContent());
							newLink.setLinkUrl(eElement.getElementsByTagName("link").item(0).getTextContent());
							newLink.setHeadlineImageUrl(Desc.substring(Desc.indexOf("src=") + 5, Desc.lastIndexOf(".cms") + 4));
							newLink.setLinkDate(new Date(eElement.getElementsByTagName("pubDate").item(0).getTextContent()));
							newLink.setCatagory(Catagory.toString());
							newLink.setContentInitial(Desc.substring(Desc.indexOf("</a>") + 4, Desc.length() - 1));
							newLink.setContentImageUrl(Desc.substring(Desc.indexOf("src=") + 5, Desc.lastIndexOf(".cms") + 4));
							newLink.setSourceAgency("Times Of India");
							newLink.setPublished(true);
							linksSet.add(newLink);

						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				}

			}
			return linksSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String... s) {

		dospeak("You Have 10 tables newly created", "kevin");
	}

	static String speaktext;

	public static void dospeak(String speak, String voicename) {
		speaktext = speak;
		String voiceName = voicename;
		try {
			System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
			Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

			SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
			Synthesizer synthesizer = Central.createSynthesizer(desc);
			synthesizer.allocate();

			desc = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
			Voice[] voices = desc.getVoices();
			Voice voice = null;
			for (int i = 0; i < voices.length; i++) {
				if (voices[i].getName().equals(voiceName)) {
					voice = voices[i];
					break;
				}
			}
			synthesizer.getSynthesizerProperties().setVoice(voice);
			synthesizer.resume();
			synthesizer.speakPlainText(speaktext, null);
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
			synthesizer.deallocate();
		} catch (Exception e) {
			String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";
			System.out.println("" + e);
			System.out.println(message);
		}
	}

}
