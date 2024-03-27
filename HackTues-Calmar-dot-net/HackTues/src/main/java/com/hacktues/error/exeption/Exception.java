package com.hacktues.error.exeption;

public class Exception extends RuntimeException{

    public Exception() {
        super();
    }
    public Exception(String message) {
        super(message);
    }
    public Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
