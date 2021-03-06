package com.example.noteapi.annotation;

import com.example.noteapi.validator.NotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * The annotated element must not be null and must contain at least one non-whitespace character.
 */
@Documented
@Constraint(validatedBy = NotEmptyValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GikiNotEmpty {
  // trường message là bắt buộc, khai báo nội dung sẽ trả về khi field k hợp lệ
  String message() default "must not be empty or null";

  // Cái này là bắt buộc phải có để Hibernate Validator có thể hoạt động
  Class<?>[] groups() default {};

  // Cái này là bắt buộc phải có để Hibernate Validator có thể hoạt động
  Class<? extends Payload>[] payload() default {};
}
