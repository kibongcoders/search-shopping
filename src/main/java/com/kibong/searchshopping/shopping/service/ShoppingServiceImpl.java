package com.kibong.searchshopping.shopping.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kibong.searchshopping.shopping.dto.PageDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShoppingServiceImpl implements ShoppingService{

    @Value("${naver.client_id}")
    private String clientId;

    @Value("${naver.client_secret}")
    private String clientSecret;

    public JSONObject getShoppingList(String searchValue, Integer currentPage, String sort) {
        JSONObject jsonObject = naverShopping(searchValue, currentPage, sort);
        Integer total = Integer.valueOf(jsonObject.get("total").toString()) ;
        PageDto pageDto = new PageDto(total, currentPage);
        jsonObject.put("page", pageDto);
        return jsonObject;
    }

    public JSONObject naverShopping(String searchValue, Integer currentPage, String sort){
        Integer offset = ((currentPage - 1) * 10) + 1;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> params = new HashMap<>();
            params.put("query", "아이폰");
            String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(params);
            HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(requestBody);

            String url = new StringBuilder()
                    .append("https://openapi.naver.com/v1/search/shop.json")
                    .append("?query=")
                    .append(searchValue)
                    .append("&start=")
                    .append(offset)
                    .append("&sort=")
                    .append(sort)
                    .toString();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("X-Naver-Client-Id", clientId)
                    .header("X-Naver-Client-Secret", clientSecret)
                    .method("GET", body)
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONParser jsonParser = new JSONParser();

            return (JSONObject) jsonParser.parse(response.body());

        } catch (IOException | InterruptedException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
