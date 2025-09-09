package com.example.schoolmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Set;

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

  public Course() {}

  public Course(String name, String description, int credits) {
    this.name = name;
    this.description = description;
    this.credits = credits;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getCredits() {
    return credits;
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }

  public Set<Student> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(Set<Student> subscriptions) {
    this.subscriptions = subscriptions;
  }
}
