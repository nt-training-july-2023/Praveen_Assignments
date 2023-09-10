package com.backend.EMS.Service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EMS.DTO.ProjectDto;
import com.backend.EMS.Exception.userAlreadyFound;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Repository.ProjectRepository;

@Service
public class AddProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public final boolean addProject(ProjectDto ProjectDto) {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Project project = this.modelMapper.map(ProjectDto, Project.class);
        if (projectRepository.findByProjectName(ProjectDto.getProjectName()) != null) {

            throw new userAlreadyFound("Project is already Added");
        }
        
        projectRepository.save(project);
        
        return true;
    }

}
