package com.example.wantedpreonboardingbackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLY_ID")
    private Apply apply;

    private String name;
    private Integer age;
    private String gender;

    public void setApply(Apply apply) {
        this.apply = apply;
    }
}
