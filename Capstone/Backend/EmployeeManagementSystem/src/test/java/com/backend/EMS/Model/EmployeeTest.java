package com.backend.EMS.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {
    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
    }
    

	@Test
	void testGetId() {
		  employee.setId(1L);
	      assertEquals(1L, employee.getId());
	}

	@Test
	void testSetId() {
		  employee.setId(1L);
	      assertEquals(1L, employee.getId());
//	      assertThrows(IllegalArgumentException.class, () -> admin.setEmpId(null));
		
	}
	

    @Test
    void testGetName() {
        employee.setName("Praveen");
        assertEquals("Praveen", employee.getName());
    }

    @Test
    void testSetName() {
        employee.setName("Praveen");
        assertEquals("Praveen", employee.getName());
    }

    @Test
    void testGetEmail() {
        employee.setEmail("Praveen@example.com");
        assertEquals("Praveen@example.com", employee.getEmail());
    }

    @Test
    void testSetEmail() {
        employee.setEmail("Praveen@example.com");
        assertEquals("Praveen@example.com", employee.getEmail());
    }

    @Test
    void testGetEmpId() {
        employee.setEmpId("N1123");
        assertEquals("N1123", employee.getEmpId());
    }

    @Test
    void testSetEmpId() {
        employee.setEmpId("N1123");
        assertEquals("N1123", employee.getEmpId());
    }


    @Test
    void testGetdob() {
        employee.setDob("1990-01-01");
        assertEquals("1990-01-01", employee.getDob());
    }

    @Test
    void testSetdob() {
        employee.setDob("1990-01-01");
        assertEquals("1990-01-01", employee.getDob());
    }

    @Test
    void testGetDoj() {
        employee.setDoj("2021-01-01");
        assertEquals("2021-01-01", employee.getDoj());
    }

    @Test
    void testSetDoj() {
        employee.setDoj("2021-01-01");
        assertEquals("2021-01-01", employee.getDoj());
    }

    @Test
    void testGetLocation() {
        employee.setLocation(Location.Raipur);
        assertEquals(Location.Raipur, employee.getLocation());
    }

    @Test
    void testSetLocation() {
        employee.setLocation(Location.Raipur);
        assertEquals(Location.Raipur, employee.getLocation());
    }

    @Test
    void testGetDesignation() {
        employee.setDesignation(Designation.Engineer);
        assertEquals(Designation.Engineer, employee.getDesignation());
    }

    @Test
    void testSetDesignation() {
        employee.setDesignation(Designation.Engineer);
        assertEquals(Designation.Engineer, employee.getDesignation());
    }

    @Test
    void testGetContactNo() {
        employee.setContactNo(1234567890L);
        assertEquals(1234567890L, employee.getContactNo());
    }

    @Test
    void testSetContactNo() {
        employee.setContactNo(1234567890L);
        assertEquals(1234567890L, employee.getContactNo());
    }

    @Test
    void testGetPassword() {
        employee.setPassword("myPassword");
        assertEquals("myPassword", employee.getPassword());
    }

    @Test
    void testSetPassword() {
        employee.setPassword("myPassword");
        assertEquals("myPassword", employee.getPassword());
    }
}
