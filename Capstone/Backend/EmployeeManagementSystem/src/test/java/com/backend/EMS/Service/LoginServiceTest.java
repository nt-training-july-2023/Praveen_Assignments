package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.EMS.Controller.EmployeeController;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.LoginOutDto;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.EmployeeRepository;

class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin() {
        // Create a mock Employee and LoginInDto
        Employee mockEmployee = new Employee();
        mockEmployee.setId(1L);
        mockEmployee.setName("John Doe");
        mockEmployee.setEmail("johndoe@example.com");
        mockEmployee.setRole(Role.Admin);

        LoginInDto loginInDto = new LoginInDto();
        loginInDto.setEmail("johndoe@example.com");
        loginInDto.setPassword("encodedPassword"); // You can use the encoded password here

        // Mock the behavior of employeeRepository.findByEmail
        when(employeeRepository.findByEmail(loginInDto.getEmail())).thenReturn(mockEmployee);

        // Call the login method
        LoginOutDto loginResponse = loginService.login(loginInDto);

        // Verify that the loginResponse is as expected
        assertEquals("Login Successful", loginResponse.getMessage());
        assertEquals(Role.Admin, loginResponse.getRole());
        assertEquals(1L, loginResponse.getId());
        assertEquals("John Doe", loginResponse.getName());
    }

//    @Test
//    void testLoginUserNotFound() {
//        // Create a LoginInDto with an email
//        LoginInDto loginInDto = new LoginInDto();
//        loginInDto.setEmail("nonexistent@example.com");
//        loginInDto.setPassword("encodedPassword");
//
//        // Mock the behavior of employeeRepository.findByEmail to return null
//        when(employeeRepository.findByEmail(loginInDto.getEmail())).thenReturn(null);
//
//        // Call the login method
//        assertThrows(UserNotFound.class, () -> loginService.login(loginInDto));
//    }
}

