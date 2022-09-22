package com.example.apachepoiexample.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Style {
    private String type;
    private int size;
    private boolean isBold;
    private boolean isItalic;
    private String color;
}
