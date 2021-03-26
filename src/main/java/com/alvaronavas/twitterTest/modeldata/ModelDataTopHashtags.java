package com.alvaronavas.twitterTest.modeldata;

import java.io.Serializable;
import java.util.List;

public class ModelDataTopHashtags implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8739866586595341719L;

	/**
	 * 
	 */
	List<String> hashtagList = null;

	public List<String> getHashtagList() {
		return hashtagList;
	}

	public void setHashtagList(List<String> hashtagList) {
		this.hashtagList = hashtagList;
	}
	
}
