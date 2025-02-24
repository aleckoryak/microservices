package com.ok.owner.service;

import com.ok.dto.DogDto;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
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
    @Value("${dog.service.endpoint}")
    private String dogServiceEndpoint;

    @Bulkhead(name = "dogService", fallbackMethod = "getDefault")
    public List<DogDto> getDogsByOwnerId(int ownerId){
        logger.info(">>DogServiceClient>>getDogsByOwnerId");

        return restTemplate.getForObject(dogServiceEndpoint + ownerId, List.class);
    }

    public List<DogDto> getDefault(int ownerId,Throwable throwable){
        logger.log(Level.SEVERE, ">>DogServiceClient>>BULKHEAD>>default");
        return Collections.emptyList();
    }
}
