package com.backend.EMS.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginDtoTest {

    private LoginDto loginDto;

    @BeforeEach
    public void setUp() {
        loginDto = new LoginDto();
    }

    @Test
    void testGetEmail() {
        loginDto.setEmail("praveen@example.com");
        assertEquals("praveen@example.com", loginDto.getEmail());
    }

    @Test
    void testSetEmail() {
        loginDto.setEmail("praveen@example.com");
        assertEquals("praveen@example.com", loginDto.getEmail());
    }

    @Test
    void testGetPassword() {
        loginDto.setPassword("myPassword");
        assertEquals("myPassword", loginDto.getPassword());
    }

    @Test
    void testSetPassword() {
        loginDto.setPassword("myPassword");
        assertEquals("myPassword", loginDto.getPassword());
    }


}

