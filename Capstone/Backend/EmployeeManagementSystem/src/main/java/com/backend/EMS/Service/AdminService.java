package com.backend.EMS.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.backend.EMS.DTO.EmployeeDto;
import com.backend.EMS.DTO.LoginDto;
import com.backend.EMS.DTO.LoginResponseDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Exception.UserNotFound;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.AdminRepository;

/**
 * Service class for managing admin-related operations.
 */
/**
 * This class provides services for managing administrative user data and
 * authentication-related functionality. It uses an
 * {@link AdminRepository} for
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
     * @param passwordEncoder The encoder used to.
     * securely hash and verify passwords
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
    public AdminService(final AdminRepository adminRepositorys,
            final BCryptPasswordEncoder passwordEncoders) {
        this.adminRepository = adminRepositorys;
        this.passwordEncoder = passwordEncoders;
    }

    /**
     * Adds a new admin user to the system.
     *
     * @param employeeDto The EmployeeDto containing
     * admin user details to be added.
     * @return True if the admin user was added successfully;
     * otherwise, false.
     * @throws UserAlreadyFound if an admin user with the
     * same email, employee ID,
     *                          or contact number already exists.
     */
    public final ResponseDto addAdmin(final EmployeeDto employeeDto) {
        // Initialize a response DTO
        ResponseDto responseDto = new ResponseDto();
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setEmpId(employeeDto.getEmpId());
        employee.setDob(employeeDto.getDob());
        employee.setDoj(employeeDto.getDoj());
        employee.setLocation(employeeDto.getLocation());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setContactNo(employeeDto.getContactNo());
        employee.setRole(Role.Admin);

        employee.setPassword(employeeDto.getPassword());
//        admin.setConfirmPassword(adminDto.getConfirmPassword());
        if (adminRepository.findByEmail(employeeDto.getEmail()) != null) {
//            System.out.println("inside findbyemail");

            throw new UserAlreadyFound("Email is already registered");
        }
        if (adminRepository.findByEmpId(employeeDto.getEmpId()) != null) {

            throw new UserAlreadyFound("EmpId is already registered");
        }
        if (adminRepository.findByContactNo(employeeDto.
                getContactNo()) != null) {
            throw new UserAlreadyFound("ContactNo is already registered");
        }

        adminRepository.save(employee);

        responseDto.setMessage("Admin Registered successfully");
        return responseDto;

    }

    /**
     * Decodes a Base64-encoded password.
     *
     * @param pwd The Base64-encoded password to decode.
     * @return The decoded password as a plain text String.
     */
    public static String decodePassword(final String pwd) {
        byte[] decodeBytes = Base64.getDecoder().decode(pwd);
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
        Employee employee = adminRepository.findByEmail(loginDto.getEmail());

        if (employee != null) {
            return passwordEncoder.matches(decodePassword(
                    loginDto.getPassword()), employee.getPassword());
        }
        return false;
    }

    /**
     * Authenticate a user based on the provided login information.
     *
     * @param loginDto The LoginDto containing user login credentials.
     * @return A LoginResponseDto containing the authentication result.
     */
    public final Role getRoleByEmial(final LoginDto loginDto) {
        Employee user = adminRepository.findByEmail(loginDto.getEmail());
        return user.getRole();
    }

    /**
     * Authenticate a user based on the provided login information.
     *
     * @param loginDto The LoginDto containing user login credentials.
     * @return A LoginResponseDto containing the authentication result.
     */
    public final LoginResponseDto login(final LoginDto loginDto) {
        // Initialize a response DTO
        LoginResponseDto loginResponseDto = new LoginResponseDto();
//        Long id = adminRepository.findIdByEmail(loginDto.getEmail());

        // Check if the email exists
        if (isEmailExist(loginDto)) {
            // Validate the user's credentials
            if (userValidation(loginDto)) {
                // Set response values for successful login
                loginResponseDto.setRole(getRoleByEmial(loginDto));
                loginResponseDto.setEmail(loginDto.getEmail());
                loginResponseDto.setMessage("Login Successful");
            } else {
                throw new UserNotFound("Incorrect Password");
            }
        } else {
            throw new UserNotFound("Email is not registered");
        }
        return loginResponseDto;
    }
}
