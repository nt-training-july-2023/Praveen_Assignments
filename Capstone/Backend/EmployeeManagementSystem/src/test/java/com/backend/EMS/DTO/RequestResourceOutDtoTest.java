package com.backend.EMS.DTO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class RequestResourceOutDtoTest {
    private RequestResourceOutDto requestResource;

    @BeforeEach
    public void setUp() {
        requestResource = new RequestResourceOutDto();
    }

    @Test
    public void testGettersAndSetters() {
        // Test setters and getters
        requestResource.setId(1L);
        requestResource.setEmployeeId(101L);
        requestResource.setEmployeeEmpId("EMP001");
        requestResource.setProjectId(201L);
        requestResource.setManagerId(301L);
        requestResource.setManagerEmpId("MGR001");
        requestResource.setEmployeeName("John Doe");
        requestResource.setManagerName("Jane Smith");
        requestResource.setProjectName("Project ABC");
        requestResource.setComment("This is a comment.");

        assertEquals(1L, (long) requestResource.getId());
        assertEquals(101L, (long) requestResource.getEmployeeId());
        assertEquals("EMP001", requestResource.getEmployeeEmpId());
        assertEquals(201L, (long) requestResource.getProjectId());
        assertEquals(301L, (long) requestResource.getManagerId());
        assertEquals("MGR001", requestResource.getManagerEmpId());
        assertEquals("John Doe", requestResource.getEmployeeName());
        assertEquals("Jane Smith", requestResource.getManagerName());
        assertEquals("Project ABC", requestResource.getProjectName());
        assertEquals("This is a comment.", requestResource.getComment());

        // Null tests for setters
        requestResource.setId(null);
        requestResource.setEmployeeId(null);
        requestResource.setEmployeeEmpId(null);
        requestResource.setProjectId(null);
        requestResource.setManagerId(null);
        requestResource.setManagerEmpId(null);
        requestResource.setEmployeeName(null);
        requestResource.setManagerName(null);
        requestResource.setProjectName(null);
        requestResource.setComment(null);

        assertNull(requestResource.getId());
        assertNull(requestResource.getEmployeeId());
        assertNull(requestResource.getEmployeeEmpId());
        assertNull(requestResource.getProjectId());
        assertNull(requestResource.getManagerId());
        assertNull(requestResource.getManagerEmpId());
        assertNull(requestResource.getEmployeeName());
        assertNull(requestResource.getManagerName());
        assertNull(requestResource.getProjectName());
        assertNull(requestResource.getComment());
    }

    @Test
    public void testHashCode() {
        // Test hashCode with non-null values (similar to previous test)
        requestResource.setId(1L);
        requestResource.setEmployeeId(101L);
        requestResource.setEmployeeEmpId("EMP001");
        requestResource.setProjectId(201L);
        requestResource.setManagerId(301L);
        requestResource.setManagerEmpId("MGR001");
        requestResource.setEmployeeName("John Doe");
        requestResource.setManagerName("Jane Smith");
        requestResource.setProjectName("Project ABC");
        requestResource.setComment("This is a comment.");

        int expectedHashCode = Objects.hash("This is a comment.", "EMP001", 101L, "John Doe", 1L, "MGR001", 301L, "Jane Smith", 201L, "Project ABC");
        assertEquals(expectedHashCode, requestResource.hashCode());

        // Test hashCode with null values (similar to null tests for setters)
        requestResource.setId(null);
        requestResource.setEmployeeId(null);
        requestResource.setEmployeeEmpId(null);
        requestResource.setProjectId(null);
        requestResource.setManagerId(null);
        requestResource.setManagerEmpId(null);
        requestResource.setEmployeeName(null);
        requestResource.setManagerName(null);
        requestResource.setProjectName(null);
        requestResource.setComment(null);

        int expectedHashCodeWithNulls = Objects.hash(null, null, null, null, null, null, null, null, null, null);
        assertEquals(expectedHashCodeWithNulls, requestResource.hashCode());
    }

    @Test
    public void testEquals() {
        RequestResourceOutDto requestResource1 = new RequestResourceOutDto();
        RequestResourceOutDto requestResource2 = new RequestResourceOutDto();

        // Test equals with non-null values (similar to previous test)
        requestResource1.setId(1L);
        requestResource1.setEmployeeId(101L);
        requestResource1.setEmployeeEmpId("EMP001");
        requestResource1.setProjectId(201L);
        requestResource1.setManagerId(301L);
        requestResource1.setManagerEmpId("MGR001");
        requestResource1.setEmployeeName("John Doe");
        requestResource1.setManagerName("Jane Smith");
        requestResource1.setProjectName("Project ABC");
        requestResource1.setComment("This is a comment.");

        requestResource2.setId(1L);
        requestResource2.setEmployeeId(101L);
        requestResource2.setEmployeeEmpId("EMP001");
        requestResource2.setProjectId(201L);
        requestResource2.setManagerId(301L);
        requestResource2.setManagerEmpId("MGR001");
        requestResource2.setEmployeeName("John Doe");
        requestResource2.setManagerName("Jane Smith");
        requestResource2.setProjectName("Project ABC");
        requestResource2.setComment("This is a comment.");

        assertTrue(requestResource1.equals(requestResource2));

        // Test equals with null values (similar to null tests for setters)
        requestResource1.setId(null);
        requestResource1.setEmployeeId(null);
        requestResource1.setEmployeeEmpId(null);
        requestResource1.setProjectId(null);
        requestResource1.setManagerId(null);
        requestResource1.setManagerEmpId(null);
        requestResource1.setEmployeeName(null);
        requestResource1.setManagerName(null);
        requestResource1.setProjectName(null);
        requestResource1.setComment(null);

        requestResource2.setId(null);
        requestResource2.setEmployeeId(null);
        requestResource2.setEmployeeEmpId(null);
        requestResource2.setProjectId(null);
        requestResource2.setManagerId(null);
        requestResource2.setManagerEmpId(null);
        requestResource2.setEmployeeName(null);
        requestResource2.setManagerName(null);
        requestResource2.setProjectName(null);
        requestResource2.setComment(null);

        assertTrue(requestResource1.equals(requestResource2));
    }

    @Test
    public void testToString() {
        // Test toString with non-null values (similar to previous test)
        requestResource.setId(1L);
        requestResource.setEmployeeId(101L);
        requestResource.setEmployeeEmpId("EMP001");
        requestResource.setProjectId(201L);
        requestResource.setManagerId(301L);
        requestResource.setManagerEmpId("MGR001");
        requestResource.setEmployeeName("John Doe");
        requestResource.setManagerName("Jane Smith");
        requestResource.setProjectName("Project ABC");
        requestResource.setComment("This is a comment.");

        String expectedToString = "RequestResourceOutDto [id=1, employeeId=101, employeeEmpId=EMP001, projectId=201, managerId=301, managerEmpId=MGR001, employeeName=John Doe, managerName=Jane Smith, projectName=Project ABC, comment=This is a comment.]";
        assertEquals(expectedToString, requestResource.toString());

        // Test toString with null values (similar to null tests for setters)
        requestResource.setId(null);
        requestResource.setEmployeeId(null);
        requestResource.setEmployeeEmpId(null);
        requestResource.setProjectId(null);
        requestResource.setManagerId(null);
        requestResource.setManagerEmpId(null);
        requestResource.setEmployeeName(null);
        requestResource.setManagerName(null);
        requestResource.setProjectName(null);
        requestResource.setComment(null);

        String expectedToStringWithNulls = "RequestResourceOutDto [id=null, employeeId=null, employeeEmpId=null, projectId=null, managerId=null, managerEmpId=null, employeeName=null, managerName=null, projectName=null, comment=null]";
        assertEquals(expectedToStringWithNulls, requestResource.toString());
    }
}

