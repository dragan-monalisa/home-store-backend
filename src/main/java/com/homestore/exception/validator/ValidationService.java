package com.homestore.exception.validator;

import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public interface ValidationService {

    List<ValidationError> buildValidationErrors(Set<ConstraintViolation<?>> violations);
}