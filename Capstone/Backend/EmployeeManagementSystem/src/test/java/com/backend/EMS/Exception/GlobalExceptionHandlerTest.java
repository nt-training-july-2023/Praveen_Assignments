package com.backend.EMS.Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.backend.EMS.DTO.ResponseDto;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testUserFoundException() {
        UserAlreadyFound userAlreadyFound = new UserAlreadyFound("User already found");
        ResponseDto responseDto = exceptionHandler.userFoundException(userAlreadyFound);

        assertEquals("User already found", responseDto.getMessage());
    }

    @Test
    public void testNotFoundException() {
        UserNotFound userNotFound = new UserNotFound("User not found");
        ResponseDto responseDto = exceptionHandler.notFoundException(userNotFound);

        assertEquals("User not found", responseDto.getMessage());
    }
    @Test
    public void testHandleEmptyDataValidation() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);

        BindingResult bindingResult = mock(BindingResult.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);
        FieldError fieldError = mock(FieldError.class);
        when(fieldError.getField()).thenReturn("fieldName");
        when(fieldError.getDefaultMessage()).thenReturn("Validation error message");
        when(bindingResult.getAllErrors()).thenReturn(java.util.List.of(fieldError));
        GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
        Map<String, String> responseEntity = exceptionHandler.handleEmptyDataValidation(exception);
        Map<String, String> responseBody = responseEntity;
        assertEquals(1, responseBody.size());
        assertEquals("Validation error message", responseBody.get("fieldName"));
    }


}


