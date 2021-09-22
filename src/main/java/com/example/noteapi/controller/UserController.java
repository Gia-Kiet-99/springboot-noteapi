package com.example.noteapi.controller;

import com.example.noteapi.dto.SignUpRequest;
import com.example.noteapi.dto.UserDto;
import com.example.noteapi.model.User;
import com.example.noteapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;
  private final ModelMapper mapper;

  @Autowired
  public UserController(UserService userService, ModelMapper mapper) {
    this.userService = userService;
    this.mapper = mapper;
  }

  @Operation(
      description = "Xem danh sách User",
      responses = {
          @ApiResponse(
              content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))),
              responseCode = "200"
          )
      })
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Thành công"),
          @ApiResponse(responseCode = "401", description = "Chưa xác thực"),
          @ApiResponse(responseCode = "403", description = "Truy cập bị cấm"),
          @ApiResponse(responseCode = "404", description = "Không tìm thấy")
      }
  )
  @GetMapping("")
  @ResponseBody
  public ResponseEntity<List<UserDto>> getAllUser(
      @RequestParam(value = "limit", required = false) Integer limit) {
    List<User> users = userService.getAll(0, limit);
    List<UserDto> dtos = users.stream()
        .map(user -> mapper.map(user, UserDto.class))
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{userId}")
  @ResponseBody
  public ResponseEntity<UserDto> getUserById(@PathVariable("userId") UUID userId) {
    User user = userService.getById(userId);
    UserDto dto = mapper.map(user, UserDto.class);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/")
  public ResponseEntity<List<UserDto>> getUsersByName(
      @RequestParam(value = "fullName", required = false) String name) {
    List<User> users = userService.getByName(name);
    List<UserDto> dtos = users.stream()
        .map(user -> mapper.map(user, UserDto.class))
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @PostMapping("")
  @ResponseBody
  /* Đánh dấu object với @Valid để validator tự động kiểm tra object đó có hợp lệ hay không */
  public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    User userFromDto = mapper.map(signUpRequest, User.class);

    User newUser = userService.add(userFromDto);
    UserDto dto = mapper.map(newUser, UserDto.class);
    return ResponseEntity.ok(dto);
  }
}
