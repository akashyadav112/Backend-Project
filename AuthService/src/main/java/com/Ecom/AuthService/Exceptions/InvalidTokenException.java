package com.Ecom.AuthService.Exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(){}
    public InvalidTokenException(String message){
        super(message);
    }
    public InvalidTokenException(String msg, Throwable cause){
        super(msg,cause);
    }
}
