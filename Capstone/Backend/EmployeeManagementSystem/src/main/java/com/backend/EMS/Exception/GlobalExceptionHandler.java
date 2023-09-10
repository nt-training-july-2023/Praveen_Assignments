package com.backend.EMS.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.EMS.DTO.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(userAlreadyFound.class)
    @ResponseStatus(HttpStatus.FOUND)
    public final ResponseDto userFoundException(userAlreadyFound ex) {
        String message = ex.getMessage();
        return new ResponseDto(message);
    }
    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseDto notFoundException(UserNotFound ex) {
        String message =ex.getMessage();
        return new ResponseDto(message);
    }
    @ExceptionHandler(AnnotationValidation.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public final ResponseDto validation(AnnotationValidation ex) {
        String message = ex.getMessage();
        return new ResponseDto(message);
    }
   
}