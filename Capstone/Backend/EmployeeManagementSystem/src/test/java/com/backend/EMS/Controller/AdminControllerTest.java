package com.backend.EMS.Controller;

import com.backend.EMS.Controller.AdminController;
import com.backend.EMS.DTO.*;
import com.backend.EMS.Exception.AnnotationValidation;
import com.backend.EMS.Service.AddEmployeeService;
import com.backend.EMS.Service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @Mock
    private AddEmployeeService addEmployeeService;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterAdmin_Success() {
        EmployeeDto employeeDto = new EmployeeDto();
        when(adminService.addAdmin(any(EmployeeDto.class))).thenReturn(new ResponseDto("Registration successful"));

        ResponseDto response = adminController.registerAdmin(employeeDto, bindingResult);

        verify(adminService, times(1)).addAdmin(any(EmployeeDto.class));

        assertEquals("Registration successful", response.getMessage());
    }

    @Test
    public void testAddEmployee_Success() {
        EmployeeDto employeeDto = new EmployeeDto();
        when(addEmployeeService.addEmployee(any(EmployeeDto.class))).thenReturn(new ResponseDto("Employee added successfully"));

        ResponseDto response = adminController.addEmployee(employeeDto, bindingResult);

        verify(addEmployeeService, times(1)).addEmployee(any(EmployeeDto.class));

        assertEquals("Employee added successfully", response.getMessage());
    }
    @Test
    public void testLogin_Success() {
        LoginDto loginDto = new LoginDto();
        
        // Create a LoginResponseDto and set the message using the setter method
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setMessage("Login successful");
        
        // Mock the behavior of the adminService
        when(adminService.login(any(LoginDto.class))).thenReturn(loginResponseDto);

        LoginResponseDto response = adminController.login(loginDto);

        verify(adminService, times(1)).login(any(LoginDto.class));

        // Assert the response
        assertNotNull(response);
        assertEquals("Login successful", response.getMessage());
    }

    @Test
    public void testUpdateEmployee_Success() {
        Long id = 1L;
        Map<String, Long> updatedDetails = new HashMap<>();
        updatedDetails.put("projectId", 2L);
        updatedDetails.put("managerId", 3L);

        when(addEmployeeService.updateEmployee(eq(id), eq(2L), eq(3L))).thenReturn(new ResponseDto("Employee updated successfully"));

        ResponseDto response = adminController.updateEmployee(id, updatedDetails);

        verify(addEmployeeService, times(1)).updateEmployee(eq(id), eq(2L), eq(3L));

        assertEquals("Employee updated successfully", response.getMessage());
    }

    @Test
    public void testGetAllManagersAndEmployees_Success() {
        List<EmployeeOutDto> employeeList = Arrays.asList(new EmployeeOutDto(), new EmployeeOutDto());
        when(addEmployeeService.getAllEmployeesAndManagers()).thenReturn(employeeList);

        List<EmployeeOutDto> response = adminController.getAllManagersAndEmployees();

        verify(addEmployeeService, times(1)).getAllEmployeesAndManagers();

        assertEquals(2, response.size());
    }

    @Test
    public void testGetByEmployeeEmail_Success() {
        String email = "test@example.com";
        EmployeeOutDto employee = new EmployeeOutDto();
        when(addEmployeeService.getEmployeeByEmail(eq(email))).thenReturn(employee);

        EmployeeOutDto response = adminController.getByEmployeeEmail(email);

        verify(addEmployeeService, times(1)).getEmployeeByEmail(eq(email));

        assertNotNull(response);
    }

//    @Test
//    public void testUpdateSkill_Success() {
//        Long id = 1L;
//        EmployeeDto employeeDto = new EmployeeDto();
//        when(addEmployeeService.updateSkills(eq(id), any(EmployeeDto.class))).thenReturn(new ResponseDto("Skills updated successfully"));
//
//        ResponseDto response = adminController.updateSkill(id, employeeDto);
//
//        verify(addEmployeeService, times(1)).updateSkills(eq(id), any(EmployeeDto.class));
//
//        assertEquals("Skills updated successfully", response.getMessage());
//    }
}
