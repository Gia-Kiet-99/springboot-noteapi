package com.example.noteapi.repository;

import com.example.noteapi.model.Note;
import com.example.noteapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  Note findNoteById(Long id);

  // Note that we need to use the @Transactional annotation for delete methods.
  @Transactional
  int removeNoteById(Long id);

  List<Note> findNotesByUser(User user);

  @Query(value = "delete from Note n where n.id = :id")
  int deleteNoteByIdQuery(@Param("id") Long id);

}
