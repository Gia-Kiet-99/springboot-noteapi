package com.example.noteapi.controller;

import com.example.noteapi.dto.UserDto;
import com.example.noteapi.model.User;
import com.example.noteapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
  public ResponseEntity<List<User>> getAllUser(
      @RequestParam(value = "limit", required = false) Integer limit) {
    List<User> users = userService.getAll(limit);
    return ResponseEntity.ok(users);
  }

  @GetMapping("/")
  public ResponseEntity<List<User>> getUsersByName(
      @RequestParam(value = "name", required = false) String name) {
    List<User> users = userService.getByName(name);
    return ResponseEntity.ok(users);
  }

  @PostMapping("")
  @ResponseBody
  /* Đánh dấu object với @Valid để validator tự động kiểm tra object đó có hợp lệ hay không */
  public ResponseEntity<User> createNewUser(@Valid @RequestBody UserDto userDto) {
    User userFromDto = UserDto.convertToUser(userDto);
    User newUser = userService.add(userFromDto);
    return ResponseEntity.ok(newUser);
  }
}
