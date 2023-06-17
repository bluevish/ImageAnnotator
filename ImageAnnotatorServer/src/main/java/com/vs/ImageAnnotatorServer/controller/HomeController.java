package com.vs.ImageAnnotatorServer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String sayHello(){
        return "Hello from ImageAnnotator";
    }

}
