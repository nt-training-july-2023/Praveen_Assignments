package com.backend.EMS.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.EMS.DTO.AdminDto;

class AdminTest {
    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin();
    }
    

	@Test
	void testGetId() {
		  admin.setId(1L);
	      assertEquals(1L, admin.getId());
	}

	@Test
	void testSetId() {
		  admin.setId(1L);
	      assertEquals(1L, admin.getId());
//	      assertThrows(IllegalArgumentException.class, () -> admin.setEmpId(null));
		
	}
	

    @Test
    void testGetName() {
        admin.setName("Praveen");
        assertEquals("Praveen", admin.getName());
    }

    @Test
    void testSetName() {
        admin.setName("Praveen");
        assertEquals("Praveen", admin.getName());
    }

    @Test
    void testGetEmail() {
        admin.setEmail("Praveen@example.com");
        assertEquals("Praveen@example.com", admin.getEmail());
    }

    @Test
    void testSetEmail() {
        admin.setEmail("Praveen@example.com");
        assertEquals("Praveen@example.com", admin.getEmail());
    }

    @Test
    void testGetEmpId() {
        admin.setEmpId("N1123");
        assertEquals("N1123", admin.getEmpId());
    }

    @Test
    void testSetEmpId() {
        admin.setEmpId("N1123");
        assertEquals("N1123", admin.getEmpId());
    }

//    @Test
//    void testSetEmpId_InvalidInput() {
//        assertThrows(IllegalArgumentException.class, () -> admin.setEmpId(null));
//    }

    @Test
    void testGetdob() {
        admin.setDob("1990-01-01");
        assertEquals("1990-01-01", admin.getDob());
    }

    @Test
    void testSetdob() {
        admin.setDob("1990-01-01");
        assertEquals("1990-01-01", admin.getDob());
    }

    @Test
    void testGetDoj() {
        admin.setDoj("2021-01-01");
        assertEquals("2021-01-01", admin.getDoj());
    }

    @Test
    void testSetDoj() {
        admin.setDoj("2021-01-01");
        assertEquals("2021-01-01", admin.getDoj());
    }

    @Test
    void testGetLocation() {
        admin.setLocation("New York");
        assertEquals("New York", admin.getLocation());
    }

    @Test
    void testSetLocation() {
        admin.setLocation("New York");
        assertEquals("New York", admin.getLocation());
    }

    @Test
    void testGetDesignation() {
        admin.setDesignation("Manager");
        assertEquals("Manager", admin.getDesignation());
    }

    @Test
    void testSetDesignation() {
        admin.setDesignation("Manager");
        assertEquals("Manager", admin.getDesignation());
    }

    @Test
    void testGetContactNo() {
        admin.setContactNo(1234567890L);
        assertEquals(1234567890L, admin.getContactNo());
    }

    @Test
    void testSetContactNo() {
        admin.setContactNo(1234567890L);
        assertEquals(1234567890L, admin.getContactNo());
    }

    @Test
    void testGetPassword() {
        admin.setPassword("myPassword");
        assertEquals("myPassword", admin.getPassword());
    }

    @Test
    void testSetPassword() {
        admin.setPassword("myPassword");
        assertEquals("myPassword", admin.getPassword());
    }

    @Test
    void testGetConfirmPassword() {
        admin.setConfirmPassword("myPassword");
        assertEquals("myPassword", admin.getConfirmPassword());
    }

    @Test
    void testSetConfirmPassword() {
        admin.setConfirmPassword("myPassword");
        assertEquals("myPassword", admin.getConfirmPassword());
    }

//    @Test
//    void testToString() {
//        admin.setName("Praveen");
//        admin.setEmail("Praveen@example.com");
//        admin.setEmpId("N1123");
//        admin.setDob("1990-01-01");
//        admin.setDoj("2021-01-01");
//        admin.setLocation("New York");
//        admin.setDesignation("Manager");
//        admin.setContactNo(1234567890L);
//        admin.setPassword("myPassword");
//        admin.setConfirmPassword("myPassword");
//
//        String expectedString = "AdminDto [name=Praveen, email=Praveen@example.com, empId=N1123, dob=1990-01-01, " +
//                "doj=2021-01-01, location=New York, designation=Manager, contactNo=1234567890, " +
//                "password=myPassword, confirmPassword=myPassword]";
//        assertEquals(expectedString, admin.toString());
//    }

}
