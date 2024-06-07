package com.example.apistock.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchException extends RuntimeException {

  private String message;
  private HttpStatus httpStatus;
  private Date execeptionDate;
  private String exceptionId;

  public SearchException(String message, HttpStatus httpStatus) {
    super(message);
    this.message = message;
    this.httpStatus = httpStatus;
    this.execeptionDate = new Date();
    this.exceptionId = String.valueOf(UUID.randomUUID());
  }
}
