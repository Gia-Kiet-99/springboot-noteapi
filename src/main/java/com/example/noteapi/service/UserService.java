package com.example.noteapi.service;

import com.example.noteapi.model.User;

import java.util.List;

public interface UserService extends CrudService<User> {
  List<User> getByName(String name);
}
