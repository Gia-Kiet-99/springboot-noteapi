package com.example.noteapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
  @GetMapping("")
  public ResponseEntity<String> welcome() {
    return ResponseEntity.ok("Welcome to TodoApi");
  }
}
