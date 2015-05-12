package oz.common.utils.net;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLUtils {

	/**
	 * isVideoUrl�Ƿ�Ϊvideo����
	 * */
	public static boolean isVideoUrl(String url)
	{
		
		String regex = "(http)?(HTTP)?(https)?(HTTPS)?(ftp)?(FTP)?://.+\\.(avi)?(AVI)?(mp4)?(MP4)?(3gp)?(3GP)?(rmvb)?(RMVB)?(rm)?(RM)?";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(url);
		
		return m.matches();
		
	}
	
	/**
	 * getVideoUrl��ȡΪvideo����
	 * */
	public static String[] getVideoUrl(String strData)
	{
		
		String regex = "(http)?(HTTP)?(https)?(HTTPS)?(ftp)?(FTP)?://.+\\.(avi)?(AVI)?(mp4)?(MP4)?(3gp)?(3GP)?(rmvb)?(RMVB)?(rm)?(RM)?";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(strData);
		
		List<String> list = new ArrayList<String>();
		
		while (m.find())
		{
			
			list.add(m.group());
			
		}
		
		String urls[] = new String[list.size()];
		
		for (int i = 0; i < urls.length; i++)
		{
			
			urls[i] = list.get(i);
			
		}
		
		return urls;
		
	}
	
	/**
	 * isVideoUrl�Ƿ�Ϊֱ��video����
	 * */
	public static boolean isLiveVideoUrl(String url)
	{
		
		String regex = "(rtmp)?(RTMP)?://.+";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(url);
		
		return m.matches();
		
	}
	
	/**
	 * getLiveVideoUrl��ȡΪֱ��video����
	 * */
	public static String[] getLiveVideoUrl(String strData)
	{
		
		String regex = "(rtmp)?(RTMP)?://.+";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(strData);
		
		List<String> list = new ArrayList<String>();
		
		while (m.find())
		{
			
			list.add(m.group());
			
		}
		
		String urls[] = new String[list.size()];
		
		for (int i = 0; i < urls.length; i++)
		{
			
			urls[i] = list.get(i);
			
		}
		
		return urls;
		
	}
	
	/**
	 * isVideoUrl�Ƿ�Ϊֱ��video����
	 * */
	public static boolean isImageUrl(String url)
	{
		
		String regex = "(http)?(HTTP)?(https)?(HTTPS)?(ftp)?(FTP)?(file)?(FILE)?://.+\\.(jpg)?(JPG)?(png)?(PNG)?";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(url);
		
		return m.matches();
		
	}
	
	/**
	 * getImageUrl��ȡImage����
	 * */
	public static String[] getImageUrl(String strData)
	{
		
		String regex = "(http)?(HTTP)?(https)?(HTTPS)?(ftp)?(FTP)?(file)?(FILE)?://.+\\.(jpg)?(JPG)?(png)?(PNG)?";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(strData);
		
		List<String> list = new ArrayList<String>();
		
		while (m.find())
		{
			
			list.add(m.group());
			
		}
		
		String urls[] = new String[list.size()];
		
		for (int i = 0; i < urls.length; i++)
		{
			
			urls[i] = list.get(i);
			
		}
		
		return urls;
		
	}
	
	/**
	 * isAudioUrl�Ƿ�Ϊaudio����
	 * */
	public static boolean isAudioUrl(String url)
	{
		
		String regex = "(http)?(HTTP)?(https)?(HTTPS)?(ftp)?(FTP)?(file)?(FILE)?://.+\\.(mp3)?(MP3)?(wvm)?(WVM)?";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(url);
		
		return m.matches();
		
	}
	
	/**
	 * getAudioUrl��ȡAudio����
	 * */
	public static String[] getAudioUrl(String strData)
	{
		
		String regex = "(http)?(HTTP)?(https)?(HTTPS)?(ftp)?(FTP)?(file)?(FILE)?://.+\\.(mp3)?(MP3)?(wvm)?(WVM)?";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(strData);
		
		List<String> list = new ArrayList<String>();
		
		while (m.find())
		{
			
			list.add(m.group());
			
		}
		
		String urls[] = new String[list.size()];
		
		for (int i = 0; i < urls.length; i++)
		{
			
			urls[i] = list.get(i);
			
		}
		
		return urls;
		
	}
	
	
}