package com.example.noteapi.service.impl;

import com.example.noteapi.model.User;
import com.example.noteapi.repository.UserRepository;
import com.example.noteapi.security.CustomUserDetails;
import com.example.noteapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getAll(Integer page, Integer limit) {
    return Optional.ofNullable(limit)
        .map(value -> userRepository.findAll(PageRequest.of(0, value)).getContent())
        .orElseGet(userRepository::findAll);
  }

  @Override
  public User getById(UUID id) {
    return userRepository.findUserById(id);
  }

  @Override
  public User add(User user) {
    return userRepository.saveAndFlush(user);
  }

  @Override
  public int remove(UUID id) {
    return userRepository.removeById(id);
  }

  @Override
  public List<User> getByName(String name) {
    return Optional.ofNullable(name)
        .map(userRepository::findUsersByFullName)
        .orElse(new ArrayList<>());
  }

  @Override
  public UserDetails loadUserById(UUID userId) {
    User user = userRepository.findById(userId).orElseThrow(
        () -> new UsernameNotFoundException("User not found with id : " + userId)
    );
    return new CustomUserDetails(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new CustomUserDetails(user);
  }

}
