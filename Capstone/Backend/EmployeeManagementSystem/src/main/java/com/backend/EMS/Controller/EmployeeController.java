package com.backend.EMS.Controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.IsRequestedDto;
import com.backend.EMS.DTO.IsRequestedOutDto;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.LoginOutDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.RequestResourceInDto;
import com.backend.EMS.DTO.RequestResourceOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.DTO.UpdateSkillsDto;
import com.backend.EMS.Service.EmployeeService;
import com.backend.EMS.Service.LoginService;
import com.backend.EMS.Service.ProjectService;
import com.backend.EMS.Validation.Validation;

import com.backend.EMS.Service.AdminService;


import jakarta.validation.Valid;

/**
 * Controller class for handling admin-related operations.
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class EmployeeController {
    /**
     * The LoginService instance used for managing login operations.
     */
    @Autowired
    private LoginService loginService;
    /**
     * The AdminService instance used for managing admin-related operations.
     */
    @Autowired
    private AdminService adminService;

    /**
     * The Validation instance used for validating operations.
     */
    @Autowired
    private Validation validation;
    /**
     * Autowired service for adding projects.
     */
    @Autowired
    private ProjectService projectService;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(EmployeeController.class);


    /**
     * Endpoint to register a new admin.
     *
     * @param employeeInDto   The admin DTO containing registration details.
     * @param bindingResult The bindingResult containing validation errors.
     * @return A response DTO indicating the registration status.
     */
    @PostMapping("/adminRegistration")
    public final ResponseDto registerAdmin(@RequestBody @Valid
            final EmployeeInDto employeeInDto, final BindingResult bindingResult
            ) {
        validation.checkAdmin(employeeInDto);
        // Check for validation errors
        validation.patterValidations(bindingResult);
        LOGGER.info("Register Admin method Invoked");
        ResponseDto responseDto =adminService.addAdmin(employeeInDto);
        LOGGER.info("Admin Registered Successful");
        return responseDto;
    }

    /**
     * The EmployeeService instance used for managing
     *  add employee related operations.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Endpoint to add a new employee.
     *
     * @param employeeInDto   The admin DTO containing registration details.
     * @param bindingResult The bindingResult containing validation errors.
     * @return A response DTO indicating the registration status.
     */
    @PostMapping("/addEmployee")
    public final ResponseDto addEmployee(@RequestBody @Valid
            final EmployeeInDto employeeInDto,
            final BindingResult bindingResult) {
        validation.checkEmployee(employeeInDto);
        validation.patterValidations(bindingResult);
        LOGGER.info("Add Admin method Invoked");
        ResponseDto responseDto = employeeService.addEmployee(employeeInDto);
        LOGGER.info("Admin Added successful");
        return responseDto;
    }
    /**
     * Get a list of employees by their role.
     *
     * @param roleName The role name to filter employees by.
     * @return A list of EmployeeOutDto objects representing
     *  employees with the specified role.
     */
    @GetMapping("/all/{roleName}")
    public final List<EmployeeOutDto> getEmployeesByRole(@PathVariable
            final String roleName) {
        LOGGER.info("getEmployeesByRole method Invoked");
        List<EmployeeOutDto> employeeOutDto = employeeService.getEmployeesByRole(roleName);
        LOGGER.info("Retrieved Employees by role");
        return employeeOutDto;
    }

    /**
     * Endpoint for admin login.
     *
     * @param loginInDto The login DTO containing user's login details.
     * @return A response DTO indicating the login status.
     */
    @PostMapping("/login")
    public final LoginOutDto login(@RequestBody final LoginInDto loginInDto) {
        LOGGER.info("Login Method Invoked");
        validation.checkLogin(loginInDto);
        LoginOutDto loginOutDto = loginService.login(loginInDto);
        LOGGER.info("Login Successful");
        return loginOutDto;
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
        LOGGER.info("Update Employee method invoked");
        ResponseDto responseDto = employeeService.updateEmployee(id, projectId, managerId);
        LOGGER.info("Employee Updated Successfully");
        return responseDto;
    }

    /**
     * Retrieve a list of all employees, including managers, from the system.
     *
     * @return A list of EmployeeOutDto objects containing
     *  information for all employees and managers.
     */
    @GetMapping("/allManagersAndEmployees")
    public final List<EmployeeOutDto> getAllManagersAndEmployees() {
        LOGGER.info("getAllEmployeeAndManagers method invoked");
        List<EmployeeOutDto>  employeeOutDto = employeeService.getAllEmployeesAndManagers();
        LOGGER.info("All managers and employees are retrieved successfully");
        return employeeOutDto;
    }
    /**
     * Get a list of projects managed by a specific manager.
     *
     * @param managerId The ID of the manager to filter projects by.
     * @return A list of ProjectOutDto objects representing
     *  projects managed by the specified manager.
     */
    @GetMapping("/projectCards/{managerId}")
    public final List<ProjectOutDto> getAllByManagerId(@PathVariable
            final Long managerId) {
        LOGGER.info("getAllProjects by Manager Id method invoked");
        List<ProjectOutDto> projectOutDto = projectService.getAllByManagerId(managerId);
        LOGGER.info("retrieved all projects of the manager successfully");
        return projectOutDto;
    }

    /**
     * Retrieve an employee's information by their email address.
     *
     * @param email The email address of the employee to look up.
     * @return An EmployeeOutDto containing the informationt
     *  of the employee with the provided email address.
     */
    @GetMapping("/employee/id/{id}")
    public final EmployeeOutDto getByEmployeeId(@PathVariable
            final Long id) {
        LOGGER.info("getByEmployeeId method invoked successfully");
        EmployeeOutDto employeeOutDto = employeeService.getEmployeeById(id);
        LOGGER.info("retrieved employee data by ID");
        return employeeOutDto;
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
        LOGGER.info("updateSkill method invoked");
        ResponseDto responseDto = employeeService.updateSkills(id, updateSkillsDto);
        LOGGER.info("Updated Skills successfully");
        return responseDto;
    }
//    /**
//     * Get an employee's name by their ID.
//     *
//     * @param employeeId The ID of the employee.
//     * @return An EmployeeNameDto object containing the employee's name.
//     */
//    @GetMapping("/employeeName/{employeeId}")
//    public final EmployeeNameDto getByEmployeeId(@PathVariable
//            final Long employeeId) {
//        return employeeService.getEmployeeById(employeeId);
//    }
    /**
     * Handles HTTP POST requests to request a resource
     * using the provided data.
     *
     * @param requestResourceInDto The data for the resource request.
     * @return A ResponseDto indicating the status of the request.
     */
    @PostMapping("/requestResource")
    public final ResponseDto requestResource(@RequestBody
            final RequestResourceInDto requestResourceInDto) {
        // Delegate the request to the service layer for processing
        LOGGER.info("request resource method invoked");
        ResponseDto responseDto = employeeService.requestResource(requestResourceInDto);
        LOGGER.info("resource requested successfully");
        return responseDto;
    }
    /**
     * Handles HTTP GET requests to retrieve a list of requested resources.
     *
     * @return A list of RequestResourceOutDto objects
     * representing the requested resources.
     */
    @GetMapping("/RequestedResource")
    public final List<RequestResourceOutDto> requestedResource() {
        LOGGER.info("requestedResource method invoked");
        List<RequestResourceOutDto> requestResourceOutDto = adminService.requestedResource();
        LOGGER.info("retrieved list of Resources Requested");
        return requestResourceOutDto;
    }
    /**
     * Handles HTTP DELETE requests to delete a requested resource.
     *
     * @param id The ID of the requested resource to be deleted.
     * @return A ResponseDto containing the result of the
     * resource deletion operation.
     */
    @DeleteMapping("/Delete/RequestedResource/{id}")
    public final ResponseDto deleteRequestedResource(@PathVariable
            final Long id) {
        LOGGER.info("Delete Requested Resource method invoked");
        ResponseDto responseDto = adminService.DeleteRequestedResource(id);
        LOGGER.info("requested Resource Deleted successfully");
        return responseDto;
    }
    /**
     * Handles HTTP PUT requests to accept a requested resource.
     *
     * @param id The ID of the requested resource to be accepted.
     * @return A ResponseDto containing the result of the
     * resource acceptance operation.
     */
    @PutMapping("/Accept/RequestedResource/{id}")
    public final ResponseDto acceptRequestedResource(@PathVariable
            final Long id) {
        ResponseDto responseDto = adminService.AcceptRequestedResource(id);
        return responseDto;
    }
    /**
     * Retrieve an employee's information by their Id.
     *
     * @param id The email address of the employee to look up.
     * @return An EmployeeOutDto containing the informationt
     *  of the employee with the provided id.
     */
    @GetMapping("/manager/id/{id}")
    public final EmployeeOutDto getManagerIdById(@PathVariable
            final Long id) {
        LOGGER.info("getManagerIdById method invoked");
        EmployeeOutDto employeeOutDto = employeeService.getMangerIdById(id);
        LOGGER.info("retrieved the manager Id by Id successfully");
        return employeeOutDto;
    }
    /**
     * Handles HTTP POST requests to check if a request is requested.
     *
     * @param isRequestedDto The data transfer object (DTO)
     * containing information about the request.
     * @return A boolean value indicating whether the request is requested.
     */
    @PostMapping("/isRequested")
    public final IsRequestedOutDto isRequested(@RequestBody final
            IsRequestedDto isRequestedDto) {
        LOGGER.info("isRequested method invoked");
        IsRequestedOutDto isRequestedOutDto = employeeService.IsRequested(isRequestedDto);
        LOGGER.info("retrieved isRequested information succesfully");
        return isRequestedOutDto;
    }
    /**
     * Handles HTTP GET requests to retrieve a list of filtered employees.
     *
     * @param selectedSkills A list of selected skills to
       filter employees by. (Optional)
     * @param shownUnassigned A boolean indicating whether to
       include unassigned employees. (Default: false)
     * @return A list of EmployeeOutDto objects representing
       the filtered employees.
     */  
    @GetMapping("/filteredEmployees")
    public final List<EmployeeOutDto> getFilteredEmployees(
            @RequestParam (value = "selectedSkills", required = false)
            final List<String> selectedSkills,
            @RequestParam(value = "showUnassigned", defaultValue = "false")
            final boolean shownUnassigned) {
        LOGGER.info("getFilteredEmployees method invoked");
        List<EmployeeOutDto> employeeOutDto = adminService.getFilteredEmployees(selectedSkills,
                shownUnassigned);
        LOGGER.info("retrieved filtered employees successfully");
        return employeeOutDto;
    }
    /**
     * Handles HTTP PUT requests to unAssign a project.
     *
     * @param id The ID of the project to unAssign.
     * @return A ResponseDto containing the result of
     * the unAssignment operation.
     */
    @PutMapping("/unAssign/Project/{id}")
    public final ResponseDto unAssign(@PathVariable final Long id) {
        LOGGER.info("unAssign method invoked");
        ResponseDto responseDto = adminService.unAssign(id);
        LOGGER.info("unassigned the project successfully");
        return responseDto;
    }
}

