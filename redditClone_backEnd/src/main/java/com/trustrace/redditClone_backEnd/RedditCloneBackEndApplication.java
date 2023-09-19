package com.trustrace.redditClone_backEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RedditCloneBackEndApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedditCloneBackEndApplication.class, args);
	}

}
