package com.vs.ImageAnnotatorServer.utils.exception;

public class UserCreationException extends Exception{

    public UserCreationException(String message) {
        super(message);
    }

    public UserCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserCreationException(Throwable cause) {
        super(cause);
    }
}
