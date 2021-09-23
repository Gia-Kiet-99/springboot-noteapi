package com.example.noteapi.controller;

import com.example.noteapi.dto.LoginRequest;
import com.example.noteapi.dto.LoginResponse;
import com.example.noteapi.dto.SignUpRequest;
import com.example.noteapi.dto.UserDto;
import com.example.noteapi.model.User;
import com.example.noteapi.security.CustomUserDetails;
import com.example.noteapi.security.JwtTokenProvider;
import com.example.noteapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;
  private final ModelMapper mapper;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AuthController(UserService userService, AuthenticationManager authenticationManager,
                        JwtTokenProvider tokenProvider, ModelMapper modelMapper,
                        PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
    this.mapper = modelMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/login")
  @ResponseBody
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
    // Xác thực từ username và password.
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()
        )
    );

    // Nếu không xảy ra exception tức là thông tin hợp lệ
    // Set thông tin authentication vào Security Context
//    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Trả về jwt cho người dùng.
    String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
    return ResponseEntity.ok(new LoginResponse(jwt));
  }

  @PostMapping("/signup")
  @ResponseBody
  /* Đánh dấu object với @Valid để validator tự động kiểm tra object đó có hợp lệ hay không */
  public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    User userToPost = getUserFromRequestAndEncodePassword(signUpRequest);
    User newUser = userService.add(userToPost);
    UserDto dto = mapper.map(newUser, UserDto.class);
    return ResponseEntity.ok(dto);
  }

  private User getUserFromRequestAndEncodePassword(SignUpRequest signUpRequest) {
    User user = mapper.map(signUpRequest, User.class);
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    return user;
  }
}
