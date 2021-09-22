package com.example.noteapi.controller;

import com.example.noteapi.dto.NoteDto;
import com.example.noteapi.model.Note;
import com.example.noteapi.service.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notes")
public class NoteController {
  private final NoteService noteService;
  private final ModelMapper mapper;

  @Autowired
  public NoteController(NoteService service, ModelMapper mapper) {
    this.noteService = service;
    this.mapper = mapper;
  }

  @GetMapping("")
  @ResponseBody
  public ResponseEntity<List<NoteDto>> getAllNotes() {
    List<Note> notes = noteService.getAll();
    List<NoteDto> noteDtos = notes.stream()
        .map(note -> mapper.map(note, NoteDto.class))
        .collect(Collectors.toList());
    return ResponseEntity.ok(noteDtos);
  }

  @PostMapping("")
  @ResponseBody
  public ResponseEntity<NoteDto> createNewNote(@Valid @RequestBody NoteDto noteDto) {
    Note noteToPost = mapper.map(noteDto, Note.class);
    Note newNote = noteService.add(noteToPost);
    NoteDto resDto = mapper.map(newNote, NoteDto.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
  }

  @GetMapping("/")
  @ResponseBody
  public ResponseEntity<List<NoteDto>> getNotesByUserId(
      @RequestParam(value = "userId", required = false) UUID userId) {
    List<Note> notes = noteService.getByUserId(userId);
    List<NoteDto> dtos = notes.stream()
        .map(note -> mapper.map(note, NoteDto.class))
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{noteId}")
  @ResponseBody
  public ResponseEntity<NoteDto> getNoteById(@PathVariable(value = "noteId") UUID noteId) {
    Note note = noteService.getById(noteId);
    NoteDto noteDto = mapper.map(note, NoteDto.class);
    return ResponseEntity.ok(noteDto);
  }

  @DeleteMapping("/{noteId}")
  @ResponseBody
  public ResponseEntity<List<NoteDto>> deleteNote(@PathVariable(value = "noteId") UUID noteId) {
    List<Note> notes = noteService.delete(noteId);
    List<NoteDto> dtos = notes.stream()
        .map(note -> mapper.map(note, NoteDto.class))
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

}
