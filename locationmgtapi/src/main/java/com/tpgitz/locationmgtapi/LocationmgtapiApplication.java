package com.tpgitz.locationmgtapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class LocationmgtapiApplication {

    @RequestMapping("/")
    String index() {
        return "Spring Boot : dont ran out the faith";
    }

    public static void main(String[] args) {
        SpringApplication.run(LocationmgtapiApplication.class, args);
    }

}
