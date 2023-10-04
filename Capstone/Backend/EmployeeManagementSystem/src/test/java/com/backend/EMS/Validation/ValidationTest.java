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
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmail("existing@example.com");
        
        when(employeeRepository.findByEmail("existing@example.com")).thenReturn(new Employee());

        try {
            validation.checkEmailExists(employeeInDto);
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Email id already exists", e.getMessage());
        }
        
        verify(employeeRepository, times(1)).findByEmail("existing@example.com");
    }

    @Test
    public void testCheckEmailExistsWhenEmailDoesNotExist() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmail("new@example.com");
        
        when(employeeRepository.findByEmail("new@example.com")).thenReturn(null);

        try {
            validation.checkEmailExists(employeeInDto);
        } catch (UserAlreadyFound e) {
            fail("Unexpected UserAlreadyFound exception");
        }
        verify(employeeRepository, times(1)).findByEmail("new@example.com");
    }
    
    @Test
    public void testCheckAdminExistsWhenAdminExists() {
        List<Employee> adminList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setRole(Role.Admin);
        adminList.add(employee);
        
        when(employeeRepository.findByRole(Role.Admin)).thenReturn(adminList);

        try {
            validation.checkAdminExists();
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Admin Already Exists", e.getMessage());
        }
        
        verify(employeeRepository, times(1)).findByRole(Role.Admin);
    }

    @Test
    public void testCheckAdminExistsWhenAdminDoesNotExist() {
        when(employeeRepository.findByRole(Role.Admin)).thenReturn(new ArrayList<>());

        try {
            validation.checkAdminExists();
        } catch (UserAlreadyFound e) {
            fail("Unexpected UserAlreadyFound exception");
        }
        verify(employeeRepository, times(1)).findByRole(Role.Admin);
    }
    @Test
    public void testCheckRoleExistsWhenRoleExists() {
        String validRole = "Employee";
        try {
            validation.checkRoleExists(validRole);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckRoleExistsWhenRoleDoesNotExist() {
        String invalidRole = "InvalidRole";

        try {
            validation.checkRoleExists(invalidRole);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("Role not found", e.getMessage());
        }
    }

    @Test
    public void testCheckEmployeeIdExistsWhenEmployeeIdExists() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmpId("existingEmpId");
        
        when(employeeRepository.findByEmpId("existingEmpId")).thenReturn(new Employee());
        try {
            validation.checkEmployeeIdExists(employeeInDto);
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Employee id already exists", e.getMessage());
        }
    }

    @Test
    public void testCheckEmployeeIdExistsWhenEmployeeIdDoesNotExist() {
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
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setContactNo(1234567890L);
        
        when(employeeRepository.findByContactNo(1234567890L)).thenReturn(new Employee());

        try {
            validation.checkContactNumberExists(employeeInDto);
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Contact Number already exists", e.getMessage());
        }
    }

    @Test
    public void testCheckContactNumberExistsWhenContactNumberDoesNotExist() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setContactNo(1234567890L);
        
        when(employeeRepository.findByContactNo(1234567890L)).thenReturn(null);

        try {
            validation.checkContactNumberExists(employeeInDto);
        } catch (UserAlreadyFound e) {
            fail("Unexpected UserAlreadyFound exception");
        }
    }

    @Test
    public void testCheckProjectWhenProjectExists() {
        ProjectInDto projectInDto = new ProjectInDto();
        projectInDto.setProjectName("existingProject");
        
        when(projectRepository.findByProjectName("existingProject")).thenReturn(new Project());
        try {
            validation.checkProject(projectInDto);
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Project already exists", e.getMessage());
        }
    }

    @Test
    public void testCheckProjectWhenProjectDoesNotExist() {
        ProjectInDto projectInDto = new ProjectInDto();
        projectInDto.setProjectName("newProject");
        
        when(projectRepository.findByProjectName("newProject")).thenReturn(null);

        try {
            validation.checkProject(projectInDto);
        } catch (UserAlreadyFound e) {
            fail("Unexpected UserAlreadyFound exception");
        }
    }
    @Test
    public void testCheckLoginWithInvalidCredentials() {
        LoginInDto loginInDto = new LoginInDto();
        loginInDto.setEmail("existing@example.com");
        loginInDto.setPassword("invalidEncodedPassword");
        
        Employee existingEmployee = new Employee();
        existingEmployee.setPassword("encodedPassword");
        when(employeeRepository.findByEmail("existing@example.com")).thenReturn(existingEmployee);
        when(passwordEncoder.matches("decodedPassword", "encodedPassword")).thenReturn(false);
        try {
            validation.checkLogin(loginInDto);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("Invalid Password", e.getMessage());
        }
    }

    @Test
    public void testCheckAdminWithExistingAdmin() {
        when(employeeRepository.findByRole(Role.Admin)).thenReturn(Collections.singletonList(new Employee()));

        try {
            validation.checkAdmin(new EmployeeInDto());
            fail("Expected UserAlreadyFound exception");
        } catch (UserAlreadyFound e) {
            assertEquals("Admin Already Exists", e.getMessage());
        }
    }
    @Test
    public void testPatterValidationsWithoutValidationErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        try {
        } catch (CustomException e) {
            fail("Unexpected CustomException");
        }
    }

    @Test
    public void testCheckEmployeeExistsWithExistingEmployee() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(new Employee()));
        try {
            validation.checkEmployeeExists(1L);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }
    @Test
    public void testCheckEmployeeExistsWithNonExistingEmployee() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            validation.checkEmployeeExists(1L);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("employee not exists", e.getMessage());
        }
    }

    @Test
    public void testCheckProjectExistsWithExistingProject() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(new Project()));
        try {
            validation.checkProjectExists(1L);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckProjectExistsWithNonExistingProject() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            validation.checkProjectExists(1L);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("Project Not Found", e.getMessage());
        }
    }

    @Test
    public void testCheckManagerExistsWithExistingManager() {
        Employee manager = new Employee();
        manager.setRole(Role.Manager);
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(manager));

        try {
            validation.checkManagerExists(1L);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckManagerExistsWithNonExistingManager() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            validation.checkManagerExists(1L);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("Employee not exists", e.getMessage());
        }
    }

    @Test
    public void testCheckRequestResourceExistsWithExistingResource() {
        when(requestResourceRepository.findById(anyLong())).thenReturn(Optional.of(new RequestResource()));

        try {
            validation.checkRequestResourceExists(1L);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckRequestResourceExistsWithNonExistingResource() {
        when(requestResourceRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            validation.checkRequestResourceExists(1L);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("RequestResource not Exists", e.getMessage());
        }
    }

    @Test
    public void testCheckOnlyEmployeeExistsWithExistingEmployee() {
        Employee employee = new Employee();
        employee.setRole(Role.Employee);
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        try {
            validation.checkOnlyEmployeeExists(1L);
        } catch (UserNotFound e) {
            fail("Unexpected UserNotFound exception");
        }
    }

    @Test
    public void testCheckOnlyEmployeeExistsWithNonExistingEmployee() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            validation.checkOnlyEmployeeExists(1L);
            fail("Expected UserNotFound exception");
        } catch (UserNotFound e) {
            assertEquals("employee not exists", e.getMessage());
        }
    }


}
