package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.domain.Company;
import com.example.wantedpreonboardingbackend.domain.Employment;
import com.example.wantedpreonboardingbackend.dto.EmploymentCreateDto;
import com.example.wantedpreonboardingbackend.dto.EmploymentReadListDto;
import com.example.wantedpreonboardingbackend.dto.EmploymentReadOneDto;
import com.example.wantedpreonboardingbackend.dto.EmploymentUpdateDto;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.repository.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmploymentService {

    private final EmploymentRepository employmentRepository;
    private final CompanyRepository companyRepository;

    @Transactional
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

    @Transactional
    public void deleteEmployment(Long employmentId) {
        employmentRepository.deleteById(employmentId);
    }

    public List<EmploymentReadListDto> readEmploymentList(String searchKeyword) {
        if (searchKeyword.isEmpty()) {
            return employmentRepository.findAllEmployment();
        }
        return employmentRepository.findByKeyword(searchKeyword);
    }

    public EmploymentReadOneDto readEmploymentOne(Long employmentId) {
        // 회사가 올린 다른 채용공고를 제외한 나머지 데이터를 dto로 생성해 가져온다
        EmploymentReadOneDto employment = employmentRepository.findOne(employmentId);

        // 해당 채용공고의 회사 id를 찾고
        Long companyId = employmentRepository.findCompanyIdByEmploymentId(employmentId);

        // 찾은 회사 id와 해당 채용공고 id로 회사가 올린 다른 채용공고 id를 리스트로 가져온다
        List<Long> otherEmploymentId = employmentRepository.findOtherEmploymentId(companyId, employmentId);

        // dto에 리스트를 저장하고 반환한다
        employment.addOtherEmploymentList(otherEmploymentId);
        return employment;
    }
}
