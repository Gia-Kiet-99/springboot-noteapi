package com.example.noteapi.repository;

import com.example.noteapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  User findUserById(UUID id);

  List<User> findUsersByFullName(String name);

  @Query(value = "select u from User u where u.fullName = :name")
  List<User> findUserByNameQuery(@Param("name") String name);

  @Transactional
  int removeById(UUID id);
}
