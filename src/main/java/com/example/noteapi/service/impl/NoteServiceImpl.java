package com.example.noteapi.service.impl;

import com.example.noteapi.model.Note;
import com.example.noteapi.model.User;
import com.example.noteapi.repository.NoteRepository;
import com.example.noteapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
  private final NoteRepository noteRepository;

  @Autowired
  public NoteServiceImpl(NoteRepository repository) {
    this.noteRepository = repository;
  }

  @Override
  public List<Note> getAll() {
    return noteRepository.findAll();
  }

  @Override
  public List<Note> getByUserId(UUID userId) {
    User user = new User();
    user.setId(userId);
    return noteRepository.findNotesByUser(user);
  }

  @Override
  public Note getById(UUID id) {
    return noteRepository.findNoteById(id);
  }

  @Override
  public Note add(Note note) {
    return noteRepository.save(note);
  }

  @Override
  public List<Note> delete(UUID id) {
    return noteRepository.removeById(id);
  }
}
