package com.example.noteapi.controller;

import com.example.noteapi.dto.UserDto;
import com.example.noteapi.model.User;
import com.example.noteapi.service.UserService;
import com.example.noteapi.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;
  private final UserConverter converter;

  @Autowired
  public UserController(UserService userService, UserConverter converter) {
    this.userService = userService;
    this.converter = converter;
  }

  @GetMapping("")
  @ResponseBody
  public ResponseEntity<List<UserDto>> getAllUser(
      @RequestParam(value = "limit", required = false) Integer limit) {
    List<User> users = userService.getAll(limit);
    List<UserDto> dtos = users.stream()
        .map(converter::convertToDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{userId}")
  @ResponseBody
  public ResponseEntity<UserDto> getUserById(@PathVariable("userId") long userId) {
    User user = userService.getById(userId);
    UserDto dto = converter.convertToDto(user);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/")
  public ResponseEntity<List<UserDto>> getUsersByName(
      @RequestParam(value = "name", required = false) String name) {
    List<User> users = userService.getByName(name);
    List<UserDto> dtos = users.stream()
        .map(converter::convertToDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @PostMapping("")
  @ResponseBody
  /* Đánh dấu object với @Valid để validator tự động kiểm tra object đó có hợp lệ hay không */
  public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto) {
    User userFromDto = converter.convertToEntity(userDto);
    User newUser = userService.add(userFromDto);
    UserDto dto = converter.convertToDto(newUser);
    return ResponseEntity.ok(dto);
  }
}
