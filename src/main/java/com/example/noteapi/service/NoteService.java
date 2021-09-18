package com.example.noteapi.service;

import com.example.noteapi.model.Note;

import java.util.List;

public interface NoteService {
  List<Note> getAll();

  Note getById(Long id);

  Note add(Note note);

  Note delete(Long id);
}
