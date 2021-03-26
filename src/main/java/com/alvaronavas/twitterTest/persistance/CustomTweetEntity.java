package com.alvaronavas.twitterTest.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import twitter4j.GeoLocation;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tweet_table")
public class CustomTweetEntity {

	@Id
	@Column(name="id")
	private long tweetId;
	
	@Column(name="user")
	private String user;
	
	@Column(name="text",length=1000)
	private String text;
	
	@Column(name="geoLocation")
	GeoLocation geoLocation;
	
	@Column(name="validated")
	boolean validated;
	
	public CustomTweetEntity(){}
	public CustomTweetEntity(long id, String user, String text, GeoLocation geoLocation, boolean validated) {
		tweetId = id;
		this.user = user;
		this.text = text;
		this.geoLocation = geoLocation;
		this.validated = validated;
	}

	public long gettweetId() {
		return tweetId;
	}

	public void settweetId(long id) {
		tweetId = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

}
