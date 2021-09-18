package com.example.noteapi.exceptionHandler;

import com.example.noteapi.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage handleAllException(Exception ex, WebRequest req) {
    return new ErrorMessage(500, ex.getLocalizedMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage validatorException(Exception ex, WebRequest req) {
    return new ErrorMessage(400, ex.getLocalizedMessage());
  }

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleTodoException(Exception ex, WebRequest req) {
    return new ErrorMessage(400, "Object does not exists");
  }
}
