package com.backend.EMS.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.EmployeeNameDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.IsRequestedDto;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.LoginOutDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.RequestResourceInDto;
import com.backend.EMS.DTO.RequestResourceOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.DTO.UpdateSkillsDto;
import com.backend.EMS.Service.AdminService;
import com.backend.EMS.Service.CardsService;
import com.backend.EMS.Service.EmployeeService;
import com.backend.EMS.Service.LoginService;
import com.backend.EMS.Validation.Validation;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeControllerTest {



    @Mock
    private AdminService adminService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private CardsService cardsService;

    @Mock
    private Validation validation;
    
    @Mock
    private LoginService loginService;
    
    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private EmployeeController employeeController;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testRegisterAdmin_Success() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        when(adminService.addAdmin(any(EmployeeInDto.class))).thenReturn(new ResponseDto("Registration successful"));

        ResponseDto response = employeeController.registerAdmin(employeeInDto, bindingResult);

        verify(adminService, times(1)).addAdmin(any(EmployeeInDto.class));

        assertEquals("Registration successful", response.getMessage());
    }

    @Test
    public void testAddEmployee_Success() {
        EmployeeInDto employeeInDto = new EmployeeInDto();
        when(employeeService.addEmployee(any(EmployeeInDto.class))).thenReturn(new ResponseDto("Employee added successfully"));

        ResponseDto response = employeeController.addEmployee(employeeInDto, bindingResult);

        verify(employeeService, times(1)).addEmployee(any(EmployeeInDto.class));

        assertEquals("Employee added successfully", response.getMessage());
    }

    @Test
    public void testGetEmployeesByRole_Success() {
        String roleName = "RoleName";
        when(cardsService.getEmployeesByRole(roleName)).thenReturn(Collections.emptyList());

        List<EmployeeOutDto> employees = employeeController.getEmployeesByRole(roleName);

        verify(cardsService, times(1)).getEmployeesByRole(roleName);

        assertTrue(employees.isEmpty());
    }

    @Test
    public void testLogin_Success() {
        LoginInDto loginInDto = new LoginInDto();
        LoginOutDto loginOutDto = new LoginOutDto();
        loginOutDto.setMessage("Login successful");
        when(loginService.login(loginInDto)).thenReturn(loginOutDto);

        LoginOutDto response = employeeController.login(loginInDto);

        verify(validation, times(1)).checkLogin(loginInDto);
        verify(loginService, times(1)).login(loginInDto);

        assertEquals("Login successful", response.getMessage());
    }

    @Test
    public void testUpdateEmployee_Success() {
        Long id = 1L;
        Map<String, Long> updatedDetails = Collections.singletonMap("projectId", 2L);
        when(employeeService.updateEmployee(id, updatedDetails.get("projectId"), updatedDetails.get("managerId")))
            .thenReturn(new ResponseDto("Employee updated successfully"));

        ResponseDto response = employeeController.updateEmployee(id, updatedDetails);

        verify(employeeService, times(1)).updateEmployee(id, updatedDetails.get("projectId"), updatedDetails.get("managerId"));

        assertEquals("Employee updated successfully", response.getMessage());
    }
    @Test
    public void testGetAllManagersAndEmployees_Success() {
        when(employeeService.getAllEmployeesAndManagers()).thenReturn(Collections.emptyList());

        List<EmployeeOutDto> employees = employeeController.getAllManagersAndEmployees();

        verify(employeeService, times(1)).getAllEmployeesAndManagers();

        assertTrue(employees.isEmpty());
    }

    @Test
    public void testGetAllByManagerId_Success() {
        Long managerId = 1L;
        when(cardsService.getAllByManagerId(managerId)).thenReturn(Collections.emptyList());

        List<ProjectOutDto> projects = employeeController.getAllByManagerId(managerId);

        verify(cardsService, times(1)).getAllByManagerId(managerId);

        assertTrue(projects.isEmpty());
    }

    @Test
    public void testGetByEmployeeEmail_Success() {
        String email = "employee@example.com";
        when(employeeService.getEmployeeByEmail(email)).thenReturn(new EmployeeOutDto());

        EmployeeOutDto employee = employeeController.getByEmployeeEmail(email);

        verify(employeeService, times(1)).getEmployeeByEmail(email);

        assertNotNull(employee);
    }

    @Test
    public void testUpdateSkill_Success() {
        Long id = 1L;
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();

        when(employeeService.updateSkills(id, updateSkillsDto)).thenReturn(new ResponseDto("Skill updated successfully"));

        ResponseDto response = employeeController.updateSkill(id, updateSkillsDto);

        verify(employeeService, times(1)).updateSkills(id, updateSkillsDto);

        assertEquals("Skill updated successfully", response.getMessage());
    }

    @Test
    public void testGetByEmployeeId_Success() {
        Long employeeId = 1L;
        when(cardsService.getEmployeeById(employeeId)).thenReturn(new EmployeeNameDto());

        EmployeeNameDto employee = employeeController.getByEmployeeId(employeeId);

        verify(cardsService, times(1)).getEmployeeById(employeeId);

        assertNotNull(employee);
    }

    @Test
    public void testRequestResource_Success() {
        RequestResourceInDto requestResourceInDto = new RequestResourceInDto();

        when(employeeService.requestResource(requestResourceInDto)).thenReturn(new ResponseDto("Resource request successful"));

        ResponseDto response = employeeController.requestResource(requestResourceInDto);

        verify(employeeService, times(1)).requestResource(requestResourceInDto);

        assertEquals("Resource request successful", response.getMessage());
    }

    @Test
    public void testRequestedResource_Success() {
        when(adminService.requestedResource()).thenReturn(Collections.emptyList());

        List<RequestResourceOutDto> requestedResources = employeeController.RequestedResource();

        verify(adminService, times(1)).requestedResource();

        assertTrue(requestedResources.isEmpty());
    }

    @Test
    public void testDeleteRequestedResource_Success() {
        Long id = 1L;

        when(adminService.DeleteRequestedResource(id)).thenReturn(new ResponseDto("Requested resource deleted successfully"));

        ResponseDto response = employeeController.DeleteRequestedResource(id);

        verify(adminService, times(1)).DeleteRequestedResource(id);

        assertEquals("Requested resource deleted successfully", response.getMessage());
    }

    @Test
    public void testAcceptRequestedResource_Success() {
        Long id = 1L;

        when(adminService.AcceptRequestedResource(id)).thenReturn(new ResponseDto("Requested resource accepted successfully"));

        ResponseDto response = employeeController.AcceptRequestedResource(id);

        verify(adminService, times(1)).AcceptRequestedResource(id);

        assertEquals("Requested resource accepted successfully", response.getMessage());
    }

    @Test
    public void testGetManagerIdByEmail_Success() {
        String email = "manager@example.com";
        when(employeeService.getMangerIdByEmail(email)).thenReturn(new EmployeeOutDto());

        EmployeeOutDto employee = employeeController.getManagerIdByEmail(email);

        verify(employeeService, times(1)).getMangerIdByEmail(email);

        assertNotNull(employee);
    }

    @Test
    public void testIsRequested_Success() {
        IsRequestedDto isRequestedDto = new IsRequestedDto();

        when(employeeService.IsRequested(isRequestedDto)).thenReturn(true);

        boolean isRequested = employeeController.IsRequested(isRequestedDto);

        verify(employeeService, times(1)).IsRequested(isRequestedDto);

        assertTrue(isRequested);
    }

    @Test
    public void testGetFilteredEmployees_Success() {
        List<String> selectedSkills = Collections.singletonList("Java");
        boolean showUnassigned = false;

        when(adminService.getFilteredEmployees(selectedSkills, showUnassigned)).thenReturn(Collections.emptyList());

        List<EmployeeOutDto> employees = employeeController.getFilteredEmployees(selectedSkills, showUnassigned);

        verify(adminService, times(1)).getFilteredEmployees(selectedSkills, showUnassigned);

        assertTrue(employees.isEmpty());
    }

    @Test
    public void testUnAssign_Success() {
        Long id = 1L;

        when(adminService.unAssign(id)).thenReturn(new ResponseDto("Unassigned successfully"));

        ResponseDto response = employeeController.unAssign(id);

        verify(adminService, times(1)).unAssign(id);

        assertEquals("Unassigned successfully", response.getMessage());
    }

    // Utility method to convert an object to JSON
    private static String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }


   
}
