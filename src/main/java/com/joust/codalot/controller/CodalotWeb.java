package com.joust.codalot.controller;

import com.joust.codalot.domain.game.CodalotGameResult;
import com.joust.codalot.service.CodalotGameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.joust.codalot")
public class CodalotWeb {
    @Resource
    CodalotGameService codalotGameService;

    @RequestMapping("/")
    public ResponseEntity<CodalotGameResult> index() {
        CodalotGameResult result = codalotGameService.play(null);

        if (null == result){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CodalotWeb.class, args);
    }
}
