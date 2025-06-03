package com.aistudio.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.aistudio.model.Player;

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
    
    private final ChatClient chatClient;
    
    /**
	 * Constructor for GeminiModelController.
	 * Initializes the RestClient with the base URL and sets up the ChatClient builder.
	 *
	 * @param builder the RestClient builder
	 * @param chatClientBuilder the ChatClient builder
	 */
    public GeminiModelController(RestClient.Builder builder, ChatClient.Builder chatClient) {
        log.info("GeminiModelController...");
        builder.baseUrl(BASE_URL).build();
		this.chatClient = chatClient.build();
    }

        
    /**	* This method is used to get the list of popular sports personalities
     * 	* It uses a path variable to accept the sports type and returns a formatted message
     * @param sports
     * @return
     */
    @GetMapping("/sports/{sports}")  
    public List<String> findPopularSportsPerson(@PathVariable String sports) {
    	
    	ListOutputConverter listOutputConverter = new ListOutputConverter(new DefaultConversionService());
    	
        String message = """
            List of 5 most popular personalities in {sports}. 
            {format}
            """;
        
        PromptTemplate template = new PromptTemplate(message);
        Prompt prompt = template.create(Map.of("sports", sports,"format", listOutputConverter.getFormat()));
        ChatResponse response =chatClient.prompt(prompt).call().chatResponse();
     // Correct way to get the content
        return listOutputConverter.convert(response.getResult().getOutput().getText());  // Get content from the message
    }
    
    @GetMapping("/")  
    public Player findPopularSportsMan(@RequestParam String sports) {
    	
    	BeanOutputConverter<Player> beanOutputConverter = new BeanOutputConverter<Player>(Player.class);
    	
        String message = """
            Generate a List of carrier achievements for the sportsperson in {sports}. \s
            Include the player as a key and achievements as the value for it.
            {format}
            """;
        
        PromptTemplate template = new PromptTemplate(message);
        Prompt prompt = template.create(Map.of("sports", sports,"format", beanOutputConverter.getFormat()));
        ChatResponse response =chatClient.prompt(prompt).call().chatResponse();
        return beanOutputConverter.convert(response.getResult().getOutput().getText());  // Get content from the message
    }
}