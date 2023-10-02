package com.backend.EMS.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestResourceInDtoTest {
    private RequestResourceInDto dto;

    @BeforeEach
    public void setUp() {
        dto = new RequestResourceInDto();
    }

    @Test
    public void testSetterGetter() {
        // Test setId and getId
        dto.setId(1L);
        assertEquals(1L,  dto.getId());

        // Test setEmployeeId and getEmployeeId
        dto.setEmployeeId(2L);
        assertEquals(2L,  dto.getEmployeeId());

        // Test setProjectId and getProjectId
        dto.setProjectId(3L);
        assertEquals(3L, dto.getProjectId());

        // Test setManagerId and getManagerId
        dto.setManagerId(4L);
        assertEquals(4L, dto.getManagerId());

        // Test setComment and getComment
        dto.setComment("Test comment");
        assertEquals("Test comment", dto.getComment());
    }

    @Test
    public void testNullValues() {
        // Test getting null values for all fields
        assertNull(dto.getId());
        assertNull(dto.getEmployeeId());
        assertNull(dto.getProjectId());
        assertNull(dto.getManagerId());
        assertNull(dto.getComment());

        // Test setting null values for all fields
        dto.setId(null);
        dto.setEmployeeId(null);
        dto.setProjectId(null);
        dto.setManagerId(null);
        dto.setComment(null);

        assertNull(dto.getId());
        assertNull(dto.getEmployeeId());
        assertNull(dto.getProjectId());
        assertNull(dto.getManagerId());
        assertNull(dto.getComment());
    }

    @Test
    public void testHashCode() {
        RequestResourceInDto dto1 = new RequestResourceInDto();
        dto1.setId(1L);
        dto1.setEmployeeId(2L);
        dto1.setProjectId(3L);
        dto1.setManagerId(4L);
        dto1.setComment("Test comment");

        RequestResourceInDto dto2 = new RequestResourceInDto();
        dto2.setId(1L);
        dto2.setEmployeeId(2L);
        dto2.setProjectId(3L);
        dto2.setManagerId(4L);
        dto2.setComment("Test comment");

        // HashCode should be the same for equal objects
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        RequestResourceInDto dto1 = new RequestResourceInDto();
        dto1.setId(1L);
        dto1.setEmployeeId(2L);
        dto1.setProjectId(3L);
        dto1.setManagerId(4L);
        dto1.setComment("Test comment");

        RequestResourceInDto dto2 = new RequestResourceInDto();
        dto2.setId(1L);
        dto2.setEmployeeId(2L);
        dto2.setProjectId(3L);
        dto2.setManagerId(4L);
        dto2.setComment("Test comment");

        RequestResourceInDto dto3 = new RequestResourceInDto();
        dto3.setId(5L);
        dto3.setEmployeeId(6L);
        dto3.setProjectId(7L);
        dto3.setManagerId(8L);
        dto3.setComment("Another comment");

        // Test equality with equal objects
        assertEquals(dto1, dto2);

        // Test inequality with different objects
        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        RequestResourceInDto dto = new RequestResourceInDto();
        dto.setId(1L);
        dto.setEmployeeId(2L);
        dto.setProjectId(3L);
        dto.setManagerId(4L);
        dto.setComment("Test comment");

        String expectedToString = "RequestResourceInDto [id=1, employeeId=2, projectId=3, managerId=4, comment=Test comment]";
        assertEquals(expectedToString, dto.toString());
    }
}
