package com.test.security.exception.handler;

import com.test.security.exceptions.AuthException;
import com.test.security.model.ExceptionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionResponse> handleException(AuthException ex) {
        log.error("exception occurred:", ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }
    //AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleException(AccessDeniedException ex) {
        log.error("exception occurred:", ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.error("exception occurred:", ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}