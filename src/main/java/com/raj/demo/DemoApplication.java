package com.raj.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject("http://localhost:8080/test/400", String.class);
            log.info("Got response={}", forObject);
        } catch (HttpStatusCodeException e) {
            ResponseEntity<String> errorBody = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
            log.info("Got error response={}",errorBody.getBody());
        }
        catch (ResourceAccessException ee) {
            log.error("Got ResourceAccessException");
        }
    }
}
