package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.domain.Company;
import com.example.wantedpreonboardingbackend.domain.Employment;
import com.example.wantedpreonboardingbackend.dto.EmploymentCreateDto;
import com.example.wantedpreonboardingbackend.dto.EmploymentUpdateDto;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.repository.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmploymentService {

    private final EmploymentRepository employmentRepository;
    private final CompanyRepository companyRepository;

    public void postEmployment(EmploymentCreateDto employmentCreateDto) {
        Optional<Company> companyOptional = companyRepository.findById(employmentCreateDto.getCompanyId());

        // 존재하는 회사라면 채용공고 등록
        if (companyOptional.isPresent()) {
            Employment employment = new Employment();
            employment.mapCreateDtoToEntity(employmentCreateDto, companyOptional.get());

            employmentRepository.save(employment);
        }
    }

    @Transactional
    public void updateEmployment(Long employmentId, EmploymentUpdateDto employmentUpdateDto) {
        Optional<Employment> employmentOptional = employmentRepository.findById(employmentId);

        if (employmentOptional.isPresent()) {
            Employment employment = employmentOptional.get();
            employment.mapUpdateDtoToEntity(employmentUpdateDto);
        }
    }
}
