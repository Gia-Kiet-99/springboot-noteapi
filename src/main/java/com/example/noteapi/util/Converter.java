package com.example.noteapi.util;

public interface Converter<E, D> {
  D convertToDto(E entity, Class<D> dClass);

  E convertToEntity(D dto, Class<E> eClass);
}
