package com.example.hrecruit.controller;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/video")
public class RoomController {

    private static final String DAILY_API_KEY = "f8c2c52d154ef4521fbd7448feec99aa1fbf968d2ebbe20d1ab27cc816959a74";
    private static final String DAILY_BASE_URL = "https://api.daily.co/v1/rooms";

    @PostMapping("/create-room")
    public Map<String, String> createRoom(@RequestParam String roomName) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Room configuration JSON
            String roomConfig = "{\"name\": \"" + roomName + "\", \"privacy\": \"public\"}";

            // Create POST request
            HttpPost postRequest = new HttpPost(DAILY_BASE_URL);
            postRequest.setHeader("Authorization", "Bearer " + DAILY_API_KEY);
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setEntity(new StringEntity(roomConfig));

            // Build the response map
            Map<String, String> result = new HashMap<>();
            result.put("room_url", "https://innterview.daily.co/" + roomName);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create room: " + e.getMessage());
        }
    }
}
