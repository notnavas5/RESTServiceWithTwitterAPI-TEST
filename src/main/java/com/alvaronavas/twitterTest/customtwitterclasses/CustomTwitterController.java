package com.alvaronavas.twitterTest.customtwitterclasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alvaronavas.twitterTest.persistance.CustomTweetEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class CustomTwitterController  {

	private static final List <String> LANGUAGES_LIST = Arrays.asList("en", "fr", "es", "it");
	private static final int MIN_TWEETS_COUNT = 1500;
	private static final int LOCATION_ID = 23424950;//SPAIN

	Twitter twitter = null;
	public CustomTwitterController() {
		this.twitter = new TwitterFactory().getInstance();
	//	service = new CustomTweetService ();
	}
	public List<CustomTweetEntity> getTweets(String topic, int nTweets){
		Query query = new Query(topic);
		QueryResult result;
		int tweetsProcessed = 0; // For Debugging
		int tweetsSaved = 0;
		int i=0;
		List<CustomTweetEntity> customTweetsList = new  ArrayList<CustomTweetEntity>();
		
		int pageIterations = nTweets / 15; // There are 15 tweets per query iteration.

		try {
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) { 
					if(checkTweet(tweet)){
						customTweetsList.add(new CustomTweetEntity(tweet.getId(), tweet.getUser().getScreenName(), tweet.getText(), tweet.getGeoLocation(), false));
						tweetsSaved++;
					}
					tweetsProcessed++;
				}
				i++;
			} while ((query = result.nextQuery()) != null && i < pageIterations);
			System.out.println("TWEETS PROCESSED: " + tweetsProcessed);
			System.out.println("TWEETS SAVED: " + tweetsSaved);

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		return customTweetsList;
	}

	public List<String> getMostUsedHashtags(){
		List<String> hashtagList = new  ArrayList<String>();
		try {
			Trends trends = twitter.getPlaceTrends(LOCATION_ID);
			System.out.println("Showing trends for " + trends.getLocation().getName());

			for (Trend trend : trends.getTrends()) {
				if(trend.getName().contains("#"))
					hashtagList.add("Trend: " + trend.getName() + " - Number of tweets: " +  trend.getTweetVolume());
			}

			System.out.println("done.");
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get trends: " + te.getMessage());
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			System.out.println("WOEID must be number");
		}

		return hashtagList;
	}

	private boolean checkTweet(Status tweet){
		return tweet.getUser().getFollowersCount() >= MIN_TWEETS_COUNT && LANGUAGES_LIST.contains(tweet.getLang());
	}


}
