package com.huggingface.dto;

import java.util.List;

import lombok.Data;

@Data
public class AIResponse {

	private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
}
