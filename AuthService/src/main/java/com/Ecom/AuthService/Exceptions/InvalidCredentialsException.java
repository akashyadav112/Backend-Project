package com.Ecom.AuthService.Exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){}
    public InvalidCredentialsException(String message){
        super(message);
    }
    public InvalidCredentialsException(String message, Throwable cause){
        super(message,cause);
    }
}
