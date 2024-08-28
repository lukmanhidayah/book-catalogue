package com.lukmanhidayah.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

  // serialVersionUID
  private static final long serialVersionUID = -1711093949024566944L;


  public BadRequestException(String message) {
    super(message);
  }

}
