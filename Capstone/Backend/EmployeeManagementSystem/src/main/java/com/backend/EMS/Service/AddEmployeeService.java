package com.backend.EMS.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.EMS.DTO.EmployeeDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.DTO.UpdateSkillsDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.AdminRepository;
import com.backend.EMS.Repository.ProjectRepository;

/**
 * Service class for adding employees.
 */
@Service
public class AddEmployeeService {
    /**
     * Repository for managing Admin entities.
     */
    private AdminRepository adminRepository;
    /**
     * ModelMapper for mapping between DTOs and entities.
     */
    private ModelMapper modelMapper;
    /**
     * Repository for managing Project entities.
     */
    private ProjectRepository projectRepository;


    /**
     * Constructs an AddEmployeeService with the necessary dependencies.
     *
     * @param adminsRepository The repository for managing Admin entities.
     * @param modelMappers     The ModelMapper for mapping between DTOs and
     *                         entities.
     */
    @Autowired
    public AddEmployeeService(final AdminRepository adminsRepository,
            final ModelMapper modelMappers) {
        super();
        this.adminRepository = adminsRepository;
        this.modelMapper = modelMappers;
    }

    /**
     * Add a new employee to the system.
     *
     * @param employeeDto The EmployeeDto containing
     * employee details to be added.
     * @return True if the employee was added successfully; otherwise, false.
     * @throws UserAlreadyFound if an employee with the
     * same email, employee ID, or
     *                          contact number already exists.
     */
    public final ResponseDto addEmployee(final EmployeeDto employeeDto) {
        modelMapper.getConfiguration().setMatchingStrategy(
                MatchingStrategies.STRICT);
        Employee employee = this.modelMapper.map(employeeDto, Employee.class);

        if (adminRepository.findByEmail(employeeDto.getEmail()) != null) {
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
        Employee employee = adminRepository.findById(id).orElse(null);
        Employee manager = adminRepository.findById(managerId).get();
        if (employee == null) {
            return new ResponseDto("Employee Not Found");
        } else {
            employee.setManagerId(managerId);
            employee.setProjectId(projectId);
            employee.setManagerName(manager.getName());
            adminRepository.save(employee);

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
                adminRepository.findByRoleIn(roles);
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
    public final EmployeeOutDto getEmployeeByEmail(final String email) {
        Employee employee = adminRepository.findByEmail(email);
        EmployeeOutDto employeeOutDto = modelMapper.
                map(employee, EmployeeOutDto.class);
        Project project = projectRepository.
                findById(employee.getProjectId()).get();
        employeeOutDto.setProjectName(project.getProjectName());

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
        Employee employee = adminRepository.findById(id).get();
        employee.setSkills(updateSkillDto.getSkills());
        adminRepository.save(employee);
        return new ResponseDto("Skills Updated");
    }
}


