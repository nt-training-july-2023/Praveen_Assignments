package com.backend.EMS.Controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.backend.EMS.Constants.InvokeConstants;
import com.backend.EMS.Constants.SuccessConstants;
import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.IsRequestedInDto;
import com.backend.EMS.DTO.IsRequestedOutDto;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.LoginOutDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.RequestResourceInDto;
import com.backend.EMS.DTO.RequestResourceOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.DTO.UpdateSkillsDto;
import com.backend.EMS.Service.AdminService;
import com.backend.EMS.Service.EmployeeService;
import com.backend.EMS.Service.LoginService;
import com.backend.EMS.Service.ProjectService;
import com.backend.EMS.Validation.Validation;

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
     * @return A response DTO indicating the registration status.
     */
    @PostMapping("/admin-registration")
    public final ResponseDto registerAdmin(@RequestBody @Valid
            final EmployeeInDto employeeInDto
            ) {
        validation.checkAdmin(employeeInDto);
        LOGGER.info(InvokeConstants.REGISTERADMIN);
        ResponseDto responseDto = adminService.addAdmin(employeeInDto);
        LOGGER.info(SuccessConstants.ADDED);
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
     * @return A response DTO indicating the registration status.
     */
    @PostMapping("/admin/add-employee")
    public final ResponseDto addEmployee(@RequestBody @Valid
            final EmployeeInDto employeeInDto) {
        validation.checkEmployee(employeeInDto);
        LOGGER.info(InvokeConstants.ADDEMPLOYEE);
        ResponseDto responseDto = employeeService.addEmployee(employeeInDto);
        LOGGER.info(SuccessConstants.ADDED);
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
        validation.checkRoleExists(roleName);
        LOGGER.info(InvokeConstants.GETEMPLOYEESBYROLE);
        List<EmployeeOutDto> employeeOutDto = employeeService.
                getEmployeesByRole(roleName);
        LOGGER.info(SuccessConstants.GETEMPLOYEESBYROLE);
        return employeeOutDto;
    }
    /**
     * Endpoint for admin login.
     *
     * @param loginInDto The login DTO containing user's login details.
     * @return A response DTO indicating the login status.
     */
    @PostMapping("/login")
    public final LoginOutDto login(@RequestBody @Valid final
            LoginInDto loginInDto) {
        LOGGER.info(InvokeConstants.LOGIN);
        validation.checkLogin(loginInDto);
        LoginOutDto loginOutDto = loginService.login(loginInDto);
        LOGGER.info(SuccessConstants.LOGINSUCCESS);
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
    @PutMapping("/admin/assign-project/{id}")
    public final ResponseDto assignProject(@PathVariable final Long id,
            @RequestBody final  Map<String, Long> updatedDetails) {
        Long projectId = updatedDetails.get("projectId");
        Long managerId = updatedDetails.get("managerId");
        validation.assignProject(id, managerId, projectId);
        LOGGER.info(InvokeConstants.ASSIGNPROJECT);
        ResponseDto responseDto = employeeService.updateEmployee(id,
                updatedDetails);
        LOGGER.info(SuccessConstants.UPDATED);
        return responseDto;
    }
    /**
     * Retrieve a list of all employees, including managers, from the system.
     *
     * @return A list of EmployeeOutDto objects containing
     *  information for all employees and managers.
     */
    @GetMapping("/managers-employees")
    public final List<EmployeeOutDto> getAllManagersAndEmployees() {
        LOGGER.info(InvokeConstants.GETALLMANAGERSANDEMPLOYEES);
        List<EmployeeOutDto>  employeeOutDto = employeeService.
                getAllEmployeesAndManagers();
        LOGGER.info(SuccessConstants.GETALLMANAGERSANDEMPLOYEES);
        return employeeOutDto;
    }
    /**
     * Get a list of projects managed by a specific manager.
     *
     * @param managerId The ID of the manager to filter projects by.
     * @return A list of ProjectOutDto objects representing
     *  projects managed by the specified manager.
     */
    @GetMapping("/projects/{managerId}")
    public final List<ProjectOutDto> getAllByManagerId(@PathVariable
            final Long managerId) {
        validation.checkManagerExists(managerId);
        LOGGER.info(InvokeConstants.GETALLBYMANAGERID);
        List<ProjectOutDto> projectOutDto = projectService.
                getAllByManagerId(managerId);
        LOGGER.info(SuccessConstants.GETALLBYMANAGERID);
        return projectOutDto;
    }
    /**
     * Retrieve an employee's information by their email address.
     *
     * @param id The id of the employee to look up.
     * @return An EmployeeOutDto containing the informationt
     *  of the employee with the provided id.
     */
    @GetMapping("/employee/{id}")
    public final EmployeeOutDto getByEmployeeId(@PathVariable
            final Long id) {
        validation.checkEmployeeExists(id);
        LOGGER.info(InvokeConstants.GETBYEMPLOYEEID);
        EmployeeOutDto employeeOutDto = employeeService.getEmployeeById(id);
        LOGGER.info(SuccessConstants.GETBYEMPLOYEEID);
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
    @PutMapping("/employee/update-skill/{id}")
    public final ResponseDto updateSkill(@PathVariable final Long id,
            @RequestBody @Valid final UpdateSkillsDto updateSkillsDto
              ) {
        validation.checkEmployeeExists(id);
        LOGGER.info(InvokeConstants.UPDATESKILLS);
        ResponseDto responseDto = employeeService.updateSkills(id,
                updateSkillsDto);
        LOGGER.info(SuccessConstants.SKILLS_ADDED);
        return responseDto;
    }
    /**
     * Handles HTTP POST requests to request a resource
     * using the provided data.
     *
     * @param requestResourceInDto The data for the resource request.
     * @return A ResponseDto indicating the status of the request.
     */
    @PostMapping("/manager/request-resource")
    public final ResponseDto requestResource(@RequestBody
            @Valid final RequestResourceInDto requestResourceInDto) {
        LOGGER.info(InvokeConstants.REQUESTRESOURCE);
        ResponseDto responseDto = employeeService.
                requestResource(requestResourceInDto);
        LOGGER.info(SuccessConstants.REQUESTRESOURCE);
        return responseDto;
    }
    /**
     * Handles HTTP GET requests to retrieve a list of requested resources.
     *
     * @return A list of RequestResourceOutDto objects
     * representing the requested resources.
     */
    @GetMapping("/admin/requested-resources")
    public final List<RequestResourceOutDto> requestedResource() {
        LOGGER.info(InvokeConstants.REQUESTEDRESOURCE);
        List<RequestResourceOutDto> requestResourceOutDto = adminService.
                requestedResource();
        LOGGER.info(SuccessConstants.REQUESTEDRESOURCE);
        return requestResourceOutDto;
    }
    /**
     * Handles HTTP DELETE requests to delete a requested resource.
     *
     * @param id The ID of the requested resource to be deleted.
     * @return A ResponseDto containing the result of the
     * resource deletion operation.
     */
    @DeleteMapping("/admin/requested-resources/reject/{id}")
    public final ResponseDto deleteRequestedResource(@PathVariable
            final Long id) {
        validation.checkRequestResourceExists(id);
        LOGGER.info(InvokeConstants.DELETEREQUESTEDRESOURCE);
        ResponseDto responseDto = adminService.deleteRequestedResource(id);
        LOGGER.info(SuccessConstants.DELETE);
        return responseDto;
    }
    /**
     * Handles HTTP PUT requests to accept a requested resource.
     *
     * @param id The ID of the requested resource to be accepted.
     * @return A ResponseDto containing the result of the
     * resource acceptance operation.
     */
    @PutMapping("/admin/requested-resources/accept/{id}")
    public final ResponseDto acceptRequestedResource(@PathVariable
            final Long id) {
        validation.checkRequestResourceExists(id);
        LOGGER.info(InvokeConstants.ACCEPTREQUESTEDRESOURCE);
        ResponseDto responseDto = adminService.acceptRequestedResource(id);
        LOGGER.info(SuccessConstants.ACCEPT);
        return responseDto;
    }
    /**
     * Handles HTTP POST requests to check if a request is requested.
     *
     * @param isRequestedInDto The data transfer object (DTO)
     * containing information about the request.
     * @return A boolean value indicating whether the request is requested.
     */
    @PostMapping("/manager/employee/is-requested")
    public final IsRequestedOutDto isRequested(@RequestBody @Valid final
            IsRequestedInDto isRequestedInDto) {
        validation.checkManagerExists(isRequestedInDto.getId());
        validation.checkOnlyEmployeeExists(isRequestedInDto.getEmployeeId());
        LOGGER.info(InvokeConstants.ISREQUESTED);
        IsRequestedOutDto isRequestedOutDto = employeeService.
                isRequested(isRequestedInDto);
        LOGGER.info(SuccessConstants.ISREQUESTED);
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
    @GetMapping("/manager/filtered-employees")
    public final List<EmployeeOutDto> getFilteredEmployees(
            @RequestParam (value = "selectedSkills", required = false)
            final List<String> selectedSkills,
            @RequestParam(value = "showUnassigned", defaultValue = "false")
            final boolean shownUnassigned) {
        LOGGER.info(InvokeConstants.GETFILTEREDEMPLOYEES);
        List<EmployeeOutDto> employeeOutDto = adminService.
                getFilteredEmployees(selectedSkills,
                shownUnassigned);
        LOGGER.info(SuccessConstants.GETFILTEREDEMPLOYEES);
        return employeeOutDto;
    }
    /**
     * Handles HTTP PUT requests to unAssign a project.
     *
     * @param id The ID of the project to unAssign.
     * @return A ResponseDto containing the result of
     * the unAssignment operation.
     */
    @PutMapping("/admin/employee/unassign/project/{id}")
    public final ResponseDto unAssign(@PathVariable final Long id) {
        validation.checkOnlyEmployeeExists(id);
        LOGGER.info(InvokeConstants.UNASSIGNPROJECT);
        ResponseDto responseDto = adminService.unAssign(id);
        LOGGER.info(SuccessConstants.UNASSIGN);
        return responseDto;
    }
}

