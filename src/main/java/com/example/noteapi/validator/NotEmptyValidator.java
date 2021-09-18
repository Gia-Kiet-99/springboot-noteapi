package com.example.noteapi.validator;

import com.example.noteapi.annotation.GikiNotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidator implements ConstraintValidator<GikiNotEmpty, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return !(value == null || value.trim().isEmpty());
  }
}
