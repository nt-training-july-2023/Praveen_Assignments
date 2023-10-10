package com.backend.EMS.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @Test
    public void testHashCode() {
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("Praveen");
        employee1.setEmail("praveen@nucleusteq.com");
        employee1.setEmpId("N1234");
        employee1.setDob("1990-01-01");
        employee1.setDoj("2020-01-01");
        employee1.setLocation(Location.Raipur);
        employee1.setDesignation(Designation.Engineer);
        employee1.setContactNo(1234567890L);
        employee1.setPassword("password");
        employee1.setRole(Role.Employee);

        Employee employee2 = new Employee();
        employee2.setId(1);
        employee2.setName("Praveen");
        employee2.setEmail("praveen@nucleusteq.com");
        employee2.setEmpId("N1234");
        employee2.setDob("1990-01-01");
        employee2.setDoj("2020-01-01");
        employee2.setLocation(Location.Raipur);
        employee2.setDesignation(Designation.Engineer);
        employee2.setContactNo(1234567890L);
        employee2.setPassword("password");
        employee2.setRole(Role.Employee);

        assertEquals(employee1.hashCode(), employee2.hashCode());
    }
    @Test
    public void testEquals() {
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("Praveen");
        employee1.setEmail("praveen@nucleusteq.com");
        employee1.setEmpId("N1234");
        employee1.setDob("1990-01-01");
        employee1.setDoj("2020-01-01");
        employee1.setLocation(Location.Raipur);
        employee1.setDesignation(Designation.Engineer);
        employee1.setContactNo(1234567890L);
        employee1.setPassword("password");
        employee1.setRole(Role.Employee);

        Employee employee2 = new Employee();
        employee2.setId(1);
        employee2.setId(1);
        employee2.setName("Praveen");
        employee2.setEmail("praveen@nucleusteq.com");
        employee2.setEmpId("N1234");
        employee2.setDob("1990-01-01");
        employee2.setDoj("2020-01-01");
        employee2.setLocation(Location.Raipur);
        employee2.setDesignation(Designation.Engineer);
        employee2.setContactNo(1234567890L);
        employee2.setPassword("password");
        employee2.setRole(Role.Employee);
        assertTrue(employee1.equals(employee2));
    }

    @Test
    public void testToString() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Praveen");
        employee.setEmail("praveen@nucleusteq.com");
        employee.setEmpId("N1234");
        employee.setDob("1990-01-01");
        employee.setDoj("2020-01-01");
        employee.setLocation(Location.Raipur);
        employee.setDesignation(Designation.Engineer);
        employee.setContactNo(1234567890L);
        employee.setPassword("password");
        employee.setRole(Role.Employee);

        String expectedToString = "Employee [id=1, name=Praveen, email=praveen@nucleusteq.com, empId=N1234, dob=1990-01-01, doj=2020-01-01, location=Raipur, designation=Engineer, contactNo=1234567890, password=password, role=Employee, skills=null, projectId=null]";
        String actualToString = employee.toString();

        assertEquals(expectedToString, actualToString);
    }
}
