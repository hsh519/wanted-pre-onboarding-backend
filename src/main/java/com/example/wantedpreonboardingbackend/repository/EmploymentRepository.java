package com.example.wantedpreonboardingbackend.repository;

import com.example.wantedpreonboardingbackend.domain.Employment;
import com.example.wantedpreonboardingbackend.dto.EmploymentReadListDto;
import com.example.wantedpreonboardingbackend.dto.EmploymentReadOneDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    @Query("select new com.example.wantedpreonboardingbackend.dto.EmploymentReadListDto(e.id, c.name, c.country, c.region, e.position, e.money, e.skill) from Employment e " +
            "left join Company c on e.company.id = c.id where " +
            "e.position like %:keyword% or " +
            "e.skill like %:keyword% or " +
            "c.name like %:keyword% or " +
            "c.country like %:keyword% " +
            "or c.region like %:keyword%")
    List<EmploymentReadListDto> findByKeyword(@Param("keyword") String keyword);

    @Query("select new com.example.wantedpreonboardingbackend.dto.EmploymentReadListDto(e.id, c.name, c.country, c.region, e.position, e.money, e.skill) from Employment e " +
            "left join Company c on e.company.id = c.id")
    List<EmploymentReadListDto> findAllEmployment();
}
