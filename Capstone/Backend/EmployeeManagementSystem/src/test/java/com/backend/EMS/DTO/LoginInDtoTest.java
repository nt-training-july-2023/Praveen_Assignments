package com.backend.EMS.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginInDtoTest {

    private LoginInDto loginInDto;

    @BeforeEach
    public void setUp() {
        loginInDto = new LoginInDto();
    }

    @Test
    public void testGetSetEmail() {
        String email = "john.doe@example.com";
        loginInDto.setEmail(email);
        Assertions.assertEquals(email, loginInDto.getEmail());
    }

    @Test
    public void testGetSetPassword() {
        String password = "secret";
        loginInDto.setPassword(password);
        Assertions.assertEquals(password, loginInDto.getPassword());
    }

    @Test
    public void testToString() {
        String email = "john.doe@example.com";
        String password = "secret";
        loginInDto.setEmail(email);
        loginInDto.setPassword(password);
        String expectedString = "LoginInDto [email=" + email + ", password=" + password + "]";
        Assertions.assertEquals(expectedString, loginInDto.toString());
    }

    @Test
    public void testHashCode() {
        String email1 = "john.doe@example.com";
        String password1 = "secret";
        loginInDto.setEmail(email1);
        loginInDto.setPassword(password1);

        LoginInDto other = new LoginInDto();
        other.setEmail(email1);
        other.setPassword(password1);

        Assertions.assertEquals(loginInDto.hashCode(), other.hashCode());
    }

    @Test
    public void testEquals() {
        String email1 = "john.doe@example.com";
        String password1 = "secret";
        loginInDto.setEmail(email1);
        loginInDto.setPassword(password1);

        LoginInDto other = new LoginInDto();
        other.setEmail(email1);
        other.setPassword(password1);

        Assertions.assertTrue(loginInDto.equals(other));
    }

    @Test
    public void testNotEquals() {
        String email1 = "john.doe@example.com";
        String password1 = "secret";
        loginInDto.setEmail(email1);
        loginInDto.setPassword(password1);

        LoginInDto other = new LoginInDto();
        other.setEmail("jane.smith@example.com");
        other.setPassword(password1);

        Assertions.assertFalse(loginInDto.equals(other));
    }
}

