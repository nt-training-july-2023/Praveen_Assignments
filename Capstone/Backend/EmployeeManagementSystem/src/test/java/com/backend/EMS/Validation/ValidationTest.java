package com.backend.EMS.Validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.ProjectInDto;
import com.backend.EMS.Exception.CustomException;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Exception.UserNotFound;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Model.RequestResource;
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
    @Mock
    private PasswordEncoder passwordEncoder;
    
    
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
    @Test
    public void testCheckRoleExistsWhenRoleExists() {
        // Arrange
        String validRole = "Employee";

        // Act and Assert
        try {
            validation.checkRoleExists(validRole);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckRoleExistsWhenRoleDoesNotExist() {
        // Arrange
        String invalidRole = "InvalidRole";

        // Act
        try {
            validation.checkRoleExists(invalidRole);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("Role not found", e.getMessage());
        }
    }

    @Test
    public void testCheckEmployeeIdExistsWhenEmployeeIdExists() {
        // Arrange
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmpId("existingEmpId");
        
        when(employeeRepository.findByEmpId("existingEmpId")).thenReturn(new Employee());

        // Act and Assert
        try {
            validation.checkEmployeeIdExists(employeeInDto);
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Employee id already exists", e.getMessage());
        }
    }

    @Test
    public void testCheckEmployeeIdExistsWhenEmployeeIdDoesNotExist() {
        // Arrange
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmpId("newEmpId");
        
        when(employeeRepository.findByEmpId("newEmpId")).thenReturn(null);

        // Act
        try {
            validation.checkEmployeeIdExists(employeeInDto);
        } catch (UserAlreadyFound e) {
            fail("Unexpected UserAlreadyFound exception");
        }
    }

    @Test
    public void testCheckContactNumberExistsWhenContactNumberExists() {
        // Arrange
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setContactNo(1234567890L);
        
        when(employeeRepository.findByContactNo(1234567890L)).thenReturn(new Employee());

        // Act and Assert
        try {
            validation.checkContactNumberExists(employeeInDto);
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Contact Number already exists", e.getMessage());
        }
    }

    @Test
    public void testCheckContactNumberExistsWhenContactNumberDoesNotExist() {
        // Arrange
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setContactNo(1234567890L);
        
        when(employeeRepository.findByContactNo(1234567890L)).thenReturn(null);

        // Act
        try {
            validation.checkContactNumberExists(employeeInDto);
        } catch (UserAlreadyFound e) {
            fail("Unexpected UserAlreadyFound exception");
        }
    }

    @Test
    public void testCheckProjectWhenProjectExists() {
        // Arrange
        ProjectInDto projectInDto = new ProjectInDto();
        projectInDto.setProjectName("existingProject");
        
        when(projectRepository.findByProjectName("existingProject")).thenReturn(new Project());

        // Act and Assert
        try {
            validation.checkProject(projectInDto);
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Project already exists", e.getMessage());
        }
    }

    @Test
    public void testCheckProjectWhenProjectDoesNotExist() {
        // Arrange
        ProjectInDto projectInDto = new ProjectInDto();
        projectInDto.setProjectName("newProject");
        
        when(projectRepository.findByProjectName("newProject")).thenReturn(null);

        // Act
        try {
            validation.checkProject(projectInDto);
        } catch (UserAlreadyFound e) {
            fail("Unexpected UserAlreadyFound exception");
        }
    }

//    @Test
//    public void testCheckEmailNotExistsWhenEmailExists() {
//        // Arrange
//        LoginInDto loginInDto = new LoginInDto();
//        loginInDto.setEmail("existing@example.com");
//        
//        when(employeeRepository.findByEmail("existing@example.com")).thenReturn(new Employee());
//
//        // Act and Assert
//        try {
//            validation.checkEmailNotExists(loginInDto);
//            fail("Expected UserNotFound exception");
//        } catch (UserNotFound e) {
//            assertEquals("Invalid Email", e.getMessage());
//        }
//    }

//    @Test
//    public void testCheckEmailNotExistsWhenEmailDoesNotExist() {
//        // Arrange
//        LoginInDto loginInDto = new LoginInDto();
//        loginInDto.setEmail("new@example.com");
//        
//        when(employeeRepository.findByEmail("new@example.com")).thenReturn(null);
//
//        // Act
//        try {
//            validation.checkEmailNotExists(loginInDto);
//        } catch (UserNotFound e) {
//            fail("Unexpected UserNotFound exception");
//        }
//    }

//    @Test
//    public void testCheckLoginWithValidCredentials() {
//        // Arrange
//        LoginInDto loginInDto = new LoginInDto();
//        loginInDto.setEmail("existing@example.com");
//        loginInDto.setPassword("encodedPassword");
//        
//        Employee existingEmployee = new Employee();
//        existingEmployee.setPassword("encodedPassword");
//        
//        when(employeeRepository.findByEmail("existing@example.com")).thenReturn(existingEmployee);
//        when(passwordEncoder.matches("decodedPassword", "encodedPassword")).thenReturn(true);
//
//        // Act
//        try {
//            validation.checkLogin(loginInDto);
//        } catch (UserNotFound e) {
//            fail("Unexpected UserNotFound exception");
//        }
//    }

    @Test
    public void testCheckLoginWithInvalidCredentials() {
        // Arrange
        LoginInDto loginInDto = new LoginInDto();
        loginInDto.setEmail("existing@example.com");
        loginInDto.setPassword("invalidEncodedPassword");
        
        Employee existingEmployee = new Employee();
        existingEmployee.setPassword("encodedPassword");
        
        when(employeeRepository.findByEmail("existing@example.com")).thenReturn(existingEmployee);
        when(passwordEncoder.matches("decodedPassword", "encodedPassword")).thenReturn(false);

        // Act
        try {
            validation.checkLogin(loginInDto);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("Invalid Password", e.getMessage());
        }
    }

    @Test
    public void testCheckAdminWithExistingAdmin() {
        // Arrange
        when(employeeRepository.findByRole(Role.Admin)).thenReturn(Collections.singletonList(new Employee()));

        // Act and Assert
        try {
            validation.checkAdmin(new EmployeeInDto());
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Admin Already Exists", e.getMessage());
        }
    }

//    @Test
//    public void testCheckAdminWithNonExistingAdmin() {
//        // Arrange
//        when(employeeRepository.findByRole(Role.Admin)).thenReturn(Collections.emptyList());
//
//        // Act
//        try {
//            validation.checkAdmin(new EmployeeInDto());
//        } catch (UserAlreadyFound e) {
//            fail("Unexpected UserAlreadyFound exception");
//        }
//    }

//    @Test
//    public void testCheckEmployeeWithExistingEmployee() {
//        // Arrange
//        when(employeeRepository.findByEmail(anyString())).thenReturn(new Employee());
//        when(employeeRepository.findByEmpId(anyString())).thenReturn(new Employee());
//        when(employeeRepository.findByContactNo(1L)).thenReturn(new Employee());
//
//        // Act
//        try {
//            validation.checkEmployee(new EmployeeInDto());
//        } catch (UserAlreadyFound e) {
//            fail("Unexpected UserAlreadyFound exception");
//        }
//    }

//    @Test
//    public void testCheckEmployeeWithNonExistingEmployee() {
//        // Arrange
//        when(employeeRepository.findByEmail(anyString())).thenReturn(null);
//        when(employeeRepository.findByEmpId(anyString())).thenReturn(null);
//        when(employeeRepository.findByContactNo(1L)).thenReturn(null);
//
//        // Act
//        try {
//            validation.checkEmployee(new EmployeeInDto());
//        } catch (UserAlreadyFound e) {
//            fail("Unexpected UserAlreadyFound exception");
//        }
//    }

//    @Test
//    public void testPatterValidationsWithValidationErrors() {
//        // Arrange
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(bindingResult.hasErrors()).thenReturn(true);
//
//        // Act
//        try {
//            validation.patterValidations(bindingResult);
//            fail("Expected CustomException");
//        } catch (CustomException e) {
//            assertTrue(e.getMessage().contains("Validation errors"));
//        }
//    }

    @Test
    public void testPatterValidationsWithoutValidationErrors() {
        // Arrange
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        // Act
        try {
        } catch (CustomException e) {
            fail("Unexpected CustomException");
        }
    }

    @Test
    public void testCheckEmployeeExistsWithExistingEmployee() {
        // Arrange
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(new Employee()));

        // Act
        try {
            validation.checkEmployeeExists(1L);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckEmployeeExistsWithNonExistingEmployee() {
        // Arrange
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        try {
            validation.checkEmployeeExists(1L);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("employee not exists", e.getMessage());
        }
    }

    @Test
    public void testCheckProjectExistsWithExistingProject() {
        // Arrange
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(new Project()));

        // Act
        try {
            validation.checkProjectExists(1L);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckProjectExistsWithNonExistingProject() {
        // Arrange
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        try {
            validation.checkProjectExists(1L);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("Project Not Found", e.getMessage());
        }
    }

    @Test
    public void testCheckManagerExistsWithExistingManager() {
        // Arrange
        Employee manager = new Employee();
        manager.setRole(Role.Manager);
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(manager));

        // Act
        try {
            validation.checkManagerExists(1L);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckManagerExistsWithNonExistingManager() {
        // Arrange
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        try {
            validation.checkManagerExists(1L);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("Employee not exists", e.getMessage());
        }
    }

    @Test
    public void testCheckRequestResourceExistsWithExistingResource() {
        // Arrange
        when(requestResourceRepository.findById(anyLong())).thenReturn(Optional.of(new RequestResource()));

        // Act
        try {
            validation.checkRequestResourceExists(1L);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckRequestResourceExistsWithNonExistingResource() {
        // Arrange
        when(requestResourceRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        try {
            validation.checkRequestResourceExists(1L);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("RequestResource not Exists", e.getMessage());
        }
    }

    @Test
    public void testCheckOnlyEmployeeExistsWithExistingEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setRole(Role.Employee);
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        // Act
        try {
            validation.checkOnlyEmployeeExists(1L);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckOnlyEmployeeExistsWithNonExistingEmployee() {
        // Arrange
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        try {
            validation.checkOnlyEmployeeExists(1L);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("employee not exists", e.getMessage());
        }
    }


}
