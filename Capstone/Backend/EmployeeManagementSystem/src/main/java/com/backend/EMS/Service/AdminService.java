package com.backend.EMS.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.EMS.DTO.Admin_DTO;
import com.backend.EMS.Model.Admin;
import com.backend.EMS.Repository.AdminRepository;

@Service
public class AdminService {
	
	private final AdminRepository adminRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminService(AdminRepository adminRepository,BCryptPasswordEncoder passwordEncoder) {
		this.adminRepository = adminRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public  ResponseEntity<String> addAdmin(Admin_DTO admin_DTO ) {
		Admin admin = new Admin();
//		BeanUtils.copyProperties(admin_DTO, admin);   //Copy DTO properties to entity
		admin.setName(admin_DTO.getName());
        admin.setEmail(admin_DTO.getEmail());
        admin.setEmp_Id(admin_DTO.getEmp_Id());
        admin.setDOB(admin_DTO.getDOB());
        admin.setDOJ(admin_DTO.getDOJ());
        admin.setLocation(admin_DTO.getLocation());
        admin.setDesignation(admin_DTO.getDesignation());
        admin.setContact_no(admin_DTO.getContact_no());
        
        admin.setPassword(this.passwordEncoder.encode(admin_DTO.getPassword()));
        
       // admin.setPassword(admin_DTO.getPassword());
		admin = adminRepository.save(admin);
		if(admin == null) {
			return ResponseEntity.ok("not added");
		}
		else {
			return ResponseEntity.ok("added");
		}

//		return adminRepository.save(admin);
		
	}
	
//	public boolean isEmailExist(String email) {
//		if(adminRepository.findByEmail(email) != null) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//	public boolean userValidation(String email, String password) {
//		
//		Admin admin = adminRepository.findByEmail(email);
//		
//		if(admin!=null) {
//			
//			return passwordEncoder.matches(password, admin.getPassword());
//		}
//		return false;
//	}
//	
	
	
	public boolean isEmailExist(Admin_DTO admin_DTO) {
		if(adminRepository.findByEmail(admin_DTO.getEmail()) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean userValidation(Admin_DTO admin_DTO) {
		
		Admin admin = adminRepository.findByEmail(admin_DTO.getEmail());
		
		if(admin!=null) {
			
			return passwordEncoder.matches(admin_DTO.getPassword(), admin.getPassword());
		}
		return false;
	}

	
}
	
	


