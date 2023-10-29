package com.homestore.exception.validator;

import jakarta.validation.ConstraintViolation;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.StreamSupport;

@Service
public class ValidationServiceImpl implements ValidationService{

    @Override
    public List<ValidationError> buildValidationErrors(Set<ConstraintViolation<?>> violations) {
        return violations
                .stream()
                .map(violation -> ValidationError.builder()
                        .field(
                                Objects.requireNonNull(
                                        StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                                .reduce((first, second) -> second)
                                                .orElse(null)
                                ).toString()
                        )
                        .error(violation.getMessage())
                        .build())
                .collect(toList());
    }
}