package com.example.wantedpreonboardingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmploymentReadListDto {

    private Long id;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer money;
    private String skill;
}
