package com.backend.EMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EMS.DTO.AdminDto;
import com.backend.EMS.DTO.LoginDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.AnnotationValidation;
import com.backend.EMS.Exception.UserNotFound;
import com.backend.EMS.Service.AddEmployeeService;
//import com.backend.EMS.Exception.UserNotFound;
import com.backend.EMS.Service.AdminService;

import jakarta.validation.Valid;

/**
 * Controller class for handling admin-related operations.
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class AdminController {
    /**
     * The AdminService instance used for managing admin-related operations.
     */
    @Autowired
    private AdminService adminService;

    /**
     * Endpoint to register a new admin.
     *
     * @param adminDto The admin DTO containing registration details.
     * @return A response DTO indicating the registration status.
     */
    @PostMapping("/adminRegistration")
    public final ResponseDto registerAdmin(@RequestBody @Valid final AdminDto adminDto, BindingResult bindingResult) {
        // Initialize a response DTO
        ResponseDto responseDto = new ResponseDto();
        // Check for validation errors
        System.out.println(bindingResult.hasErrors());    
        if (bindingResult.hasErrors()) {
            // Set response values for validation errors
            System.out.println(bindingResult.getAllErrors());
            throw new AnnotationValidation("Validation failed from backend");
          
      
//            responseDto.setMessage("Validation failed");
////            responseDto.setData(bindingResult.getAllErrors());
//            return responseDto;
        }
        // Call the service to add the admin
        if (adminService.addAdmin(adminDto)) {
            // Set response values for successful registration
            responseDto.setMessage("Admin Registered successfully");
        }
        return responseDto;
    }
    
    @Autowired
    private AddEmployeeService addEmployeeService;
    
    @PostMapping("/addEmployee")
    public final ResponseDto addEmployee(@RequestBody AdminDto adminDto) {
//        Initialize a responseDto
        ResponseDto responseDto = new ResponseDto();
        // Call the service to add the employee
        if (addEmployeeService.addEmployee(adminDto)) {
            // Set response values for successful registration
            responseDto.setMessage("Employee Added successfully");
        }
        return responseDto;
        
        
    }

    /**
     * Endpoint for admin login.
     *
     * @param loginDto The login DTO containing user's login details.
     * @return A response DTO indicating the login status.
     */
    @PostMapping("/login")
    public final ResponseDto login(@RequestBody final LoginDto loginDto) {
        // Initialize a response DTO
        ResponseDto responseDto = new ResponseDto();

        // Check if the email exists
        if (adminService.isEmailExist(loginDto)) {
            // Validate the user's credentials
            if (adminService.userValidation(loginDto)) {
                // Set response values for successful login
                responseDto.setMessage("Login Successful");
            } else {
                ;
                throw new UserNotFound("Password Incorrectt");
            }
        } else {
            throw new UserNotFound("Email is not registered");
        }
        return responseDto;
    }
}
