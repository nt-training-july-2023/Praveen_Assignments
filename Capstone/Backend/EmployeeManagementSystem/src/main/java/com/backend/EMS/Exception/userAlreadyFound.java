package com.backend.EMS.Exception;

public class userAlreadyFound extends RuntimeException{
    public userAlreadyFound(final String message) {
        super(message);
    }

}
