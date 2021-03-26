package com.alvaronavas.twitterTest.modeldata;

import java.io.Serializable;
import java.util.List;

import com.alvaronavas.twitterTest.persistance.CustomTweetEntity;

public class ModelDataTweetListByTopic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6254276175970660486L;

	List<CustomTweetEntity> tweetListByTopic = null;

	public List<CustomTweetEntity> getTweetListByTopic() {
		return tweetListByTopic;
	}

	public void setTweetListByTopic(List<CustomTweetEntity> tweetListByTopic) {
		this.tweetListByTopic = tweetListByTopic;
	}

}
