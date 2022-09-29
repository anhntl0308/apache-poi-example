package com.example.apachepoiexample.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class General {
    private String customer;
    private String customerType;
    private String projectScale;
    private String projectLevel;
    private String division;
    private String hardwareAndPlatform;
    private String programmingLanguages;
    private String framework;
    private String tools;
    private String businessDomain;
    private String softwareLifeCycle;
    private String projectType;
    private String projectCost;
    private String projectCode;
    private String expandedFrom;
    private String startDate;
    private String endDate;
}
