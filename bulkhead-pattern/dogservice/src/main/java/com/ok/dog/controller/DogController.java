package com.ok.dog.controller;

import com.ok.dog.service.DogService;
import com.ok.dto.DogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/dogs")
public class DogController {
    Logger logger = Logger.getLogger(DogController.class.getName());

    @Autowired
    private DogService dogService;

    @GetMapping
    public List<DogDto> getAllDogs() {
        logger.info(">>DogController>>getAllDogs");
        return dogService.getAllDogs();
    }

    @GetMapping("{ownerId}")
    public List<DogDto> getDogsByOwner(@PathVariable int ownerId) throws InterruptedException {
        Thread.sleep(3000);
        logger.info(">>DogController>>getDogsByOwnerId");
        return dogService.getDogsByOwnerId(ownerId);
    }
}
