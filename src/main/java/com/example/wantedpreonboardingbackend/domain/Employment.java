package com.example.wantedpreonboardingbackend.domain;

import com.example.wantedpreonboardingbackend.dto.EmploymentCreateDto;
import com.example.wantedpreonboardingbackend.dto.EmploymentUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Employment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTICE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    private String position;
    private Integer money;
    private String content;
    private String skill;

    public Employment(Company company, String position, Integer money, String content, String skill) {
        this.company = company;
        this.position = position;
        this.money = money;
        this.content = content;
        this.skill = skill;
    }

    public void mapCreateDtoToEntity(EmploymentCreateDto employmentCreateDto, Company company) {
        this.company = company;
        this.position = employmentCreateDto.getPosition();
        this.money = employmentCreateDto.getMoney();
        this.content = employmentCreateDto.getContent();
        this.skill = employmentCreateDto.getSkill();
    }

    public void mapUpdateDtoToEntity(EmploymentUpdateDto employmentUpdateDto) {
        this.position = employmentUpdateDto.getPosition();
        this.money = employmentUpdateDto.getMoney();
        this.content = employmentUpdateDto.getContent();
        this.skill = employmentUpdateDto.getSkill();
    }
}
