package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.dto.CourseDto;
import com.example.schoolmanagement.entity.Course;
import com.example.schoolmanagement.entity.Student;
import com.example.schoolmanagement.repository.CourseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

  @Autowired private CourseRepository courseRepository;

  @GetMapping
  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
    Optional<Course> course = courseRepository.findById(id);
    return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Course createCourse(@RequestBody CourseDto courseDto) {
    Course course =
        new Course(courseDto.getName(), courseDto.getDescription(), courseDto.getCredits());
    return courseRepository.save(course);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> updateCourse(
      @PathVariable Long id, @RequestBody CourseDto courseDto) {
    Optional<Course> optionalCourse = courseRepository.findById(id);
    if (optionalCourse.isPresent()) {
      Course course = optionalCourse.get();
      course.setName(courseDto.getName());
      course.setDescription(courseDto.getDescription());
      course.setCredits(courseDto.getCredits());
      return ResponseEntity.ok(courseRepository.save(course));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
    if (courseRepository.existsById(id)) {
      courseRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/{id}/students")
  public ResponseEntity<List<Student>> getCourseStudents(@PathVariable Long id) {
    List<Student> students = courseRepository.findStudentsByCourseId(id);
    return ResponseEntity.ok(students);
  }
}
