package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.domain.Company;
import com.example.wantedpreonboardingbackend.domain.Employment;
import com.example.wantedpreonboardingbackend.dto.EmploymentDto;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.repository.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmploymentService {

    private final EmploymentRepository employmentRepository;
    private final CompanyRepository companyRepository;

    public void postEmployment(EmploymentDto employmentDto) {
        Optional<Company> companyOptional = companyRepository.findById(employmentDto.getCompanyId());

        // 존재하는 회사라면 채용공고 등록
        if (companyOptional.isPresent()) {
            Employment employment = new Employment();
            employment.mapDtoToEntity(employmentDto, companyOptional.get());

            employmentRepository.save(employment);
        }
    }
}
