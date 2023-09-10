package com.backend.EMS.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EMS.DTO.AdminDto;
import com.backend.EMS.DTO.ProjectDto;
import com.backend.EMS.Model.Admin;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.AdminRepository;
import com.backend.EMS.Repository.ProjectRepository;

@Service
public class CardsService {
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AdminRepository adminRepository;

    public List<AdminDto> getEmployeesByRole(String roleName) {
        Role role = Role.valueOf(roleName);
        System.out.println(role);
        List<Admin> employees = adminRepository.findByRole(role);
        
        List<AdminDto> adminDtoList = new ArrayList<>();
        
        for (Admin admin : employees) {
            AdminDto adminDto = modelMapper.map(admin, AdminDto.class);
            adminDtoList.add(adminDto);
            System.out.println(adminDto.getId());
        }

        return adminDtoList;
    }

    @Autowired
    private ProjectRepository projectRepository;
    
    public final List<ProjectDto> getAllProject() {
        List<Project> projects = this.projectRepository.findAll();
        
        List<ProjectDto> projectDtoList = new ArrayList<>();
        
        for (Project project : projects) {
            ProjectDto dto = this.modelMapper.map(project, ProjectDto.class);
            // fetching manager name from admin repo and storing it in head field 
            Admin manager = adminRepository.findById(project.getManagerId()).orElse(null);
            if(manager!=null) {
                dto.setHead(manager.getName()); //set manager name in ProjectDto
            }
            else {
                dto.setHead("Ankita");
            }
            projectDtoList.add(dto);
        }

        return projectDtoList;
    }
    public List<ProjectDto> getAllByManagerId(Long managerId) {
        // TODO Auto-generated method stub
        List<Project> projectList = projectRepository
                .findAllByManagerId(managerId);
        List<ProjectDto> projectOutList = new ArrayList<ProjectDto>();

        for (Project project : projectList) {
            ProjectDto projectDto = new ProjectDto();

            projectDto.setId(project.getId());
            projectDto.setProjectName(project.getProjectName());
            projectDto.setManagerId(project.getManagerId());
            projectDto.setRequiredSkills(project.getRequiredSkills());

            projectOutList.add(projectDto);
        }
        return projectOutList;
    }

}
