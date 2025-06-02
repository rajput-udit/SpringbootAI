package com.aistudio.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.aistudio.model.GeminiModel;
import com.aistudio.model.ModelListResponse;

@RestController
public class GeminiModelController {

    private static final Logger log = LoggerFactory.getLogger(GeminiModelController.class);
    @Value("${spring.ai.openai.api-key}")
    private String GEMINI_API_KEY;
    private final RestClient restClient;

    private final ChatClient.Builder chatClientBuilder;

    public GeminiModelController(RestClient.Builder builder, ChatClient.Builder chatClientBuilder) {
        log.info("GeminiModelController...");
        this.restClient = builder
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();
		this.chatClientBuilder = chatClientBuilder;
    }

    @GetMapping("/models")
    public List<GeminiModel> models() {
        ResponseEntity<ModelListResponse> response = restClient.get()
                .uri("/v1beta/openai/models")
                .header("Authorization","Bearer " + GEMINI_API_KEY)
                .retrieve()
                .toEntity(ModelListResponse.class);
        return response.getBody().data();
    }
    
    @GetMapping("/{message}")
    public String getCustomFact(@PathVariable String message) {
        return chatClientBuilder.build()
                .prompt(message)
                .call()
                .content();
    }

}