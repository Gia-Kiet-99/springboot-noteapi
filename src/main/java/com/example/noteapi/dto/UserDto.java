package com.example.noteapi.dto;

import com.example.noteapi.annotation.GikiNotEmpty;
import com.example.noteapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Long id;
  /*
  # Validate Annotation
  ## Use default annotation (defined in dependency: hibernate-validator)
    @NotEmpty: the property is not null or empty; can be applied to String, Collection, Map or Array values.
    @NotBlank: can be applied only to text values and validates that the property is not null or whitespace.
  ## Or create custom validate (GikiNotEmpty)
  */
  @GikiNotEmpty
  private String name;

  public static User convertToUser(UserDto dto) {
    User user = new User();
    user.setName(dto.getName());
    return user;
  }
}
