package com.example.noteapi.dto;

import com.example.noteapi.config.annotation.GikiNotEmpty;
import com.example.noteapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  @GikiNotEmpty
  private String name;

  public static User convertToUser(UserDto dto) {
    User user = new User();
    user.setName(dto.getName());
    return user;
  }
}
