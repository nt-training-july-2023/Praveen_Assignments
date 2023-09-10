package com.backend.EMS.Service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.EMS.DTO.AdminDto;
import com.backend.EMS.Exception.userAlreadyFound;
import com.backend.EMS.Model.Admin;
import com.backend.EMS.Repository.AdminRepository;

@Service
public class AddEmployeeService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private BCryptPasswordEncoder PasswordEncoder;
    PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public final boolean addEmployee(AdminDto adminDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Admin admin = this.modelMapper.map(adminDto , Admin.class);

        if (adminRepository.findByEmail(adminDto.getEmail()) != null) {

            throw new userAlreadyFound("Email is already registered");
        }
        if (adminRepository.findByEmpId(adminDto.getEmpId()) != null) {

            throw new userAlreadyFound("EmpId is already registered");
        }
        if (adminRepository.findByContactNo(adminDto.getContactNo()) != null) {
            throw new userAlreadyFound("ContactNo is already registered");
        }
        
        adminRepository.save(admin);
        
        return true;

    }

}
