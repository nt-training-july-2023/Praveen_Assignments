package com.backend.EMS.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.backend.EMS.DTO.AdminDto;
import com.backend.EMS.DTO.LoginDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Service.AdminService;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {
    
    @Mock
    private AdminService adminService; 
    
    @InjectMocks
    private AdminController adminController;
    BindingResult bindingResult;
    


    @Test
    void testRegisterAdmin() {
        // Create an AdminDto with relevant data
        AdminDto adminDto = new AdminDto();
        adminDto.setName("praveen");
        adminDto.setEmpId("N0123");
        adminDto.setEmail("praveen@example.com");
        adminDto.setDob("14-01-22");
        adminDto.setDoj("23-2-23");
        adminDto.setLocation("Raipur");
        adminDto.setConfirmPassword("password123");
        adminDto.setPassword("password123");
        adminDto.setContactNo(1234567890L);
        adminDto.setDesignation("SDE-1");
        // Set up other fields of the adminDto
        
        // Mock the behavior of the adminService.addAdmin method
//        when(adminService.addAdmin(adminDto)).thenReturn(true);
        
        // Call the registerAdmin method of the adminController
//        ResponseDto responseDto = adminController.registerAdmin(adminDto,bindingResult);
//        
//        // Assert the expected response values
//        assertEquals(HttpStatus.CREATED.value(), responseDto.getStatusCode());
//        assertEquals("Admin Registered successfully", responseDto.getMessage());
        
        // Mock the behavior of the BindingResult object
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false); // Set this according to your test case
        
        // Call the registerAdmin method of the adminController
        ResponseDto responseDto = adminController.registerAdmin(adminDto, bindingResult);
        
        // Assert the expected response values
        assertEquals(HttpStatus.CREATED.value(), responseDto.getStatusCode());
        assertEquals("Admin Registered successfully", responseDto.getMessage());
      
    }

    @Test
    void testLogin() {
     // Create a LoginDto with relevant data
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("praveen@example.com");
        loginDto.setPassword("password123");
        // Set up other fields of the loginDto
        
        // Mock the behavior of the adminService.isEmailExist and adminService.userValidation methods
        when(adminService.isEmailExist(loginDto)).thenReturn(true);
        when(adminService.userValidation(loginDto)).thenReturn(true);
        
        // Call the login method of the adminController
        ResponseDto responseDto = adminController.login(loginDto);
        
        // Assert the expected response values
        assertEquals(HttpStatus.OK.value(), responseDto.getStatusCode());
        assertEquals("Login Successful", responseDto.getMessage());
    }

}