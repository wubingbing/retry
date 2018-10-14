package com.example.retry.controller;

import com.example.retry.service.RemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private RemoteService remoteService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name) {
        System.out.println("name:" + name);
        return name;
    }

    @RequestMapping("/test")
    public String login() {
        remoteService.call();
        return String.valueOf("11");
    }




}
