package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.entity.Student;
import com.example.schoolmanagement.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  @Autowired private StudentRepository studentRepository;

  @GetMapping
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
    Optional<Student> student = studentRepository.findById(id);
    return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Student createStudent(@RequestBody Student student) {
    return studentRepository.save(student);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Student> updateStudent(
      @PathVariable Long id, @RequestBody Student studentDetails) {
    Optional<Student> optionalStudent = studentRepository.findById(id);
    if (optionalStudent.isPresent()) {
      Student student = optionalStudent.get();
      student.setFirstName(studentDetails.getFirstName());
      student.setLastName(studentDetails.getLastName());
      student.setEmail(studentDetails.getEmail());
      student.setPhoneNumber(studentDetails.getPhoneNumber());
      return ResponseEntity.ok(studentRepository.save(student));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
    if (studentRepository.existsById(id)) {
      studentRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
