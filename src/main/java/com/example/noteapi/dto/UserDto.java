package com.example.noteapi.dto;

import com.example.noteapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserDto {
  private Long id;
  private String name;

  public static User convertToUser(UserDto dto) {
    return new User(dto.getId(), dto.getName());
  }
}
