package com.example.noteapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue
  @Column(columnDefinition = "BINARY(16)", updatable = false)
  private UUID id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "phone")
  private String phone;

  @Column(name = "username", nullable = false, unique = true)
  private String username;
  @Column(name = "password", nullable = false)
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private List<Note> notes;
}
