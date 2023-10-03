package com.backend.EMS.Validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.EmployeeRepository;
import com.backend.EMS.Repository.ProjectRepository;
import com.backend.EMS.Repository.RequestResourceRepository;
import com.backend.EMS.Service.EmployeeService;

class ValidationTest {
    @InjectMocks
    private Validation validation;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private RequestResourceRepository requestResourceRepository;

    @Mock
    private ProjectRepository projectRepository;
    
    @Mock
    private EmployeeService employeeService;
    
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckEmailExistsWhenEmailExists() {
        // Arrange
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmail("existing@example.com");
        
        // Mock the behavior of findByEmail to return a non-null result
        when(employeeRepository.findByEmail("existing@example.com")).thenReturn(new Employee());

        // Act and Assert
        try {
            validation.checkEmailExists(employeeInDto);
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Email id already exists", e.getMessage());
        }
        
        // Verify that findByEmail was called with the correct email
        verify(employeeRepository, times(1)).findByEmail("existing@example.com");
    }

    @Test
    public void testCheckEmailExistsWhenEmailDoesNotExist() {
        // Arrange
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmail("new@example.com");
        
        // Mock the behavior of findByEmail to return null (email doesn't exist)
        when(employeeRepository.findByEmail("new@example.com")).thenReturn(null);

        // Act
        try {
            validation.checkEmailExists(employeeInDto);
        } catch (UserAlreadyFound e) {
            fail("Unexpected UserAlreadyFound exception");
        }
        
        // Verify that findByEmail was called with the correct email
        verify(employeeRepository, times(1)).findByEmail("new@example.com");
    }
    
    @Test
    public void testCheckAdminExistsWhenAdminExists() {
        // Arrange
        // Create a sample list with one Admin
        List<Employee> adminList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setRole(Role.Admin);
        adminList.add(employee);
        
        // Mock the behavior of findByRole to return the adminList
        when(employeeRepository.findByRole(Role.Admin)).thenReturn(adminList);

        // Act and Assert
        try {
            validation.checkAdminExists();
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Admin Already Exists", e.getMessage());
        }
        
        // Verify that findByRole was called with the correct role
        verify(employeeRepository, times(1)).findByRole(Role.Admin);
    }

    @Test
    public void testCheckAdminExistsWhenAdminDoesNotExist() {
        // Arrange
        // Mock the behavior of findByRole to return an empty list (no Admin)
        when(employeeRepository.findByRole(Role.Admin)).thenReturn(new ArrayList<>());

        // Act
        try {
            validation.checkAdminExists();
        } catch (UserAlreadyFound e) {
            fail("Unexpected UserAlreadyFound exception");
        }
        
        // Verify that findByRole was called with the correct role
        verify(employeeRepository, times(1)).findByRole(Role.Admin);
    }



}
