package com.backend.EMS.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.IsRequestedDto;
import com.backend.EMS.DTO.IsRequestedOutDto;
import com.backend.EMS.DTO.RequestResourceInDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.DTO.UpdateSkillsDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Model.RequestResource;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.EmployeeRepository;
import com.backend.EMS.Repository.ProjectRepository;
import com.backend.EMS.Repository.RequestResourceRepository;

/**
 * Service class for adding employees.
 */
@Service
public class EmployeeService {
    /**
     * Repository for managing Admin entities.
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    
    /**
     * Repository for managing RequestResource entities.
     */
    @Autowired
    private RequestResourceRepository requestResourceRepository;
    /**
     * ModelMapper for mapping between DTOs and entities.
     */
    @Autowired
    private ModelMapper modelMapper;
    /**
     * Repository for managing Project entities.
     */
    @Autowired
    private ProjectRepository projectRepository;


//    /**
//     * Constructs an EmployeeService with the necessary dependencies.
//     *
//     * @param adminsRepository The repository for managing Admin entities.
//     * @param modelMappers     The ModelMapper for mapping between DTOs and
//     *                         entities.
//     */
//    @Autowired
//    public EmployeeService(final EmployeeRepository adminsRepository,
//            final ModelMapper modelMappers) {
//        super();
//        this.adminRepository = adminsRepository;
//        this.modelMapper = modelMappers;
//    }

    /**
     * Add a new employee to the system.
     *
     * @param employeeInDto The EmployeeInDto containing
     * employee details to be added.
     * @return True if the employee was added successfully; otherwise, false.
     * @throws UserAlreadyFound if an employee with the
     * same email, employee ID, or
     *                          contact number already exists.
     */
    public final ResponseDto addEmployee(final EmployeeInDto employeeInDto) {
//        modelMapper.getConfiguration().setMatchingStrategy(
//                MatchingStrategies.STRICT);
        Employee employee = this.modelMapper.map(employeeInDto, Employee.class);
        employee.setManagerName("Ankita");
        employee.setManagerId(1L);

        employeeRepository.save(employee);
        // Initialize a responseDto
        ResponseDto responseDto = new ResponseDto();
        // Set response values for successful registration
        responseDto.setMessage("Employee Added successfully");
        return responseDto;
    }

    /**
     * Update employee information including project and manager.
     *
     * @param id        The ID of the employee to update.
     * @param projectId The ID of the project to assign to the employee.
     * @param managerId The ID of the manager for the employee.
     * @return A ResponseDto indicating the status of the update.
     */
    public final ResponseDto updateEmployee(final Long id,
            final Long projectId, final Long managerId) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        Employee manager = employeeRepository.findById(managerId).get();
        if (employee == null) {
            return new ResponseDto("Employee Not Found");
        } else {
            employee.setManagerId(managerId);
            employee.setProjectId(projectId);
            employee.setManagerName(manager.getName());
            employeeRepository.save(employee);

            return new ResponseDto("Updated Successfully");
        }
    }

    /**
     * Get a list of all employees and managers in the system.
     *
     * @return A list of EmployeeOutDto objects containing
     * employee and manager
     *         information.
     */
    public final List<EmployeeOutDto> getAllEmployeesAndManagers() {
        List<Role> roles = new ArrayList<>(List.of(Role.Manager,
                Role.Employee));
        List<Employee> employeesAndManagers =
                employeeRepository.findByRoleIn(roles);
        List<EmployeeOutDto> employeeOutDtoList = new ArrayList<>();
        for (Employee employee : employeesAndManagers) {
            EmployeeOutDto employeeOutDto = modelMapper.
                    map(employee, EmployeeOutDto.class);
            employeeOutDtoList.add(employeeOutDto);
        }
        return employeeOutDtoList;
    }
    /**
     * Get an employee by their email address and include project information.
     *
     * @param email The email address of the employee to retrieve.
     * @return An EmployeeOutDto object containing employee and project
     *         information.
     */
    public final EmployeeOutDto getEmployeeById(final Long id) {
        Employee employee = employeeRepository.findById(id).get();
        System.out.println(employee);
        EmployeeOutDto employeeOutDto = modelMapper.
                map(employee, EmployeeOutDto.class);
        if(employee.getProjectId()!=null) {
        Project project = projectRepository.
                findById(employee.getProjectId()).get();
        employeeOutDto.setProjectName(project.getProjectName());
        }
        else {
            employeeOutDto.setProjectName("N/A");
        }

        return employeeOutDto;
    }

    /**
     * Update the skills of an employee.
     *
     * @param id          The ID of the employee whose skills to update.
     * @param updateSkillDto The UpdateSkillDto containing the updated skills
     * @return A ResponseDto indicating the status of the update.
     */
    public final ResponseDto updateSkills(final Long id,
            final UpdateSkillsDto updateSkillDto) {
        Employee employee = employeeRepository.findById(id).get();
        employee.setSkills(updateSkillDto.getSkills());
        employeeRepository.save(employee);
        return new ResponseDto("Skills Updated");
    }
    /**
     * Requests a resource using the provided data and
     * saves it to the repository.
     *
     * @param requestResourceInDto The data for the resource request.
     * @return A ResponseDto indicating the status of the request.
     */
    public final ResponseDto requestResource(final 
            RequestResourceInDto requestResourceInDto) {
        // Map the request data to a RequestResource object
        RequestResource requestResource = this.modelMapper.map(
                requestResourceInDto, RequestResource.class);
        // Save the mapped RequestResource object to the repository
        requestResourceRepository.save(requestResource);
        // Return a ResponseDto indicating the success of the request
        return new ResponseDto("Requested resource");
    }
    /**
     * Get an employee by their email address and include project information.
     *
     * @param email The email address of the employee to retrieve.
     * @return An EmployeeOutDto object containing employee and project
     *         information.
     */
    public final EmployeeOutDto getMangerIdById(final Long id) {
        Employee employee = employeeRepository.findById(id).get();
        EmployeeOutDto employeeOutDto = new EmployeeOutDto();
        employeeOutDto.setId(employee.getId());

        return employeeOutDto;
    }

    public final IsRequestedOutDto IsRequested(final IsRequestedDto isRequestedDto) {
        Employee manager = this.employeeRepository.findById(isRequestedDto.getId()).get();
        Long managerId = manager.getId();
        Long employeeId = isRequestedDto.getEmployeeId();
        RequestResource requestResource = this.requestResourceRepository.findByEmployeeIdAndManagerId(employeeId, managerId);
        IsRequestedOutDto isRequestedOutDto = new IsRequestedOutDto();
        if(requestResource!=null) {
            isRequestedOutDto.setRequested(true);
            return isRequestedOutDto;
            
        }
        else {
            isRequestedOutDto.setRequested(false);
            return isRequestedOutDto;
        }
    }
    /**
     * Get a list of employees by their role.
     *
     * @param roleName The role name to filter employees by.
     * @return A list of EmployeeOutDto objects representing
     * employees with the specified role.
     */
    public final List<EmployeeOutDto> getEmployeesByRole(
            final String roleName) {
        Role role = Role.valueOf(roleName);
        List<Employee> employees = employeeRepository.findByRole(role);
        List<EmployeeOutDto> employeeOutDtoList = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeOutDto employeeOutDto = modelMapper.
                    map(employee, EmployeeOutDto.class);
            if (employee.getProjectId() == null) {
                employeeOutDto.setProjectName("N/A");
            } else {
                Project project = projectRepository.
                        findById(employee.getProjectId()).orElse(null);
                if (project != null) {
                    employeeOutDto.setProjectName(project.getProjectName());
                } else {
                    employeeOutDto.setProjectName("N/A");
                }
            }
            employeeOutDtoList.add(employeeOutDto);
        }
        return employeeOutDtoList;
    }
//    /**
//     * Get an employee's name by their ID.
//     *
//     * @param id The ID of the employee.
//     * @return An EmployeeNameDto object representing the employee's name.
//     */
//    public final EmployeeNameDto getEmployeeById(final Long id) {
//        Employee employee = employeeRepository.findById(id).orElse(null);
//        EmployeeNameDto employeeNameDto = modelMapper.
//                map(employee, EmployeeNameDto.class);
//        return employeeNameDto;
//    }
    

}


