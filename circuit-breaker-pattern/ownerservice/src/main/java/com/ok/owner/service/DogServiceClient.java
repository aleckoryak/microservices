package com.ok.owner.service;

import com.ok.dto.DogDto;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DogServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private Logger logger = Logger.getLogger(DogServiceClient.class.getName());
    @Value("${dogService.service.endpoint}")
    private String dogServiceEndpoint;


    @CircuitBreaker(name = "dogService", fallbackMethod = "onError")
    public List<DogDto> getDogsByOwnerId(int ownerId){
        logger.info(">>DogServiceClient>>getDogsByOwnerId");

        return restTemplate.getForObject(dogServiceEndpoint + ownerId, List.class);
    }

    public List<DogDto> onError(int ownerId,Throwable throwable){
        logger.log(Level.SEVERE, ">>DogServiceClient>>CIRCUIRBREACKER>>onError:"+throwable.getMessage());
        return Collections.emptyList();
    }
}
