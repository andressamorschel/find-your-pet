package com.findyourpet.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    private final LocalizedMessageTranslation localizedMessageTranslation;

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var fieldError = (FieldError) ex.getBindingResult().getAllErrors().get(0);
        var errorMessage = fieldError.getDefaultMessage(); // TODO: improve implementation to use localizedMessageTranslation as well

        var errorResponse = ErrorResponse.builder()
                .status("400")
                .message(errorMessage)
                .build();

        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(NotFoundException cause) {
        var errorMessage = localizedMessageTranslation.getMessage(cause.getError());

        var errorResponse = ErrorResponse.builder()
                .status("404")
                .message(errorMessage)
                .build();

        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }
}
