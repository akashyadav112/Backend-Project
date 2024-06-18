package com.Ecom.ProductService.Exceptions;

import com.Ecom.ProductService.Dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * when null pointer is thrown, globally it will be handled here
     * @param  e
     * @return error
     */
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNullPointer(Exception e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorMsg(e.getMessage());
        errorResponseDTO.setErrorCd("404");
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<ErrorResponseDTO> handleTokenInvalidException(Exception e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorMsg(e.getMessage());
        errorResponseDTO.setErrorCd("403");
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.FORBIDDEN);
    }
}
