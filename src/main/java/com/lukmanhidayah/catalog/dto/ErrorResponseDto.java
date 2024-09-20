package com.lukmanhidayah.catalog.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

import com.lukmanhidayah.catalog.enums.ErrorCode;

@Data
public class ErrorResponseDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Date timestamp;

  private String message;

  private ErrorCode errorCode;

  private List<String> details;

  private HttpStatus httpStatus;

  public static ErrorResponseDto of(final String message, List<String> details, final ErrorCode errorCode,
      final HttpStatus httpStatus) {
    return new ErrorResponseDto(message, details, errorCode, httpStatus);
  }

  public ErrorResponseDto(String message, List<String> details, ErrorCode errorCode, HttpStatus httpStatus) {
    super();

    this.timestamp = new Date();
    this.message = message;
    this.errorCode = errorCode;
    this.details = details;
    this.httpStatus = httpStatus;

  }
}
