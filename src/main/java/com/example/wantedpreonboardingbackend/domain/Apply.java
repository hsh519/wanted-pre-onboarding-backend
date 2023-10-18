package com.example.wantedpreonboardingbackend.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Apply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYMENT_ID")
    private Employment employment;

    public void setEmployment(Employment employment) {
        this.employment = employment;
    }
}
