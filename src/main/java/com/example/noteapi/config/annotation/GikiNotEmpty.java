package com.example.noteapi.config.annotation;

import com.example.noteapi.validator.NotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Khai báo một custom annotation
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
