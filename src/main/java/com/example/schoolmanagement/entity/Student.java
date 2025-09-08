package com.example.schoolmanagement.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(unique = true, nullable = false)
  private String email;

  private String phoneNumber;

  @ManyToMany
  @JoinTable(
          name = "student_course_subscriptions",
          joinColumns = @JoinColumn(name = "student_id"),
          inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  @JsonManagedReference
  Set<Course> subscibedCourses;

  public Student() {}

  public Student(String firstName, String lastName, String email, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Set<Course> getSubscibedCourses() {
    return subscibedCourses;
  }

  public void setSubscibedCourses(Set<Course> subscibedCourses) {
    this.subscibedCourses = subscibedCourses;
  }
}
