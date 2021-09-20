package com.example.noteapi.service;

import com.example.noteapi.model.Note;

import java.util.List;

public interface NoteService {
  List<Note> getAll();

  List<Note> getByUserId(Long userId);

  Note getById(Long id);

  Note add(Note note);

  List<Note> delete(Long id);
}
