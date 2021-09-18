package com.example.noteapi.repository;

import com.example.noteapi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  Note findNoteById(Long id);

  Note deleteNoteById(Long id);
}
