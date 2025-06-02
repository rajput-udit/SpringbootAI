package com.huggingface.dto;

import lombok.Data;

@Data
public class ChoiceMessage {
    private String role;
    private String content;

}
