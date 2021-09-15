package com.example.noteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NoteApiApplication {

  public static void main(String[] args) {
    // ApplicationContext chứa toàn bộ dependency trong project.
    ApplicationContext context = SpringApplication.run(NoteApiApplication.class, args);
  }

}
