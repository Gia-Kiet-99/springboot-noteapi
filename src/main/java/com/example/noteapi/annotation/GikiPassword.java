package com.example.noteapi.annotation;

import com.example.noteapi.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GikiPassword {
  // trường message là bắt buộc, khai báo nội dung sẽ trả về khi field k hợp lệ
  String message() default "must contain the following: a lowercase letter, a capital letter, a number and minimum 8 characters";

  // Cái này là bắt buộc phải có để Hibernate Validator có thể hoạt động
  Class<?>[] groups() default {};

  // Cái này là bắt buộc phải có để Hibernate Validator có thể hoạt động
  Class<? extends Payload>[] payload() default {};
}
