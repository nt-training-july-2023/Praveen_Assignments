package com.backend.EMS.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EMS.DTO.ProjectDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Repository.ProjectRepository;

/**
 * Service class for adding projects.
 */
@Service
public class AddProjectService {
    /**
     * Repository for managing Project entities.
     */
    private ProjectRepository projectRepository;
    /**
     * ModelMapper for mapping between DTOs and entities.
     */
    private ModelMapper modelMapper;
    /**
     * Constructor for the AddProjectService class.
     *
     * @param projectRepositorys The ProjectRepository to
     * be used by the service.
     * @param modelMappers       The ModelMapper to be used by the service.
     */
    @Autowired
    public AddProjectService(final ProjectRepository projectRepositorys,
            final ModelMapper modelMappers) {
        super();
        this.projectRepository = projectRepositorys;
        this.modelMapper = modelMappers;
    }
    /**
     * Add a new project to the system.
     *
     * @param projectDto The ProjectDto containing project details to be added.
     * @return True if the project was added successfully; otherwise, false.
     * @throws UserAlreadyFound if a project with the same name already exists.
     */
    public final ResponseDto addProject(final ProjectDto projectDto) {
        Project project = this.modelMapper.map(projectDto, Project.class);
        ResponseDto responseDto = new ResponseDto();
        if (projectRepository.findByProjectName(
                projectDto.getProjectName()) != null) {
            throw new UserAlreadyFound("Project is already added");
        }
        projectRepository.save(project);
        responseDto.setMessage("Project Added Successfully");
        return responseDto;
    }
}

