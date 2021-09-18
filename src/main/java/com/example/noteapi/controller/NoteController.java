package com.example.noteapi.controller;

import com.example.noteapi.dto.NoteDto;
import com.example.noteapi.model.Note;
import com.example.noteapi.service.NoteService;
import com.example.noteapi.util.NoteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notes")
public class NoteController {
  private final NoteService noteService;
  private final NoteConverter converter;

  @Autowired
  public NoteController(NoteService service, NoteConverter converter) {
    this.noteService = service;
    this.converter = converter;
  }

  @GetMapping("")
  public ResponseEntity<List<NoteDto>> getAllNotes() {
    List<Note> notes = noteService.getAll();
    List<NoteDto> noteDtos = notes.stream()
        .map(converter::convertToDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(noteDtos);
  }

}
