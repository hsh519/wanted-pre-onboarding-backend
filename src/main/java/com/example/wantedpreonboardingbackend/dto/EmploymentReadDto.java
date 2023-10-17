package com.example.wantedpreonboardingbackend.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmploymentReadDto {

    private Long id;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer money;
    private String skill;
}
