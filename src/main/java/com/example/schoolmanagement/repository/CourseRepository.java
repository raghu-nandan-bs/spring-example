package com.example.schoolmanagement.repository;

import com.example.schoolmanagement.entity.Course;
import com.example.schoolmanagement.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

  @Query("SELECT s FROM Student s JOIN s.subscribedCourses c WHERE c.id = :courseId")
  List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
}
