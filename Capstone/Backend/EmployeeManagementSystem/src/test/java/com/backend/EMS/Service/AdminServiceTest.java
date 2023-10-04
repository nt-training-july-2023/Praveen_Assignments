package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.RequestResourceOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Model.RequestResource;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.EmployeeRepository;
import com.backend.EMS.Repository.ProjectRepository;
import com.backend.EMS.Repository.RequestResourceRepository;

public class AdminServiceTest {

    
    @InjectMocks
    private AdminService adminService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private RequestResourceRepository requestResourceRepository;

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testAddAdmin_Success() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setName("Praveen");
        employeeInDto.setEmail("praveen@nucleusteq.com");
        employeeInDto.setEmpId("N0001");
        employeeInDto.setDesignation(Designation.Engineer);
        employeeInDto.setLocation(Location.Raipur);
        employeeInDto.setContactNo(1234567890L);
        employeeInDto.setDob("14-10-2001");
        employeeInDto.setDoj("17-07-2023");
        
        Employee employee = new Employee();
        employee.setName(employeeInDto.getName());
        employee.setEmail(employeeInDto.getEmail());
        employee.setEmpId(employeeInDto.getEmpId());
        employee.setDesignation(employeeInDto.getDesignation());
        employee.setLocation(employeeInDto.getLocation());
        employee.setContactNo(employeeInDto.getContactNo());
        employee.setDob(employeeInDto.getDob());
        employee.setDoj(employeeInDto.getDob());
        

        when(employeeRepository.save(employee)).thenReturn(employee);
        ResponseDto response = adminService.addAdmin(employeeInDto);
        assertEquals("Admin Registered successfully", response.getMessage());

    }
    @Test
    public void testGetRoleByEmail_Success() {
        LoginInDto loginInDto = new LoginInDto();
        loginInDto.setEmail("test@example.com");

        Employee mockEmployee = new Employee();
        mockEmployee.setRole(Role.Admin);

        when(employeeRepository.findByEmail("test@example.com")).thenReturn(mockEmployee);

        Role role = adminService.getRoleByEmial(loginInDto);

        assertNotNull(role);
        assertEquals(Role.Admin, role);
    }


    @Test
    public void testRequestedResource_Success() {
        RequestResource requestResource1 = new RequestResource();

        requestResource1.setEmployeeId(2L);
        requestResource1.setManagerId(3L);
        requestResource1.setProjectId(4L);
        requestResource1.setComment("Request 1 Comment");

        RequestResource requestResource2 = new RequestResource();

        requestResource2.setEmployeeId(6L);
        requestResource2.setManagerId(7L);
        requestResource2.setProjectId(8L);
        requestResource2.setComment("Request 2 Comment");

        Employee employee1 = new Employee();
        employee1.setId(2L);
        employee1.setEmpId("E001");
        employee1.setName("Employee 1");

        Employee employee2 = new Employee();
        employee2.setId(6L);
        employee2.setEmpId("E002");
        employee2.setName("Employee 2");

        Employee manager1 = new Employee();
        manager1.setId(3L);
        manager1.setEmpId("M001");
        manager1.setName("Manager 1");

        Employee manager2 = new Employee();
        manager2.setId(7L);
        manager2.setEmpId("M002");
        manager2.setName("Manager 2");

        Project project1 = new Project();
        project1.setId(4L);
        project1.setProjectName("Project 1");

        Project project2 = new Project();
        project2.setId(8L);
        project2.setProjectName("Project 2");

        when(requestResourceRepository.findAll()).thenReturn(List.of(requestResource1, requestResource2));
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee1));
        when(employeeRepository.findById(6L)).thenReturn(Optional.of(employee2));
        when(employeeRepository.findById(3L)).thenReturn(Optional.of(manager1));
        when(employeeRepository.findById(7L)).thenReturn(Optional.of(manager2));
        when(projectRepository.findById(4L)).thenReturn(Optional.of(project1));
        when(projectRepository.findById(8L)).thenReturn(Optional.of(project2));

        List<RequestResourceOutDto> requestResourceOutDtos = adminService.requestedResource();

        // Assertions
        assertNotNull(requestResourceOutDtos);
        assertEquals(2, requestResourceOutDtos.size());

        RequestResourceOutDto requestResourceOutDto1 = requestResourceOutDtos.get(0);
        assertEquals(2L, requestResourceOutDto1.getEmployeeId());
        assertEquals(3L, requestResourceOutDto1.getManagerId());
        assertEquals(4L, requestResourceOutDto1.getProjectId());
        assertEquals("Request 1 Comment", requestResourceOutDto1.getComment());
        assertEquals("E001", requestResourceOutDto1.getEmployeeEmpId());
        assertEquals("Employee 1", requestResourceOutDto1.getEmployeeName());
        assertEquals("M001", requestResourceOutDto1.getManagerEmpId());
        assertEquals("Manager 1", requestResourceOutDto1.getManagerName());
        assertEquals("Project 1", requestResourceOutDto1.getProjectName());

        RequestResourceOutDto requestResourceOutDto2 = requestResourceOutDtos.get(1);
        assertEquals(6L, requestResourceOutDto2.getEmployeeId());
        assertEquals(7L, requestResourceOutDto2.getManagerId());
        assertEquals(8L, requestResourceOutDto2.getProjectId());
        assertEquals("Request 2 Comment", requestResourceOutDto2.getComment());
        assertEquals("E002", requestResourceOutDto2.getEmployeeEmpId());
        assertEquals("Employee 2", requestResourceOutDto2.getEmployeeName());
        assertEquals("M002", requestResourceOutDto2.getManagerEmpId());
        assertEquals("Manager 2", requestResourceOutDto2.getManagerName());
        assertEquals("Project 2", requestResourceOutDto2.getProjectName());
    }
    @Test
    public void testDeleteRequestedResource_Success() {
        Long id = 1L;
        doNothing().when(requestResourceRepository).deleteById(anyLong());
        ResponseDto response = adminService.deleteRequestedResource(id);

        assertNotNull(response);
        assertEquals("Deleted Successfuly", response.getMessage());
    }

    @Test
    public void testAcceptRequestedResource_Success() {
        Long id = 1L;

        RequestResource requestResource = new RequestResource();
        requestResource.setEmployeeId(2L);
        requestResource.setManagerId(3L);
        requestResource.setProjectId(4L);

        Employee employee = new Employee();
        employee.setProjectId(null);
        employee.setManagerId(null);

        Employee manager = new Employee();
        manager.setName("Manager");

        when(requestResourceRepository.findById(id)).thenReturn(Optional.of(requestResource));
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));
        when(employeeRepository.findById(3L)).thenReturn(Optional.of(manager));
        when(requestResourceRepository.findByEmployeeId(2L)).thenReturn(Collections.singletonList(requestResource));

        ResponseDto response = adminService.acceptRequestedResource(id);

        assertNotNull(response);
        assertEquals("Requested Resource Accepted", response.getMessage());
        assertEquals(4L, employee.getProjectId());
        assertEquals(3L, employee.getManagerId());
        assertEquals("Manager", employee.getManagerName());
    }

    @Test
    public void testAcceptRequestedResource_RequestNotFound() {
        Long id = 1L;

        when(requestResourceRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            adminService.acceptRequestedResource(id);
        });
    }

    @Test
    public void testAcceptRequestedResource_EmployeeNotFound() {
        Long id = 1L;

        RequestResource requestResource = new RequestResource();
        requestResource.setEmployeeId(2L);
        requestResource.setManagerId(3L);
        requestResource.setProjectId(4L);
        requestResource.setComment("Request");

        when(requestResourceRepository.findById(id)).thenReturn(Optional.of(requestResource));
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            adminService.acceptRequestedResource(id);
        });
    }

    @Test
    public void testGetFilteredEmployees_EmptyList() {
        List<String> selectedSkills = Collections.singletonList("Java");
        boolean showUnassigned = false;
        when(employeeRepository.findByRole(Role.Employee)).thenReturn(Collections.emptyList());
        List<EmployeeOutDto> filteredEmployees = adminService.getFilteredEmployees(selectedSkills, showUnassigned);
        assertNotNull(filteredEmployees);
        assertTrue(filteredEmployees.isEmpty());
    }
    @Test
    public void testGetFilteredEmployees_ShowUnassignedAndSelectedSkills() {
        // Create test data
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setEmpId("E001");
        employee1.setName("Employee 1");
        employee1.setSkills(List.of("Skill1", "Skill2"));
        employee1.setProjectId(null);
        
        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setEmpId("E002");
        employee2.setName("Employee 2");
        employee2.setProjectId(3L);
        employee2.setSkills(List.of("Skill1", "Skill2"));
        
        EmployeeOutDto employeeOutDto1 = new EmployeeOutDto();
        employeeOutDto1.setId(employee1.getId());
        employeeOutDto1.setEmpId(employee1.getEmpId());
        employeeOutDto1.setName(employee1.getName());
        employeeOutDto1.setProjectId(employee1.getProjectId());
        employeeOutDto1.setSkills(employee1.getSkills());
        employeeOutDto1.setProjectName("AAA");
        
        EmployeeOutDto employeeOutDto2 = new EmployeeOutDto();
        employeeOutDto2.setId(employee2.getId());
        employeeOutDto2.setEmpId(employee2.getEmpId());
        employeeOutDto2.setName(employee2.getName());
        employeeOutDto2.setProjectId(employee2.getProjectId());
        employeeOutDto2.setSkills(employee2.getSkills());
        employeeOutDto2.setProjectName("AAA");
        List<String> selectedSkills = List.of("Skill1", "Skill2");
        when(modelMapper.map(employee1, EmployeeOutDto.class)).thenReturn(employeeOutDto1);
        when(modelMapper.map(employee2, EmployeeOutDto.class)).thenReturn(employeeOutDto2);
        when(employeeRepository.findByRole(Role.Employee)).thenReturn(List.of(employee1, employee2));
        Project project = new Project();
        project.setProjectName("EMS");
      Optional<Project> optionalProject = Optional.of(project);
        
        when(projectRepository.findById(3L)).thenReturn(optionalProject);
        List<EmployeeOutDto> filteredEmployees = adminService.getFilteredEmployees(selectedSkills, true);
        assertNotNull(filteredEmployees);
        assertEquals(1, filteredEmployees.size());
        EmployeeOutDto employeeOutDto = filteredEmployees.get(0);
        assertEquals(1L, employeeOutDto.getId());
        assertEquals("E001", employeeOutDto.getEmpId());
        assertEquals("Employee 1", employeeOutDto.getName());
        assertEquals("N/A", employeeOutDto.getProjectName());
    }

    @Test
    public void testGetFilteredEmployees_ShowUnassignedAndNoSelectedSkills() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setEmpId("E001");
        employee1.setName("Employee 1");
        employee1.setProjectId(null);
        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setEmpId("E002");
        employee2.setName("Employee 2");
        employee2.setProjectId(3L);
        EmployeeOutDto employeeOutDto1 = new EmployeeOutDto();
        employeeOutDto1.setId(employee1.getId());
        employeeOutDto1.setEmpId(employee1.getEmpId());
        employeeOutDto1.setName(employee1.getName());
        employeeOutDto1.setProjectId(employee1.getProjectId());
        EmployeeOutDto employeeOutDto2 = new EmployeeOutDto();
        employeeOutDto2.setId(employee2.getId());
        employeeOutDto2.setEmpId(employee2.getEmpId());
        employeeOutDto2.setName(employee2.getName());
        employeeOutDto2.setProjectId(employee2.getProjectId());
        employeeOutDto2.setProjectName("AAA");
        when(employeeRepository.findByRole(Role.Employee)).thenReturn(List.of(employee1, employee2));
        when(modelMapper.map(employee1, EmployeeOutDto.class)).thenReturn(employeeOutDto1);
        when(modelMapper.map(employee2, EmployeeOutDto.class)).thenReturn(employeeOutDto2);
        when(projectRepository.findById(3L)).thenReturn(Optional.of(new Project()));
        List<EmployeeOutDto> filteredEmployees = adminService.getFilteredEmployees(null, true);
        assertNotNull(filteredEmployees);
        assertEquals(1, filteredEmployees.size());
    }

    @Test
    public void testGetFilteredEmployees_NotShowUnassignedAndSelectedSkills() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setEmpId("E001");
        employee1.setName("Employee 1");
        employee1.setProjectId(1L);
        employee1.setSkills(List.of("Skill1", "Skill2"));
        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setEmpId("E002");
        employee2.setName("Employee 2");
        employee2.setProjectId(3L);
        employee2.setSkills(List.of("Skill1", "Skill2"));
        EmployeeOutDto employeeOutDto1 = new EmployeeOutDto();
        employeeOutDto1.setId(employee1.getId());
        employeeOutDto1.setEmpId(employee1.getEmpId());
        employeeOutDto1.setName(employee1.getName());
        employeeOutDto1.setProjectId(employee1.getProjectId());
        employeeOutDto1.setSkills(employee1.getSkills());
        employeeOutDto1.setProjectName("AAA");
        EmployeeOutDto employeeOutDto2 = new EmployeeOutDto();
        employeeOutDto2.setId(employee2.getId());
        employeeOutDto2.setEmpId(employee2.getEmpId());
        employeeOutDto2.setName(employee2.getName());
        employeeOutDto2.setProjectId(employee2.getProjectId());
        employeeOutDto2.setSkills(employee2.getSkills());
        employeeOutDto2.setProjectName("AAA");
        List<String> selectedSkills = List.of("Skill1", "Skill2");
        when(employeeRepository.findByRole(Role.Employee)).thenReturn(List.of(employee1, employee2));
        when(modelMapper.map(employee1, EmployeeOutDto.class)).thenReturn(employeeOutDto1);
        when(modelMapper.map(employee2, EmployeeOutDto.class)).thenReturn(employeeOutDto2);
        when(projectRepository.findById(3L)).thenReturn(Optional.of(new Project()));
        List<EmployeeOutDto> filteredEmployees = adminService.getFilteredEmployees(selectedSkills, false);
        assertNotNull(filteredEmployees);
        assertEquals(2, filteredEmployees.size());
        EmployeeOutDto employeeOutDto = filteredEmployees.get(1);
        assertEquals(2L, employeeOutDto.getId());
        assertEquals("E002", employeeOutDto.getEmpId());
        assertEquals("Employee 2", employeeOutDto.getName());
    }

    @Test
    public void testGetFilteredEmployees_NotShowUnassignedAndNoSelectedSkills() {
        // Create test data
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setEmpId("E001");
        employee1.setName("Employee 1");
        employee1.setProjectId(null);
        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setEmpId("E002");
        employee2.setName("Employee 2");
        employee2.setProjectId(3L);       
        EmployeeOutDto employeeOutDto1 = new EmployeeOutDto();
        employeeOutDto1.setId(employee1.getId());
        employeeOutDto1.setEmpId(employee1.getEmpId());
        employeeOutDto1.setName(employee1.getName());
        employeeOutDto1.setProjectId(employee1.getProjectId());
//        employeeOutDto1.setSkills(employee1.getSkills());
        
        EmployeeOutDto employeeOutDto2 = new EmployeeOutDto();
        employeeOutDto2.setId(employee2.getId());
        employeeOutDto2.setEmpId(employee2.getEmpId());
        employeeOutDto2.setName(employee2.getName());
        employeeOutDto2.setProjectId(employee2.getProjectId());
        when(employeeRepository.findByRole(Role.Employee)).thenReturn(List.of(employee1, employee2));
        when(modelMapper.map(employee1, EmployeeOutDto.class)).thenReturn(employeeOutDto1);
        when(modelMapper.map(employee2, EmployeeOutDto.class)).thenReturn(employeeOutDto2);
        when(projectRepository.findById(3L)).thenReturn(Optional.of(new Project()));
        List<EmployeeOutDto> filteredEmployees = adminService.getFilteredEmployees(null, false);
        assertNotNull(filteredEmployees);
        assertEquals(2, filteredEmployees.size());
    }


    @Test
    public void testUnAssign_Success() {
        Long id = 1L;

        Employee employee = new Employee();
        employee.setProjectId(2L);
        employee.setManagerId(3L);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        ResponseDto response = adminService.unAssign(id);
        assertNotNull(response);
        assertEquals("Unassigned the project", response.getMessage());
        assertNull(employee.getProjectId());
        assertEquals(1L, employee.getManagerId());
        assertEquals("Ankita", employee.getManagerName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testUnAssign_EmployeeNotFound() {
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            adminService.unAssign(id);
        });
    }

    private EmployeeInDto createSampleEmployeeDto() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        // Set sample employee DTO properties here
        employeeInDto.setName("Praveen");
        employeeInDto.setEmail("praveen@nucleusteq.com");
        employeeInDto.setEmpId("N0001");
        employeeInDto.setDesignation(Designation.Engineer);
        employeeInDto.setLocation(Location.Raipur);
        employeeInDto.setContactNo(1234567890L);
        employeeInDto.setDob("14-10-2001");
        employeeInDto.setDoj("17-07-2023");
        return employeeInDto;
    }

    private Employee createSampleEmployee() {
        Employee employee = new Employee();
        // Set sample employee properties here
        employee.setName("Praveen");
        employee.setEmail("praveen@nucleusteq.com");
        employee.setEmpId("N0001");
        employee.setDesignation(Designation.Engineer);
        employee.setLocation(Location.Raipur);
        employee.setContactNo(1234567890L);
        employee.setDob("14-10-2001");
        employee.setDoj("17-07-2023");
        return employee;
    }
}
