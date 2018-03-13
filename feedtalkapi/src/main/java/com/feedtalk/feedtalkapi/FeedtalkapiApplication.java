package com.feedtalk.feedtalkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.feedtalk.feedtalkapi.Repositories.FeedLinksRepository;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedLinksRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.UserRepoImpl;
import com.feedtalk.feedtalkapi.Scrapper.NDTVLinksExtractor;
import com.feedtalk.feedtalkapi.Scrapper.RSSReader;
import com.feedtalk.feedtalkapi.RepositoryImpl.LikeDislikeHistoryRepoImpl;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.feedtalk.feedtalkapi.Repositories")
@Configuration
@EnableScheduling
public class FeedtalkapiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FeedtalkapiApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(FeedtalkapiApplication.class, args);
	}
	
	@Bean
	public FeedRepoImpl feedRepoImplementation(){
		return new FeedRepoImpl();
	}
	@Bean
	public UserRepoImpl userRepoImplementation(){
		return new UserRepoImpl();
	}
	@Bean
	public FeedLinksRepoImpl feedLinksRepoImplementation(){
		return new FeedLinksRepoImpl();
	}
	@Bean
	public LikeDislikeHistoryRepoImpl LikeDislikeHistoryRepoImpl(){
		return new LikeDislikeHistoryRepoImpl();
	}
	
	@Bean 
	public RSSReader rssReader(){
		return new RSSReader();
	}
}

