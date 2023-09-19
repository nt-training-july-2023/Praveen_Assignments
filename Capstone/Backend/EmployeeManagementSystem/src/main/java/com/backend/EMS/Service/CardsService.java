package com.backend.EMS.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.EMS.DTO.EmployeeNameDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.AdminRepository;
import com.backend.EMS.Repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing card-related operations.
 */
@Service
public class CardsService {
    /**
     * ModelMapper used for mapping from dto to entity.
     */
    private ModelMapper modelMapper;
    /**
     * Repository for managing admin entities.
     */
    private AdminRepository adminRepository;
    /**
     * Repository for managing Project entities.
     */

    private ProjectRepository projectRepository;

    /**
     * Constructs a CardsService with the necessary dependencies.
     *
     * @param modelMappers     The ModelMapper used for mapping.
     * @param adminRepositorys The repository for managing admin entities.
     * @param projectRepositorys The repository for managing Project entities.
     */
    @Autowired
    public CardsService(final ModelMapper modelMappers,
            final AdminRepository adminRepositorys,
            final ProjectRepository projectRepositorys) {
        super();
        this.modelMapper = modelMappers;
        this.adminRepository = adminRepositorys;
        this.projectRepository = projectRepositorys;
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
        List<Employee> employees = adminRepository.findByRole(role);
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

    /**
     * Get an employee's name by their ID.
     *
     * @param id The ID of the employee.
     * @return An EmployeeNameDto object representing the employee's name.
     */
    public final EmployeeNameDto getEmployeeById(final Long id) {
        Employee employee = adminRepository.findById(id).orElse(null);
        EmployeeNameDto employeeNameDto = modelMapper.
                map(employee, EmployeeNameDto.class);
        return employeeNameDto;
    }

    /**
     * Get a list of all projects.
     *
     * @return A list of ProjectDto objects representing all projects.
     */
    public final List<ProjectOutDto> getAllProject() {
        List<Project> projects = this.projectRepository.findAll();
        List<ProjectOutDto> projectOutDtoList = new ArrayList<>();
        for (Project project : projects) {
            ProjectOutDto dto = this.modelMapper.map(project,
                    ProjectOutDto.class);

                List<Employee> teamMembers = adminRepository.
                        findEmployeesByProjectId(project.getId());

                List<String> teamMemberNames = new ArrayList<>();

                // Add names of team members to the list
                for (Employee teamMember : teamMembers) {
                    teamMemberNames.add(teamMember.getName());
                    // Assuming there's a "getName()" method in Employee class
                }

                // Set the team members' names in the employeeOutDto
                dto.setTeam(teamMemberNames);
            Employee manager = adminRepository.findById(project.
                    getManagerId()).orElse(null);
            if (manager != null) {
                dto.setHead(manager.getName());
            } else {
                dto.setHead("N/A");
            }
            projectOutDtoList.add(dto);
        }
        return projectOutDtoList;
    }

    /**
     * Get a list of projects managed by a specific manager.
     *
     * @param managerId The ID of the manager to filter projects by.
     * @return A list of ProjectDto objects representing
     * projects managed by the specified manager.
     */
    public final List<ProjectOutDto> getAllByManagerId(final Long managerId) {
        List<Project> projectList = projectRepository.
                findAllByManagerId(managerId);
        List<ProjectOutDto> projectOutList = new ArrayList<>();
        for (Project project : projectList) {
            ProjectOutDto projectOutDto = new ProjectOutDto();
            projectOutDto.setId(project.getId());
            projectOutDto.setProjectName(project.getProjectName());
            projectOutDto.setManagerId(project.getManagerId());
            projectOutDto.setRequiredSkills(project.getRequiredSkills());
            List<Employee> teamMembers = adminRepository.
                    findEmployeesByProjectId(project.getId());
            List<String> teamMemberNames = new ArrayList<>();
            // Add names of team members to the list
            for (Employee teamMember : teamMembers) {
                teamMemberNames.add(teamMember.getName());
                // Assuming there's a "getName()" method in Employee class
            }
            // Set the team members' names in the employeeOutDto
            projectOutDto.setTeam(teamMemberNames);
            projectOutList.add(projectOutDto);
        }
        return projectOutList;
    }
}


