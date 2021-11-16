package com.example.restaurantmcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RestaurantMcsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantMcsApplication.class, args);
    }

}
