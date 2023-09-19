package com.backend.EMS.Controller;

import com.backend.EMS.Controller.ProjectController;
import com.backend.EMS.DTO.ProjectDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Service.AddProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProjectControllerTest {

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private AddProjectService addProjectService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProject_Success() {
        ProjectDto projectDto = new ProjectDto();
        ResponseDto responseDto = new ResponseDto("Project added successfully");

        when(addProjectService.addProject(any(ProjectDto.class))).thenReturn(responseDto);

        ResponseDto response = projectController.addProject(projectDto);

        verify(addProjectService, times(1)).addProject(any(ProjectDto.class));

        assertEquals("Project added successfully", response.getMessage());
    }
}
