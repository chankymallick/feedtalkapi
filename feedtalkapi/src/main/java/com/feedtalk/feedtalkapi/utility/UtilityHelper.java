package com.feedtalk.feedtalkapi.utility;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;

public class UtilityHelper {

	public enum UserAuthourities{
		ADMIN,AUTHOUR,READER
	}
	public enum FeedCatagory {
		TOPSTORY,
		TECH,
		CRICKET,
		POLITICS,
		MOVIES,
		ENTERTAINMENT ,
		FACTCHECK,	
		WORLD,		
		SPORTS,
		INFORMATIVE,
		HEALTH,
		OTHERS

		
	}

	public enum CommentType {
		MAIN, REPLY
	}

	public static String NDTVRssFeed_TrendingNews = "http://feeds.feedburner.com/ndtvnews-trending-news";
	public static String NDTVRssFeed_Technology = "http://feeds.feedburner.com/gadgets360-latest";
	public static String NDTVRssFeed_Cricket = "http://feeds.feedburner.com/ndtvsports-cricket";
	public static String TimesOfIndiaTopTrending = "https://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms";
	public static String TimesOfIndiaEntertainment = "https://timesofindia.indiatimes.com/rssfeeds/1081479906.cms";

	public static String getBase64DecodedValue(String base64encodedString){
		try {
			byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);		
	        return new String(base64decodedBytes, "utf-8");
		} catch (Exception e) {
			return null;
		}
		
	}
	public static String encrypt(String key, String value) {
        try {
            for (int i = key.length(); i <= 25; i++) {
                key += Integer.toString(i);
            }
            final String strPassPhrase = key;
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            SecretKey genkey = factory.generateSecret(new DESedeKeySpec(strPassPhrase.getBytes()));
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, genkey);
            String str = DatatypeConverter.printBase64Binary(cipher.doFinal(value.getBytes()));
            return str;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String key, String encryptedText) {
        try {
            for (int i = key.length(); i <= 25; i++) {
                key += Integer.toString(i);
            }
            final String strPassPhrase = key;
            Cipher cipher = Cipher.getInstance("DESede");
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            SecretKey genkey = factory.generateSecret(new DESedeKeySpec(strPassPhrase.getBytes()));
            cipher.init(Cipher.DECRYPT_MODE, genkey);
            String str2 = new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(encryptedText)));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
