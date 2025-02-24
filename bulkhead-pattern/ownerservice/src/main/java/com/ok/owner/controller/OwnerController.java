package com.ok.owner.controller;

import com.ok.dto.OwnerDto;
import com.ok.owner.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    Logger logger = Logger.getLogger(OwnerController.class.getName());
    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<OwnerDto> getAllOwners() throws InterruptedException {
        logger.info(">>OwnerController>>getAllOwners");
        Thread.sleep(100);
        return ownerService.getAllOwners();
    }

    @GetMapping("/{ownerId}")
    public OwnerDto getOwnerById(@PathVariable int ownerId) {
        logger.info(">>OwnerController>>getOwnerById");
        return ownerService.getOwnerById(ownerId);
    }
}
