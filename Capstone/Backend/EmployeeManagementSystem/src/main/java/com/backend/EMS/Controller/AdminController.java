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
import com.backend.EMS.Exception.userAlreadyFound;
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
    public final ResponseDto registerAdmin(@RequestBody @Valid
            final  AdminDto adminDto, BindingResult bindingResult) {
        // Initialize a response DTO
        ResponseDto responseDto = new ResponseDto();
        // Check for validation errors
//        System.out.println(bindingResult.hasErrors());    
        if (bindingResult.hasErrors()) {
            // Set response values for validation errors
            responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
            responseDto.setMessage("Validation failed");
            responseDto.setData(bindingResult.getAllErrors());
            return responseDto;
        }

        try {
            // Call the service to add the admin
            adminService.addAdmin(adminDto);
            // Set response values for successful registration
            responseDto.setStatusCode(HttpStatus.CREATED.value());
            responseDto.setMessage("Admin Registered successfully");
//            if(adminService.addAdmin(adminDto)) {
//                responseDto.setStatusCode(HttpStatus.CONFLICT.value());
//                responseDto.setMessage("Admin already Registered");
//            }else {
//            // Set response values for successful registration
//            responseDto.setStatusCode(HttpStatus.CREATED.value());
//            responseDto.setMessage("Admin Registered successfully");
//            }
        } 
        catch(userAlreadyFound e) {
            responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDto.setMessage(e.getMessage());
            
        }
        catch (Exception e) {
            // Set response values for registration error
            responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDto.setMessage("Errorrr Occurred");
           
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
                responseDto.setStatusCode(HttpStatus.OK.value());
                responseDto.setMessage("Login Successful");
            } else {
                // Set response values for incorrect password
                responseDto.setStatusCode(HttpStatus.UNAUTHORIZED.value());
                responseDto.setMessage("Password Incorrect");
            }
        } else {
            // Set response values for email not registered
            responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseDto.setMessage("Email not registered");
        }
        return responseDto;
    }
}
