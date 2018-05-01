package com.heapix.events.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author mgergalov
 */
@SpringBootApplication
@ComponentScan("com.heapix.events")
@EnableJpaRepositories("com.heapix.events")
@EntityScan("com.heapix.events")
public class EventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsApplication.class, args);
    }

}
