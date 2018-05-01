package com.heapix.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author mgergalov
 */
@SpringBootApplication(scanBasePackages={"com.heapix.*"})
@EnableAutoConfiguration
public class EventsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EventsApplication.class, args);
    }

}
