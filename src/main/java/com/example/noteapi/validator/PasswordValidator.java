package com.example.noteapi.validator;

import com.example.noteapi.annotation.GikiPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<GikiPassword, String> {
  private final static Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return pattern.matcher(value).matches();
  }
}
