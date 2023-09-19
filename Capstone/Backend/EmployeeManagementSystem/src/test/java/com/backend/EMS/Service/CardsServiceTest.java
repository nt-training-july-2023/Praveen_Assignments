package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.backend.EMS.DTO.EmployeeDto;
import com.backend.EMS.DTO.EmployeeNameDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.ProjectDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.AdminRepository;
import com.backend.EMS.Repository.ProjectRepository;
import com.backend.EMS.Service.CardsService;

public class CardsServiceTest {

    private CardsService cardsService;
    private AdminRepository adminRepository;
    private ProjectRepository projectRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        adminRepository = mock(AdminRepository.class);
        projectRepository = mock(ProjectRepository.class);
        modelMapper = new ModelMapper();
        cardsService = new CardsService(modelMapper, adminRepository, projectRepository);
    }

    // Helper method to create a sample employee
    private Employee createSampleEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Praveen"); // Updated name
        employee.setEmail("praveen@nucleusteq.com");
        employee.setRole(Role.Admin); // Updated role
        employee.setEmpId("N0001");
        employee.setDesignation(Designation.Engineer);
        employee.setLocation(Location.Raipur);
        employee.setContactNo(1234567890L);
        employee.setDob("14-10-2001");
        employee.setDoj("17-07-2023");
        // Set other properties as needed
        return employee;
    }

    // Helper method to create a sample list of projects
    private List<Project> createSampleProjectList() {
        List<Project> projects = new ArrayList<>();
        projects.add(createSampleProject());
        return projects;
    }

    // Helper method to create a sample project
    private Project createSampleProject() {
        Project project = new Project();
        project.setId(1L);
        project.setProjectName("NuoData"); // Updated project name
        project.setManagerId(1L);
        project.setRequiredSkills(List.of("python", "java")); // Updated required skills
        project.setDescription("project NuoData about dataaa"); // Updated description
        // Set other properties as needed
        return project;
    }

    @Test
    public void testGetEmployeesByRole() {
        Role role = Role.Employee;
        List<Employee> employees = new ArrayList<>();
        employees.add(createSampleEmployee());

        when(adminRepository.findByRole(role)).thenReturn(employees);

        List<EmployeeOutDto> result = cardsService.getEmployeesByRole(role.toString());

        assertEquals(1, result.size());
        EmployeeOutDto employeeOutDto = result.get(0);
        assertEquals("Praveen", employeeOutDto.getName());
        assertEquals("N/A", employeeOutDto.getProjectName());
        assertEquals("N0001", employeeOutDto.getEmpId());
        // Add assertions for other fields
    }

    @Test
    public void testGetEmployeeById() {
        Long id = 1L;
        Employee employee = createSampleEmployee();

        when(adminRepository.findById(id)).thenReturn(Optional.of(employee));

        EmployeeNameDto result = cardsService.getEmployeeById(id);

        assertEquals("Praveen", result.getName());
    }

    @Test
    public void testGetAllProject() {
        List<Project> projects = createSampleProjectList();

        when(projectRepository.findAll()).thenReturn(projects);
        when(adminRepository.findById(1L)).thenReturn(Optional.of(createSampleEmployee()));

        List<ProjectOutDto> result = cardsService.getAllProject();

        assertEquals(1, result.size());
        ProjectOutDto projectOutDto = result.get(0);
        assertEquals("NuoData", projectOutDto.getProjectName());
        assertEquals("Praveen", projectOutDto.getHead());
        assertEquals("project NuoData about dataaa", projectOutDto.getDescription());
        // Add assertions for other fields
    }

    @Test
    public void testGetAllByManagerId() {
        Long managerId = 1L;
        List<Project> projects = createSampleProjectList();

        when(projectRepository.findAllByManagerId(managerId)).thenReturn(projects);

        List<ProjectOutDto> result = cardsService.getAllByManagerId(managerId);

        assertEquals(1, result.size());
        ProjectOutDto projectOutDto = result.get(0);
        assertEquals("NuoData", projectOutDto.getProjectName());
        assertEquals(1L, projectOutDto.getManagerId());
        assertEquals(List.of("python", "java"), projectOutDto.getRequiredSkills());
        // Add assertions for other fields
    }
}


