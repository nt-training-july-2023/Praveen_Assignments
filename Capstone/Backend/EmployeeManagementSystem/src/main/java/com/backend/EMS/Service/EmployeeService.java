package com.backend.EMS.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EMS.Constants.SuccessConstants;
import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.IsRequestedInDto;
import com.backend.EMS.DTO.IsRequestedOutDto;
import com.backend.EMS.DTO.RequestResourceInDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.DTO.UpdateSkillsDto;
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
        Employee employee = this.modelMapper.map(employeeInDto, Employee.class);
        employee.setManagerName("Ankita");
        employee.setManagerId(1L);
        employeeRepository.save(employee);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(SuccessConstants.ADDED);
        return responseDto;
    }

    /**
     * Update employee information including project and manager.
     *
     * @param id        The ID of the employee to update.
     * @param updatedDetails The UpdatedDetails contains of the
     * managerId and projectId for the employee.
     * @return A ResponseDto indicating the status of the update.
     */
    public final ResponseDto updateEmployee(final Long id,
            final Map<String, Long> updatedDetails) {
        Long projectId = updatedDetails.get("projectId");
        Long managerId = updatedDetails.get("managerId");
        Employee employee = employeeRepository.findById(id).orElse(null);
        Employee manager = employeeRepository.findById(managerId).get();
        if (employee == null) {
            return new ResponseDto("Employee Not Found");
        } else {
            employee.setManagerId(managerId);
            employee.setProjectId(projectId);
            employee.setManagerName(manager.getName());
            employeeRepository.save(employee);
            return new ResponseDto(SuccessConstants.UPDATED);
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
     * Get an employee by their id and include project information.
     *
     * @param id The id of the employee to retrieve.
     * @return An EmployeeOutDto object containing employee and project
     *         information.
     */
    public final EmployeeOutDto getEmployeeById(final Long id) {
        Employee employee = employeeRepository.findById(id).get();
        EmployeeOutDto employeeOutDto = modelMapper.
                map(employee, EmployeeOutDto.class);
        if (employee.getProjectId() != null) {
        Project project = projectRepository.
                findById(employee.getProjectId()).get();
        employeeOutDto.setProjectName(project.getProjectName());
        } else {
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
        return new ResponseDto(SuccessConstants.SKILLS_ADDED);
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
        RequestResource requestResource = this.modelMapper.map(
                requestResourceInDto, RequestResource.class);
        requestResourceRepository.save(requestResource);
        return new ResponseDto(SuccessConstants.REQUESTRESOURCE);
    }
    /**
     * Check if a resource is requested based on the provided input data.
     *
     * @param isRequestedInDto The input data used to determine
     * if a resource is requested.
     * @return An IsRequestedOutDto indicating whether the
     * resource is requested or not.
     */
    public final IsRequestedOutDto isRequested(final
            IsRequestedInDto isRequestedInDto) {
        Employee manager = this.employeeRepository.
                findById(isRequestedInDto.getId()).get();
        Long managerId = manager.getId();
        Long employeeId = isRequestedInDto.getEmployeeId();
        RequestResource requestResource = this.requestResourceRepository.
                findByEmployeeIdAndManagerId(employeeId, managerId);
        IsRequestedOutDto isRequestedOutDto = new IsRequestedOutDto();
        if (requestResource != null) {
            isRequestedOutDto.setRequested(true);
            return isRequestedOutDto;
            } else {
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
}


