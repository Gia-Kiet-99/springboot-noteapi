package com.example.noteapi.service;

import java.util.List;

public interface CrudService<T> {
  List<T> getAll(Integer page, Integer limit);

  T getById(long id);

  T add(T t);

  int remove(long id);
}
