package com.backend.EMS.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backend.EMS.DTO.ProjectInDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Service.ProjectService;
import com.backend.EMS.Validation.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
class ProjectControllerTest {
    @InjectMocks
    private ProjectController projectController;
    @Mock
    private ProjectService projectService;
    @Mock
    private Validation validator;
    @Mock
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    List<String> skills = new ArrayList<>();
    
    @Test
    void testAddproject() throws Exception{
        skills.add("React");
        skills.add("Java");
        ProjectInDto projectDto = new ProjectInDto();
        projectDto.setProjectName("Fynder");
        projectDto.setManagerId(1L);
        projectDto.setStartDate("2023-06-07");
        projectDto.setRequiredSkills(skills);
        projectDto.setDescription("Description");
        

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(projectDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("Project added successfully");
        doNothing().when(validator).checkProject(projectDto);
        when(projectService.addProject(projectDto)).thenReturn(response);

        MvcResult mvcResult = this.mockMvc.perform(post("/api/admin/add-project")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        
    }
    @Test
    void testGetAllProjects() throws Exception {
        List<ProjectOutDto> list = new ArrayList<>();
        List<String> teams = new ArrayList<>();
        ProjectOutDto prjDto = new ProjectOutDto();
        prjDto.setProjectName("Fynder");
        prjDto.setManagerId(1L);
        prjDto.setStartDate("2023-06-07");
        prjDto.setRequiredSkills(skills);
        prjDto.setDescription("Description");
        prjDto.setHead("Ankita Sharma");
        prjDto.setId(0L);
        prjDto.setTeam(teams);
        list.add(prjDto);
        

        when(projectService.getAllProject()).thenReturn(list);

        MvcResult mvcResult = this.mockMvc.perform(get("/api/projects")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        
    }
}
