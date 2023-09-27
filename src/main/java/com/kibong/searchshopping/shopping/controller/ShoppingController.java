package com.kibong.searchshopping.shopping.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search-shopping/shopping")
@Slf4j
public class ShoppingController {

    @GetMapping("hello")
    public String hello(){
        return "shopping hello";
    }

    @GetMapping("/message")
    @ResponseBody
    String message(@RequestHeader("search-request") String searchRequest){
        log.info("search-request = {}", searchRequest);
        return "search message";
    }
}
