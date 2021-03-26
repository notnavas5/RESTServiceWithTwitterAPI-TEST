package com.alvaronavas.twitterTest.modeldata;

import java.io.Serializable;

import com.alvaronavas.twitterTest.persistance.CustomTweetEntity;

public class ModelDataValidateTwit implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3065298130183080873L;

	CustomTweetEntity tweet = null;
	int status = 0;
	
	public CustomTweetEntity getTweet() {
		return tweet;
	}

	public void setTweet(CustomTweetEntity tweet) {
		this.tweet = tweet;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
