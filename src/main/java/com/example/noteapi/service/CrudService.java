package com.example.noteapi.service;

import java.util.List;
import java.util.UUID;

public interface CrudService<T> {
  List<T> getAll(Integer page, Integer limit);

  T getById(UUID id);

  T add(T t);

  int remove(UUID id);
}
