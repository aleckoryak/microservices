package com.ok.restapioptimization.restapioptimization.undertow;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.springframework.stereotype.Component;

@Component
public class HelloHttpHandler implements HttpHandler {


    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) {
        String name = httpServerExchange.getQueryParameters().get("name").peekFirst();
        httpServerExchange.getResponseSender().send("Hello, " + name + "!");
    }
}
