package com.ok.ratelimeter.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;


@RestController
@RequestMapping("/ratelimeter")
public class RateLimiterController {
    Logger log = LoggerFactory.getLogger(RateLimiterController.class);
    @GetMapping("/one")
    public Response one() {
        return new Response(HttpStatus.OK, "one");
    }

    @GetMapping("/two")
    @RateLimiter(name = "twoLimiter", fallbackMethod = "limiterErrorResponse")
    public Response two() {
        return new Response(HttpStatus.OK, "one");
    }

    public Response limiterErrorResponse(Throwable throwable){
        log.warn("limiterErrorResponse>>default response: {}", throwable.getMessage());
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "one:" + throwable.getMessage());
    }

    @Data
    @AllArgsConstructor
    public class Response{
        HttpStatus statusCode;
        String message;
    }
}
