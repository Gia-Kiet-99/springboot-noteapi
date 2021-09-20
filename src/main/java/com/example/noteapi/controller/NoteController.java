package com.example.noteapi.controller;

import com.example.noteapi.dto.NoteDto;
import com.example.noteapi.model.Note;
import com.example.noteapi.service.NoteService;
import com.example.noteapi.util.NoteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
  @ResponseBody
  public ResponseEntity<List<NoteDto>> getAllNotes() {
    List<Note> notes = noteService.getAll();
    List<NoteDto> noteDtos = notes.stream()
        .map(converter::convertToDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(noteDtos);
  }

  @PostMapping("")
  @ResponseBody
  public ResponseEntity<NoteDto> createNewNote(@Valid @RequestBody NoteDto noteDto) {
    Note noteToPost = converter.convertToEntity(noteDto);
    Note newNote = noteService.add(noteToPost);
    NoteDto resDto = converter.convertToDto(newNote);
    return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
  }

  @GetMapping("/")
  @ResponseBody
  public ResponseEntity<List<NoteDto>> getNotesByUserId(
      @RequestParam(value = "userId", required = false) Long userId) {
    List<Note> notes = noteService.getByUserId(userId);
    List<NoteDto> dtos = notes.stream()
        .map(converter::convertToDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{noteId}")
  @ResponseBody
  public ResponseEntity<NoteDto> getNoteById(@PathVariable(value = "noteId") Long noteId) {
    Note note = noteService.getById(noteId);
    NoteDto noteDto = converter.convertToDto(note);
    return ResponseEntity.ok(noteDto);
  }

  @DeleteMapping("/{noteId}")
  @ResponseBody
  public ResponseEntity<Integer> deleteNote(@PathVariable(value = "noteId") Long noteId) {
    return ResponseEntity.ok(noteService.delete(noteId));
  }

}
