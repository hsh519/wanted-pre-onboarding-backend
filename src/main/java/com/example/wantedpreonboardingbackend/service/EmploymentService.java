package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.domain.Apply;
import com.example.wantedpreonboardingbackend.domain.Company;
import com.example.wantedpreonboardingbackend.domain.Employment;
import com.example.wantedpreonboardingbackend.domain.Member;
import com.example.wantedpreonboardingbackend.dto.*;
import com.example.wantedpreonboardingbackend.repository.ApplyRepository;
import com.example.wantedpreonboardingbackend.repository.CompanyRepository;
import com.example.wantedpreonboardingbackend.repository.EmploymentRepository;
import com.example.wantedpreonboardingbackend.repository.MemberRepository;
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
    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;

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

    @Transactional
    public String applyEmployment(ApplyDto applyDto) {
        Apply apply = new Apply();
        // 채용공고 id로 채용공고를, 사용자 id로 사용자를 찾습니다.
        Optional<Employment> employmentOptional = employmentRepository.findById(applyDto.getEmploymentId());
        Optional<Member> memberOptional = memberRepository.findById(applyDto.getMemberId());

        // 사용자가 존재하는지 확인
        if (memberOptional.isEmpty()) {
            return "없는 사용자 id 입니다.";
        }

        // 처음 지원하는 사용자인지 확인
        Member member = memberOptional.get();
        if(member.getApply() != null) {
            return "이미 1회 지원한 사용자입니다.";
        }

        // 처음 지원하고 채용공고도 존재한다면
        if (employmentOptional.isPresent()) {

            // 지원내역 저장
            Employment employment = employmentOptional.get();
            apply.setEmployment(employment);
            applyRepository.save(apply);

            // 사용자가 지원한 회사도 수정
            member.setApply(apply);
            return "지원 완료";
        }
        return "없는 채용공고 id 입니다.";
    }
}
