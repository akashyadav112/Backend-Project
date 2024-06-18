package com.Ecom.ProductService.Exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(){}
    public InvalidTokenException(String msg){
        super(msg);
    }
    public InvalidTokenException(String msg, Throwable cause){
        super(msg,cause);
    }
}
