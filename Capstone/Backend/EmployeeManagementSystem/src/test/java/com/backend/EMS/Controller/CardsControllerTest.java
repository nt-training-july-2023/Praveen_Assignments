package com.backend.EMS.Controller;

import com.backend.EMS.Controller.CardsController;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.ProjectDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.EmployeeNameDto;
import com.backend.EMS.Service.CardsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CardsControllerTest {

    @InjectMocks
    private CardsController cardsController;

    @Mock
    private CardsService cardsService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEmployeesByRole_Success() {
        String roleName = "Manager";
        List<EmployeeOutDto> employeeList = Arrays.asList(new EmployeeOutDto(), new EmployeeOutDto());

        when(cardsService.getEmployeesByRole(eq(roleName))).thenReturn(employeeList);

        List<EmployeeOutDto> response = cardsController.getEmployeesByRole(roleName);

        verify(cardsService, times(1)).getEmployeesByRole(eq(roleName));

        assertEquals(2, response.size());
    }

    @Test
    public void testGetAllProjects_Success() {
        List<ProjectOutDto> projectList = Arrays.asList(new ProjectOutDto(), new ProjectOutDto());

        when(cardsService.getAllProject()).thenReturn(projectList);

        List<ProjectOutDto> response = cardsController.getAllProjects();

        verify(cardsService, times(1)).getAllProject();

        assertEquals(2, response.size());
    }

    @Test
    public void testGetAllByManagerId_Success() {
        Long managerId = 1L;
        List<ProjectOutDto> projectList = Arrays.asList(new ProjectOutDto());

        when(cardsService.getAllByManagerId(eq(managerId))).thenReturn(projectList);

        List<ProjectOutDto> response = cardsController.getAllByManagerId(managerId);

        verify(cardsService, times(1)).getAllByManagerId(eq(managerId));

        assertEquals(1, response.size());
    }

    @Test
    public void testGetByEmployeeId_Success() {
        Long employeeId = 1L;
        EmployeeNameDto employee = new EmployeeNameDto();

        when(cardsService.getEmployeeById(eq(employeeId))).thenReturn(employee);

        EmployeeNameDto response = cardsController.getByEmployeeId(employeeId);

        verify(cardsService, times(1)).getEmployeeById(eq(employeeId));

        assertEquals(employee, response);
    }
}
