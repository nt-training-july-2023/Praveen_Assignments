package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import com.backend.EMS.DTO.ProjectInDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Repository.ProjectRepository;

public class ProjectServiceTest {
    @InjectMocks
    private ProjectService projectService;
    
    @Mock
    private ProjectRepository projectRepository;
    
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
//        projectRepository = mock(ProjectRepository.class);
//        modelMapper = new ModelMapper();
//        projectService = new ProjectService(projectRepository, modelMapper);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProject_Successful() {
        // Arrange
        ProjectInDto projectInDto = new ProjectInDto();
        projectInDto= createSampleProjectDto();

        when(projectRepository.findByProjectName(anyString())).thenReturn(null);

        // Act
        ResponseDto responseDto = projectService.addProject(projectInDto);

        // Assert
        assertNotNull(responseDto);
        assertEquals("Project Added Successfully", responseDto.getMessage());
    }

    @Test
    public void testAddProject_DuplicateProjectName() {
        // Arrange
        ProjectInDto projectInDto = new ProjectInDto();
        projectInDto.setProjectName("Sample Project");

        when(projectRepository.findByProjectName(anyString())).thenReturn(new Project());

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> projectService.addProject(projectInDto));
    }

    // Add more test cases as needed for different scenarios

    // Helper method to create a sample ProjectInDto
    private ProjectInDto createSampleProjectDto() {
        ProjectInDto projectInDto = new ProjectInDto();
        // Set sample project DTO properties here
        projectInDto.setProjectName("NuoData");
        projectInDto.setId(1L);
        projectInDto.setManagerId(1L);
        projectInDto.setRequiredSkills(List.of("python", "java"));
        projectInDto.setDescription("project NuoData about dataaa");
        
        return projectInDto;
    }
}

