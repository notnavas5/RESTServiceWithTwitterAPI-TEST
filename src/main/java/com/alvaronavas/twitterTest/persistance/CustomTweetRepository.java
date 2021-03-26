package com.alvaronavas.twitterTest.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomTweetRepository extends JpaRepository<CustomTweetEntity,Integer> {
	
	CustomTweetEntity findBytweetId(long Id);
	List<CustomTweetEntity> findAllByvalidated(boolean validated);

}

