package com.example.wantedpreonboardingbackend.domain;

import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.repository.EmploymentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmploymentTest {

    @Autowired
    private EmploymentRepository employmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void 채용공고_등록() {
        Company company = new Company("원티드랩", "한국", "서울");
        companyRepository.save(company);
        Employment employment = new Employment(
                company,
                "백엔드 주니어 개발자",
                1000000,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Java"
        );

        employmentRepository.save(employment);

        int size = employmentRepository.findAll().size();
        Assertions.assertThat(size).isEqualTo(1);
    }

}