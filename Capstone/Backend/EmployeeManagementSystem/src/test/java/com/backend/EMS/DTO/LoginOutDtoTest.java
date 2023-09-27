package com.backend.EMS.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.EMS.Model.Role;

public class LoginOutDtoTest {

    private LoginOutDto loginOutDto;

    @BeforeEach
    public void setUp() {
        loginOutDto = new LoginOutDto();
    }

    @Test
    public void testGetSetEmail() {
        String email = "john.doe@example.com";
        loginOutDto.setEmail(email);
        Assertions.assertEquals(email, loginOutDto.getEmail());
    }

    @Test
    public void testGetSetRole() {
        Role role = Role.Admin;
        loginOutDto.setRole(role);
        Assertions.assertEquals(role, loginOutDto.getRole());
    }

    @Test
    public void testGetSetMessage() {
        String message = "Login successful";
        loginOutDto.setMessage(message);
        Assertions.assertEquals(message, loginOutDto.getMessage());
    }

    @Test
    public void testToString() {
        Role role = Role.Admin;
        String message = "Login failed";
        String email = "jane.smith@example.com";
        loginOutDto.setRole(role);
        loginOutDto.setMessage(message);
        loginOutDto.setEmail(email);
        String expectedString = "LoginOutDto [role=" + role + ", message=" + message + ", email=" + email + "]";
        Assertions.assertEquals(expectedString, loginOutDto.toString());
    }

    @Test
    public void testHashCode() {
        Role role = Role.Admin;
        String message = "Login successful";
        String email = "john.doe@example.com";
        String name ="John";
        loginOutDto.setRole(role);
        loginOutDto.setMessage(message);
        loginOutDto.setEmail(email);
        loginOutDto.setName(name);

        LoginOutDto other = new LoginOutDto(role, message, email, name);

        Assertions.assertEquals(loginOutDto.hashCode(), other.hashCode());
    }

    @Test
    public void testEquals() {
        Role role = Role.Admin;
        String message = "Login failed";
        String email = "jane.smith@example.com";
        String name ="jane";
        loginOutDto.setRole(role);
        loginOutDto.setMessage(message);
        loginOutDto.setEmail(email);
        loginOutDto.setName(name);

        LoginOutDto other = new LoginOutDto(role, message, email,name);

        Assertions.assertTrue(loginOutDto.equals(other));
    }

    @Test
    public void testNotEquals() {
        Role role1 = Role.Admin;
        String message1 = "Login successful";
        String email1 = "john.doe@example.com";
        String name ="John";
        loginOutDto.setRole(role1);
        loginOutDto.setMessage(message1);
        loginOutDto.setEmail(email1);
        loginOutDto.setName(name);

        Role role2 = Role.Admin;
        String message2 = "Login failed";
        String email2 = "jane.smith@example.com";
        String name2 = "Jane";

        LoginOutDto other = new LoginOutDto(role2, message2, email2, name2);

        Assertions.assertFalse(loginOutDto.equals(other));
    }
}
