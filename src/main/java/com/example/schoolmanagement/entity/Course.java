package com.example.schoolmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToMany(mappedBy = "subscibedCourses")
  @JsonBackReference
  Set<Student> subscriptions;

  private String description;

  private int credits;

  @ManyToOne
  @JoinColumn(name = "school_id")
  private School school;

  public Course(String name, String description, int credits) {
    this.name = name;
    this.description = description;
    this.credits = credits;
  }
}
