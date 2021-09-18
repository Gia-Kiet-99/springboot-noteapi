package com.example.noteapi.config;

import com.example.noteapi.dto.NoteDto;
import com.example.noteapi.dto.UserDto;
import com.example.noteapi.model.Note;
import com.example.noteapi.model.User;
import com.example.noteapi.util.NoteConverter;
import com.example.noteapi.util.UserConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

  @Bean
  public UserConverter userConverter() {
    ModelMapper modelMapper = new ModelMapper();
    return new UserConverter(modelMapper, User.class, UserDto.class);
  }

  @Bean
  public NoteConverter noteConverter() {
    ModelMapper modelMapper = new ModelMapper();
    return new NoteConverter(modelMapper, Note.class, NoteDto.class);
  }

}
