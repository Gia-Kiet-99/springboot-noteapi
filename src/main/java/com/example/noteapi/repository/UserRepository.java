package com.example.noteapi.repository;

import com.example.noteapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByName(String name);

  @Query(value = "select u from User u where u.name = :name")
  User findUserByNameQuery(@Param("name") String name);
}
