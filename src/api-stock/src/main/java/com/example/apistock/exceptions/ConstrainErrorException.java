package com.example.apistock.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConstrainErrorException extends RuntimeException {

    private String error;
    private String message;
    private int status;
}
