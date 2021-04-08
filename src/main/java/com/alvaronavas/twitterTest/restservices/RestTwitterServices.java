package com.alvaronavas.twitterTest.restservices;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alvaronavas.twitterTest.customtwitterclasses.CustomTwitterController;
import com.alvaronavas.twitterTest.modeldata.ModelDataTopHashtags;
import com.alvaronavas.twitterTest.modeldata.ModelDataTweetListByTopic;
import com.alvaronavas.twitterTest.modeldata.ModelDataValidatedTweetList;
import com.alvaronavas.twitterTest.persistance.CustomTweetEntity;
import com.alvaronavas.twitterTest.persistance.CustomTweetService;


@RestController
@RequestMapping("/rest/twitterapp")
@Validated
public class RestTwitterServices {
	
	CustomTwitterController twControl = new CustomTwitterController(); //esto lo hare interfaz
	@Autowired
	private CustomTweetService service;
	
	@GetMapping(path ="/getTweets", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelDataTweetListByTopic getTweets(@NotNull @RequestParam(name = "topic") String topic , @Min(1) @Max(1500) @RequestParam(name = "ntweets") int nTweets){
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
	@PutMapping(path ="/validateTweet", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> setValidateTweet(@Valid @RequestParam(name = "id") long id ){
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		
		if(validateTweet(id) != null){
			response =new ResponseEntity<String>(HttpStatus.OK);
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
