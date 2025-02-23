package com.ok.owner.controller;

import com.ok.owner.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    Logger logger = Logger.getLogger(OwnerController.class.getName());
    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public String getAllOwners() throws InterruptedException {
        logger.info(">>OwnerController>>getAllOwners");
        Thread.sleep(100);
        return ownerService.getAllOwners().toString();
    }

    @GetMapping("/{ownerId}")
    public String getOwnerById(@PathVariable int ownerId) {
        logger.info(">>OwnerController>>getOwnerById");
        return ownerService.getOwnerById(ownerId).toString();
    }
}
