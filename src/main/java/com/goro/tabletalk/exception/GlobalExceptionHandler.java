package com.goro.tabletalk.exception;

import com.goro.tabletalk.dto.ApiDTO.ErrorResponse;
import com.goro.tabletalk.dto.ApiDTO.MultipleErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Global exception handler for the application.
 * Provides centralized exception handling across all controllers
 * and converts exceptions into appropriate API responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions from request body validation.
     * Converts validation errors into a list of field-specific error messages.
     *
     * @param ex The validation exception
     * @return ResponseEntity containing a list of validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MultipleErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorResponse> validationErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            validationErrors.add(new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()));
        });

        MultipleErrorResponse errorResponse = new MultipleErrorResponse("BAD_REQUEST", validationErrors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions for non-unique results in database queries.
     * Typically occurs when a unique constraint is violated.
     *
     * @param ex The non-unique result exception
     * @return ResponseEntity containing the error message
     */
    @ExceptionHandler(NonUniqueResultException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNonUniqueExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions for entities not found in the database.
     * Returns a 404 Not Found response with the error message.
     *
     * @param ex The entity not found exception
     * @return ResponseEntity containing the error message
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Fallback handler for all unhandled exceptions.
     * Returns a 500 Internal Server Error response with the error message.
     *
     * @param ex The unhandled exception
     * @return ResponseEntity containing the error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}