package com.example.noteapi.dto;

import com.example.noteapi.annotation.GikiNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
  private Long id;
  @NotBlank
  @NotEmpty // default validation
  private String title;
  @GikiNotEmpty // custom validation
  private String content;
  @GikiNotEmpty
  private String userId;
}
