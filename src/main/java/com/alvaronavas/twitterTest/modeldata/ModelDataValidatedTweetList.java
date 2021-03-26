package com.alvaronavas.twitterTest.modeldata;

import java.io.Serializable;
import java.util.List;

import com.alvaronavas.twitterTest.persistance.CustomTweetEntity;

public class ModelDataValidatedTweetList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5460685873568045739L;

	List<CustomTweetEntity> validatedTweetList = null;

	public List<CustomTweetEntity> getvalidatedTweetList() {
		return validatedTweetList;
	}

	public void setvalidatedTweetList(List<CustomTweetEntity> validatedTweetList) {
		this.validatedTweetList = validatedTweetList;
	}
}
