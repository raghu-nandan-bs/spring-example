package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.entity.Teacher;
import com.example.schoolmanagement.repository.TeacherRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

  @Autowired private TeacherRepository teacherRepository;

  @GetMapping
  public List<Teacher> getAllTeachers() {
    return teacherRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
    Optional<Teacher> teacher = teacherRepository.findById(id);
    return teacher.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Teacher createTeacher(@RequestBody Teacher teacher) {
    return teacherRepository.save(teacher);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Teacher> updateTeacher(
      @PathVariable Long id, @RequestBody Teacher teacherDetails) {
    Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
    if (optionalTeacher.isPresent()) {
      Teacher teacher = optionalTeacher.get();
      teacher.setFirstName(teacherDetails.getFirstName());
      teacher.setLastName(teacherDetails.getLastName());
      teacher.setEmail(teacherDetails.getEmail());
      teacher.setDepartment(teacherDetails.getDepartment());
      teacher.setPhoneNumber(teacherDetails.getPhoneNumber());
      return ResponseEntity.ok(teacherRepository.save(teacher));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
    if (teacherRepository.existsById(id)) {
      teacherRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
