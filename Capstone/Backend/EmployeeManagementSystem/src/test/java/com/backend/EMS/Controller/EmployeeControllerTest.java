package com.backend.EMS.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.IsRequestedInDto;
import com.backend.EMS.DTO.IsRequestedOutDto;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.LoginOutDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.RequestResourceInDto;
import com.backend.EMS.DTO.RequestResourceOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.UserNotFound;
import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Model.Role;
import com.backend.EMS.Service.AdminService;
import com.backend.EMS.Service.EmployeeService;
import com.backend.EMS.Service.LoginService;
import com.backend.EMS.Service.ProjectService;
import com.backend.EMS.Validation.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private AdminService adminService;

    @Mock
    private LoginService loginService;

    @Mock
    private EmployeeService employeeService;
   
    @Mock
    private ProjectService projectService;
    @Mock
    private Validation validator;
    @Mock
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    List<String> skills = new ArrayList<>();

    @Test
    void testAddEmployee() throws Exception {
        skills.add("React");
        skills.add("Java");
        EmployeeInDto empDto = new EmployeeInDto();
        empDto.setEmpId("N0001");
        empDto.setName("Praveen");
        empDto.setEmail("praveen@nucleusteq.com");
        empDto.setDob("2001-10-14");
        empDto.setDoj("2023-07-17");
        empDto.setLocation(Location.Raipur);
        empDto.setDesignation(Designation.Engineer);
        empDto.setContactNo(6300033822L);
        empDto.setRole(Role.Employee);
        empDto.setSkills(skills);
        empDto.setPassword("N1004@14102001");
        empDto.setSkills(skills);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(empDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("Employee Added Succesfully");
        doNothing().when(validator).checkEmployee(empDto);
//        when(employeeService.addEmployee(Mockito.any())).thenReturn(response);

        MvcResult mvcResult = this.mockMvc.perform(post("/api/addEmployee")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void testAddEmployeeException() throws Exception {
        skills.add("React");
        skills.add("Java");
        EmployeeInDto empDto = new EmployeeInDto();
        empDto.setEmpId("N0001");
        empDto.setName("Praveen");
        empDto.setEmail("praveen@nucleusteq.com");
        empDto.setDob("2001-10-14");
        empDto.setDoj("2023-07-17");
        empDto.setLocation(Location.Raipur);
        empDto.setDesignation(Designation.Engineer);
        empDto.setContactNo(6300033822L);
        empDto.setRole(Role.Employee);
        empDto.setSkills(skills);
        empDto.setPassword("N1004@14102001");
        empDto.setSkills(skills);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(empDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("Employee Added Succesfully");
        doThrow(UserNotFound.class).when(validator).checkEmployee(empDto);
        MvcResult mvcResult = this.mockMvc.perform(post("/api/addEmployee")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }
//    @Test
//    void testRegisterAdmin_Success() throws Exception {
//        EmployeeInDto employeeInDto = new EmployeeInDto();
//        employeeInDto.setEmployeeId("N0001");
//        employeeInDto.setEmployeeName("Hemant Kumar");
//        employeeInDto.setEmployeeEmail("hemant.kumar@nucleusteq.com");
//        employeeInDto.setDateOfBirth("2001-05-12");
//        employeeInDto.setDateOfJoining("2023-07-17");
//        employeeInDto.setLocation(Location.Raipur);
//        employeeInDto.setDesignation(Designation.Engineer);
//        employeeInDto.setContactNumber("1234567890");
//        employeeInDto.setPassword("N0001@12052001");
//        skills.add("Java");
//        skills.add("Spring");
//        employeeInDto.setSkills(skills);
//        employeeInDto.setManager("Ankita Sharma");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String inputJSON = objectMapper.writeValueAsString(employeeInDto);
//        MvcResult mvcResult = mockMvc.perform(post("/api/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(inputJSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // Verify the response message
//        String responseBody = mvcResult.getResponse().getContentAsString();
//        assertTrue(responseBody.contains("Registered Successfully"));
//    }
    @Test
    public void testRegisterAdmin() throws Exception {
        skills.add("React");
        skills.add("Java");
        EmployeeInDto empDto = new EmployeeInDto();
        empDto.setEmpId("N0001");
        empDto.setName("Praveen");
        empDto.setEmail("praveen@nucleusteq.com");
        empDto.setDob("2001-10-14");
        empDto.setDoj("2023-07-17");
        empDto.setLocation(Location.Raipur);
        empDto.setDesignation(Designation.Engineer);
        empDto.setContactNo(6300033822L);
        empDto.setRole(Role.Employee);
        empDto.setSkills(skills);
        empDto.setPassword("N1004@14102001");
        empDto.setSkills(skills);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(empDto);
        doNothing().when(validator).checkAdmin(empDto);
        ResponseDto response = new ResponseDto();
        response.setMessage("Admin Registered successfully");
        when(adminService.addAdmin(Mockito.any())).thenReturn(response);

        MvcResult mvcResult = this.mockMvc.perform(post("/api/adminRegistration")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testLogin() throws Exception {
        LoginInDto loginDto = new LoginInDto();
        loginDto.setEmail("ankita.sharma@nucleusteq.com");
        loginDto.setPassword("admin123");
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(loginDto);
        LoginOutDto response = new LoginOutDto();
        response.setMessage("Login Successful");
        response.setName("Ankita Sharma");
        response.setRole(Role.Admin);
        response.setId(1L);
        doNothing().when(validator).checkLogin(loginDto);
        when(loginService.login(Mockito.any())).thenReturn(response);

        MvcResult mvcResult = this.mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void testGetEmployees() throws Exception {
        List<String> skills = new ArrayList<>();
        List<EmployeeOutDto> empDtoList = new ArrayList<>();
        skills.add("React");
        skills.add("Java");
        EmployeeOutDto empDto = new EmployeeOutDto();
        empDto.setEmpId("N0001");
        empDto.setName("Praveen");
        empDto.setEmail("praveen@nucleusteq.com");
        empDto.setDob("2001-10-14");
        empDto.setDoj("2023-07-17");
        empDto.setLocation(Location.Raipur);
        empDto.setDesignation(Designation.Engineer);
        empDto.setContactNo(6300033822L);
        skills.add("Java");
        skills.add("Spring");
        empDto.setSkills(skills);
        empDto.setManagerName("Ankita Sharma");
        empDto.setProjectName("Fynder");
        empDtoList.add(empDto);

        when(employeeService.getEmployeesByRole("Employee")).thenReturn(empDtoList);

        MvcResult mvcResult = this.mockMvc.perform(
                get("/api/all/Employee").contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testGetAllManagers() throws Exception {
        List<EmployeeOutDto> managers = new ArrayList<>();
        when(employeeService.getEmployeesByRole("Manager")).thenReturn(managers);

        MvcResult mvcResult = this.mockMvc.perform(
                get("/api/all/Manger").contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void testGetEmployeeById() throws Exception {

        EmployeeOutDto empDto = new EmployeeOutDto();
        empDto.setId(2L);
        empDto.setEmpId("N0001");
        empDto.setName("Praveen");
        empDto.setEmail("praveen@nucleusteq.com");
        empDto.setDob("2001-10-14");
        empDto.setDoj("2023-07-17");
        empDto.setLocation(Location.Raipur);
        empDto.setDesignation(Designation.Engineer);
        empDto.setContactNo(6300033822L);
        empDto.setSkills(skills);
        empDto.setManagerName("Ankita Sharma");
        empDto.setProjectName("Fynder");
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(empDto);
        when(employeeService.getEmployeeById(Mockito.any())).thenReturn(empDto);

        MvcResult mvcResult = this.mockMvc.perform(get("/api/employee/id/2")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void testGetAll() throws Exception {
        skills.add("React");
        skills.add("Java");
        EmployeeOutDto organization = new EmployeeOutDto();
        organization.setName("Anjali Sharma");
        organization.setEmail("anjali.sharma@nucluesteq.com");
        organization.setEmpId("N1001");
        organization.setManagerName("Ankita Sharma");
        organization.setDesignation(Designation.Engineer);
        organization.setLocation(Location.Raipur);
        organization.setDob("2001-09-07");
        organization.setDoj("2023-07-17");
        organization.setContactNo(1234567890L);
        organization.setSkills(skills);
        List<EmployeeOutDto> empOutList = new ArrayList<>();
        empOutList.add(organization);
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(organization);
        when(employeeService.getAllEmployeesAndManagers()).thenReturn(empOutList);

        
        MvcResult mvcResult = this.mockMvc.perform(get("/api/allManagersAndEmployees")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void testAssignProject() throws Exception {
        skills.add("React");
        skills.add("Java");
        EmployeeInDto empDto = new EmployeeInDto();
        empDto.setEmpId("N0001");
        empDto.setName("Anjali Sharma");
        empDto.setEmail("anjali.sharma@nucleusteq.com");
        empDto.setDob("2001-09-07");
        empDto.setDoj("2023-07-17");
        empDto.setLocation(Location.Raipur);
        empDto.setDesignation(Designation.Engineer);
        empDto.setContactNo(1234567890L);
        empDto.setRole(Role.Employee);
        empDto.setSkills(skills);
        empDto.setPassword("N1004@07092001");
        empDto.setSkills(skills);

        ResponseDto response = new ResponseDto();
        response.setMessage("Project Assigned.");
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("projectId", 2L);
        map.put("managerId", 3L);
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(map);
        when(employeeService.updateEmployee(1L, map)).thenReturn(response);

        MvcResult mvcResult = this.mockMvc.perform(put("/api/updateEmployee/1")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    void testUnassignProject() throws Exception {

        ResponseDto resp = new ResponseDto();
        resp.setMessage("Project Unassigned.");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmpId("N0001");
        employee.setName("Anjali Sharma");
        employee.setEmail("anjali.sharma@nucleusteq.com");
        employee.setDob("2001-09-07");
        employee.setDoj("2023-07-17");
        employee.setLocation(Location.Raipur);
        employee.setDesignation(Designation.Engineer);
        employee.setContactNo(1234567890L);
        employee.setRole(Role.Employee);
        employee.setSkills(skills);
        employee.setPassword("N1004@07092001");
        employee.setProjectId(2L);
        employee.setManagerId(2L);
        employee.setManagerName("Vanshika");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(employee);
        when (adminService.unAssign(1L)).thenReturn(resp);

        MvcResult mvcResult = this.mockMvc
                .perform(put("/api/unAssign/Project/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    void testUpdateSkills() throws Exception {
       skills.add("Java");
        long employeeId = 1L; 
        Map<String, List<String>> updatedSkills = new HashMap<>();
        updatedSkills.put("skills", skills);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(updatedSkills);

//        mockMvc.perform(put("/api/updateSkill/{id}", employeeId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isOk());
        MvcResult mvcResult = this .mockMvc.perform(put("/api/updateSkill/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
        }
    @Test
    void testGetFilteredEmployees() throws Exception {
        // Define query parameters
        List<String> selectedSkills = Arrays.asList("Java", "Spring");
        boolean showOnlyUnassigned = true; 

        mockMvc.perform(get("/api/filteredEmployees")
                .param("selectedSkills", String.join(",", selectedSkills))
                .param("showOnlyUnassigned", String.valueOf(showOnlyUnassigned)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray()); 
    }
    @Test
    void testRequestResource() throws Exception {

        RequestResourceInDto request = new RequestResourceInDto();
        request.setEmployeeId(1l);
        request.setManagerId(1L);
        request.setProjectId(1l);
        request.setComment("Comments");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(request);
        ResponseDto response = new ResponseDto();
        response.setMessage("Requested resource");
        when(employeeService.requestResource(Mockito.any())).thenReturn(response);

        MvcResult mvcResult = this.mockMvc.perform(post("/api/requestResource")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    void testRequestedResource() throws Exception {

        List<RequestResourceOutDto> requestOutList = new ArrayList<>();
        RequestResourceOutDto requestDto = new RequestResourceOutDto();
        requestDto.setId(1L);
        requestDto.setEmployeeId(2L);
        requestDto.setManagerName("Ankita sharma");
        requestDto.setProjectName("Fynder");
        requestDto.setComment("Comments");
        requestOutList.add(requestDto);
        when(adminService.requestedResource()).thenReturn(requestOutList);

        MvcResult mvcResult = this.mockMvc.perform(
                get("/api/RequestedResource").contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void testAcceptRequestedResource() throws Exception {
        RequestResourceInDto request = new RequestResourceInDto();

        request.setEmployeeId(1l);
        request.setManagerId(1L);
        request.setProjectId(1l);
        request.setComment("Comments");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(request);
        ResponseDto response = new ResponseDto();
        response.setMessage("Request Accepted");
        when(adminService.acceptRequestedResource(Mockito.any())).thenReturn(response);

        MvcResult mvcResult = this.mockMvc.perform(put("/api/Accept/RequestedResource/5")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void testDeleteRequestedResource() throws Exception {
        RequestResourceInDto request = new RequestResourceInDto();

        request.setEmployeeId(1l);
        request.setManagerId(1L);
        request.setProjectId(1l);
        request.setComment("Comments");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON = objectMapper.writeValueAsString(request);
        ResponseDto response = new ResponseDto();
        response.setMessage("Request Deleted");
        when(adminService.deleteRequestedResource(Mockito.any())).thenReturn(response);

        MvcResult mvcResult = this.mockMvc.perform(delete("/api/Delete/RequestedResource/8")
                .contentType(MediaType.APPLICATION_JSON).content(inputJSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    void testGetAllByManagerId() throws Exception{
//        List<ProjectOutDto> list = new ArrayList<>();
//        List<String> teams = new ArrayList<>();
//        ProjectOutDto prjDto = new ProjectOutDto();
//        prjDto.setProjectName("Fynder");
//        prjDto.setManagerId(1L);
//        prjDto.setStartDate("2023-06-07");
//        prjDto.setRequiredSkills(skills);
//        prjDto.setDescription("Description");
//        prjDto.setHead("Ankita Sharma");
//        prjDto.setId(0L);
//        prjDto.setTeam(teams);
//        list.add(prjDto);
//
//       
//        when(projectService.getAllByManagerId(prjDto.getManagerId())).thenReturn(list);
        MvcResult mvcResult = this.mockMvc.perform(get("/api/projectCards/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    
    @Test
    void testIsResquested() throws Exception{
        IsRequestedInDto isRequestedInDto = new IsRequestedInDto();
        isRequestedInDto.setEmployeeId(2L);
        isRequestedInDto.setId(8L);
        ObjectMapper objectMapper = new ObjectMapper();
        String inputJSON  = objectMapper.writeValueAsString(isRequestedInDto);
        IsRequestedOutDto isRequestedOutDto = new IsRequestedOutDto();
        isRequestedOutDto.setRequested(false);
        
        doNothing().when(validator).checkManagerExists(isRequestedInDto.getId());
        doNothing().when(validator).checkOnlyEmployeeExists(isRequestedInDto.getEmployeeId());
        
        when(employeeService.isRequested(isRequestedInDto)).thenReturn(isRequestedOutDto);
        MvcResult mvcResult = this.mockMvc.perform(post("/api/isRequested")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJSON))
                .andReturn();
        
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
        
    }
}
