package com.kibong.searchshopping.shopping.service;

import org.json.simple.JSONObject;

public interface ShoppingService {

    JSONObject getShoppingList(String searchValue, Integer currentPage, String sort);
}
