//package com.backend.EMS.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.backend.EMS.DTO.Admin_DTO;
//import com.backend.EMS.DTO.Login_DTO;
//import com.backend.EMS.DTO.Response_DTO;
//import com.backend.EMS.DTO.Response_DTO;
//import com.backend.EMS.Service.AdminService;
//
//
//
//@RestController
//@CrossOrigin
//@RequestMapping("/api")
//public class AdminController {
//	
//	private final AdminService adminService;
//	
//	@Autowired
//	public AdminController(AdminService adminService) {
//		this.adminService= adminService;
//		
//	}
//	
//	@PostMapping("/adminRegistration")
////	public ResponseEntity<String> addAdmin(@RequestBody Admin_DTO admin_DTO){
////		return adminService.addAdmin(admin_DTO);
////	}
//	public Response_DTO registerAdmin(@RequestBody Admin_DTO admin_DTO){
//		Response_DTO response_DTO = new Response_DTO();
//		try {
//			adminService.addAdmin(admin_DTO);
//			response_DTO.setStatusCode(HttpStatus.CREATED.value());
//			response_DTO.setMessage("Admin Registered successfully");
//		} catch(Exception e) {
//			response_DTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//			response_DTO.setMessage("Err Ocuured");
//		}
//		return response_DTO;
//	}
//	
//	@PostMapping("/login")
//	public Response_DTO login(@RequestBody Login_DTO login_DTO){
//		Response_DTO response_DTO = new Response_DTO();
//		
//		if(adminService.isEmailExist(login_DTO)) {
//		
//		if (adminService.userValidation(login_DTO)){
//			response_DTO.setStatusCode(HttpStatus.OK.value());
//			response_DTO.setMessage("Login Successful");
//		}
//		else {
//			response_DTO.setStatusCode(HttpStatus.UNAUTHORIZED.value());
//			response_DTO.setMessage("Password Incorrect");
//			
//		}
//		}
//		else {
////			throw new  UserNotFound("User not found");
//			response_DTO.setStatusCode(HttpStatus.NOT_FOUND.value());
//			response_DTO.setMessage("email not registered");	
//		}
//		return response_DTO;
//		
//	}
//		
//	}
//	
package com.backend.EMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EMS.DTO.Admin_DTO;
import com.backend.EMS.DTO.Login_DTO;
import com.backend.EMS.DTO.Response_DTO;
import com.backend.EMS.Exception.UserNotFound;
import com.backend.EMS.Service.AdminService;

@RestController
//@RestController: Indicates that this class is a Spring MVC controller, and it's responsible for handling HTTP requests and returning data.

@CrossOrigin
//@CrossOrigin: Allows cross-origin requests, which are requests from different domains. This is helpful when your frontend and backend are hosted on different domains.

@RequestMapping("/api")
//@RequestMapping("/api"): Defines the base URL mapping for all endpoints in this controller. All endpoints will have URLs starting with "/api".

public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Endpoint to register a new admin.
    @PostMapping("/adminRegistration")
    public Response_DTO registerAdmin(@RequestBody Admin_DTO admin_DTO) {
        // Initialize a response DTO
        Response_DTO response_DTO = new Response_DTO();
        try {
            // Call the service to add the admin
            adminService.addAdmin(admin_DTO);
            // Set response values for successful registration
            response_DTO.setStatusCode(HttpStatus.CREATED.value());
            response_DTO.setMessage("Admin Registered successfully");
        } catch (Exception e) {
            // Set response values for registration error
            response_DTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response_DTO.setMessage("Error Occurred");
//            throw new UserNotFound("Error ooooOccured");
        }
        return response_DTO;
    }

    // Endpoint for admin login.
    @PostMapping("/login")
    public Response_DTO login(@RequestBody Login_DTO login_DTO) {
        // Initialize a response DTO
        Response_DTO response_DTO = new Response_DTO();

        // Check if the email exists
        if (adminService.isEmailExist(login_DTO)) {
            // Validate the user's credentials
            if (adminService.userValidation(login_DTO)) {
                // Set response values for successful login
                response_DTO.setStatusCode(HttpStatus.OK.value());
                response_DTO.setMessage("Login Successful");
            } else {
                // Set response values for incorrect password
                response_DTO.setStatusCode(HttpStatus.UNAUTHORIZED.value());
                response_DTO.setMessage("Password Incorrect");
            }
        } else {
            // Set response values for email not registered
            response_DTO.setStatusCode(HttpStatus.NOT_FOUND.value());
            response_DTO.setMessage("Email not registered");
        }
        return response_DTO;
    }
}



