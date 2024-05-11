package com.rs.findyourpet.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.rs.findyourpet.exceptions.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionMapperAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var fieldError = (FieldError) ex.getBindingResult().getAllErrors().get(0);
        var errorMessage = fieldError.getDefaultMessage();

        var errorResponse = ErrorResponse.builder()
                .status("400")
                .message(errorMessage)
                .build();

        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }
}
