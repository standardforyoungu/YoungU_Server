package com.youngustandard.youngu_server.Exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Data
@Builder
public class ErrorResponseEntity {
    private String result;
    private String message;
    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        FieldError fieldError = fieldErrors.get(fieldErrors.size()-1);
        String fieldName = fieldError.getField();
        Object rejectedValue = fieldError.getRejectedValue();
        System.out.println("fieldName = " + fieldName);
        System.out.println("rejectedValue = " + rejectedValue);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseEntity.builder()
                .result("Fail")
                .message(fieldError.getDefaultMessage()).build());
    }
}
