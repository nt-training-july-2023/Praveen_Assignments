package com.backend.EMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EMS.DTO.Admin_DTO;
import com.backend.EMS.Exception.UserNotFound;
import com.backend.EMS.Service.AdminService;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class AdminController {
	
	private final AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService= adminService;
		
	}
	
	@PostMapping("/adminRegistration")
//	public ResponseEntity<String> addAdmin(@RequestBody Admin_DTO admin_DTO){
//		return adminService.addAdmin(admin_DTO);
//	}
	public ResponseEntity<String> registerAdmin(@RequestBody Admin_DTO admin_DTO){
		try {
			adminService.addAdmin(admin_DTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Admin is registered Successfully");
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occured");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Admin_DTO admin_DTO){
		
		if(adminService.isEmailExist(admin_DTO)) {
		
		if (adminService.userValidation(admin_DTO)){
			return ResponseEntity.ok("Login Successfull");
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password is incorrect");
		}
		}
		else {
//			throw new  UserNotFound("User not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email is not registered");
		
			
		}
		
	}
		
	}
	


