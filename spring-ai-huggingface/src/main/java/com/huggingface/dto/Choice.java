package com.huggingface.dto;

import lombok.Data;

@Data
public class Choice {
    private int index;
    private ChoiceMessage message;
    private String finish_reason;
}
