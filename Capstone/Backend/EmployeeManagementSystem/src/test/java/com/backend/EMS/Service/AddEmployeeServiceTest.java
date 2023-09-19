package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import com.backend.EMS.DTO.EmployeeDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Repository.AdminRepository;

public class AddEmployeeServiceTest {

    private AddEmployeeService addEmployeeService;
    private AdminRepository adminRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        adminRepository = mock(AdminRepository.class);
        modelMapper = new ModelMapper();
        addEmployeeService = new AddEmployeeService(adminRepository, modelMapper);
    }

    @Test
    public void testAddEmployee_Successful() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Praveen");
        employeeDto.setEmail("praveen@nucleusteq.com");
        employeeDto.setEmpId("N0001");
        employeeDto.setDesignation(Designation.Engineer);
        employeeDto.setLocation(Location.Raipur);
        employeeDto.setContactNo(1234567890L);
        employeeDto.setDob("14-10-2001");
        employeeDto.setDoj("17-07-2023");
        when(adminRepository.findByEmail(anyString())).thenReturn(null);
        when(adminRepository.findByEmpId(anyString())).thenReturn(null);
        when(adminRepository.findByContactNo(anyLong())).thenReturn(null); // Update this line

        // Act
        ResponseDto responseDto = addEmployeeService.addEmployee(employeeDto);

        // Assert
        assertNotNull(responseDto);
        assertEquals("Employee Added successfully", responseDto.getMessage());
    }

    @Test
    public void testAddEmployee_DuplicateContactNo() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setContactNo(1234567890L); // Use a long value

        when(adminRepository.findByEmail(anyString())).thenReturn(null);
        when(adminRepository.findByEmpId(anyString())).thenReturn(null);
        when(adminRepository.findByContactNo(anyLong())).thenReturn(new Employee()); // Update this line

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> addEmployeeService.addEmployee(employeeDto));
    }

    @Test
    public void testAddEmployee_DuplicateEmail() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmail("test@example.com");

        when(adminRepository.findByEmail(anyString())).thenReturn(new Employee());

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> addEmployeeService.addEmployee(employeeDto));
    }

    @Test
    public void testAddEmployee_DuplicateEmpId() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmpId("EMP001");

        when(adminRepository.findByEmail(anyString())).thenReturn(null);
        when(adminRepository.findByEmpId(anyString())).thenReturn(new Employee());

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> addEmployeeService.addEmployee(employeeDto));
    }
//    @Test
//    public void testUpdateSkills() {
//        Long id = 1L;
//        EmployeeDto employeeDto = new EmployeeDto();
//        List<String> skills = new ArrayList<>();
//        skills.add("Java");
//        skills.add("Spring");
//        employeeDto.setSkills(skills);
//
//        Employee employee = new Employee();
//        employee.setId(id);
//
//        when(adminRepository.findById(id)).thenReturn(java.util.Optional.of(employee));
//
//        ResponseDto response = addEmployeeService.updateSkills(id, employeeDto);
//
//        assertEquals("Skills Updated", response.getMessage());
//        assertEquals(2, employee.getSkills().size());
//    }


}
