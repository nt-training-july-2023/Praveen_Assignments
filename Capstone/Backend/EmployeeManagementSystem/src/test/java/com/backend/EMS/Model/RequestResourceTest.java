package com.backend.EMS.Model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestResourceTest {
    private RequestResource requestResource;

    @BeforeEach
    public void setUp() {
        requestResource = new RequestResource();
    }

    @Test
    public void testGettersAndSetters() {
        // Test setters and getters
        requestResource.setEmployeeId(1L);
        requestResource.setProjectId(2L);
        requestResource.setManagerId(3L);
        requestResource.setComment("Test comment");

        assertEquals(1L, (long) requestResource.getEmployeeId());
        assertEquals(2L, (long) requestResource.getProjectId());
        assertEquals(3L, (long) requestResource.getManagerId());
        assertEquals("Test comment", requestResource.getComment());

        // Test null values for setters and getters
        requestResource.setEmployeeId(null);
        requestResource.setProjectId(null);
        requestResource.setManagerId(null);
        requestResource.setComment(null);

        assertNull(requestResource.getEmployeeId());
        assertNull(requestResource.getProjectId());
        assertNull(requestResource.getManagerId());
        assertNull(requestResource.getComment());
    }

    @Test
    public void testHashCode() {
        // Test hashCode with non-null values
        requestResource.setEmployeeId(1L);
        requestResource.setProjectId(2L);
        requestResource.setManagerId(3L);
        requestResource.setComment("Test comment");

        RequestResource anotherRequestResource = new RequestResource();
        anotherRequestResource.setEmployeeId(1L);
        anotherRequestResource.setProjectId(2L);
        anotherRequestResource.setManagerId(3L);
        anotherRequestResource.setComment("Test comment");

        assertEquals(requestResource.hashCode(), anotherRequestResource.hashCode());

        // Test hashCode with null values
        requestResource.setEmployeeId(null);
        requestResource.setProjectId(null);
        requestResource.setManagerId(null);
        requestResource.setComment(null);

        RequestResource nullRequestResource = new RequestResource();
        nullRequestResource.setEmployeeId(null);
        nullRequestResource.setProjectId(null);
        nullRequestResource.setManagerId(null);
        nullRequestResource.setComment(null);

        assertEquals(requestResource.hashCode(), nullRequestResource.hashCode());
    }

    @Test
    public void testEquals() {
        // Test equals with non-null values
        requestResource.setEmployeeId(1L);
        requestResource.setProjectId(2L);
        requestResource.setManagerId(3L);
        requestResource.setComment("Test comment");

        RequestResource anotherRequestResource = new RequestResource();
        anotherRequestResource.setEmployeeId(1L);
        anotherRequestResource.setProjectId(2L);
        anotherRequestResource.setManagerId(3L);
        anotherRequestResource.setComment("Test comment");

        assertTrue(requestResource.equals(anotherRequestResource));

        // Test equals with null values
        requestResource.setEmployeeId(null);
        requestResource.setProjectId(null);
        requestResource.setManagerId(null);
        requestResource.setComment(null);

        RequestResource nullRequestResource = new RequestResource();
        nullRequestResource.setEmployeeId(null);
        nullRequestResource.setProjectId(null);
        nullRequestResource.setManagerId(null);
        nullRequestResource.setComment(null);

        assertTrue(requestResource.equals(nullRequestResource));
    }

    @Test
    public void testToString() {
        // Test toString with non-null values
        requestResource.setEmployeeId(1L);
        requestResource.setProjectId(2L);
        requestResource.setManagerId(3L);
        requestResource.setComment("Test comment");

        String expectedToString = "RequestResource [id=null, employeeId=1, projectId=2, managerId=3, comment=Test comment]";
        assertEquals(expectedToString, requestResource.toString());

        // Test toString with null values
        requestResource.setEmployeeId(null);
        requestResource.setProjectId(null);
        requestResource.setManagerId(null);
        requestResource.setComment(null);

        String expectedToStringWithNulls = "RequestResource [id=null, employeeId=null, projectId=null, managerId=null, comment=null]";
        assertEquals(expectedToStringWithNulls, requestResource.toString());
    }
}
