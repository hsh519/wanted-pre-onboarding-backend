package com.example.wantedpreonboardingbackend.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Company {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID")
    private Long id;

    private String name;
    private String country;
    private String region;

    public Company(String name, String country, String region) {
        this.name = name;
        this.country = country;
        this.region = region;
    }
}
