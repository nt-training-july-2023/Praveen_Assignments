package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import com.backend.EMS.DTO.ProjectDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Repository.ProjectRepository;

public class AddProjectServiceTest {

    private AddProjectService addProjectService;
    private ProjectRepository projectRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        projectRepository = mock(ProjectRepository.class);
        modelMapper = new ModelMapper();
        addProjectService = new AddProjectService(projectRepository, modelMapper);
    }

    @Test
    public void testAddProject_Successful() {
        // Arrange
        ProjectDto projectDto = new ProjectDto();
        projectDto= createSampleProjectDto();

        when(projectRepository.findByProjectName(anyString())).thenReturn(null);

        // Act
        ResponseDto responseDto = addProjectService.addProject(projectDto);

        // Assert
        assertNotNull(responseDto);
        assertEquals("Project Added Successfully", responseDto.getMessage());
    }

    @Test
    public void testAddProject_DuplicateProjectName() {
        // Arrange
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectName("Sample Project");

        when(projectRepository.findByProjectName(anyString())).thenReturn(new Project());

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> addProjectService.addProject(projectDto));
    }

    // Add more test cases as needed for different scenarios

    // Helper method to create a sample ProjectDto
    private ProjectDto createSampleProjectDto() {
        ProjectDto projectDto = new ProjectDto();
        // Set sample project DTO properties here
        projectDto.setProjectName("NuoData");
        projectDto.setId(1L);
        projectDto.setManagerId(1L);
        projectDto.setRequiredSkills(List.of("python", "java"));
        projectDto.setDescription("project NuoData about dataaa");
        
        return projectDto;
    }
}

