package com.example.wantedpreonboardingbackend.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class EmploymentDto {

    @NotNull
    private Long companyId;

    @NotEmpty
    private String position;

    @NotNull
    private Integer money;

    @NotEmpty
    private String content;

    @NotEmpty
    private String skill;
}
