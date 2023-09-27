package com.backend.EMS.Controller;

import com.backend.EMS.Controller.ProjectController;
import com.backend.EMS.DTO.ProjectInDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Service.ProjectService;
import com.backend.EMS.Service.CardsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class ProjectControllerTest {

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectService projectService;
    @Mock
    private CardsService cardsService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProject_Success() {
        ProjectInDto projectInDto = new ProjectInDto();
        ResponseDto responseDto = new ResponseDto("Project added successfully");

        when(projectService.addProject(any(ProjectInDto.class))).thenReturn(responseDto);

        ResponseDto response = projectController.addProject(projectInDto);

        verify(projectService, times(1)).addProject(any(ProjectInDto.class));

        assertEquals("Project added successfully", response.getMessage());
    }
    @Test
    public void testGetAllProjects_Success() {
        List<ProjectOutDto> projectList = Arrays.asList(new ProjectOutDto(), new ProjectOutDto());

        when(cardsService.getAllProject()).thenReturn(projectList);

        List<ProjectOutDto> response = projectController.getAllProjects();

        verify(cardsService, times(1)).getAllProject();

        assertEquals(2, response.size());
    }
}
