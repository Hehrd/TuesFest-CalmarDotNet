package com.hacktues.error.handler;

import com.hacktues.error.exeption.Exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleTestException(java.lang.Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Възникна тестов ексепшън", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
