package com.example.noteapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "note")
@Data
@NoArgsConstructor
public class Note {
  @Id
  @GeneratedValue
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "content", nullable = false)
  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_note_user_userId"))
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private User user;
}
