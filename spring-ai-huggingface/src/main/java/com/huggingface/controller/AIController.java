package com.huggingface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.huggingface.service.HuggingFaceAIService;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private HuggingFaceAIService aiService;

    @GetMapping("/ask")
    public String askAI(@RequestParam String question) {
        return aiService.getAnswerFromAI(question);
    }
}
