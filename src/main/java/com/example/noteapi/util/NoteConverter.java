package com.example.noteapi.util;

import com.example.noteapi.dto.NoteDto;
import com.example.noteapi.model.Note;
import org.modelmapper.ModelMapper;

public class NoteConverter implements Converter<Note, NoteDto> {
  private final ModelMapper modelMapper;
  private final Class<Note> noteClass;
  private final Class<NoteDto> noteDtoClass;

  public NoteConverter(ModelMapper modelMapper, Class<Note> noteClass, Class<NoteDto> noteDtoClass) {
    this.modelMapper = modelMapper;
    this.noteClass = noteClass;
    this.noteDtoClass = noteDtoClass;
  }

  @Override
  public NoteDto convertToDto(Note entity) {
    return modelMapper.map(entity, noteDtoClass);
  }

  @Override
  public Note convertToEntity(NoteDto dto) {
    return modelMapper.map(dto, noteClass);
  }
}
