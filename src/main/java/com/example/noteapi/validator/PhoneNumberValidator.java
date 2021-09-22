package com.example.noteapi.validator;

import com.example.noteapi.annotation.GikiPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<GikiPhoneNumber, String> {
  private final static Pattern pattern = Pattern.compile("\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || pattern.matcher(value).matches();
  }
}
