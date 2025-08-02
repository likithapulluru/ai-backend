package com.ai.testtool.service;

import com.ai.testtool.dto.ChatMessage;
import com.ai.testtool.dto.ChatRequest;
import com.ai.testtool.dto.ChatResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class OpenRouterService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${openrouter.api.key}")
    private String apiKey;

    public String askAi(String userPrompt) {
        String url = "https://openrouter.ai/api/v1/chat/completions";

        ChatMessage message = new ChatMessage("user", userPrompt);
        ChatRequest request = new ChatRequest("mistralai/mistral-7b-instruct", Collections.singletonList(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("HTTP-Referer", "http://localhost:3000");

        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ChatResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, ChatResponse.class
        );

        return response.getBody().getChoices().get(0).getMessage().getContent();
    }
}
