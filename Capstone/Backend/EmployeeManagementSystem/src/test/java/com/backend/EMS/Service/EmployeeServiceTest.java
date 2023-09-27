package com.backend.EMS.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Exception.UserAlreadyFound;
import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Repository.EmployeeRepository;

public class EmployeeServiceTest {
   @InjectMocks
    private EmployeeService employeeService;
   @Mock
    private EmployeeRepository employeeRepository;
   @Mock
    private ModelMapper modelMapper;

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
        when(employeeRepository.findByEmail(anyString())).thenReturn(null);
        when(employeeRepository.findByEmpId(anyString())).thenReturn(null);
        when(employeeRepository.findByContactNo(anyLong())).thenReturn(null); // Update this line

        // Act
        ResponseDto responseDto = employeeService.addEmployee(employeeInDto);

        // Assert
        assertNotNull(responseDto);
        assertEquals("Employee Added successfully", responseDto.getMessage());
    }

    @Test
    public void testAddEmployee_DuplicateContactNo() {
        // Arrange
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setContactNo(1234567890L); // Use a long value

        when(employeeRepository.findByEmail(anyString())).thenReturn(null);
        when(employeeRepository.findByEmpId(anyString())).thenReturn(null);
        when(employeeRepository.findByContactNo(anyLong())).thenReturn(new Employee()); // Update this line

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> employeeService.addEmployee(employeeInDto));
    }

    @Test
    public void testAddEmployee_DuplicateEmail() {
        // Arrange
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmail("test@example.com");

        when(employeeRepository.findByEmail(anyString())).thenReturn(new Employee());

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> employeeService.addEmployee(employeeInDto));
    }

    @Test
    public void testAddEmployee_DuplicateEmpId() {
        // Arrange
        EmployeeInDto employeeInDto = new EmployeeInDto();
        employeeInDto.setEmpId("EMP001");

        when(employeeRepository.findByEmail(anyString())).thenReturn(null);
        when(employeeRepository.findByEmpId(anyString())).thenReturn(new Employee());

        // Act and Assert
        assertThrows(UserAlreadyFound.class, () -> employeeService.addEmployee(employeeInDto));
    }
//    @Test
//    public void testUpdateSkills() {
//        Long id = 1L;
//        EmployeeInDto employeeDto = new EmployeeInDto();
//        List<String> skills = new ArrayList<>();
//        skills.add("Java");
//        skills.add("Spring");
//        employeeDto.setSkills(skills);
//
//        Employee employee = new Employee();
//        employee.setId(id);
//
//        when(employeeRepository.findById(id)).thenReturn(java.util.Optional.of(employee));
//
//        ResponseDto response = employeeService.updateSkills(id, employeeDto);
//
//        assertEquals("Skills Updated", response.getMessage());
//        assertEquals(2, employee.getSkills().size());
//    }


}
