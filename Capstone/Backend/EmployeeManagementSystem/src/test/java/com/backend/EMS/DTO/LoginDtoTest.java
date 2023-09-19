package com.backend.EMS.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginDtoTest {

    private LoginDto loginDto;

    @BeforeEach
    public void setUp() {
        loginDto = new LoginDto();
    }

    @Test
    public void testGetSetEmail() {
        String email = "john.doe@example.com";
        loginDto.setEmail(email);
        Assertions.assertEquals(email, loginDto.getEmail());
    }

    @Test
    public void testGetSetPassword() {
        String password = "secret";
        loginDto.setPassword(password);
        Assertions.assertEquals(password, loginDto.getPassword());
    }

    @Test
    public void testToString() {
        String email = "john.doe@example.com";
        String password = "secret";
        loginDto.setEmail(email);
        loginDto.setPassword(password);
        String expectedString = "LoginDto [email=" + email + ", password=" + password + "]";
        Assertions.assertEquals(expectedString, loginDto.toString());
    }

    @Test
    public void testHashCode() {
        String email1 = "john.doe@example.com";
        String password1 = "secret";
        loginDto.setEmail(email1);
        loginDto.setPassword(password1);

        LoginDto other = new LoginDto();
        other.setEmail(email1);
        other.setPassword(password1);

        Assertions.assertEquals(loginDto.hashCode(), other.hashCode());
    }

    @Test
    public void testEquals() {
        String email1 = "john.doe@example.com";
        String password1 = "secret";
        loginDto.setEmail(email1);
        loginDto.setPassword(password1);

        LoginDto other = new LoginDto();
        other.setEmail(email1);
        other.setPassword(password1);

        Assertions.assertTrue(loginDto.equals(other));
    }

    @Test
    public void testNotEquals() {
        String email1 = "john.doe@example.com";
        String password1 = "secret";
        loginDto.setEmail(email1);
        loginDto.setPassword(password1);

        LoginDto other = new LoginDto();
        other.setEmail("jane.smith@example.com");
        other.setPassword(password1);

        Assertions.assertFalse(loginDto.equals(other));
    }
}

