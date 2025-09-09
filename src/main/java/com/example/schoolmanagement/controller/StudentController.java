package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.dto.StudentDto;
import com.example.schoolmanagement.entity.Course;
import com.example.schoolmanagement.entity.Student;
import com.example.schoolmanagement.repository.CourseRepository;
import com.example.schoolmanagement.repository.StudentRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  @Autowired private StudentRepository studentRepository;
  @Autowired private CourseRepository courseRepository;

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
  public Student createStudent(@RequestBody StudentDto studentDto) {
    Student student =
        new Student(
            studentDto.getFirstName(),
            studentDto.getLastName(),
            studentDto.getEmail(),
            studentDto.getPhoneNumber());
    return studentRepository.save(student);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Student> updateStudent(
      @PathVariable Long id, @RequestBody StudentDto studentDto) {
    Optional<Student> optionalStudent = studentRepository.findById(id);
    if (optionalStudent.isPresent()) {
      Student student = optionalStudent.get();
      student.setFirstName(studentDto.getFirstName());
      student.setLastName(studentDto.getLastName());
      student.setEmail(studentDto.getEmail());
      student.setPhoneNumber(studentDto.getPhoneNumber());
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

  @GetMapping("/{id}/courses")
  public ResponseEntity<Set<Course>> getStudentCourses(@PathVariable Long id) {
    Optional<Student> student = studentRepository.findById(id);
    if (student.isPresent()) {
      return ResponseEntity.ok(student.get().getSubscribedCourses());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/{studentId}/courses/{courseId}")
  public ResponseEntity<Student> addCourseToStudent(
      @PathVariable Long studentId, @PathVariable Long courseId) {
    Optional<Student> studentOpt = studentRepository.findById(studentId);
    Optional<Course> courseOpt = courseRepository.findById(courseId);

    if (studentOpt.isPresent() && courseOpt.isPresent()) {
      Student student = studentOpt.get();
      Course course = courseOpt.get();

      if (student.getSubscribedCourses() == null) {
        student.setSubscribedCourses(new HashSet<>());
      }

      student.getSubscribedCourses().add(course);
      return ResponseEntity.ok(studentRepository.save(student));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{studentId}/courses/{courseId}")
  public ResponseEntity<Student> removeCourseFromStudent(
      @PathVariable Long studentId, @PathVariable Long courseId) {
    Optional<Student> studentOpt = studentRepository.findById(studentId);
    Optional<Course> courseOpt = courseRepository.findById(courseId);

    if (studentOpt.isPresent() && courseOpt.isPresent()) {
      Student student = studentOpt.get();
      Course course = courseOpt.get();

      if (student.getSubscribedCourses() != null) {
        student.getSubscribedCourses().remove(course);
        return ResponseEntity.ok(studentRepository.save(student));
      }
    }
    return ResponseEntity.notFound().build();
  }
}
