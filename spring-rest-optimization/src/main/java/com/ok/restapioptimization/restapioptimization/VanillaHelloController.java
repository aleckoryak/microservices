package com.ok.restapioptimization.restapioptimization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VanillaHelloController {


    @ResponseBody
    @GetMapping("/hello-vanilla")
    public String hello(@RequestParam String name) {
        return "Hello, " + name + "!";
    }
}
