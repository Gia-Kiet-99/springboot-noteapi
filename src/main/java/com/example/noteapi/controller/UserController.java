package com.example.noteapi.controller;

import com.example.noteapi.model.User;
import com.example.noteapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("")
  @ResponseBody
  public ResponseEntity<List<User>> getAllUser() {
    List<User> users = userService.getAll();
    return ResponseEntity.ok(users);
  }

  @PostMapping("")
  @ResponseBody
  public ResponseEntity<User> createNewUser(@RequestBody User user) {
    User newUser = userService.add(user);
    return ResponseEntity.ok(newUser);
  }
}
