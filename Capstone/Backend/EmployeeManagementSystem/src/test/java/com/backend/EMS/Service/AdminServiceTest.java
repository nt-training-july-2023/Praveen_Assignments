package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.backend.EMS.DTO.EmployeeDto;
import com.backend.EMS.DTO.LoginDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Exception.UserNotFound;
import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.AdminRepository;

public class AdminServiceTest {

    private AdminService adminService;
    private AdminRepository adminRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        adminRepository = mock(AdminRepository.class);
        passwordEncoder = mock(BCryptPasswordEncoder.class);
        adminService = new AdminService(adminRepository, passwordEncoder);
    }

    @Test
    public void testAddAdmin_Successful() {
        // Arrange
        EmployeeDto employeeDto = createSampleEmployeeDto();
        Employee employee = createSampleEmployee();
        when(adminRepository.findByEmail(anyString())).thenReturn(null);
        when(adminRepository.findByEmpId(anyString())).thenReturn(null);
        when(adminRepository.findByContactNo(anyLong())).thenReturn(null);

        // Act
        ResponseDto responseDto = adminService.addAdmin(employeeDto);

        // Assert
        assertNotNull(responseDto);
        assertEquals("Admin Registered successfully", responseDto.getMessage());
    }

    @Test
    public void testAddAdmin_DuplicateEmail() {
        // Arrange
        EmployeeDto employeeDto = createSampleEmployeeDto();
        when(adminRepository.findByEmail(anyString())).thenReturn(createSampleEmployee());

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> adminService.addAdmin(employeeDto));
    }

    @Test
    public void testAddAdmin_DuplicateEmpId() {
        // Arrange
        EmployeeDto employeeDto = createSampleEmployeeDto();
        when(adminRepository.findByEmpId(anyString())).thenReturn(createSampleEmployee());

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> adminService.addAdmin(employeeDto));
    }

    @Test
    public void testAddAdmin_DuplicateContactNo() {
        // Arrange
        EmployeeDto employeeDto = createSampleEmployeeDto();
        when(adminRepository.findByContactNo(anyLong())).thenReturn(createSampleEmployee());

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> adminService.addAdmin(employeeDto));
    }
    @Test
    public void testIsEmailExist_Exists() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        when(adminRepository.findByEmail(anyString())).thenReturn(createSampleEmployee());

        // Act
        boolean exists = adminService.isEmailExist(loginDto);

        // Assert
        assertTrue(exists);
    }

    @Test
    public void testIsEmailExist_NotExists() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        when(adminRepository.findByEmail(anyString())).thenReturn(null);

        // Act
        boolean exists = adminService.isEmailExist(loginDto);

        // Assert
        assertFalse(exists);
    }

//    @Test
//    public void testUserValidation_ValidPassword() {
//        // Arrange
//        LoginDto loginDto = new LoginDto();
//        loginDto.setEmail("test@example.com");
//        loginDto.setPassword("password");
//        Employee employee = createSampleEmployee();
//        when(adminRepository.findByEmail(anyString())).thenReturn(employee);
//        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
//
//        // Act
//        boolean isValid = adminService.userValidation(loginDto);
//
//        // Assert
//        assertTrue(isValid);
//    }

    @Test
    public void testUserValidation_InvalidPassword() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("password");
        Employee employee = createSampleEmployee();
        when(adminRepository.findByEmail(anyString())).thenReturn(employee);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // Act
        boolean isValid = adminService.userValidation(loginDto);

        // Assert
        assertFalse(isValid);
    }

//    @Test
//    public void testLogin_ValidCredentials() {
//        // Arrange
//        LoginDto loginDto = new LoginDto();
//        loginDto.setEmail("test@nucleusteq.com");
//        loginDto.setPassword("password");
//        Employee employee = createSampleEmployee();
//        when(adminRepository.findByEmail(anyString())).thenReturn(employee);
//        when(anyString().equals(anyString())).thenReturn(true);
//       
//
//        // Act
//        ResponseDto responseDto = adminService.login(loginDto);
//
//        // Assert
//        assertNotNull(responseDto);
//        assertEquals("Login Successful", responseDto.getMessage());
//    }

    @Test
    public void testLogin_InvalidPassword() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("password");
        Employee employee = createSampleEmployee();
        when(adminRepository.findByEmail(anyString())).thenReturn(employee);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // Act and Assert
        assertThrows(UserNotFound.class, () -> adminService.login(loginDto));
    }

    @Test
    public void testLogin_UserNotFound() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("password");
        when(adminRepository.findByEmail(anyString())).thenReturn(null);

        // Act and Assert
        assertThrows(UserNotFound.class, () -> adminService.login(loginDto));
    }

    private EmployeeDto createSampleEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        // Set sample employee DTO properties here
        employeeDto.setName("Praveen");
        employeeDto.setEmail("praveen@nucleusteq.com");
        employeeDto.setEmpId("N0001");
        employeeDto.setDesignation(Designation.Engineer);
        employeeDto.setLocation(Location.Raipur);
        employeeDto.setContactNo(1234567890L);
        employeeDto.setDob("14-10-2001");
        employeeDto.setDoj("17-07-2023");
        return employeeDto;
    }

    private Employee createSampleEmployee() {
        Employee employee = new Employee();
        // Set sample employee properties here
        employee.setName("Praveen");
        employee.setEmail("praveen@nucleusteq.com");
        employee.setEmpId("N0001");
        employee.setDesignation(Designation.Engineer);
        employee.setLocation(Location.Raipur);
        employee.setContactNo(1234567890L);
        employee.setDob("14-10-2001");
        employee.setDoj("17-07-2023");
        return employee;
    }
}
