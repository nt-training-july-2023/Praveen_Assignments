package com.backend.EMS.Exception;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.EMS.DTO.ResponseDto;

/**
 * Global exception handler for handling specific
   exceptions and returning appropriate responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle the userAlreadyFound exception by returning a
      FOUND status and a ResponseDto with the error message.
     *
     * @param ex The userAlreadyFound exception.
     * @return A ResponseDto with the error message.
     */
    @ExceptionHandler(UserAlreadyFound.class)
    @ResponseStatus(HttpStatus.FOUND)
    public final ResponseDto userFoundException(final UserAlreadyFound ex) {
        String message = ex.getMessage();
        return new ResponseDto(message);
    }
    /**
     * Handle the UserNotFound exception by returning a
      NOT_FOUND status and a ResponseDto with the error message.
     *
     * @param ex The UserNotFound exception.
     * @return A ResponseDto with the error message.
     */
    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseDto notFoundException(final UserNotFound ex) {
        String message = ex.getMessage();
        return new ResponseDto(message);
    }
    /**
     * validation method.
     * @param ex exception
     * @return response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final Map<String, String> handleEmptyDataValidation(
            final MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName, message);
        });
        return resp;
    }
}
