package com.Ecom.ProductService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * when null pointer is thrown, globally it will be handled here
     * @param  e
     * @return error
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<String> handleNullPointer(Exception e){
        String exceptionResponse = "error : something went wrong" + "errorCd:"+ HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.ok(exceptionResponse);
    }
}
