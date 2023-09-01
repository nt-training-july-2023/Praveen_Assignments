package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.backend.EMS.DTO.AdminDto;
import com.backend.EMS.DTO.LoginDto;
import com.backend.EMS.Model.Admin;
import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Repository.AdminRepository;


@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
    
    @Mock
    private AdminRepository adminRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    
    

    @Test
    void testAdminService() {
        AdminDto adminDto = new AdminDto();
        adminDto.setName("praveen");
        adminDto.setEmpId("N0123");
        adminDto.setEmail("praveen@example.com");
        adminDto.setDob("14-01-22");
        adminDto.setDoj("23-2-23");
        adminDto.setLocation(Location.Raipur);
        adminDto.setConfirmPassword("password123");
        adminDto.setPassword("password123");
        adminDto.setContactNo(1234567890L);
        adminDto.setDesignation(Designation.Engineer);

        when(passwordEncoder.encode(adminDto.getPassword())).thenReturn("encodedPassword");
        when(adminRepository.save(any())).thenReturn(new Admin()); // Simulate successful save

        adminService.addAdmin(adminDto);

        verify(adminRepository).save(any()); // Verify that save was called with the expected data
        verify(passwordEncoder).encode(adminDto.getPassword()); // Verify that password was encoded
    }

    @Test
    void testAddAdmin() {
        AdminDto adminDto = new AdminDto();
        adminDto.setName("praveen");
        adminDto.setEmpId("N0123");
        adminDto.setEmail("praveen@example.com");
        adminDto.setDob("14-01-22");
        adminDto.setDoj("23-2-23");
        adminDto.setLocation(Location.Raipur);
        adminDto.setConfirmPassword("password123");
        adminDto.setPassword("password123");
        adminDto.setContactNo(1234567890L);
        adminDto.setDesignation(Designation.Engineer);
        // Set up other fields of the adminDto

        when(passwordEncoder.encode(adminDto.getPassword())).thenReturn("encodedPassword");
        when(adminRepository.save(any())).thenReturn(new Admin()); // Simulate successful save

        adminService.addAdmin(adminDto);

//        verify(adminRepository).save(any()); // Verify that save was called with the expected data
    }
    

    @Test
    void testIsEmailExist() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("praveen@example.com");
        loginDto.setPassword("password123");

        Admin admin = new Admin();
        when(adminRepository.findByEmail(loginDto.getEmail())).thenReturn(admin);
        when(passwordEncoder.matches(loginDto.getPassword(), admin.getPassword())).thenReturn(true);

        boolean result = adminService.userValidation(loginDto);

        assertTrue(result); // Assert valid credentials
    }

    @Test
    void testUserValidation() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("john@example.com");
        loginDto.setPassword("wrongpassword");

        Admin admin = new Admin();
        when(adminRepository.findByEmail(loginDto.getEmail())).thenReturn(admin);
        when(passwordEncoder.matches(loginDto.getPassword(), admin.getPassword())).thenReturn(false);

        boolean result = adminService.userValidation(loginDto);
        assertFalse(result); // Assert invalid credentials

        
    }

}
