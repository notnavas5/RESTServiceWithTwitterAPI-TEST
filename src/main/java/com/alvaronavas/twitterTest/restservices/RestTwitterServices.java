package com.alvaronavas.twitterTest.restservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvaronavas.twitterTest.customtwitterclasses.CustomTwitterController;
import com.alvaronavas.twitterTest.modeldata.ModelDataTopHashtags;
import com.alvaronavas.twitterTest.modeldata.ModelDataTweetListByTopic;
import com.alvaronavas.twitterTest.modeldata.ModelDataValidateTwit;
import com.alvaronavas.twitterTest.modeldata.ModelDataValidatedTweetList;
import com.alvaronavas.twitterTest.persistance.CustomTweetEntity;
import com.alvaronavas.twitterTest.persistance.CustomTweetService;

@RestController
@RequestMapping("/rest/twitterapp")
public class RestTwitterServices {
	
	CustomTwitterController twControl = new CustomTwitterController(); //esto lo hare interfaz
	@Autowired
	private CustomTweetService service;
	
	@GetMapping(path ="/getTweets", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelDataTweetListByTopic getTweets(@RequestParam(name = "topic") String topic , @RequestParam(name = "ntweets") int nTweets){
		ModelDataTweetListByTopic response = new ModelDataTweetListByTopic();
		List<CustomTweetEntity> tweetList =twControl.getTweets(topic, nTweets);
		storeTweets(twControl.getTweets(topic, nTweets)) ;
		response.setTweetListByTopic(tweetList);
		return response;
	}
	
	@GetMapping(path ="/getTopHashtags", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelDataTopHashtags getTopHashtags(){
		ModelDataTopHashtags response = new ModelDataTopHashtags();
		response.setHashtagList(twControl.getMostUsedHashtags());
		return response;
	}
	
	@GetMapping(path ="/getValidatedTweets", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelDataValidatedTweetList getValidatedTweets(){
		ModelDataValidatedTweetList response = new ModelDataValidatedTweetList();
		response.setvalidatedTweetList(getValdiatedTweets());
		return response;
	}
	@GetMapping(path ="/validateTweet", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelDataValidateTwit setValidateTweet(@RequestParam(name = "id") long id ){
		ModelDataValidateTwit response = new ModelDataValidateTwit();
		
		response.setTweet(validateTweet(id));
		if(response.getTweet() != null){
			response.setStatus(201);
		}else{
			response.setStatus(400);
		}
		
		return response;
	}
	private List<CustomTweetEntity> getValdiatedTweets() {
		// TODO Auto-generated method stub
		return service.getValidatedTweets();
	}
	private CustomTweetEntity validateTweet(long id) {
		// TODO Auto-generated method stub
		return service.validateCustomTweetEntity(id);
	}

	private void storeTweets(List<CustomTweetEntity> tweetList) {
		// TODO Auto-generated method stub
		service.saveCustomTweetEntitys(tweetList);
	}
}
