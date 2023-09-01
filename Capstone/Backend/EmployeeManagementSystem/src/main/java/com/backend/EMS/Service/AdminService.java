package com.backend.EMS.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.EMS.DTO.AdminDto;
import com.backend.EMS.DTO.LoginDto;
import com.backend.EMS.Exception.userAlreadyFound;
import com.backend.EMS.Model.Admin;
import com.backend.EMS.Repository.AdminRepository;

/**
 * Service class for managing admin-related operations.
 */
/**
 * This class provides services for managing administrative user data and
 * authentication-related functionality. It uses an {@link AdminRepository} for
 * data storage and retrieval and a {@link BCryptPasswordEncoder} for secure
 * password encoding and verification.
 */
@Service
public class AdminService {
    /**
     * The repository for administrator data.
     */
    private final AdminRepository adminRepository;
    /**
     * @param passwordEncoder The encoder used to securely hash and verify passwords
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructs a new instance of the AdminManager class.
     *
     * @param adminRepositorys The repository used for accessing and managing
     *                         administrative user data.
     * @param passwordEncoders The encoder used to securely hash and verify
     *                         passwords.
     */
    @Autowired
    public AdminService(final AdminRepository adminRepositorys,BCryptPasswordEncoder passwordEncoders) {
        this.adminRepository = adminRepositorys;
        this.passwordEncoder = passwordEncoders;
    }

    /**
     * Add a new admin to the database.
     *
     * @param adminDto The adminDto
     */
    public final void addAdmin(final AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setName(adminDto.getName());
        admin.setEmail(adminDto.getEmail());
        admin.setEmpId(adminDto.getEmpId());
        admin.setDob(adminDto.getDob());
        admin.setDoj(adminDto.getDoj());
        admin.setLocation(adminDto.getLocation());
        admin.setDesignation(adminDto.getDesignation());
        admin.setContactNo(adminDto.getContactNo());

        admin.setPassword(adminDto.getPassword());
        admin.setConfirmPassword(adminDto.getConfirmPassword());
        if (adminRepository.findByEmail(adminDto.getEmail()) != null) {
//            System.out.println("inside findbyemail");

            throw new userAlreadyFound("Email is already registered");
        }
        if (adminRepository.findByEmpId(adminDto.getEmpId()) != null) {

            throw new userAlreadyFound("EmpId is already registered");
        }
        if (adminRepository.findByContactNo(adminDto.getContactNo()) != null) {
            throw new userAlreadyFound("ContactNo is already registered");
        }

        adminRepository.save(admin);
//        List<Admin> admins = adminRepository.findAll();
//        System.out.println(admins);
//        if (!admins.isEmpty()) {
//            return true;
//        }
//        else {
//           adminRepository.save(admin);
//           return false;
//        }

    }
    
    public static String decodePassword(String pwd) {
        byte[] decodeBytes =Base64.getDecoder().decode(pwd);
        return new String(decodeBytes, StandardCharsets.UTF_8);
        
    }

    /**
     * Check if an admin with the given email exists.
     *
     * @param loginDto The login DTO containing user's login details.
     * @return True if an admin with the email exists, false otherwise.
     */
    public final boolean isEmailExist(final LoginDto loginDto) {
        return adminRepository.findByEmail(loginDto.getEmail()) != null;
    }

    /**
     * Validate the user's credentials for login.
     *
     * @param loginDto The login DTO containing user's login details.
     * @return True if the credentials are valid, false otherwise.
     */
    public final boolean userValidation(final LoginDto loginDto) {
        Admin admin = adminRepository.findByEmail(loginDto.getEmail());

        if (admin != null) {
            return passwordEncoder.matches(decodePassword(loginDto.getPassword()), admin.getPassword());
        }
        return false;
    }
}
