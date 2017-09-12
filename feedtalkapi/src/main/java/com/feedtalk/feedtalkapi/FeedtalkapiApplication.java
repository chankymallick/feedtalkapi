package com.feedtalk.feedtalkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.feedtalk.feedtalkapi.RepositoryImpl.FeedRepoImpl;
import com.feedtalk.feedtalkapi.RepositoryImpl.UserRepoImpl;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.feedtalk.feedtalkapi.Repositories")
@Configuration
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
}

