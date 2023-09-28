package com.backend.EMS.Validation;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.ProjectInDto;
import com.backend.EMS.Exception.CustomException;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Exception.UserNotFound;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Repository.EmployeeRepository;
import com.backend.EMS.Repository.ProjectRepository;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 
 */
@Component
public class Validation {
    /**
     * instance for employee repository
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * instance for project repository
     */
    @Autowired
    private ProjectRepository projectRepository;
    /**
     * Instance passwrodEncoder for encoding passwords.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * the logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Validation.class);
    /**
     * check if email is already present
     * @param email employee email
     * @throwsFound exception
     */
    public void checkEmailExists(final EmployeeInDto employeeInDto) {
        if(employeeRepository.findByEmail(employeeInDto.getEmail()) != null) {
            LOGGER.error("Email id already exists");
            throw new UserAlreadyFound(
                    "Email id already exists");
        }
    }
    /**
     * check if employee id is already present
     * @param empId employee id
     * @throwsFound exception
     */
    public void checkEmployeeIdExists(final EmployeeInDto employeeInDto) {
        if (employeeRepository.findByEmpId(employeeInDto.getEmpId()) != null) {
            LOGGER.error("Employee id already exists");
                throw new UserAlreadyFound(
                        "Employee id already exists");
        }
    }
    /**
     * check if Contact Number is already present
     * @param contactNumber employee id
     * @throws Found exception
     */
    public void checkContactNumberExists(final EmployeeInDto employeeInDto) {
        if (employeeRepository.findByContactNo(employeeInDto.
              getContactNo()) != null) {
            LOGGER.error("Contact Number already exists");
                throw new UserAlreadyFound(
                        "Contact Number already exists");
        }
    }
    /**
     * check if project name is already present
     * @param name employee name
     * @throwsFound exception
     */
    public void checkProjecExists(final ProjectInDto projectInDto) {
        if(projectRepository.findByProjectName(
                projectInDto.getProjectName()) != null) {
            LOGGER.error("Project already exists");
             throw new UserAlreadyFound(
                     "Project already exists");
         }
    }
    /**
     * check if email is not present
     * @param email employee email
     * @throws Resource not found exception
     */
    public void checkEmailNotExists(final LoginInDto loginInDto) {
        
        if(employeeRepository.findByEmail(loginInDto.getEmail()) == null) {
            LOGGER.error("Invalid Email");
            throw new UserNotFound("Invalid Email");
        }
    }
    /**
     * decoding password using Base 64.
     * @param password password to be decoded.
     * @return Decoded password.
     */
    public static String decodePassword(final String password) {
        byte[] decodeBytes = Base64.getDecoder().decode(password);
        return new String(decodeBytes, StandardCharsets.UTF_8);
    }
    /**
     * check to perform login operation
     * @param loginInDto login dto containing login informations
     */
        public void checkLogin(@Valid LoginInDto loginInDto) {
            // TODO Auto-generated method stub
            checkEmailNotExists(loginInDto);
            Employee employee = employeeRepository
                    .findByEmail(loginInDto.getEmail());
            if (!passwordEncoder.matches(
                    decodePassword(loginInDto.getPassword()),
                    employee.getPassword())) {
               LOGGER.error("Invalid Password"); 
               throw new UserNotFound("Wrong Password");
            };
        }

        /**
         * check to perform admin operation
         * @param empDto Employee dto containing admin informations
         */
        public void checkAdmin(@Valid EmployeeInDto empDto) {
            // TODO Auto-generated method stub
            checkEmailExists(empDto);
            checkEmployeeIdExists(empDto);
            checkContactNumberExists(empDto);
        }
        /**
         * check to perform add employee operation
         * @param empDto employee dto containing employee informations
         */
        public void checkEmployee(@Valid EmployeeInDto empDto) {
            // TODO Auto-generated method stub
            checkEmailExists(empDto);
            checkEmployeeIdExists(empDto);
            checkContactNumberExists(empDto);
        }
        /**
         * Perform pattern-based validations and handle validation errors.
         *
         * This method checks for validation errors in the provided {@link BindingResult}.
         * If validation errors are found, it throws an {@link CustomException} exception
         * containing a string representation of the validation errors.
         *
         * @param bindingResult The {@link BindingResult} containing validation results.
         * @throws CustomException If validation errors are found, an exception is thrown
         *                             with details about the validation errors.
         */
        public void patterValidations(final BindingResult bindingResult) {
            // Check for validation errors
            if (bindingResult.hasErrors()) {
                // Get validation errors as a String
                String validationErrors = getValidationErrorsAsString(bindingResult);

                // Throw a custom exception with the validation errors
                LOGGER.error("Validation errors");
                throw new CustomException(validationErrors);
            }
        }

        private String getValidationErrorsAsString(BindingResult bindingResult) {
            StringBuilder errorBuilder = new StringBuilder();
            
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorBuilder.append("Field: ").append(error.getField());
                errorBuilder.append(", Error: ").append(error.getDefaultMessage()).append("\n");
            }
            
            return errorBuilder.toString();
        }

}