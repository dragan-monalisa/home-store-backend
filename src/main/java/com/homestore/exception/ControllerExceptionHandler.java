package com.homestore.exception;

import com.homestore.exception.validator.ValidationError;
import com.homestore.exception.validator.ValidationService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ResponseBody
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final ValidationService validationService;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse resourceNotFoundException(ResourceNotFoundException e){
        return new ErrorResponse(e.getMessage(), formatter.format(LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ErrorResponse unauthorizedAccessException(UnauthorizedAccessException e){
        return new ErrorResponse(e.getMessage(), formatter.format(LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceConflictException.class)
    public ErrorResponse resourceConflictException(ResourceConflictException e){
        return new ErrorResponse(e.getMessage(), formatter.format(LocalDateTime.now()));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<ValidationError> handleConstraintViolation(ConstraintViolationException e) {
        return validationService.buildValidationErrors(e.getConstraintViolations());
    }
}