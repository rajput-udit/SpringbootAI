package com.openrouter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class OpenRouterAIController {

	private final ChatModel chatModel;
	private static final Logger log = LoggerFactory.getLogger(OpenRouterAIController.class);
	
	public OpenRouterAIController(ChatModel chatModel) {
		this.chatModel = chatModel;
		log.info("OpenRouterAIController (instance) initialized with Spring AI ChatModel.");
	}
	
	@GetMapping("/{message}")
	public ResponseEntity<String> chatWithOpenRouter(@PathVariable String message) {
		try {
			String response = this.chatModel.call(message);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error("Error processing chat request via Spring AI: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, an error occurred while communicating with the AI service.");
		}
	}
	
	@PostMapping("/chat")
	public ResponseEntity<String> chatWithOpenRouter(@RequestBody ChatRequest chatRequest) {
		try {
			String response = this.chatModel.call(chatRequest.getMessage());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error("Error processing chat request via Spring AI: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Sorry, an error occurred while communicating with the AI service.");
		}
	}
}
