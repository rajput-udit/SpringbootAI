package com.huggingface.service;

import com.huggingface.dto.AIResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class HuggingFaceAIService {

    private static final String API_URL = "https://router.huggingface.co/fireworks-ai/inference/v1/chat/completions";
    private static final String BEARER_TOKEN = "hf_jHNBrIRBEMRWIZCkOIUJHYTUIHGHJNSDHKjGHGHKKwbkIgxuFYu"; // replace with your actual token

    public String getAnswerFromAI(String question) {
        RestTemplate restTemplate = new RestTemplate();

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(BEARER_TOKEN);

        // Request body
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", question);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", List.of(message));
        requestBody.put("model", "accounts/fireworks/models/deepseek-r1-0528");
        requestBody.put("stream", false);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<AIResponse> response = restTemplate.postForEntity(API_URL, request, AIResponse.class);

        // Extract response
        if (response.getBody() != null && !response.getBody().getChoices().isEmpty()) {
            return response.getBody().getChoices().get(0).getMessage().getContent();
        }

        return "No response from AI.";
    }
}
