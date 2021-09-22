package com.example.noteapi.dto;

import com.example.noteapi.annotation.GikiPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
  @NotBlank
  private String username;
  @GikiPassword
  private String password;
  @NotBlank
  private String fullName;
}
