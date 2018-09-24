package com.microservice.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Spring boot main application.
 *
 * @author rohitnathani
 */
@SpringBootApplication
@EnableJpaAuditing
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

}
