package com.backend.EMS.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Location;

import java.util.ArrayList;
import java.util.List;

public class EmployeeOutDtoTest {

    private EmployeeOutDto employee;

    @BeforeEach
    public void setUp() {
        employee = new EmployeeOutDto();
    }

    @Test
    public void testGetAndSetId() {
        employee.setId(1L);
        Assertions.assertEquals(1L, employee.getId());
    }

    @Test
    public void testGetAndSetName() {
        employee.setName("John Doe");
        Assertions.assertEquals("John Doe", employee.getName());
    }

    @Test
    public void testGetAndSetEmail() {
        employee.setEmail("john.doe@example.com");
        Assertions.assertEquals("john.doe@example.com", employee.getEmail());
    }

    @Test
    public void testGetAndSetEmpId() {
        employee.setEmpId("N1234");
        Assertions.assertEquals("N1234", employee.getEmpId());
    }

    @Test
    public void testGetAndSetDob() {
        employee.setDob("1990-01-01");
        Assertions.assertEquals("1990-01-01", employee.getDob());
    }

    @Test
    public void testGetAndSetDoj() {
        employee.setDoj("2022-05-15");
        Assertions.assertEquals("2022-05-15", employee.getDoj());
    }

    @Test
    public void testGetAndSetLocation() {
        
        employee.setLocation(Location.Bangalore);
        Assertions.assertEquals(Location.Bangalore, employee.getLocation());
    }

    @Test
    public void testGetAndSetDesignation() {
        employee.setDesignation(Designation.Engineer);
        Assertions.assertEquals(Designation.Engineer, employee.getDesignation());
    }

    @Test
    public void testGetAndSetContactNo() {
        employee.setContactNo(1234567890L);
        Assertions.assertEquals(1234567890L, employee.getContactNo());
    }

    @Test
    public void testGetAndSetManagerId() {
        employee.setManagerId(2L);
        Assertions.assertEquals(2L, employee.getManagerId());
    }

    @Test
    public void testGetAndSetProjectId() {
        employee.setProjectId(1001L);
        Assertions.assertEquals(1001L, employee.getProjectId());
    }

    @Test
    public void testGetAndSetManagerName() {
        employee.setManagerName("Manager Name");
        Assertions.assertEquals("Manager Name", employee.getManagerName());
    }

    @Test
    public void testGetAndSetProjectName() {
        employee.setProjectName("Project Name");
        Assertions.assertEquals("Project Name", employee.getProjectName());
    }

    @Test
    public void testGetAndSetSkills() {
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("SQL");
        employee.setSkills(skills);
        Assertions.assertEquals(skills, employee.getSkills());
    }

    @Test
    public void testEquals() {
        EmployeeOutDto employee1 = new EmployeeOutDto();
        employee1.setId(1L);

        EmployeeOutDto employee2 = new EmployeeOutDto();
        employee2.setId(1L);

        EmployeeOutDto employee3 = new EmployeeOutDto();
        employee3.setId(2L);

        Assertions.assertEquals(employee1, employee2);
        Assertions.assertNotEquals(employee1, employee3);
    }

    @Test
    public void testHashCode() {
        EmployeeOutDto employee1 = new EmployeeOutDto();
        employee1.setId(1L);

        EmployeeOutDto employee2 = new EmployeeOutDto();
        employee2.setId(1L);

        EmployeeOutDto employee3 = new EmployeeOutDto();
        employee3.setId(2L);

        Assertions.assertEquals(employee1.hashCode(), employee2.hashCode());
        Assertions.assertNotEquals(employee1.hashCode(), employee3.hashCode());
    }
}
