package com.gitlab.lamapizama.notee.user.account.errorhandling;

import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@ControllerAdvice
@RequiredArgsConstructor
public class NoteeExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(
                "Validation error",
                status.value(),
                "Input validation failed",
                Instant.now(),
                ex.getClass().getSimpleName());
        errorDetail.getErrors().putAll(getValidationErrors(ex.getBindingResult()));

        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(
                "Message not readable",
                status.value(),
                ex.getMessage(),
                Instant.now(),
                ex.getClass().getSimpleName());

        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ErrorDetail errorDetail = new ErrorDetail(
                "Resource not found",
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Instant.now(),
                ex.getClass().getSimpleName());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    private Map<String, List<String>> getValidationErrors(BindingResult bindingResult) {
        Map<String, List<String>> result = new HashMap<>();
        result.putAll(getGlobalErrors(bindingResult.getGlobalErrors()));
        result.putAll(getFieldErrors(bindingResult.getFieldErrors()));
        return result;
    }

    private Map<String, List<String>> getGlobalErrors(List<ObjectError> errors) {
        return errors.stream()
                .collect(toMap(ObjectError::getObjectName, objectError -> singletonList(
                        messageSource.getMessage(objectError, null))));
    }

    private Map<String, List<String>> getFieldErrors(List<FieldError> errors) {
        return errors.stream()
                .collect(groupingBy(FieldError::getField,
                        mapping(fieldError -> messageSource.getMessage(fieldError, null), toList())));
    }
}
