package com.lukmanhidayah.catalog.exception;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lukmanhidayah.catalog.dto.ErrorResponseDto;
import com.lukmanhidayah.catalog.enums.ErrorCode;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  protected ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
      ResourceNotFoundException ex, WebRequest request) {
    List<String> details = new ArrayList<String>();

    details.add(ex.getMessage());
    ErrorResponseDto errorResponseDto = ErrorResponseDto.of("Data not found", details, ErrorCode.DATA_NOT_FOUND,
        HttpStatus.NOT_FOUND);

    return ResponseEntity.badRequest().body(errorResponseDto);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    List<String> details = new ArrayList<String>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ErrorResponseDto errorResponseDto = ErrorResponseDto.of("Invalid Data", details, ErrorCode.INVALID_DATA,
        HttpStatus.BAD_REQUEST);
    return ResponseEntity.badRequest().body(errorResponseDto);
  }

}
