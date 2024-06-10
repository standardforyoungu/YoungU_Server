package com.youngustandard.youngu_server.Exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;

@RestController
@ControllerAdvice
public class CustomizeResponseEntityException {
    HttpHeaders httpHeaders = new HttpHeaders();
    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<Object> NotFoundException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail",ex.getMessage());
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<Object> ViolateRuleException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail",ex.getMessage());
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorResponseEntity> handleValidationException(MethodArgumentNotValidException e) {
        return ErrorResponseEntity.toResponseEntity(e);
    }

    @ExceptionHandler(IOException.class)
    public final ResponseEntity<Object> malformed(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Fail","잠시후 다시 시도해주세요.");
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
