package com.example.noteapi.service;

import com.example.noteapi.model.User;

import java.util.List;

public interface UserService {
  List<User> getAll();
  User add(User user);
}
