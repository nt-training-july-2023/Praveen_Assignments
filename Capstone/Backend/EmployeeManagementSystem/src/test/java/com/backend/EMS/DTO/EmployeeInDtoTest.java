package com.backend.EMS.DTO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Model.Role;

import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;

public class EmployeeInDtoTest {

    private EmployeeInDto employee;

    @BeforeEach
    public void setUp() {
        employee = new EmployeeInDto();
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
        employee.setLocation(Location.Canada);
        Assertions.assertEquals(Location.Canada, employee.getLocation());
    }

    @Test
    public void testGetAndSetDesignation() {
        employee.setDesignation(Designation.Architect);
        Assertions.assertEquals(Designation.Architect, employee.getDesignation());
    }

    @Test
    public void testGetAndSetContactNo() {
        employee.setContactNo(1234567890L);
        Assertions.assertEquals(1234567890L, employee.getContactNo());
    }

    @Test
    public void testGetAndSetPassword() {
        employee.setPassword("secret");
        Assertions.assertEquals("secret", employee.getPassword());
    }

    @Test
    public void testGetAndSetRole() {
        employee.setRole(Role.Admin);
        Assertions.assertEquals(Role.Admin, employee.getRole());
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
        EmployeeInDto employee1 = new EmployeeInDto();
        employee1.setId(1L);

        EmployeeInDto employee2 = new EmployeeInDto();
        employee2.setId(1L);

        EmployeeInDto employee3 = new EmployeeInDto();
        employee3.setId(2L);

        Assertions.assertEquals(employee1, employee2);
        Assertions.assertNotEquals(employee1, employee3);
    }

    @Test
    public void testHashCode() {
        EmployeeInDto employee1 = new EmployeeInDto();
        employee1.setId(1L);

        EmployeeInDto employee2 = new EmployeeInDto();
        employee2.setId(1L);

        EmployeeInDto employee3 = new EmployeeInDto();
        employee3.setId(2L);

        Assertions.assertEquals(employee1.hashCode(), employee2.hashCode());
        Assertions.assertNotEquals(employee1.hashCode(), employee3.hashCode());
    }
    @Test
    public void testToString() {
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setEmpId("N1234");
        employee.setDob("1990-01-01");
        employee.setDoj("2022-05-15");
        employee.setLocation(Location.Bangalore); // Use the enum constant
        employee.setDesignation(Designation.Architect); // Use the enum constant
        employee.setContactNo(1234567890L);
        employee.setPassword("secret");
        employee.setRole(Role.Admin); // Use the enum constant

        String expectedString = "EmployeeInDto [id=1, name=John Doe, email=john.doe@example.com, empId=N1234, dob=1990-01-01, doj=2022-05-15, location=Bangalore, designation=Architect, contactNo=1234567890, password=secret, role=Admin, skills=null]";
        Assertions.assertEquals(expectedString, employee.toString());
    }

}

