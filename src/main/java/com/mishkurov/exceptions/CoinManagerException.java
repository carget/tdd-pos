package com.mishkurov.exceptions;

public class CoinManagerException extends RuntimeException {

    public CoinManagerException(){
        super();
    }

    public CoinManagerException(String message){
        super(message);
    }

    public CoinManagerException(String message, Throwable cause){
        super(message, cause);
    }
}
