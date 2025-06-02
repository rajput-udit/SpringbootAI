package com.huggingface.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/ai")
public class ChatController {

	@Value("${huggingface.chat.api-key}")
	private String apiKey;

	@Value("${huggingface.chat.url}")
	private String apiUrl;
	
	@Value("${huggingface.chat.options.model}")
	private String model;

	private final RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/chat")
	public Map<String, Object> generate(@RequestParam String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(apiKey);

		Map<String, Object> body = new HashMap<>();
		body.put("model", model);
		body.put("stream", false);

		List<Map<String, String>> messages = List.of(Map.of("role", "user", "content", message));
		body.put("messages", messages);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
		ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);
		return Map.of("response", response.getBody());
	}
}
