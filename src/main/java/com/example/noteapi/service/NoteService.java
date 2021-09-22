package com.example.noteapi.service;

import com.example.noteapi.model.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {
  List<Note> getAll();

  List<Note> getByUserId(UUID userId);

  Note getById(UUID id);

  Note add(Note note);

  List<Note> delete(UUID id);
}
