package com.example.schoolmanagement.entity;

import jakarta.persistence.*;
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
