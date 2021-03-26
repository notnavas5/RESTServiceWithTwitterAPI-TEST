package com.alvaronavas.twitterTest.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomTweetService {
    
	@Autowired
    private CustomTweetRepository repository;

    public CustomTweetEntity saveCustomTweetEntity(CustomTweetEntity customTweetEntity) {
        return repository.save(customTweetEntity);
    }

    public List<CustomTweetEntity> saveCustomTweetEntitys(List<CustomTweetEntity> customTweetEntity) {
        return repository.saveAll(customTweetEntity);
    }

    public List<CustomTweetEntity> getCustomTweetEntitys() {
        return repository.findAll();
    }

    public CustomTweetEntity getCustomTweetEntityById(int id) {
        return repository.findById(id).orElse(null);
    }
    public CustomTweetEntity getCustomTweetEntityByTweetId(int id) {
        return repository.findBytweetId(id);
    }
    public List<CustomTweetEntity> getValidatedTweets() {
        return repository.findAllByvalidated(true);
    }
    public CustomTweetEntity validateCustomTweetEntity(long id) {
    	CustomTweetEntity existingCustomTweetEntity = repository.findBytweetId(id);
    	if(existingCustomTweetEntity != null){
    		existingCustomTweetEntity.setValidated(true);
    	}
        return repository.save(existingCustomTweetEntity);
    }


}
