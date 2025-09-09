package com.example.schoolmanagement.dto;

public class CourseDto {
  private String name;
  private String description;
  private int credits;

  public CourseDto() {}

  public CourseDto(String name, String description, int credits) {
    this.name = name;
    this.description = description;
    this.credits = credits;
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
}
