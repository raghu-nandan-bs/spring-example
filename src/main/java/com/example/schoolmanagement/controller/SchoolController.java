package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.dto.SchoolDto;
import com.example.schoolmanagement.entity.School;
import com.example.schoolmanagement.repository.SchoolRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

  @Autowired private SchoolRepository schoolRepository;

  @GetMapping
  public List<School> getAllSchools() {
    return schoolRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
    Optional<School> school = schoolRepository.findById(id);
    return school.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public School createSchool(@RequestBody SchoolDto schoolDto) {
    School school =
        new School(
            null,
            schoolDto.getName(),
            schoolDto.getAddress(),
            schoolDto.getPhoneNumber(),
            schoolDto.getEmail(),
            schoolDto.getPrincipalName());
    return schoolRepository.save(school);
  }

  @PutMapping("/{id}")
  public ResponseEntity<School> updateSchool(
      @PathVariable Long id, @RequestBody SchoolDto schoolDto) {
    Optional<School> optionalSchool = schoolRepository.findById(id);
    if (optionalSchool.isPresent()) {
      School school = optionalSchool.get();
      school.setName(schoolDto.getName());
      school.setAddress(schoolDto.getAddress());
      school.setPhoneNumber(schoolDto.getPhoneNumber());
      school.setEmail(schoolDto.getEmail());
      school.setPrincipalName(schoolDto.getPrincipalName());
      return ResponseEntity.ok(schoolRepository.save(school));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSchool(@PathVariable Long id) {
    if (schoolRepository.existsById(id)) {
      schoolRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
