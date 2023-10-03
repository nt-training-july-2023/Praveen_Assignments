package com.backend.EMS.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.RequestResourceOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Model.RequestResource;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.EmployeeRepository;
import com.backend.EMS.Repository.ProjectRepository;
import com.backend.EMS.Repository.RequestResourceRepository;

/**
 * Service class for managing admin-related operations.
 */
@Service

public class AdminService {
    /**
     * The repository for administrator data.
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * The repository for RequestResource.
     */
    @Autowired
    private RequestResourceRepository requestResourceRepository;
    /**
     * The repository for Projetc.
     */
    @Autowired
    private ProjectRepository projectRepository;
    /**
     * ModelMapper used for mapping from dto to entity.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Adds a new admin user to the system.
     *
     * @param employeeInDto The EmployeeInDto containing admin
     * user details to be
     *                      added.
     * @return True if the admin user was added successfully; otherwise, false.
     * @throws UserAlreadyFound if an admin user with the
     * same email, employee ID,
     *                          or contact number already exists.
     */
    public final ResponseDto addAdmin(final EmployeeInDto employeeInDto) {
        // Initialize a response DTO
        ResponseDto responseDto = new ResponseDto();
        Employee employee = new Employee();
        employee.setName(employeeInDto.getName());
        employee.setEmail(employeeInDto.getEmail());
        employee.setEmpId(employeeInDto.getEmpId());
        employee.setDob(employeeInDto.getDob());
        employee.setDoj(employeeInDto.getDoj());
        employee.setLocation(employeeInDto.getLocation());
        employee.setDesignation(employeeInDto.getDesignation());
        employee.setContactNo(employeeInDto.getContactNo());
        employee.setRole(Role.Admin);

        employee.setPassword(employeeInDto.getPassword());
        employeeRepository.save(employee);
        responseDto.setMessage("Admin Registered successfully");
        return responseDto;

    }

    /**
     * Authenticate a user based on the provided login information.
     *
     * @param loginInDto The LoginInDto containing user login credentials.
     * @return A LoginOutDto containing the authentication result.
     */
    public final Role getRoleByEmial(final LoginInDto loginInDto) {
        Employee user = employeeRepository.findByEmail(loginInDto.getEmail());
        return user.getRole();
    }

    /**
     * Retrieve a list of requested resources as RequestResourceOutDto objects.
     *
     * @return A list of RequestResourceOutDto objects
     * representing the requested
     *         resources.
     */
    public final List<RequestResourceOutDto> requestedResource() {
        List<RequestResource> requestResourceList = this.
                requestResourceRepository.findAll();
        System.out.println(requestResourceList + "praveen");
//        RequestResourceOutDto requestResourceOutDto = new
//        RequestResourceOutDto();
        List<RequestResourceOutDto> requestResourceOutDtoList = new
                ArrayList<>();
        for (RequestResource requestResource : requestResourceList) {
            RequestResourceOutDto requestResourceOutDto = new
                    RequestResourceOutDto();
            // Create a new instance for each iteration
            requestResourceOutDto.setId(requestResource.getId());
            requestResourceOutDto.
            setEmployeeId(requestResource.getEmployeeId());
            requestResourceOutDto.setManagerId(requestResource.getManagerId());
            requestResourceOutDto.setProjectId(requestResource.getProjectId());
            requestResourceOutDto.setComment(requestResource.getComment());
            Employee employee = this.employeeRepository.
                    findById(requestResource.getEmployeeId()).get();
            requestResourceOutDto.setEmployeeEmpId(employee.getEmpId());
            requestResourceOutDto.setEmployeeName(employee.getName());
            Employee manager = this.employeeRepository.
                    findById(requestResource.getManagerId()).get();
            requestResourceOutDto.setManagerEmpId(manager.getEmpId());
            requestResourceOutDto.setManagerName(manager.getName());

            Optional<Project> project = projectRepository.
                    findById(requestResource.getProjectId());
            System.out.println(project);
            if (project.isPresent()) {
                requestResourceOutDto.setProjectName(project.get()
                        .getProjectName());
                requestResourceOutDtoList.add(requestResourceOutDto);
            }
////            System.out.println(requestResourceOutDto + "praveen");
        }

        return requestResourceOutDtoList;
    }

    /**
     * Deletes a requested resource by its ID.
     *
     * @param id The ID of the requested resource to be deleted.
     * @return A ResponseDto indicating the status of the
     * resource deletion process.
     */
    public final ResponseDto deleteRequestedResource(final Long id) {
        requestResourceRepository.deleteById(id);
        return new ResponseDto("Deleted Successfuly");
    }

    /**
     * Accepts a requested resource and assigns it to an employee.
     *
     * @param id The ID of the requested resource to be accepted.
     * @return A ResponseDto indicating the status of the resource acceptance
     *         process.
     */
    public final ResponseDto acceptRequestedResource(final Long id) {
        RequestResource requestResource = this.
                requestResourceRepository.findById(id).get();
        Employee employee = this.employeeRepository.
                findById(requestResource.getEmployeeId()).get();
        employee.setProjectId(requestResource.getProjectId());
        employee.setManagerId(requestResource.getManagerId());
        Employee manager = this.employeeRepository.
                findById(requestResource.getManagerId()).get();
        employee.setManagerName(manager.getName());
        employeeRepository.save(employee);
        List<RequestResource> requestResourceList = this.
                requestResourceRepository.findByEmployeeId(employee.getId());
        for (RequestResource rR : requestResourceList) {
            requestResourceRepository.deleteById(rR.getId());
        }
        return new ResponseDto("Requested Resource Accepted");
    }

    /**
     * Retrieve a list of filtered employees based on selected
     * skills and an option
     * to include unassigned employees.
     *
     * @param selectedSkills A list of skills to filter employees by.
     * @param showUnassigned A flag indicating whether to include unassigned
     *                       employees in the result.
     * @return A list of EmployeeOutDto objects representing filtered employees.
     */
    public final List<EmployeeOutDto> getFilteredEmployees(final
            List<String> selectedSkills,
            final boolean showUnassigned) {
        List<Employee> employees = employeeRepository.
                findByRole(Role.Employee);
        List<EmployeeOutDto> employeeOutDtoList = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeOutDto employeeOutDto = modelMapper.map(employee,
                    EmployeeOutDto.class);
            // Check if the employee is assigned to a project and
//            set the project name accordingly
            if (employee.getProjectId() == null) {
                employeeOutDto.setProjectName("N/A");
            } else {
                Project project = projectRepository.findById(employee.
                        getProjectId()).orElse(null);
                if (project != null) {
                    employeeOutDto.setProjectName(project.getProjectName());
                } else {
                    employeeOutDto.setProjectName("N/A");
                }
            }

            employeeOutDtoList.add(employeeOutDto);
        }

        List<EmployeeOutDto> filteredEmployees = new ArrayList<>();

        for (EmployeeOutDto employee : employeeOutDtoList) {
            boolean addEmployee = true;

            // Check if the employee should be included
//            based on showUnassigned
            if (showUnassigned && employee.getProjectId() != null) {
                addEmployee = false;
            }
            // Check if the employee should be included based on selectedSkills
            if (selectedSkills != null && !selectedSkills.isEmpty()) {
                boolean hasMatchingSkill = false;
                for (String skill : selectedSkills) {
                    if (employee.getSkills().contains(skill)) {
                        hasMatchingSkill = true;
                        break; // Exit the loop as soon as
//                        a matching skill is found
                    }
                }
                if (!hasMatchingSkill) {
                    addEmployee = false;
                }
            }

            // Add the employee to the filtered list if it meets the criteria
            if (addEmployee) {
                filteredEmployees.add(employee);
            }
        }

        return filteredEmployees;
    }

    /**
     * Unassigns a project from an employee.
     *
     * @param id The ID of the employee whose project is to be unassigned.
     * @return A ResponseDto indicating the status of the unassignment process.
     */
    public final ResponseDto unAssign(final Long id) {
        // Find the employee by their ID
        Employee employee = employeeRepository.findById(id).get();
        // Set the employee's manager ID to 1 and manager name to "Ankita"
        employee.setManagerId(1L);
        employee.setManagerName("Ankita");
        // Clear the employee's project ID
        employee.setProjectId(null);
        // Save the updated employee information
        employeeRepository.save(employee);
        // Return a ResponseDto with a message indicating
//        the project has been unassigned
        return new ResponseDto("Unassigned the project");
    }

}
