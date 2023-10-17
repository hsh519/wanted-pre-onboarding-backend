package com.example.wantedpreonboardingbackend.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class EmploymentCreateDto {

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
