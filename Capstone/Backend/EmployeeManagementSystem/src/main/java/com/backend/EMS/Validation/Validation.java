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
import com.backend.EMS.Model.Project;
import com.backend.EMS.Model.RequestResource;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.EmployeeRepository;
import com.backend.EMS.Repository.ProjectRepository;
import com.backend.EMS.Repository.RequestResourceRepository;

import jakarta.validation.Valid;


/**
 *
 */
@Component
public class Validation {
    /**
     * instance for employee repository.
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * instance for project repository.
     */
    @Autowired
    private ProjectRepository projectRepository;
    /**
     * instance for requestResource repository.
     */
    @Autowired
    private RequestResourceRepository requestResourceRepository;
    /**
     * Instance passwrodEncoder for encoding passwords.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * the logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.
            getLogger(Validation.class);
    /**
     * check if email is already present.
     * @param employeeInDto The EmployeeinDto
     * @throwsUserAlreadyFound exception
     */
    public final void checkEmailExists(final EmployeeInDto employeeInDto) {
        if (employeeRepository.findByEmail(employeeInDto.
                getEmail()) != null) {
            LOGGER.error("Email id already exists");
            throw new UserAlreadyFound(
                    "Email id already exists");
        }
    }
    /**
     * Check if a role exists.
     *
     * @param role The role to check.
     * @throws UserNotFound If the role is not found, throw this exception.
     */
    public final void checkRoleExists(final String role) {
        try {
            Role role1 = Role.valueOf(role);
            return; // The role exists
        } catch (IllegalArgumentException e) {
            LOGGER.error("Role is not Present");
            throw new UserNotFound("Role not found");
        }
    }
    /**
     * check if employee id is already present.
     * @param employeeInDto employee id
     * @throwsFound exception
     */
    public final void checkEmployeeIdExists(final
            EmployeeInDto employeeInDto) {
        if (employeeRepository.findByEmpId(employeeInDto.
                getEmpId()) != null) {
            LOGGER.error("Employee id already exists");
                throw new UserAlreadyFound(
                        "Employee id already exists");
        }
    }
    /**
     * check if Contact Number is already present.
     * @param employeeInDto The EmployeeInDto
     * @throws Found exception
     */
    public final void checkContactNumberExists(final
            EmployeeInDto employeeInDto) {
        if (employeeRepository.findByContactNo(employeeInDto.
              getContactNo()) != null) {
            LOGGER.error("Contact Number already exists");
                throw new UserAlreadyFound(
                        "Contact Number already exists");
        }
    }
    /**
     * check if project name is already present.
     * @param projectInDto The ProjectInDto
     * @throwsFound exception
     */
    public final void checkProject(final ProjectInDto projectInDto) {
        if (projectRepository.findByProjectName(
                projectInDto.getProjectName()) != null) {
            LOGGER.error("Project already exists");
             throw new UserAlreadyFound(
                     "Project already exists");
         }
    }
    /**
     * check if email is not present.
     * @param loginInDto The LoginDto.
     * @throws Resource not found exception
     */
    public final void checkEmailNotExists(final LoginInDto loginInDto) {
        if (employeeRepository.findByEmail(loginInDto.getEmail()) == null) {
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
     * check to perform login operation.
     * @param loginInDto login dto containing login informations
     */
        public final void checkLogin(@Valid final LoginInDto loginInDto) {
            // TODO Auto-generated method stub
            checkEmailNotExists(loginInDto);
            Employee employee = employeeRepository
                    .findByEmail(loginInDto.getEmail());
            try {
            if (!passwordEncoder.matches(
                    decodePassword(loginInDto.getPassword()),
                    employee.getPassword())) {
               LOGGER.error("Invalid Password");
               throw new UserNotFound("Invalid Password");
            }
          } catch (IllegalArgumentException e) {
                LOGGER.error("Invalid encoded password: " + e.getMessage());
                throw new CustomException("Invalid Password");
            }
        }

        /**
         * check to perform admin operation.
         * @param empDto Employee dto containing admin informations
         */
        public final void checkAdmin(@Valid final EmployeeInDto empDto) {
            // TODO Auto-generated method stub
            checkEmailExists(empDto);
            checkEmployeeIdExists(empDto);
            checkContactNumberExists(empDto);
        }
        /**
         * check to perform add employee operation.
         * @param empDto employee dto containing employee informations
         */
        public final void checkEmployee(@Valid final EmployeeInDto empDto) {
            // TODO Auto-generated method stub
            checkEmailExists(empDto);
            checkEmployeeIdExists(empDto);
            checkContactNumberExists(empDto);
        }
        /**
         * Perform pattern-based validations and handle validation errors.
         *
         * This method checks for validation
         * errors in the provided {@link BindingResult}.
         * If validation errors are found, it
         * throws an {@link CustomException} exception
         * containing a string representation of the validation errors.
         *
         * @param bindingResult The {@link BindingResult}
         * containing validation results.
         * @throws CustomException If validation errors
         * are found, an exception is thrown
         *                             with details about the validation errors.
         */
        public final void patterValidations(final BindingResult bindingResult) {
            // Check for validation errors
            if (bindingResult.hasErrors()) {
                // Get validation errors as a String
                String validationErrors =
                        getValidationErrorsAsString(bindingResult);

                // Throw a custom exception with the validation errors
                LOGGER.error("Validation errors");
                throw new CustomException(validationErrors);
            }
        }
        /**
         * Get validation errors as a string.
         *
         * @param bindingResult The BindingResult containing validation errors.
         * @return A string representation of validation errors.
         */
        public final String getValidationErrorsAsString(final
                BindingResult bindingResult) {
            StringBuilder errorBuilder = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorBuilder.append("Field: ").append(error.getField());
                errorBuilder.append(", Error: ").append(error.
                        getDefaultMessage()).append("\n");
            }
            return errorBuilder.toString();
        }
        /**
         * Check if an employee exists by ID.
         *
         * @param id The ID of the employee to check.
         * @throws UserNotFound If the employee does not
         * exist, throw this exception.
         */
        public final void checkEmployeeExists(final Long id) {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null || employee.getRole() == Role.Admin) {
                LOGGER.error("Employee does not exists");
                throw new UserNotFound("employee not exists");
            }
        }
        /**
         * Check if a project exists by ID.
         *
         * @param id The ID of the project to check.
         * @throws UserNotFound If the project does
         * not exist, throw this exception.
         */
        public final void checkProjectExists(final Long id) {
           Project project = projectRepository.findById(id).orElse(null);
            if (project == null) {
                LOGGER.error("Project does not exist");
                throw new UserNotFound("Project Not Found");
            }
        }
        /**
         * Check if a manager exists by ID.
         *
         * @param id The ID of the manager to check.
         * @throws UserNotFound If the manager does not
         * exist, throw this exception.
         */
        public final void checkManagerExists(final Long id) {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null) {
                LOGGER.error("Employee does not exists");
                throw new UserNotFound("Employee not exists");
            } else if (employee.getRole() != Role.Manager) {
                LOGGER.error("Manager does not exists");
                throw new UserNotFound("Manager does not exists");
            }
        }
        /**
         * Check if a request resource exists by ID.
         *
         * @param id The ID of the request resource to check.
         * @throws UserNotFound If the request resource
         * does not exist, throw this exception.
         */
        public final void checkRequestResourceExists(final Long id) {
            RequestResource requestResource = requestResourceRepository.
                    findById(id).orElse(null);
            if (requestResource == null) {
                LOGGER.error("RequestResource does not Exists");
                throw new UserNotFound("RequestResource not Exists");
            }
        }
        /**
         * Check if an employee exists by ID.
         *
         * @param id The ID of the employee to check.
         * @throws UserNotFound If the employee
         * does not exist, throw this exception.
         */
        public final void checkOnlyEmployeeExists(final Long id) {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null || employee.getRole() != Role.Employee) {
                LOGGER.error("Employee does not exists");
                throw new UserNotFound("employee not exists");
            }
        }

}

