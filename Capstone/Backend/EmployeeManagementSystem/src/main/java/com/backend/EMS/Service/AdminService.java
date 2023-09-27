package com.backend.EMS.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.RequestResourceOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.UserAlreadyFound;
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
/**
 * This class provides services for managing administrative user data and
 * authentication-related functionality. It uses an
 * {@link EmployeeRepository} for
 * data storage and retrieval and a {@link BCryptPasswordEncoder} for secure
 * password encoding and verification.
 */
@Service

public class AdminService {
    /**
     * The repository for administrator data.
     */
    @Autowired
    private  EmployeeRepository employeeRepository;
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
     * @param employeeInDto The EmployeeInDto containing
     * admin user details to be added.
     * @return True if the admin user was added successfully;
     * otherwise, false.
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

    public final List<RequestResourceOutDto> requestedResource() {
        List<RequestResource> requestResourceList = this.requestResourceRepository.findAll();
//        RequestResourceOutDto requestResourceOutDto = new RequestResourceOutDto();
        List<RequestResourceOutDto> RequestResourceOutDtoList = new ArrayList<>();
        for (RequestResource requestResource : requestResourceList) {
            RequestResourceOutDto requestResourceOutDto = new RequestResourceOutDto(); // Create a new instance for each iteration
            requestResourceOutDto.setId(requestResource.getId());
            requestResourceOutDto.setEmployeeId(requestResource.getEmployeeId());
            requestResourceOutDto.setManagerId(requestResource.getManagerId());
            requestResourceOutDto.setProjectId(requestResource.getProjectId());
            requestResourceOutDto.setComment(requestResource.getComment());
            
            Employee employee = this.employeeRepository.findById(requestResource.getEmployeeId()).get();
            requestResourceOutDto.setEmployeeEmpId(employee.getEmpId());
            requestResourceOutDto.setEmployeeName(employee.getName());
         
            Employee manager = this.employeeRepository.findById(requestResource.getManagerId()).get();
            requestResourceOutDto.setManagerEmpId(manager.getEmpId());
            requestResourceOutDto.setManagerName(manager.getName());

            Optional<Project> project = projectRepository.findById(requestResource.getProjectId());
            System.out.println(project);
            if(project.isPresent()) {
            requestResourceOutDto.setProjectName(project.get().getProjectName());
           
            RequestResourceOutDtoList.add(requestResourceOutDto);
            }
////            System.out.println(requestResourceOutDto + "praveen");
        }

        return RequestResourceOutDtoList;
        
    }
   
    public final ResponseDto DeleteRequestedResource(Long id) {
        requestResourceRepository.deleteById(id);
        return new ResponseDto("Deleted Successfuly");
    }

    public final ResponseDto AcceptRequestedResource(Long id) {
        RequestResource requestResource = this.requestResourceRepository.findById(id).get();
        Employee employee = this.employeeRepository.findById(requestResource.getEmployeeId()).get();
        employee.setProjectId(requestResource.getProjectId());
        employee.setManagerId(requestResource.getManagerId());
        Employee manager = this.employeeRepository.findById(requestResource.getManagerId()).get();
        employee.setManagerName(manager.getName());
        employeeRepository.save(employee);
        List<RequestResource> requestResourceList = this.requestResourceRepository.findByEmployeeId(employee.getId());
        for(RequestResource rR : requestResourceList) {
            requestResourceRepository.deleteById(rR.getId());
        }
        return new ResponseDto("Requested Resource Accepted");

    }

    public List<EmployeeOutDto> getFilteredEmployees(List<String> selectedSkills, boolean showUnassigned) {
        List<Employee> employees = employeeRepository.findByRole(Role.Employee);
        List<EmployeeOutDto> employeeOutDtoList = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeOutDto employeeOutDto = modelMapper.map(employee, EmployeeOutDto.class);
            
            // Check if the employee is assigned to a project and set the project name accordingly
            if (employee.getProjectId() == null) {
                employeeOutDto.setProjectName("N/A");
            } else {
                Project project = projectRepository.findById(employee.getProjectId()).orElse(null);
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

            // Check if the employee should be included based on showUnassigned
            if (showUnassigned && employee.getProjectId() != null) {
                addEmployee = false;
            }

//            // Check if the employee should be included based on selectedSkills
//            if (selectedSkills != null && !selectedSkills.isEmpty()) {
//                boolean hasAllSkills = employee.getSkills().containsAll(selectedSkills);
//                if (!hasAllSkills) {
//                    addEmployee = false;
//                }
//            }
         // Check if the employee should be included based on selectedSkills
            if (selectedSkills != null && !selectedSkills.isEmpty()) {
                boolean hasMatchingSkill = false;
                for (String skill : selectedSkills) {
                    if (employee.getSkills().contains(skill)) {
                        hasMatchingSkill = true;
                        break; // Exit the loop as soon as a matching skill is found
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
    
    public final ResponseDto unAssign(final Long id) {

        Employee employee = employeeRepository.findById(id).get();
        employee.setManagerId(1L);
        employee.setManagerName("Ankita");
        employee.setProjectId(null);
        employeeRepository.save(employee);
        return new ResponseDto("UnAssigned the project");
    }


}
