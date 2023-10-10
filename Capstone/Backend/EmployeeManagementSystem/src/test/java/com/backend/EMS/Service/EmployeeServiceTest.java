package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.backend.EMS.Constants.SuccessConstants;
import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.IsRequestedInDto;
import com.backend.EMS.DTO.IsRequestedOutDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.RequestResourceInDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.DTO.UpdateSkillsDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Model.RequestResource;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Repository.EmployeeRepository;
import com.backend.EMS.Repository.ProjectRepository;
import com.backend.EMS.Repository.RequestResourceRepository;

public class EmployeeServiceTest {
   @InjectMocks
    private EmployeeService employeeService;
   @Mock
    private EmployeeRepository employeeRepository;
   @Mock
    private ModelMapper modelMapper;
   @Mock
   private ProjectRepository projectRepository;
   @Mock
   private RequestResourceRepository requestResourceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEmployee_Successful() {
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
        
        when(modelMapper.map(employeeInDto, Employee.class)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);      
        ResponseDto responseDto = employeeService.addEmployee(employeeInDto);

        assertNotNull(responseDto);
        assertEquals(SuccessConstants.ADDED, responseDto.getMessage());
    }
    @Test
    public void testUpdateEmployee() {
        Long id = 1L;
        Long projectId = 2L;
        Long managerId = 3L;
        String managerName = "Manager Name";

        Map<String, Long> updatedDetails = new HashMap<>();
        updatedDetails.put("projectId", projectId);
        updatedDetails.put("managerId", managerId);

        Employee employee = new Employee();
        employee.setId(id);

        Employee manager = new Employee();
        manager.setId(managerId);
        manager.setName(managerName);

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(employeeRepository.findById(managerId)).thenReturn(Optional.of(manager));

        ResponseDto response = employeeService.updateEmployee(id, updatedDetails);

        assertEquals(projectId, employee.getProjectId());
        assertEquals(managerId, employee.getManagerId());
        assertEquals(managerName, employee.getManagerName());

        assertEquals("Updated Successfully", response.getMessage());
    }
    

    @Test
    public void testGetAllEmployeesAndManagers() {
        List<Role> roles = new ArrayList<>(List.of(Role.Manager, Role.Employee));
        Employee employee1 = new Employee();
        employee1.setName("Praveen");
        employee1.setEmail("praveen@nucleusteq.com");
        employee1.setEmpId("N0001");
        employee1.setDesignation(Designation.Engineer);
        employee1.setLocation(Location.Raipur);
        employee1.setContactNo(1234567890L);
        employee1.setDob("14-10-2001");
        employee1.setDoj("17-07-2023");

        Employee employee2 = new Employee();
        employee2.setName("mahesh");
        employee2.setEmail("mahesh@nucleusteq.com");
        employee2.setEmpId("N0002");
        employee2.setDesignation(Designation.Engineer);
        employee2.setLocation(Location.Raipur);
        employee2.setContactNo(1234567890L);
        employee2.setDob("14-10-2001");
        employee2.setDoj("17-07-2023");

        when(employeeRepository.findByRoleIn(roles)).thenReturn(List.of(employee1, employee2));

        EmployeeOutDto employeeOutDto1 = new EmployeeOutDto();
        employeeOutDto1.setName("Praveen");
        employeeOutDto1.setEmail("praveen@nucleusteq.com");
        employeeOutDto1.setEmpId("N0001");
        employeeOutDto1.setDesignation(Designation.Engineer);
        employeeOutDto1.setLocation(Location.Raipur);
        employeeOutDto1.setContactNo(1234567890L);
        employeeOutDto1.setDob("14-10-2001");
        employeeOutDto1.setDoj("17-07-2023");
       

        EmployeeOutDto employeeOutDto2 = new EmployeeOutDto();
        employeeOutDto2.setName("mahesh");
        employeeOutDto2.setEmail("mahesh@nucleusteq.com");
        employeeOutDto2.setEmpId("N0002");
        employeeOutDto2.setDesignation(Designation.Engineer);
        employeeOutDto2.setLocation(Location.Raipur);
        employeeOutDto2.setContactNo(1234567890L);
        employeeOutDto2.setDob("14-10-2001");
        employeeOutDto2.setDoj("17-07-2023");


        when(modelMapper.map(employee1, EmployeeOutDto.class)).thenReturn(employeeOutDto1);
        when(modelMapper.map(employee2, EmployeeOutDto.class)).thenReturn(employeeOutDto2);

        List<EmployeeOutDto> result = employeeService.getAllEmployeesAndManagers();

        assertEquals(2, result.size());

        EmployeeOutDto retrievedEmployee1 = result.get(0);
        assertEquals("Praveen", retrievedEmployee1.getName());
        assertEquals("praveen@nucleusteq.com", retrievedEmployee1.getEmail());
        assertEquals("N0001", retrievedEmployee1.getEmpId());
        assertEquals(Designation.Engineer, retrievedEmployee1.getDesignation());
        assertEquals(Location.Raipur, retrievedEmployee1.getLocation());
        assertEquals(1234567890L, retrievedEmployee1.getContactNo());
        assertEquals("14-10-2001", retrievedEmployee1.getDob());
        assertEquals("17-07-2023", retrievedEmployee1.getDoj());
        
        


        EmployeeOutDto retrievedEmployee2 = result.get(1);
        assertEquals("mahesh", retrievedEmployee2.getName());
        assertEquals("mahesh@nucleusteq.com", retrievedEmployee2.getEmail());
        assertEquals("N0002", retrievedEmployee2.getEmpId());
        assertEquals(Designation.Engineer, retrievedEmployee2.getDesignation());
        assertEquals(Location.Raipur, retrievedEmployee2.getLocation());
        assertEquals(1234567890L, retrievedEmployee2.getContactNo());
        assertEquals("14-10-2001", retrievedEmployee2.getDob());
        assertEquals("17-07-2023", retrievedEmployee2.getDoj());

    }
    
    @Test
    public void testGetEmployeeById() {
        Long employeeId = 1L;
        Employee employee1 = new Employee();
        employee1.setId(employeeId);
        employee1.setName("Praveen");
        employee1.setEmail("praveen@nucleusteq.com");
        employee1.setEmpId("N0001");
        employee1.setDesignation(Designation.Engineer);
        employee1.setLocation(Location.Raipur);
        employee1.setContactNo(1234567890L);
        employee1.setDob("14-10-2001");
        employee1.setDoj("17-07-2023");

        Project project = new Project();
        project.setId(2L);
        project.setProjectName("Test Project");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee1));
        when(projectRepository.findById(2L)).thenReturn(Optional.of(project));

        EmployeeOutDto expectedEmployeeOutDto1 = new EmployeeOutDto();
        expectedEmployeeOutDto1.setId(employeeId);
        expectedEmployeeOutDto1.setName("Praveen");
        expectedEmployeeOutDto1.setEmail("praveen@nucleusteq.com");
        expectedEmployeeOutDto1.setEmpId("N0001");
        expectedEmployeeOutDto1.setDesignation(Designation.Engineer);
        expectedEmployeeOutDto1.setLocation(Location.Raipur);
        expectedEmployeeOutDto1.setContactNo(1234567890L);
        expectedEmployeeOutDto1.setDob("14-10-2001");
        expectedEmployeeOutDto1.setDoj("17-07-2023");

        when(modelMapper.map(employee1, EmployeeOutDto.class)).thenReturn(expectedEmployeeOutDto1);

        EmployeeOutDto result = employeeService.getEmployeeById(employeeId);

        assertNotNull(result);
        assertEquals(employeeId, result.getId());
        assertEquals("Praveen", result.getName());
        assertEquals("praveen@nucleusteq.com", result.getEmail());
        assertEquals("N0001", result.getEmpId());
        assertEquals(Designation.Engineer,result.getDesignation());
        assertEquals(Location.Raipur, result.getLocation());
        assertEquals(1234567890L, result.getContactNo());
        assertEquals("14-10-2001", result.getDob());
        assertEquals("17-07-2023", result.getDoj());
    }

    @Test
    public void testUpdateSkills() {
        Long id = 1L;
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Spring");
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();
        updateSkillsDto.setSkills(skills);

        Employee employee = new Employee();
        employee.setId(id);
        employee.setSkills(updateSkillsDto.getSkills());
        
        when(employeeRepository.findById(id)).thenReturn(java.util.Optional.of(employee));

        ResponseDto response = employeeService.updateSkills(id, updateSkillsDto);

        assertEquals(SuccessConstants.SKILLS_ADDED, response.getMessage());
        assertEquals(2, employee.getSkills().size());
    }
    
    @Test
    public void testRequestResource() {
        RequestResourceInDto requestResourceInDto = new RequestResourceInDto();
        requestResourceInDto.setEmployeeId(1L);
        requestResourceInDto.setProjectId(2L);
        requestResourceInDto.setManagerId(3L);
        requestResourceInDto.setComment("Test comment");

        RequestResource mappedRequestResource = new RequestResource();
        mappedRequestResource.setEmployeeId(1L);
        mappedRequestResource.setProjectId(2L);
        mappedRequestResource.setManagerId(3L);
        mappedRequestResource.setComment("Test comment");

        when(modelMapper.map(requestResourceInDto, RequestResource.class)).thenReturn(mappedRequestResource);
        
        when(requestResourceRepository.save(mappedRequestResource)).thenReturn(mappedRequestResource);

        ResponseDto response = employeeService.requestResource(requestResourceInDto);

        assertNotNull(response);
        assertEquals("Requested resource", response.getMessage());

    }

    @Test
    public void testIsRequested() {
        Long employeeId = 1L;
        Long managerId = 2L;

        IsRequestedInDto isRequestedInDto = new IsRequestedInDto();
        isRequestedInDto.setId(managerId);
        isRequestedInDto.setEmployeeId(employeeId);

        Employee manager = new Employee();
        manager.setId(managerId);

        RequestResource requestResource = new RequestResource();
        requestResource.setEmployeeId(employeeId);
        requestResource.setManagerId(managerId);

        when(employeeRepository.findById(managerId)).thenReturn(Optional.of(manager));
        when(requestResourceRepository.findByEmployeeIdAndManagerId(employeeId, managerId)).thenReturn(requestResource);

        IsRequestedOutDto result = employeeService.isRequested(isRequestedInDto);

        assertNotNull(result);
        assertTrue(result.isRequested());
    }
    
    @Test
    public void testGetEmployeesByRole() {
        String roleName = "Admin";
        Role role = Role.valueOf(roleName);
       
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setProjectId(2L);
        employee1.setName("Praveen");
        employee1.setEmail("praveen@nucleusteq.com");
        employee1.setEmpId("N0001");
        employee1.setDesignation(Designation.Engineer);
        employee1.setLocation(Location.Raipur);
        employee1.setContactNo(1234567890L);
        employee1.setDob("14-10-2001");
        employee1.setDoj("17-07-2023");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setProjectId(null);
        
        Project project = new Project();
        project.setId(2L);
        project.setProjectName("Test Project");

        when(employeeRepository.findByRole(role)).thenReturn(List.of(employee1, employee2));
        when(projectRepository.findById(employee1.getProjectId())).thenReturn(Optional.of(project));

        EmployeeOutDto employeeOutDto1 = new EmployeeOutDto();
        employeeOutDto1.setId(1L);
        employeeOutDto1.setProjectId(2L);
        employeeOutDto1.setProjectName("Test Project");

        EmployeeOutDto employeeOutDto2 = new EmployeeOutDto();
        employeeOutDto2.setId(2L);
        employeeOutDto2.setProjectName("N/A");

        when(modelMapper.map(employee1, EmployeeOutDto.class)).thenReturn(employeeOutDto1);
        when(modelMapper.map(employee2, EmployeeOutDto.class)).thenReturn(employeeOutDto2);

        List<EmployeeOutDto> result = employeeService.getEmployeesByRole(roleName);

        assertNotNull(result);
        assertEquals(2, result.size());

        EmployeeOutDto retrievedEmployee1 = result.get(0);
        assertEquals(1L, retrievedEmployee1.getId());
        assertEquals("Test Project", retrievedEmployee1.getProjectName());

        EmployeeOutDto retrievedEmployee2 = result.get(1);
        assertEquals(2L, retrievedEmployee2.getId());
        assertEquals("N/A", retrievedEmployee2.getProjectName());
    }



}
