package com.ok.dog.service;

import com.ok.dto.DogDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class DogService {
    private Logger logger = Logger.getLogger(DogService.class.getName());
    private Map<Integer, DogDto> map;

    @PostConstruct
    private void init(){
        logger.info(">>DogService>>init");
        map = Map.of(1, DogDto.of(1, 1,"Paris","welsh terrier", 13),
                2, DogDto.of(2, 1,"dog2", "dog", 13),
                3, DogDto.of(3, 2, "dog3","dog", 13));
    }

    public List<DogDto> getDogsByOwnerId(int ownerId){
        logger.info(">>DogService>>getDogsByOwnerId");
        return map.values().stream()
                .filter(dogDto -> dogDto.getOwnerId() == ownerId)
                .toList();
    }

    public List<DogDto> getAllDogs(){
        logger.info(">>DogService>>getAllDogs");
        return map.values().stream().toList();
    }

}
