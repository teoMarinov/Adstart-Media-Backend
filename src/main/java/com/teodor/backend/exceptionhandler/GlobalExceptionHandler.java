package com.teodor.backend.exceptionhandler;

import com.teodor.backend.dto.BaseResponseDto;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponseDto<Void>> handleInvalidFormat(HttpMessageNotReadableException ex) {
        BaseResponseDto<Void> response = new BaseResponseDto<>();

        response.setErrorMessage("Invalid request format: " + ex.getMostSpecificCause().getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponseDto<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        BaseResponseDto<Map<String, String>> response = new BaseResponseDto<>();

        response.setErrorMessage("Validation failed for request body");

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        response.setAdditionalErrorData(errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<BaseResponseDto<Map<String, String>>> handleTypeMismatch(TypeMismatchException ex) {
        BaseResponseDto<Map<String, String>> response = new BaseResponseDto<>();

        response.setErrorMessage(
                "Invalid type provided. Expected type " +
                        ex.getRequiredType().getSimpleName() +
                        ", but got rejected value " +
                        String.valueOf(ex.getValue())
                );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseDto<Void>> handleGenericException(Exception ex) {
        BaseResponseDto<Void> response = new BaseResponseDto<>();

        response.setErrorMessage("An unexpected error occurred: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
