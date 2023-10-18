package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.*;
import com.example.wantedpreonboardingbackend.service.EmploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmploymentController {

    private final EmploymentService employmentService;

    @PostMapping("/employment/create")
    public String create(@RequestBody EmploymentCreateDto employmentCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "채용공고 등록 실패";
        }

        employmentService.postEmployment(employmentCreateDto);
        return "채용공고 등록 성공";
    }

    @PostMapping("/employment/update/{employmentId}")
    public String update(@PathVariable Long employmentId,
                         @RequestBody EmploymentUpdateDto employmentUpdateDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "채용공고 수정 실패";
        }

        employmentService.updateEmployment(employmentId, employmentUpdateDto);
        return "채용공고 수정 성공";
    }

    @DeleteMapping("/employment/delete/{employmentId}")
    public String delete(@PathVariable Long employmentId) {
        employmentService.deleteEmployment(employmentId);
        return "삭제 완료";
    }

    @GetMapping("/employment/list")
    public List<EmploymentReadListDto> getList(@RequestParam(name = "search", defaultValue = "") String searchKeyword) {
        return employmentService.readEmploymentList(searchKeyword);
    }

    @GetMapping("/employment/{employmentId}")
    public EmploymentReadOneDto getOne(@PathVariable Long employmentId) {
        return employmentService.readEmploymentOne(employmentId);
    }

    @PostMapping("/employment/apply")
    public String apply(@RequestBody ApplyDto applyDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "채용공고 id 혹은 사용자 id가 누락되었습니다.";
        }
        return employmentService.applyEmployment(applyDto);
    }
}
