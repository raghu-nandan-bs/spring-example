package com.example.schoolmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDto {
  private String name;
  private String address;
  private String phoneNumber;
  private String email;
  private String principalName;
}
