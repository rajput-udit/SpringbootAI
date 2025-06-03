package com.aistudio.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

/**
 * This controller handles requests related to Gemini AI model.
 * It provides endpoints to get information about popular sports personalities
 * and allows users to query the AI model with custom messages.
 */
@RestController
public class GeminiModelController {

    private static final Logger log = LoggerFactory.getLogger(GeminiModelController.class);
    @Value("${spring.ai.openai.api-key}")
    private String GEMINI_API_KEY;
    @Value("${spring.ai.openai.chat.base-url}")
    private String BASE_URL;
    private final ChatClient.Builder chatClientBuilder;
    
    /**
	 * Constructor for GeminiModelController.
	 * Initializes the RestClient with the base URL and sets up the ChatClient builder.
	 *
	 * @param builder the RestClient builder
	 * @param chatClientBuilder the ChatClient builder
	 */
    public GeminiModelController(RestClient.Builder builder, ChatClient.Builder chatClientBuilder) {
        log.info("GeminiModelController...");
        builder.baseUrl(BASE_URL).build();
		this.chatClientBuilder = chatClientBuilder;
    }

    /**
	 * This method is used to get the list of popular sports personalities
	 * It uses a path variable to accept the message and returns a formatted message
	 * @param message
	 * @return
	 */
    @GetMapping("/{message}")
    public String getCustomFact(@PathVariable String message) {
        return chatClientBuilder.build().prompt(message).call().content();
    }
    
    /**	* This method is used to get the list of popular sports personalities
     * 	* It uses a path variable to accept the sports type and returns a formatted message
     * @param sports
     * @return
     */
    @GetMapping("/sports/{sports}")  
    public String findPopularSportsPerson(@PathVariable String sports) {
        String message = """
            List of 5 most popular personalities in {sports} along 
            with their career achievements. 
            Show the details in proper readable format.
            """;
        PromptTemplate template = new PromptTemplate(message);
        Prompt prompt = template.create(Map.of("sports", sports));
        return chatClientBuilder.build().prompt(prompt).call().content();
    }
    
    /**
	 * This method is used to get the list of popular sports personalities
	 * @param sports
	 * @return
	 */
    @GetMapping("/")  
    public String findPopularSportsMan(@RequestParam String sports) {
    	
    	var SystemMessage = new SystemMessage("""
    			Your primary function is to share the information about the sports personalities.
    			If someone ask about anything else, 
    			you can say you only share information about sports category.  
    			""");
    	
    	var userMessage = new UserMessage(String.format("List of 5 most popular personalities in %s along "
    			+ "with their career achievements. "
    			+ "Show the details in proper readable format.", sports));
    	
        Prompt prompt =new Prompt(List.of(SystemMessage, userMessage));
        return chatClientBuilder.build().prompt(prompt).call().content();
    }
}