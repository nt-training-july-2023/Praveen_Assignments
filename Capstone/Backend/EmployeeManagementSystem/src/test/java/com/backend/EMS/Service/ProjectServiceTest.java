package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.backend.EMS.DTO.ProjectInDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Repository.EmployeeRepository;
import com.backend.EMS.Repository.ProjectRepository;

public class ProjectServiceTest {
    @InjectMocks
    private ProjectService projectService;
    
    @Mock
    private ProjectRepository projectRepository;
    
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testAddProject() {
        ProjectInDto projectInDto = new ProjectInDto();
        projectInDto.setProjectName("NuoData");
        projectInDto.setId(1L);
        projectInDto.setManagerId(1L);
        projectInDto.setRequiredSkills(List.of("python", "java"));
        projectInDto.setDescription("project NuoData about dataaa");

        Project project = new Project();
        project.setId(1L);
        project.setProjectName("NuoData");

        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ResponseDto response = projectService.addProject(projectInDto);

        assertNotNull(response);
        assertEquals("Project Added Successfully", response.getMessage());
    }
    
    @Test
    public void testGetAllProject() {
        Project project1 = new Project();
        project1.setId(1L);
        project1.setProjectName("Project A");
        project1.setManagerId(1L);

        Project project2 = new Project();
        project2.setId(2L);
        project2.setProjectName("Project B");
        project2.setManagerId(2L);

        List<Project> projects = List.of(project1, project2);

        Employee manager1 = new Employee();
        manager1.setId(1L);
        manager1.setName("Manager A");

        Employee manager2 = new Employee();
        manager2.setId(2L);
        manager2.setName("Manager B");

        when(projectRepository.findAll()).thenReturn(projects);
        when(employeeRepository.findEmployeesByProjectId(Mockito.anyLong())).thenReturn(new ArrayList<>());
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(manager1));
        when(employeeRepository.findById(2L)).thenReturn(java.util.Optional.of(manager2));

        ProjectOutDto projectOutDto1 = new ProjectOutDto();
        projectOutDto1.setId(1L);
        projectOutDto1.setProjectName("Project A");
        projectOutDto1.setHead("Manager A");
        projectOutDto1.setTeam(new ArrayList<>());

        ProjectOutDto projectOutDto2 = new ProjectOutDto();
        projectOutDto2.setId(2L);
        projectOutDto2.setProjectName("Project B");
        projectOutDto2.setHead("Manager B");
        projectOutDto2.setTeam(new ArrayList<>());

        when(modelMapper.map(project1, ProjectOutDto.class)).thenReturn(projectOutDto1);
        when(modelMapper.map(project2, ProjectOutDto.class)).thenReturn(projectOutDto2);

        List<ProjectOutDto> result = projectService.getAllProject();

        assertNotNull(result);
        assertEquals(2, result.size());

        ProjectOutDto retrievedProject1 = result.get(0);
        assertEquals(1L, retrievedProject1.getId());
        assertEquals("Project A", retrievedProject1.getProjectName());
        assertEquals("Manager A", retrievedProject1.getHead());

        ProjectOutDto retrievedProject2 = result.get(1);
        assertEquals(2L, retrievedProject2.getId());
        assertEquals("Project B", retrievedProject2.getProjectName());
        assertEquals("Manager B", retrievedProject2.getHead());
    }
    
    @Test
    public void testGetAllByManagerId() {
        Long managerId = 1L;

        Project project1 = new Project();
        project1.setId(1L);
        project1.setProjectName("Project A");
        project1.setManagerId(managerId);
        project1.setRequiredSkills(List.of("python", "java"));;

        Project project2 = new Project();
        project2.setId(2L);
        project2.setProjectName("Project B");
        project2.setManagerId(managerId);
        project2.setRequiredSkills(List.of("python", "java"));

        List<Project> projects = List.of(project1, project2);

        Employee teamMember1 = new Employee();
        teamMember1.setId(3L);
        teamMember1.setName("Employee 1");

        Employee teamMember2 = new Employee();
        teamMember2.setId(4L);
        teamMember2.setName("Employee 2");

        when(projectRepository.findAllByManagerId(managerId)).thenReturn(projects);
        when(employeeRepository.findEmployeesByProjectId(Mockito.anyLong())).thenReturn(new ArrayList<>());

        when(employeeRepository.findById(3L)).thenReturn(java.util.Optional.of(teamMember1));
        when(employeeRepository.findById(4L)).thenReturn(java.util.Optional.of(teamMember2));

        ProjectOutDto projectOutDto1 = new ProjectOutDto();
        projectOutDto1.setId(1L);
        projectOutDto1.setProjectName("Project A");
        projectOutDto1.setManagerId(managerId);
        projectOutDto1.setTeam(List.of("Employee 1", "Employee 2"));

        ProjectOutDto projectOutDto2 = new ProjectOutDto();
        projectOutDto2.setId(2L);
        projectOutDto2.setProjectName("Project B");
        projectOutDto2.setManagerId(managerId);
        projectOutDto2.setTeam(List.of("Employee 1", "Employee 2"));

        when(modelMapper.map(project1, ProjectOutDto.class)).thenReturn(projectOutDto1);
        when(modelMapper.map(project2, ProjectOutDto.class)).thenReturn(projectOutDto2);

        List<ProjectOutDto> result = projectService.getAllByManagerId(managerId);

        assertNotNull(result);
        assertEquals(2, result.size());

        ProjectOutDto retrievedProject1 = result.get(0);
        assertEquals(1L, retrievedProject1.getId());
        assertEquals("Project A", retrievedProject1.getProjectName());
        assertEquals(managerId, retrievedProject1.getManagerId());

        ProjectOutDto retrievedProject2 = result.get(1);
        assertEquals(2L, retrievedProject2.getId());
        assertEquals("Project B", retrievedProject2.getProjectName());
        assertEquals(managerId, retrievedProject2.getManagerId());
    }

    private ProjectInDto createSampleProjectDto() {
        ProjectInDto projectInDto = new ProjectInDto();
        projectInDto.setProjectName("NuoData");
        projectInDto.setId(1L);
        projectInDto.setManagerId(1L);
        projectInDto.setRequiredSkills(List.of("python", "java"));
        projectInDto.setDescription("project NuoData about dataaa");
        
        return projectInDto;
    }
    

}

