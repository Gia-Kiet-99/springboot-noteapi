package com.example.noteapi.service;

import com.example.noteapi.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends CrudService<User>, UserDetailsService {
  List<User> getByName(String name);

  UserDetails loadUserById(UUID userId);
}
