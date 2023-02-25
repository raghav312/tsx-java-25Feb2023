package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/webhook")

// http://localhost:8080/webhook

public class WebHook {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @PostMapping
    public void logEvents(@RequestBody String req){
        try{
            LOGGER.log(Level.INFO,"New Log from webhook:");
            LOGGER.log(Level.INFO, req);
            System.out.println("Webhook Reponse -> " + req);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
