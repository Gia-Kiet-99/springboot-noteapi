package com.example.noteapi.dto;

import com.example.noteapi.annotation.GikiNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
  private UUID id;
  @NotBlank // default validation
  private String title;
  @GikiNotEmpty // custom validation (similar @NotBlank)
  private String content;
  @NotNull
  private UUID userId;
}
