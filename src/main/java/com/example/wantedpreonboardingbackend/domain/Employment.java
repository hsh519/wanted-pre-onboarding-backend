package com.example.wantedpreonboardingbackend.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
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
}
