package com.mishkurov.exceptions;

public class ProductManagerException extends RuntimeException {

    public ProductManagerException(){
        super();
    }

    public ProductManagerException(String message){
        super(message);
    }

    public ProductManagerException(String message, Throwable cause){
        super(message, cause);
    }
}
