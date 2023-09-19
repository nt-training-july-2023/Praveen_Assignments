package com.backend.EMS.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.EMS.Model.Role;

public class LoginResponseDtoTest {

    private LoginResponseDto loginResponseDto;

    @BeforeEach
    public void setUp() {
        loginResponseDto = new LoginResponseDto();
    }

    @Test
    public void testGetSetEmail() {
        String email = "john.doe@example.com";
        loginResponseDto.setEmail(email);
        Assertions.assertEquals(email, loginResponseDto.getEmail());
    }

    @Test
    public void testGetSetRole() {
        Role role = Role.Admin;
        loginResponseDto.setRole(role);
        Assertions.assertEquals(role, loginResponseDto.getRole());
    }

    @Test
    public void testGetSetMessage() {
        String message = "Login successful";
        loginResponseDto.setMessage(message);
        Assertions.assertEquals(message, loginResponseDto.getMessage());
    }

    @Test
    public void testToString() {
        Role role = Role.Admin;
        String message = "Login failed";
        String email = "jane.smith@example.com";
        loginResponseDto.setRole(role);
        loginResponseDto.setMessage(message);
        loginResponseDto.setEmail(email);
        String expectedString = "LoginResponseDto [role=" + role + ", message=" + message + ", email=" + email + "]";
        Assertions.assertEquals(expectedString, loginResponseDto.toString());
    }

    @Test
    public void testHashCode() {
        Role role = Role.Admin;
        String message = "Login successful";
        String email = "john.doe@example.com";
        loginResponseDto.setRole(role);
        loginResponseDto.setMessage(message);
        loginResponseDto.setEmail(email);

        LoginResponseDto other = new LoginResponseDto(role, message, email);

        Assertions.assertEquals(loginResponseDto.hashCode(), other.hashCode());
    }

    @Test
    public void testEquals() {
        Role role = Role.Admin;
        String message = "Login failed";
        String email = "jane.smith@example.com";
        loginResponseDto.setRole(role);
        loginResponseDto.setMessage(message);
        loginResponseDto.setEmail(email);

        LoginResponseDto other = new LoginResponseDto(role, message, email);

        Assertions.assertTrue(loginResponseDto.equals(other));
    }

    @Test
    public void testNotEquals() {
        Role role1 = Role.Admin;
        String message1 = "Login successful";
        String email1 = "john.doe@example.com";
        loginResponseDto.setRole(role1);
        loginResponseDto.setMessage(message1);
        loginResponseDto.setEmail(email1);

        Role role2 = Role.Admin;
        String message2 = "Login failed";
        String email2 = "jane.smith@example.com";

        LoginResponseDto other = new LoginResponseDto(role2, message2, email2);

        Assertions.assertFalse(loginResponseDto.equals(other));
    }
}
