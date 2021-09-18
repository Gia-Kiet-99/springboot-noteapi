package com.example.noteapi.service.impl;

import com.example.noteapi.model.User;
import com.example.noteapi.repository.UserRepository;
import com.example.noteapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getAll(Integer limit) {
    return Optional.ofNullable(limit)
        .map(value -> userRepository.findAll(PageRequest.of(0, value)).getContent())
        .orElseGet(userRepository::findAll);
  }

  @Override
  public User add(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> getByName(String name) {
    return Optional.ofNullable(name)
        .map(userRepository::findUsersByName)
        .orElse(new ArrayList<>());
  }

  @Override
  public User getById(Long id) {
    return userRepository.findUserById(id);
  }

}
