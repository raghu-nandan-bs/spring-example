package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.entity.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  
  @EntityGraph(attributePaths = {"subscribedCourses", "school"})
  Optional<Student> findById(Long id);
}
