package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.EmploymentDto;
import com.example.wantedpreonboardingbackend.service.EmploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class EmploymentController {

    private final EmploymentService employmentService;

    @PostMapping("/employment/create")
    public String create(@RequestBody EmploymentDto employmentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "채용공고 등록 실패";
        }

        employmentService.postEmployment(employmentDto);
        return "채용공고 등록 성공";
    }
}
