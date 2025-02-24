package com.ok.owner.service;

import com.ok.dto.DogDto;
import com.ok.dto.OwnerDto;
import com.ok.owner.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class OwnerService {
    private Map<Integer, Owner> map;
    private Logger logger = Logger.getLogger(OwnerService.class.getName());

    @Autowired
    private DogServiceClient dogServiceClient;

    @PostConstruct
    public void init() {
        logger.info(">>OwnerService>>init");
        map = Map.of(1, Owner.of(1, "owner1"),
                2, Owner.of(2, "owner2"),
                3, Owner.of(3, "owner3"));
    }

    public OwnerDto getOwnerById(int ownerId) {
        logger.info(">>OwnerService>>getOwnerById");
        List<DogDto> dogs = dogServiceClient.getDogsByOwnerId(ownerId);
        Owner owner = map.get(ownerId);
        return OwnerDto.of(owner.getOwnerId(), owner.getOwnerName(), dogs);
    }


    public List<OwnerDto> getAllOwners() {
        return this.map.values().stream()
                .map(owner -> OwnerDto.of(owner.getOwnerId(), owner.getOwnerName(), Collections.emptyList()))
                .toList();
    }

}
