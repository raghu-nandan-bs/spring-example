package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.entity.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  @Query(
      "SELECT DISTINCT s FROM Student s "
          + "LEFT JOIN FETCH s.school "
          + "LEFT JOIN FETCH s.subscribedCourses c "
          + "LEFT JOIN FETCH c.school "
          + "WHERE s.id = :id")
  Optional<Student> findByIdWithCoursesAndSchool(@Param("id") Long id);
}
