package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class App {
    @Autowired
    ConnectionRepository connectionRepository;
    @Autowired
    Twitter twitter;

    @RequestMapping("/")
    String home(@RequestParam("tweet") String tweet) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "<a href='/connect/twitter'>Please connect</a>";
        }
        twitter.timelineOperations().updateStatus(tweet);
        return tweet + " is tweeted!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
