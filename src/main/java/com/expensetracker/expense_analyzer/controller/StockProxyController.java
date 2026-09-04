package com.expensetracker.expense_analyzer.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/market")
public class StockProxyController {

    @GetMapping("/stock")
    public Map<String, Object> getStockPrice(@RequestParam String symbol) {
        String url = "https://query1.finance.yahoo.com/v8/finance/chart/" + symbol;
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Yahoo Finance blocks default automated Java clients; a User-Agent header bypasses 403 errors
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            Map<String, Object> body = response.getBody();

            if (body != null && body.containsKey("chart")) {
                Map<String, Object> chart = (Map<String, Object>) body.get("chart");
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) chart.get("result");

                if (resultList != null && !resultList.isEmpty()) {
                    Map<String, Object> meta = (Map<String, Object>) resultList.get(0).get("meta");
                    Object regularMarketPrice = meta.get("regularMarketPrice");

                    Map<String, Object> normalizedMap = new HashMap<>();
                    normalizedMap.put("c", regularMarketPrice);
                    return normalizedMap;
                }
            }
        } catch (Exception e) {
            System.err.println("Yahoo Finance fetch failed for symbol " + symbol + ": " + e.getMessage());
        }

        return Map.of("c", 0);
    }
}