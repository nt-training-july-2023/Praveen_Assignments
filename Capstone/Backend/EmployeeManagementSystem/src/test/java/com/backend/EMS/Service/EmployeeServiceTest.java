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
//        employeeRepository = mock(EmployeeRepository.class);
//        modelMapper = new ModelMapper();
//        employeeService = new EmployeeService(employeeRepository, modelMapper);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEmployee_Successful() {
        // Arrange
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
        // Set sample employee properties here
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
        // Act
        ResponseDto responseDto = employeeService.addEmployee(employeeInDto);


        // Assert
        assertNotNull(responseDto);
        assertEquals("Employee Added successfully", responseDto.getMessage());
    }

//    @Test
//    public void testAddEmployee_DuplicateContactNo() {
//        // Arrange
//        EmployeeInDto employeeInDto = new EmployeeInDto();
//        employeeInDto.setContactNo(1234567890L); // Use a long value
//
//        when(employeeRepository.findByEmail(anyString())).thenReturn(null);
//        when(employeeRepository.findByEmpId(anyString())).thenReturn(null);
//        when(employeeRepository.findByContactNo(anyLong())).thenReturn(new Employee()); // Update this line
//
//        // Act and Assert
//        assertThrows(UserAlreadyFound.class, () -> employeeService.addEmployee(employeeInDto));
//    }
//
//    @Test
//    public void testAddEmployee_DuplicateEmail() {
//        // Arrange
//        EmployeeInDto employeeInDto = new EmployeeInDto();
//        employeeInDto.setEmail("test@example.com");
//
//        when(employeeRepository.findByEmail(anyString())).thenReturn(new Employee());
//
//        // Act and Assert
//        assertThrows(UserAlreadyFound.class, () -> employeeService.addEmployee(employeeInDto));
//    }
//
//    @Test
//    public void testAddEmployee_DuplicateEmpId() {
//        // Arrange
//        EmployeeInDto employeeInDto = new EmployeeInDto();
//        employeeInDto.setEmpId("EMP001");
//
//        when(employeeRepository.findByEmail(anyString())).thenReturn(null);
//        when(employeeRepository.findByEmpId(anyString())).thenReturn(new Employee());
//
//        // Act and Assert
//        assertThrows(UserAlreadyFound.class, () -> employeeService.addEmployee(employeeInDto));
//    }
    @Test
    public void testUpdateEmployee() {
        // Prepare the test data
        Long id = 1L;
        Long projectId = 2L;
        Long managerId = 3L;
        String managerName = "Manager Name";

        Map<String, Long> updatedDetails = new HashMap<>();
        updatedDetails.put("projectId", projectId);
        updatedDetails.put("managerId", managerId);

        // Create a mock employee and manager
        Employee employee = new Employee();
        employee.setId(id);

        Employee manager = new Employee();
        manager.setId(managerId);
        manager.setName(managerName);

        // Mock repository behavior
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(employeeRepository.findById(managerId)).thenReturn(Optional.of(manager));

        // Call the method to test
        ResponseDto response = employeeService.updateEmployee(id, updatedDetails);

        // Verify that the employee properties were updated
        assertEquals(projectId, employee.getProjectId());
        assertEquals(managerId, employee.getManagerId());
        assertEquals(managerName, employee.getManagerName());

        // Verify that the response is as expected
        assertEquals("Updated Successfully", response.getMessage());
    }
    

    @Test
    public void testGetAllEmployeesAndManagers() {
        // Create test data
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

        // Mock repository behavior
        when(employeeRepository.findByRoleIn(roles)).thenReturn(List.of(employee1, employee2));

        // Mock modelMapper behavior
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

        // Call the method to test
        List<EmployeeOutDto> result = employeeService.getAllEmployeesAndManagers();

        // Verify the result
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
        // Create test data
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

        // Mock repository behavior
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee1));
        when(projectRepository.findById(2L)).thenReturn(Optional.of(project));

        // Mock modelMapper behavior
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

        // Call the method to test
        EmployeeOutDto result = employeeService.getEmployeeById(employeeId);

        // Verify the result
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

        assertEquals("Skills Updated", response.getMessage());
        assertEquals(2, employee.getSkills().size());
    }
    
    @Test
    public void testRequestResource() {
        // Create test data
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

        // Mock modelMapper behavior
        when(modelMapper.map(requestResourceInDto, RequestResource.class)).thenReturn(mappedRequestResource);
        
        when(requestResourceRepository.save(mappedRequestResource)).thenReturn(mappedRequestResource);

        // Call the method to test
        ResponseDto response = employeeService.requestResource(requestResourceInDto);

        // Verify the result
        assertNotNull(response);
        assertEquals("Requested resource", response.getMessage());

        // Verify that the mapped RequestResource object is saved to the repository
//        verify(employeeRepository, times(1)).save(mappedRequestResource);
    }

    
//    @Test
//    public void testGetManagerIdById() {
//        // Create test data
//        Long employeeId = 1L;
//        Employee employee = new Employee();
//        employee.setId(employeeId);
//
//        // Mock repository behavior
//        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
//
//        // Call the method to test
//        EmployeeOutDto result = employeeService.getMangerIdById(employeeId);
//
//        // Verify the result
//        assertNotNull(result);
//        assertEquals(employeeId, result.getId());
//    }
//    
    
    @Test
    public void testIsRequested() {
        // Create test data
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

        // Mock repository behavior
        when(employeeRepository.findById(managerId)).thenReturn(Optional.of(manager));
        when(requestResourceRepository.findByEmployeeIdAndManagerId(employeeId, managerId)).thenReturn(requestResource);

        // Call the method to test
        IsRequestedOutDto result = employeeService.isRequested(isRequestedInDto);

        // Verify the result
        assertNotNull(result);
        assertTrue(result.isRequested());
    }
    
    @Test
    public void testGetEmployeesByRole() {
        // Create test data
        String roleName = "Admin";
        Role role = Role.valueOf(roleName);
       

        // Set up the employee object with the provided data
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

        // Mock repository behavior
        when(employeeRepository.findByRole(role)).thenReturn(List.of(employee1, employee2));
        when(projectRepository.findById(employee1.getProjectId())).thenReturn(Optional.of(project));

        // Mock modelMapper behavior
        EmployeeOutDto employeeOutDto1 = new EmployeeOutDto();
        employeeOutDto1.setId(1L);
        employeeOutDto1.setProjectId(2L);
        employeeOutDto1.setProjectName("Test Project");

        EmployeeOutDto employeeOutDto2 = new EmployeeOutDto();
        employeeOutDto2.setId(2L);
        employeeOutDto2.setProjectName("N/A");

        when(modelMapper.map(employee1, EmployeeOutDto.class)).thenReturn(employeeOutDto1);
        when(modelMapper.map(employee2, EmployeeOutDto.class)).thenReturn(employeeOutDto2);

        // Call the method to test
        List<EmployeeOutDto> result = employeeService.getEmployeesByRole(roleName);

        // Verify the result
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
