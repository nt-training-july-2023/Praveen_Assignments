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
    public void testGetterAndSetterMethods() {
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmail("john.doe@nucleusteq.com");
        employee.setEmpId("N1234");
        employee.setDob("1990-01-01");
        employee.setDoj("2020-01-01");
        employee.setLocation(Location.Raipur);
        employee.setDesignation(Designation.Engineer);
        employee.setContactNo(1234567890L);
        employee.setPassword("securePassword");
        employee.setRole(Role.Admin);
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Spring Boot");
        employee.setSkills(skills);

        assertEquals(1L, employee.getId());
        assertEquals("John Doe", employee.getName());
        assertEquals("john.doe@nucleusteq.com", employee.getEmail());
        assertEquals("N1234", employee.getEmpId());
        assertEquals("1990-01-01", employee.getDob());
        assertEquals("2020-01-01", employee.getDoj());
        assertEquals(Location.Raipur, employee.getLocation());
        assertEquals(Designation.Engineer, employee.getDesignation());
        assertEquals(1234567890L, employee.getContactNo());
        assertEquals("securePassword", employee.getPassword());
        assertEquals(Role.Admin, employee.getRole());
        assertEquals(2, employee.getSkills().size());
        assertEquals("Java", employee.getSkills().get(0));
        assertEquals("Spring Boot", employee.getSkills().get(1));

        employee.setId(null);
        employee.setName(null);
        employee.setEmail(null);
        employee.setEmpId(null);
        employee.setDob(null);
        employee.setDoj(null);
        employee.setLocation(null);
        employee.setDesignation(null);
        employee.setContactNo(null);
        employee.setPassword(null);
        employee.setRole(null);
        employee.setSkills(new ArrayList());

        assertNull(employee.getId());
        assertNull(employee.getName());
        assertNull(employee.getEmail());
        assertNull(employee.getEmpId());
        assertNull(employee.getDob());
        assertNull(employee.getDoj());
        assertNull(employee.getLocation());
        assertNull(employee.getDesignation());
        assertNull(employee.getContactNo());
        assertNull(employee.getPassword());
        assertNull(employee.getRole());
        assertNull(employee.getSkills());
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
        employee.setLocation(Location.Bangalore); 
        employee.setDesignation(Designation.Architect); 
        employee.setContactNo(1234567890L);
        employee.setPassword("secret");
        employee.setRole(Role.Admin);

        String expectedString = "EmployeeInDto [id=1, name=John Doe, email=john.doe@example.com, empId=N1234, dob=1990-01-01, doj=2022-05-15, location=Bangalore, designation=Architect, contactNo=1234567890, password=secret, role=Admin, skills=null]";
        Assertions.assertEquals(expectedString, employee.toString());
    }

}

