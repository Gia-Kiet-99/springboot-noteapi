package com.example.noteapi.service;

import com.example.noteapi.dto.UserDto;
import com.example.noteapi.model.User;

import java.util.List;

public interface UserService {
  List<User> getAll(Integer limit);

  User add(User user);

  List<User> getByName(String name);

  UserDto getById(Long id);
}
