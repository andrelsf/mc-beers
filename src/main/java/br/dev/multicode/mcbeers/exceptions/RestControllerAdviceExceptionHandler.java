package br.dev.multicode.mcbeers.exceptions;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  protected ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
    log.error(ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
  {
    log.error(ex.getMessage(), ex);
    return ResponseEntity.status(status)
        .body(new ErrorResponse(status.value(), status.getReasonPhrase()));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
  {
    List<ErrorResponse> errorMessages = new ArrayList<>();

    Optional.of(ex.getBindingResult())
      .map(BindingResult::getFieldErrors)
      .ifPresentOrElse(fieldErrors ->
        fieldErrors.forEach(fieldError ->
          errorMessages.add(new ErrorResponse(status.value(),
            format("'%s' %s", fieldError.getField(), fieldError.getDefaultMessage())))),
        () ->
          errorMessages.add(new ErrorResponse(status.value(), status.getReasonPhrase())));

    log.error(ex.getMessage(), ex);

    return ResponseEntity.status(status).body(errorMessages);
  }
}
