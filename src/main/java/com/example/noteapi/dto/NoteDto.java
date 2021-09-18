package com.example.noteapi.dto;

import com.example.noteapi.annotation.GikiNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
  private Long id;
  @NotEmpty // default validation
  private String title;
  @GikiNotEmpty // custom validation
  private String content;
//  private User user;
}
