package com.backend.EMS.Validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import com.backend.EMS.Constants.ErrorConstants;
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

//    @Test
//    public void testCheckEmailExistsWhenEmailExists() {
//        EmployeeInDto employeeInDto = new EmployeeInDto();
//        employeeInDto.setEmail("existing@example.com");
//        
//        when(employeeRepository.findByEmail("existing@example.com")).thenReturn(new Employee());
//
//        try {
//            validation.checkEmailExists(employeeInDto);
//            fail("Expected UserAlreadyFound exception");
//        } catch (UserAlreadyFound e) {
//            assertEquals("Email id already exists", e.getMessage());
//        }
//        
//        verify(employeeRepository, times(1)).findByEmail("existing@example.com");
//    }
//
//    @Test
//    public void testCheckEmailExistsWhenEmailDoesNotExist() {
//        EmployeeInDto employeeInDto = new EmployeeInDto();
//        employeeInDto.setEmail("new@example.com");
//        
//        when(employeeRepository.findByEmail("new@example.com")).thenReturn(null);
//
//        try {
//            validation.checkEmailExists(employeeInDto);
//        } catch (UserAlreadyFound e) {
//            fail("Unexpected UserAlreadyFound exception");
//        }
//        verify(employeeRepository, times(1)).findByEmail("new@example.com");
//    }
//    
//    @Test
//    public void testCheckAdminExistsWhenAdminExists() {
//        List<Employee> adminList = new ArrayList<>();
//        Employee employee = new Employee();
//        employee.setRole(Role.Admin);
//        adminList.add(employee);
//        
//        when(employeeRepository.findByRole(Role.Admin)).thenReturn(adminList);
//
//        try {
//            validation.checkAdminExists();
//            fail("Expected UserAlreadyFound exception");
//        } catch (UserAlreadyFound e) {
//            assertEquals(ErrorConstants.ADMIN_FOUND, e.getMessage());
//        }
//        
//        verify(employeeRepository, times(1)).findByRole(Role.Admin);
//    }
//
//    @Test
//    public void testCheckAdminExistsWhenAdminDoesNotExist() {
//        when(employeeRepository.findByRole(Role.Admin)).thenReturn(new ArrayList<>());
//
//        try {
//            validation.checkAdminExists();
//        } catch (UserAlreadyFound e) {
//            fail("Unexpected UserAlreadyFound exception");
//        }
//        verify(employeeRepository, times(1)).findByRole(Role.Admin);
//    }
//    @Test
//    public void testCheckRoleExistsWhenRoleExists() {
//        String validRole = "Employee";
//        try {
//            validation.checkRoleExists(validRole);
//        } catch (UserNotFound e) {
//            fail("Unexpected UserNotFound exception");
//        }
//    }
//
//    @Test
//    public void testCheckRoleExistsWhenRoleDoesNotExist() {
//        String invalidRole = "InvalidRole";
//
//        try {
//            validation.checkRoleExists(invalidRole);
//            fail("Expected UserNotFound exception");
//        } catch (UserNotFound e) {
//            assertEquals(ErrorConstants.ROLE_NOT_FOUND, e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCheckEmployeeIdExistsWhenEmployeeIdExists() {
//        EmployeeInDto employeeInDto = new EmployeeInDto();
//        employeeInDto.setEmpId("existingEmpId");
//        
//        when(employeeRepository.findByEmpId("existingEmpId")).thenReturn(new Employee());
//        try {
//            validation.checkEmployeeIdExists(employeeInDto);
//            fail("Expected UserAlreadyFound exception");
//        } catch (UserAlreadyFound e) {
//            assertEquals(ErrorConstants.DUPLICATE_EMPID, e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCheckEmployeeIdExistsWhenEmployeeIdDoesNotExist() {
//        EmployeeInDto employeeInDto = new EmployeeInDto();
//        employeeInDto.setEmpId("newEmpId");
//        
//        when(employeeRepository.findByEmpId("newEmpId")).thenReturn(null);
//
//        // Act
//        try {
//            validation.checkEmployeeIdExists(employeeInDto);
//        } catch (UserAlreadyFound e) {
//            fail("Unexpected UserAlreadyFound exception");
//        }
//    }
//
//    @Test
//    public void testCheckContactNumberExistsWhenContactNumberExists() {
//        EmployeeInDto employeeInDto = new EmployeeInDto();
//        employeeInDto.setContactNo(1234567890L);
//        
//        when(employeeRepository.findByContactNo(1234567890L)).thenReturn(new Employee());
//
//        try {
//            validation.checkContactNumberExists(employeeInDto);
//            fail("Expected UserAlreadyFound exception");
//        } catch (UserAlreadyFound e) {
//            assertEquals(ErrorConstants.DUPLICATE_PHONE_NUMBER, e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCheckContactNumberExistsWhenContactNumberDoesNotExist() {
//        EmployeeInDto employeeInDto = new EmployeeInDto();
//        employeeInDto.setContactNo(1234567890L);
//        
//        when(employeeRepository.findByContactNo(1234567890L)).thenReturn(null);
//
//        try {
//            validation.checkContactNumberExists(employeeInDto);
//        } catch (UserAlreadyFound e) {
//            fail("Unexpected UserAlreadyFound exception");
//        }
//    }
//
//    @Test
//    public void testCheckProjectWhenProjectExists() {
//        ProjectInDto projectInDto = new ProjectInDto();
//        projectInDto.setProjectName("existingProject");
//        
//        when(projectRepository.findByProjectName("existingProject")).thenReturn(new Project());
//        try {
//            validation.checkProject(projectInDto);
//            fail("Expected UserAlreadyFound exception");
//        } catch (UserAlreadyFound e) {
//            assertEquals(ErrorConstants.DUPLICATE_PROJECT_NAME, e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCheckProjectWhenProjectDoesNotExist() {
//        ProjectInDto projectInDto = new ProjectInDto();
//        projectInDto.setProjectName("newProject");
//        
//        when(projectRepository.findByProjectName("newProject")).thenReturn(null);
//
//        try {
//            validation.checkProject(projectInDto);
//        } catch (UserAlreadyFound e) {
//            fail("Unexpected UserAlreadyFound exception");
//        }
//    }
//    @Test
//    public void testCheckLoginWithInvalidCredentials() {
//        LoginInDto loginInDto = new LoginInDto();
//        loginInDto.setEmail("existing@example.com");
//        loginInDto.setPassword("invalidEncodedPassword");
//        
//        Employee existingEmployee = new Employee();
//        existingEmployee.setPassword("encodedPassword");
//        when(employeeRepository.findByEmail("existing@example.com")).thenReturn(existingEmployee);
//        when(passwordEncoder.matches("decodedPassword", "encodedPassword")).thenReturn(false);
//        try {
//            validation.checkLogin(loginInDto);
//            fail("Expected UserNotFound exception");
//        } catch (UserNotFound e) {
//            assertEquals(ErrorConstants.INVAILD_PASSWORD, e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCheckAdminWithExistingAdmin() {
//        when(employeeRepository.findByRole(Role.Admin)).thenReturn(Collections.singletonList(new Employee()));
//
//        try {
//            validation.checkAdmin(new EmployeeInDto());
//            fail("Expected UserAlreadyFound exception");
//        } catch (UserAlreadyFound e) {
//            assertEquals(ErrorConstants.ADMIN_FOUND, e.getMessage());
//        }
//    }
//    @Test
//    public void testPatterValidationsWithoutValidationErrors() {
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(bindingResult.hasErrors()).thenReturn(false);
//
//        try {
//        } catch (CustomException e) {
//            fail("Unexpected CustomException");
//        }
//    }
//
//    @Test
//    public void testCheckEmployeeExistsWithExistingEmployee() {
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(new Employee()));
//        try {
//            validation.checkEmployeeExists(1L);
//        } catch (UserNotFound e) {
//            fail("Unexpected UserNotFound exception");
//        }
//    }
//    @Test
//    public void testCheckEmployeeExistsWithNonExistingEmployee() {
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
//        try {
//            validation.checkEmployeeExists(1L);
//            fail("Expected UserNotFound exception");
//        } catch (UserNotFound e) {
//            assertEquals(ErrorConstants.EMPLOYEE_NOT_FOUND, e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCheckProjectExistsWithExistingProject() {
//        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(new Project()));
//        try {
//            validation.checkProjectExists(1L);
//        } catch (UserNotFound e) {
//            fail("Unexpected UserNotFound exception");
//        }
//    }
//
//    @Test
//    public void testCheckProjectExistsWithNonExistingProject() {
//        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());
//        try {
//            validation.checkProjectExists(1L);
//            fail("Expected UserNotFound exception");
//        } catch (UserNotFound e) {
//            assertEquals(ErrorConstants.PROJECT_NOT_FOUND, e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCheckManagerExistsWithExistingManager() {
//        Employee manager = new Employee();
//        manager.setRole(Role.Manager);
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(manager));
//
//        try {
//            validation.checkManagerExists(1L);
//        } catch (UserNotFound e) {
//            fail("Unexpected UserNotFound exception");
//        }
//    }
//
//    @Test
//    public void testCheckManagerExistsWithNonExistingManager() {
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
//        try {
//            validation.checkManagerExists(1L);
//            fail("Expected UserNotFound exception");
//        } catch (UserNotFound e) {
//            assertEquals(ErrorConstants.EMPLOYEE_NOT_FOUND, e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCheckRequestResourceExistsWithExistingResource() {
//        when(requestResourceRepository.findById(anyLong())).thenReturn(Optional.of(new RequestResource()));
//
//        try {
//            validation.checkRequestResourceExists(1L);
//        } catch (UserNotFound e) {
//            fail("Unexpected UserNotFound exception");
//        }
//    }
//
//    @Test
//    public void testCheckRequestResourceExistsWithNonExistingResource() {
//        when(requestResourceRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        try {
//            validation.checkRequestResourceExists(1L);
//            fail("Expected UserNotFound exception");
//        } catch (UserNotFound e) {
//            assertEquals(ErrorConstants.REQUES_NOT_FOUND, e.getMessage());
//        }
//    }
//
//    @Test
//    public void testCheckOnlyEmployeeExistsWithExistingEmployee() {
//        Employee employee = new Employee();
//        employee.setRole(Role.Employee);
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
//
//        try {
//            validation.checkOnlyEmployeeExists(1L);
//        } catch (UserNotFound e) {
//            fail("Unexpected UserNotFound exception");
//        }
//    }
//
//    @Test
//    public void testCheckOnlyEmployeeExistsWithNonExistingEmployee() {
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        try {
//            validation.checkOnlyEmployeeExists(1L);
//            fail("Expected UserNotFound exception");
//        } catch (UserNotFound e) {
//            assertEquals(ErrorConstants.EMPLOYEE_NOT_FOUND, e.getMessage());
//        }
//    }
    @Test
    public void testCheckEmailNotExist() {
        LoginInDto loginInDto = new LoginInDto();
        loginInDto.setEmail("ankita.sharma@nucleusteq.com");
        when(employeeRepository.findByEmail(loginInDto.getEmail()))
                .thenReturn(null);
        assertThrows(UserNotFound.class, () -> {
            validation.checkEmailNotExists(loginInDto);
        });
    }

    @Test
    public void testCheckEmailNotExists_ValidEmail() {
        Employee emp = new Employee();
        LoginInDto loginInDto = new LoginInDto();
        loginInDto.setEmail("ankita.sharma@nucleusteq.com");
        when(employeeRepository.findByEmail(loginInDto.getEmail()))
                .thenReturn(Optional.of(emp).get());
        assertDoesNotThrow(() -> validation.checkEmailNotExists(loginInDto));
    }

    @Test
    public void testCheckEmailExistsEmailNotFound() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmail("ankita.sharma@nucleusteq.com");
        when(employeeRepository.findByEmail(employeeInDto.getEmail()))
                .thenReturn(null);

        assertDoesNotThrow(() -> validation.checkEmailExists(employeeInDto));
    }

    @Test
    public void testCheckEmailExistsEmailFound() {
        Employee employee = new Employee();
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmail("ankita.sharma@nucleusteq.com");
        when(employeeRepository.findByEmail(employeeInDto.getEmail()))
                .thenReturn(Optional.of(employee).get());

        assertThrows(UserAlreadyFound.class,
                () -> validation.checkEmailExists(employeeInDto));
    }

    @Test
    public void testCheckEmployeeIdExistsIdNotFound() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmpId("N1234");
        when(employeeRepository.findByEmpId(employeeInDto.getEmpId()))
                .thenReturn(null);

        assertDoesNotThrow(() -> validation.checkEmployeeIdExists(employeeInDto));
    }

    @Test
    public void testCheckEmployeeIdExistsIdFound() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmpId("N1234");
        Employee existingEmployee = new Employee();
        when(employeeRepository.findByEmpId(employeeInDto.getEmpId()))
                .thenReturn(Optional.of(existingEmployee).get());

        assertThrows(UserAlreadyFound.class,
                () -> validation.checkEmployeeIdExists(employeeInDto));
    }

    @Test
    public void testCheckContactNumberExistsNumberNotFound() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setContactNo(1234567890L);
        when(employeeRepository.findByContactNo(employeeInDto.getContactNo()))
                .thenReturn(null);

        assertDoesNotThrow(
                () -> validation.checkContactNumberExists(employeeInDto));
    }

    @Test
    public void testCheckContactNumberExistsNumberFound() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setContactNo(1234567890L);
        Employee existingEmployee = new Employee();
        when(employeeRepository.findByContactNo(employeeInDto.getContactNo()))
                .thenReturn(existingEmployee);

        assertThrows(UserAlreadyFound.class,
                () -> validation.checkContactNumberExists(employeeInDto));
    }

    @Test
    public void testCheckProjectProjectNotFound() {
        ProjectInDto projectInDto = new ProjectInDto();
        projectInDto.setProjectName("Fynder");
        when(projectRepository.findByProjectName(projectInDto.getProjectName()))
                .thenReturn(null);

        assertDoesNotThrow(() -> validation.checkProject(projectInDto));
    }

    @Test
    public void testCheckProjectProjectFound() {
        ProjectInDto projectInDto = new ProjectInDto();
        projectInDto.setProjectName("Fynder");
        Project existingProject = new Project();
        when(projectRepository.findByProjectName(projectInDto.getProjectName()))
                .thenReturn(existingProject);

        assertThrows(UserAlreadyFound.class,
                () -> validation.checkProject(projectInDto));

    }

    @Test
    public void testCheckAdminExistsNoAdminFound() {
        when(employeeRepository.findByRole(Role.Admin)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> validation.checkAdminExists());
    }

    @Test
    public void testCheckAdminExistsAdminFound() {
        Employee adminEmployee = new Employee();
        when(employeeRepository.findByRole(Role.Admin))
                .thenReturn(List.of(adminEmployee));

        assertThrows(UserAlreadyFound.class,
                () -> validation.checkAdminExists());
    }

    @Test
    public void testCheckOnlyEmployeeExistsEmployeeFound() {
        Long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setRole(Role.Employee);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        assertDoesNotThrow(() -> validation.checkOnlyEmployeeExists(id));
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
          assertEquals(ErrorConstants.INVAILD_PASSWORD, e.getMessage());
      }
  }

    @Test
    public void testCheckOnlyEmployeeExistsEmployeeNotFound() {
        Long id = 2L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFound.class,
                () -> validation.checkOnlyEmployeeExists(id));
    }

    @Test
    public void testCheckManagerExistsManagerFound() {
        Long id = 1L;
        Employee managerEmployee = new Employee();
        managerEmployee.setId(id);
        managerEmployee.setRole(Role.Manager);
        when(employeeRepository.findById(id))
                .thenReturn(Optional.of(managerEmployee));

        assertDoesNotThrow(() -> validation.checkManagerExists(id));
    }

    @Test
    public void testCheckManagerExistsEmployeeNotFound() {
        Long id = 2L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFound.class,
                () -> validation.checkManagerExists(id));
    }

    @Test
    public void testCheckManagerExistsNonManagerFound() {
        Long id = 3L;
        Employee nonManager = new Employee();
        nonManager.setId(id);
        nonManager.setRole(Role.Employee);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(nonManager));

        assertThrows(UserNotFound.class,
                () -> validation.checkManagerExists(id));
    }

    @Test
    public void testCheckProjectExistsProjectFoundUsingId() {
        Long id = 1L;
        Project project = new Project();
        project.setId(id);
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));

        assertDoesNotThrow(() -> validation.checkProjectExists(id));
    }

    @Test
    public void testCheckProjectExistsProjectNotFoundUsingId() {
        Long id = 2L;
        when(projectRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFound.class,
                () -> validation.checkProjectExists(id));
    }

    @Test
    public void testCheckRequestIdExistsRequestFound() {
        Long id = 1L;
        RequestResource request = new RequestResource();
        request.setId(id);
        when(requestResourceRepository.findById(id)).thenReturn(Optional.of(request));

        assertDoesNotThrow(() -> validation.checkRequestResourceExists(id));
    }

    @Test
    public void testCheckRequestIdExistsRequestNotFound() {
        Long id = 2L;
        RequestResource request = new RequestResource();
        request.setId(id);
        when(requestResourceRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFound.class,
                () -> validation.checkRequestResourceExists(id));
    }
    @Test
    public void testCheckRoleExistsWithInvalidRole() {
        String invalidRole = "NonExistentRole";

        assertThrows(UserNotFound.class, () -> {
            validation.checkRoleExists(invalidRole);
        });
    }
    @Test
    public void testCheckEmployeeExistsWithExistingEmployee() {
        Long id = 4L;
        Employee existingEmployee = new Employee();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        assertDoesNotThrow(() -> validation.checkEmployeeExists(id));
    }
    @Test
    public void testCheckEmployeeExistsWithNonExistingEmployee() {
        Long id = 4L;
        Employee existingEmployee = new Employee();
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(UserNotFound.class,() -> validation.checkEmployeeExists(id));
    }



}

