package com.backend.EMS.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.EMS.DTO.EmployeeDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.LoginDto;
import com.backend.EMS.DTO.LoginResponseDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.DTO.UpdateSkillsDto;
import com.backend.EMS.Exception.AnnotationValidation;
import com.backend.EMS.Service.AddEmployeeService;
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
     * @param employeeDto   The admin DTO containing registration details.
     * @param bindingResult The bindingResult containing validation errors.
     * @return A response DTO indicating the registration status.
     */
    @PostMapping("/adminRegistration")
    public final ResponseDto registerAdmin(@RequestBody @Valid
            final EmployeeDto employeeDto,
            final BindingResult bindingResult) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            // Set response values for validation errors
            throw new AnnotationValidation("Validation failed from backend");
        }
        // Call the service to add the admin
        return adminService.addAdmin(employeeDto);
    }

    /**
     * The AddEmployeeService instance used for managing
     *  add employee related operations.
     */
    @Autowired
    private AddEmployeeService addEmployeeService;

    /**
     * Endpoint to add a new employee.
     *
     * @param employeeDto   The admin DTO containing registration details.
     * @param bindingResult The bindingResult containing validation errors.
     * @return A response DTO indicating the registration status.
     */
    @PostMapping("/addEmployee")
    public final ResponseDto addEmployee(@RequestBody @Valid
            final EmployeeDto employeeDto,
            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Set response values for validation errors
            throw new AnnotationValidation("Validation failed from backend");
        }
        return addEmployeeService.addEmployee(employeeDto);
    }

    /**
     * Endpoint for admin login.
     *
     * @param loginDto The login DTO containing user's login details.
     * @return A response DTO indicating the login status.
     */
    @PostMapping("/login")
    public final LoginResponseDto login(@RequestBody final LoginDto loginDto) {
        return adminService.login(loginDto);
    }

    /**
     * Update employee details, including project and manager, by ID.
     *
     * @param id              The unique identifier of the employee.
     * @param updatedDetails  A map containing updated
     *  details like projectId and managerId.
     * @return A ResponseDto representing the result of the update operation.
     */
    @PutMapping("/updateEmployee/{id}")
    public final ResponseDto updateEmployee(@PathVariable final Long id,
            @RequestBody final Map<String, Long> updatedDetails) {
        Long projectId = updatedDetails.get("projectId");
        Long managerId = updatedDetails.get("managerId");
        return addEmployeeService.updateEmployee(id, projectId, managerId);
    }

    /**
     * Retrieve a list of all employees, including managers, from the system.
     *
     * @return A list of EmployeeOutDto objects containing
     *  information for all employees and managers.
     */
    @GetMapping("/allManagersAndEmployees")
    public final List<EmployeeOutDto> getAllManagersAndEmployees() {
        return addEmployeeService.getAllEmployeesAndManagers();
    }

    /**
     * Retrieve an employee's information by their email address.
     *
     * @param email The email address of the employee to look up.
     * @return An EmployeeOutDto containing the informationt
     *  of the employee with the provided email address.
     */
    @GetMapping("/employee/email/{email}")
    public final EmployeeOutDto getByEmployeeEmail(@PathVariable
            final String email) {
        return addEmployeeService.getEmployeeByEmail(email);
    }

    /**
     * Update a skill record identified by its
     *  unique ID using an HTTP PUT request.
     *
     * This endpoint allows clients to update a specific skill record
     * by providing its unique identifier in the URL path
     *  and the updated data in the request body.
     *
     * @param id              The unique identifier of the
     *  skill record to be updated.
     * @param updateSkillsDto The data containing the updates
     *  to be applied to the skill record.
     * @return A ResponseDto representing the result of the update operation.
     */
    @PutMapping("/updateSkill/{id}")
    public final ResponseDto updateSkill(@PathVariable final Long id,
            @RequestBody final UpdateSkillsDto updateSkillsDto) {
        // Call the service to perform the skill record
//        update and return a response.
        return addEmployeeService.updateSkills(id, updateSkillsDto);
    }
}

