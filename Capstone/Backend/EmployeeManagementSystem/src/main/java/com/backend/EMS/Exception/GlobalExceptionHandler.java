package com.backend.EMS.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
     * Handle the AnnotationValidation exception by returning a
      NOT_ACCEPTABLE status and a ResponseDto with the error message.
     *
     * @param ex The AnnotationValidation exception.
     * @return A ResponseDto with the error message.
     */
    @ExceptionHandler(AnnotationValidation.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public final ResponseDto validation(final AnnotationValidation ex) {
        String message = ex.getMessage();
        return new ResponseDto(message);
    }
}
