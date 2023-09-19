package com.backend.EMS.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeNameDtoTest {

    private EmployeeNameDto employeeNameDto;

    @BeforeEach
    public void setUp() {
        employeeNameDto = new EmployeeNameDto();
    }

    @Test
    public void testConstructorWithName() {
        String name = "John Doe";
        employeeNameDto = new EmployeeNameDto(name);
        Assertions.assertEquals(name, employeeNameDto.getName());
    }

    @Test
    public void testGetName() {
        String name = "Jane Smith";
        employeeNameDto.setName(name);
        Assertions.assertEquals(name, employeeNameDto.getName());
    }

    @Test
    public void testSetName() {
        String name = "Alice Johnson";
        employeeNameDto.setName(name);
        Assertions.assertEquals(name, employeeNameDto.getName());
    }

    @Test
    public void testHashCode() {
        String name1 = "John Doe";
        String name2 = "John Doe";
        employeeNameDto.setName(name1);
        EmployeeNameDto other = new EmployeeNameDto(name2);

        Assertions.assertEquals(employeeNameDto.hashCode(), other.hashCode());
    }

    @Test
    public void testEquals() {
        String name1 = "John Doe";
        String name2 = "John Doe";
        employeeNameDto.setName(name1);
        EmployeeNameDto other = new EmployeeNameDto(name2);

        Assertions.assertTrue(employeeNameDto.equals(other));
    }

    @Test
    public void testNotEquals() {
        String name1 = "John Doe";
        String name2 = "Jane Smith";
        employeeNameDto.setName(name1);
        EmployeeNameDto other = new EmployeeNameDto(name2);

        Assertions.assertFalse(employeeNameDto.equals(other));
    }

    @Test
    public void testToString() {
        String name = "John Doe";
        employeeNameDto.setName(name);
        String expectedString = "EmployeeNameDto [name=" + name + "]";
        Assertions.assertEquals(expectedString, employeeNameDto.toString());
    }
}
