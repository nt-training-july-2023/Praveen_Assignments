package com.backend.EMS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class userAlreadyFound extends RuntimeException{
    public userAlreadyFound(final String message) {
        super(message);
    }

}
