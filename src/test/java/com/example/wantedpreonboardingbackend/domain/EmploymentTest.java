package com.example.wantedpreonboardingbackend.domain;

import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.repository.EmploymentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmploymentTest {

    @Autowired
    private EmploymentRepository employmentRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @BeforeEach
    void 테스트_시작하기_전_데이터_추가() {
        Company company = new Company("원티드랩", "한국", "서울");
        companyRepository.save(company);

        Employment employment1 = new Employment(
                company,
                "백엔드 주니어 개발자",
                1000000,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "Java"
        );

        Employment employment2 = new Employment(
                company,
                "프론트엔드 주니어 개발자",
                1000000,
                "원티드랩에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은..",
                "JavaScript"
        );

        employmentRepository.save(employment1);
        employmentRepository.save(employment2);
    }

    @Test
    void 채용공고_등록() {
        int size = employmentRepository.findAll().size();

        Assertions.assertThat(size).isEqualTo(2);
    }


    @Test
    void 채용공고_수정() {
        // 채용공고를 가져와 수정했을 때
        Employment findEmployment = employmentRepository.findById(1L).get();
        findEmployment.setMoney(150000);
        findEmployment.setContent("원티드랩에서 백엔드 주니어 개발자를 \'적극\' 채용합니다. 자격요건은..");
        findEmployment.setSkill("Spring");
        employmentRepository.save(findEmployment);

        // 채용공고 수정이 성공했는지
        Employment result = employmentRepository.findById(1L).get();
        Assertions.assertThat(result.getMoney()).isEqualTo(150000);
        Assertions.assertThat(result.getContent()).isEqualTo("원티드랩에서 백엔드 주니어 개발자를 \'적극\' 채용합니다. 자격요건은..");
        Assertions.assertThat(result.getSkill()).isEqualTo("Spring");
    }

    @Test
    void 채용공고_삭제() {
        employmentRepository.deleteById(1L);

        Assertions.assertThat(employmentRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    void 채용공고_조회() {
        int size = employmentRepository.findAllEmployment().size();

        Assertions.assertThat(size).isEqualTo(2);
    }

    @Test
    void 채용공고_검색() {
        List<Employment> list1 = employmentRepository.findByKeyword("원티드");
        List<Employment> list2 = employmentRepository.findByKeyword("Java");

        Assertions.assertThat(list1.size()).isEqualTo(2);
        Assertions.assertThat(list2.size()).isEqualTo(2);
    }
}