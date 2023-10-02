package com.backend.EMS.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.backend.EMS.Model.Role;

public class LoginOutDtoTest {


    @Test
    public void testSetterGetter() {
        LoginOutDto dto = new LoginOutDto();

        // Test setId and getId
        dto.setId(1L);
        assertEquals(Long.valueOf(1L), dto.getId());

        // Test setRole and getRole
        dto.setRole(Role.Admin);
        assertEquals(Role.Admin, dto.getRole());

        // Test setMessage and getMessage
        dto.setMessage("Success");
        assertEquals("Success", dto.getMessage());

        // Test setName and getName
        dto.setName("John");
        assertEquals("John", dto.getName());
    }

    @Test
    public void testNullValues() {
        LoginOutDto dto = new LoginOutDto();

        // Test setting null values
        dto.setId(null);
        dto.setRole(null);
        dto.setMessage(null);
        dto.setName(null);

        // Test getting null values
        assertNull(dto.getId());
        assertNull(dto.getRole());
        assertNull(dto.getMessage());
        assertNull(dto.getName());
    }

    @Test
    public void testHashCode() {
        LoginOutDto dto1 = new LoginOutDto();
        dto1.setId(1L);
        dto1.setRole(Role.Admin);
        dto1.setMessage("Success");
        dto1.setName("John");

        LoginOutDto dto2 = new LoginOutDto();
        dto2.setId(1L);
        dto2.setRole(Role.Admin);
        dto2.setMessage("Success");
        dto2.setName("John");

        // HashCode should be the same for equal objects
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        LoginOutDto dto1 = new LoginOutDto();
        dto1.setId(1L);
        dto1.setRole(Role.Admin);
        dto1.setMessage("Success");
        dto1.setName("John");

        LoginOutDto dto2 = new LoginOutDto();
        dto2.setId(1L);
        dto2.setRole(Role.Admin);
        dto2.setMessage("Success");
        dto2.setName("John");

        LoginOutDto dto3 = new LoginOutDto();
        dto3.setId(2L);
        dto3.setRole(Role.Admin);
        dto3.setMessage("Failure");
        dto3.setName("Jane");

        // Test equality with equal objects
        assertEquals(dto1, dto2);

        // Test inequality with different objects
        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        LoginOutDto dto = new LoginOutDto();
        dto.setId(1L);
        dto.setRole(Role.Admin);
        dto.setMessage("Success");
        dto.setName("John");

        String expectedToString = "LoginOutDto [role=Admin, message=Success, name=John, email=1]";
        assertEquals(expectedToString, dto.toString());
    }
}
