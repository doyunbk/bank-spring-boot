package com.example.kakao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException e){
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);

    }

}
