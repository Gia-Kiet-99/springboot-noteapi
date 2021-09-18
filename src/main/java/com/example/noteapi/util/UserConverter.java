package com.example.noteapi.util;

import com.example.noteapi.dto.UserDto;
import com.example.noteapi.model.User;
import org.modelmapper.ModelMapper;

public class UserConverter implements Converter<User, UserDto> {
  private final ModelMapper modelMapper;
  private final Class<User> userClass;
  private final Class<UserDto> userDtoClass;

  public UserConverter(ModelMapper modelMapper, Class<User> userClass, Class<UserDto> userDtoClass) {
    this.modelMapper = modelMapper;
    this.userClass = userClass;
    this.userDtoClass = userDtoClass;
  }

  @Override
  public UserDto convertToDto(User entity) {
    return modelMapper.map(entity, userDtoClass);
  }

  @Override
  public User convertToEntity(UserDto dto) {
    return modelMapper.map(dto, userClass);
  }
}
