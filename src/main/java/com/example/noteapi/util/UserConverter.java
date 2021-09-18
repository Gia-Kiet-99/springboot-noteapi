package com.example.noteapi.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter<E, D> implements Converter<E, D> {
  private final ModelMapper modelMapper;

  @Autowired
  public UserConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public D convertToDto(E entity, Class<D> dClass) {
    return modelMapper.map(entity, dClass);
  }

  @Override
  public E convertToEntity(D dto, Class<E> eClass) {
    return modelMapper.map(dto, eClass);
  }
}
