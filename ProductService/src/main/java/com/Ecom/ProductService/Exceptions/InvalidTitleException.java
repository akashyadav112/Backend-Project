package com.Ecom.ProductService.Exceptions;

public class InvalidTitleException extends RuntimeException{
    public InvalidTitleException(){}
    public InvalidTitleException(String msg){
        super(msg);
    }
    public InvalidTitleException(String msg, Throwable cause){
        super(msg,cause);
    }
}
