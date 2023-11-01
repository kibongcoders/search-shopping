package com.kibong.searchshopping.shopping.controller;

import com.kibong.searchshopping.shopping.service.ShoppingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping")
@Slf4j
@RequiredArgsConstructor
public class ShoppingController {

    private final ShoppingService shoppingService;

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

    @GetMapping("/check")
    public String check(){
        return "search check";
    }

    @GetMapping("/getShoppingList/{searchValue}")
    public JSONObject getShoppingList(
            @PathVariable String searchValue,
            @RequestParam Integer currentPage,
            @RequestParam String sort
    ){
        return shoppingService.getShoppingList(searchValue, currentPage, sort);
    }

}
