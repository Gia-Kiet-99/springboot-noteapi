package com.example.noteapi.service.impl;

import com.example.noteapi.model.User;
import com.example.noteapi.repository.UserRepository;
import com.example.noteapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public User add(User user) {
    return userRepository.save(user);
  }

}
