package com.example.wantedpreonboardingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EmploymentReadOneDto {

    private Long id;
    private String companyName;
    private String country;
    private String region;
    private String position;
    private Integer money;
    private String skill;
    private String content;
    private List<Long> otherEmploymentList;

    public EmploymentReadOneDto(Long id, String companyName, String country, String region, String position, Integer money, String skill, String content) {
        this.id = id;
        this.companyName = companyName;
        this.country = country;
        this.region = region;
        this.position = position;
        this.money = money;
        this.skill = skill;
        this.content = content;
    }

    public void addOtherEmploymentList(List<Long> list) {
        this.otherEmploymentList = list;
    }
}
