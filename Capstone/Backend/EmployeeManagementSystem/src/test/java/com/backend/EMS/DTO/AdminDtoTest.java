package com.backend.EMS.DTO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Location;

class AdminDtoTest {
    private AdminDto adminDto;

    @BeforeEach
    public void setUp() {
        adminDto = new AdminDto();
    }

    @Test
    void testGetName() {
        adminDto.setName("John");
        assertEquals("John", adminDto.getName());
    }

    @Test
    void testSetName() {
        adminDto.setName("John");
        assertEquals("John", adminDto.getName());
    }

    @Test
    void testGetEmail() {
        adminDto.setEmail("john@example.com");
        assertEquals("john@example.com", adminDto.getEmail());
    }

    @Test
    void testSetEmail() {
        adminDto.setEmail("john@example.com");
        assertEquals("john@example.com", adminDto.getEmail());
    }

    @Test
    void testGetEmpId() {
        adminDto.setEmpId("EMP123");
        assertEquals("EMP123", adminDto.getEmpId());
    }

    @Test
    void testSetEmpId() {
        adminDto.setEmpId("EMP123");
        assertEquals("EMP123", adminDto.getEmpId());
    }



    @Test
    void testGetdob() {
        adminDto.setDob("1990-01-01");
        assertEquals("1990-01-01", adminDto.getDob());
    }

    @Test
    void testSetdob() {
        adminDto.setDob("1990-01-01");
        assertEquals("1990-01-01", adminDto.getDob());
    }

    @Test
    void testGetDoj() {
        adminDto.setDoj("2021-01-01");
        assertEquals("2021-01-01", adminDto.getDoj());
    }

    @Test
    void testSetDoj() {
        adminDto.setDoj("2021-01-01");
        assertEquals("2021-01-01", adminDto.getDoj());
    }

    @Test
    void testGetLocation() {
        adminDto.setLocation(Location.Indore);
        assertEquals(Location.Indore, adminDto.getLocation());
    }

    @Test
    void testSetLocation() {
        adminDto.setLocation(Location.Canda);
        assertEquals(Location.Canda, adminDto.getLocation());
    }

    @Test
    void testGetDesignation() {
        adminDto.setDesignation(Designation.Engineer);
        assertEquals(Designation.Engineer, adminDto.getDesignation());
    }

    @Test
    void testSetDesignation() {
        adminDto.setDesignation(Designation.Engineer);
        assertEquals(Designation.Engineer, adminDto.getDesignation());
    }

    @Test
    void testGetContactNo() {
        adminDto.setContactNo(1234567890L);
        assertEquals(1234567890L, adminDto.getContactNo());
    }

    @Test
    void testSetContactNo() {
        adminDto.setContactNo(1234567890L);
        assertEquals(1234567890L, adminDto.getContactNo());
    }

    @Test
    void testGetPassword() {
        adminDto.setPassword("myPassword");
        assertEquals("myPassword", adminDto.getPassword());
    }

    @Test
    void testSetPassword() {
        adminDto.setPassword("myPassword");
        assertEquals("myPassword", adminDto.getPassword());
    }

    @Test
    void testGetConfirmPassword() {
        adminDto.setConfirmPassword("myPassword");
        assertEquals("myPassword", adminDto.getConfirmPassword());
    }

    @Test
    void testSetConfirmPassword() {
        adminDto.setConfirmPassword("myPassword");
        assertEquals("myPassword", adminDto.getConfirmPassword());
    }

//    @Test
//    void testToString() {
//        adminDto.setName("Praveen");
//        adminDto.setEmail("Praveen@example.com");
//        adminDto.setEmpId("EMP123");
//        adminDto.setDob("1990-01-01");
//        adminDto.setDoj("2021-01-01");
//        adminDto.setLocation("New York");
//        adminDto.setDesignation("Manager");
//        adminDto.setContactNo(1234567890L);
//        adminDto.setPassword("myPassword");
//        adminDto.setConfirmPassword("myPassword");
//
//        String expectedString = "AdminDto [name=Praveen, email=Praveen@example.com, empId=EMP123, dob=1990-01-01, " +
//                "doj=2021-01-01, location=New York, designation=Manager, contactNo=1234567890, " +
//                "password=myPassword, confirmPassword=myPassword]";
//        assertEquals(expectedString, adminDto.toString());
//    }
}
