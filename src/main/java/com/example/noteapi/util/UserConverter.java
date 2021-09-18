package com.example.noteapi.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Converter<E, D> {
  private final ModelMapper modelMapper;
  private final Class<E> eType = new Class<E>();
  private final Class<D> dType;

  @Autowired
  public Converter(ModelMapper modelMapper, Class<D> dType, Class<E> eType) {
    this.modelMapper = modelMapper;
    this.dType = dType;
    this.eType = eType;
  }

  public D convertToDto(E entity) {
    return modelMapper.map(entity, dType);
  }

  public E convertToEntity(D dto, Class<E> eClass) {
    return modelMapper.map(dto, eClass);
  }
}
