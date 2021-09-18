package com.example.noteapi.util;

public interface Converter<E, D> {
  //  D convertToDto(E entity, Class<D> dClass);
  D convertToDto(E entity);

  //  E convertToEntity(D dto, Class<E> eClass);
  E convertToEntity(D dto);
}
