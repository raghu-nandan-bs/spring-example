package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.entity.Course;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  
  @EntityGraph(attributePaths = {"subscriptions", "school"})
  @Override
  Optional<Course> findById(Long id);
}
