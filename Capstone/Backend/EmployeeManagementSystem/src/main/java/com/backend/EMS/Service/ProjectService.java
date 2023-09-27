package com.backend.EMS.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EMS.DTO.ProjectInDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Repository.EmployeeRepository;
import com.backend.EMS.Repository.ProjectRepository;

/**
 * Service class for adding projects.
 */
@Service
public class ProjectService {
    /**
     * Repository for managing Project entities.
     */
    @Autowired
    private ProjectRepository projectRepository;
    /**
     * ModelMapper for mapping between DTOs and entities.
     */
    @Autowired
    private ModelMapper modelMapper;
    /**
     * Repository for managing admin entities.
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * Add a new project to the system.
     *
     * @param projectInDto The ProjectInDto containing project details to be added.
     * @return True if the project was added successfully; otherwise, false.
     * @throws UserAlreadyFound if a project with the same name already exists.
     */
    public final ResponseDto addProject(final ProjectInDto projectInDto) {
        Project project = this.modelMapper.map(projectInDto, Project.class);
        ResponseDto responseDto = new ResponseDto();
        if (projectRepository.findByProjectName(
                projectInDto.getProjectName()) != null) {
            throw new UserAlreadyFound("Project is already added");
        }
        projectRepository.save(project);
        responseDto.setMessage("Project Added Successfully");
        return responseDto;
    }
    /**
     * Get a list of all projects.
     *
     * @return A list of ProjectInDto objects representing all projects.
     */
    public final List<ProjectOutDto> getAllProject() {
        List<Project> projects = this.projectRepository.findAll();
        List<ProjectOutDto> projectOutDtoList = new ArrayList<>();
        for (Project project : projects) {
            ProjectOutDto dto = this.modelMapper.map(project,
                    ProjectOutDto.class);

                List<Employee> teamMembers = employeeRepository.
                        findEmployeesByProjectId(project.getId());

                List<String> teamMemberNames = new ArrayList<>();

                // Add names of team members to the list
                for (Employee teamMember : teamMembers) {
                    teamMemberNames.add(teamMember.getName());
                    // Assuming there's a "getName()" method in Employee class
                }

                // Set the team members' names in the employeeOutDto
                dto.setTeam(teamMemberNames);
            Employee manager = employeeRepository.findById(project.
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
     * @return A list of ProjectInDto objects representing
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
            List<Employee> teamMembers = employeeRepository.
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

