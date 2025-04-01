package com.ok.restapioptimization.restapioptimization.undertow;

import io.undertow.Undertow;
import io.undertow.servlet.api.DeploymentManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.embedded.undertow.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.util.List;

@Component
public class CustomUndertowServletWebServerFactory  extends UndertowServletWebServerFactory {


    private final HelloHttpHandler helloHttpHandler;


    public CustomUndertowServletWebServerFactory(
            ObjectProvider<UndertowDeploymentInfoCustomizer> deploymentInfoCustomizers,
            ObjectProvider<UndertowBuilderCustomizer> builderCustomizers,
            HelloHttpHandler helloHttpHandler) {
        this.getDeploymentInfoCustomizers().addAll(deploymentInfoCustomizers.orderedStream().toList());
        this.getBuilderCustomizers().addAll(builderCustomizers.orderedStream().toList());
        this.helloHttpHandler = helloHttpHandler;
    }


    @Override
    protected UndertowServletWebServer getUndertowWebServer(
            Undertow.Builder builder,
            DeploymentManager manager,
            int port) {
        UndertowServletWebServer undertowServletWebServer = super.getUndertowWebServer(builder, manager, port);
        Field httpHandlerFactoriesField = ReflectionUtils.findField(UndertowWebServer.class, "httpHandlerFactories");
        if (httpHandlerFactoriesField == null) {
            throw new IllegalStateException("Unable to create undertow web server: no httpHandlerFactories field in UndertowWebServer class");
        }
        ReflectionUtils.makeAccessible(httpHandlerFactoriesField);
        List<HttpHandlerFactory> httpHandlerFactories = (List<HttpHandlerFactory>) ReflectionUtils.getField(httpHandlerFactoriesField, undertowServletWebServer);
        httpHandlerFactories.add(new PathHttpHandlerFactory(helloHttpHandler));
        return new UndertowServletWebServer(builder, httpHandlerFactories, getContextPath(), port >= 0);
    }
}