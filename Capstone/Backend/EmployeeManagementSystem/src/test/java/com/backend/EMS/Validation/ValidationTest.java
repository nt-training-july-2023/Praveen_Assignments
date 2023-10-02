package com.backend.EMS.Validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import com.backend.EMS.DTO.EmployeeInDto;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.ProjectInDto;
import com.backend.EMS.Exception.CustomException;
import com.backend.EMS.Exception.UserNotFound;

public class ValidationTest {

    private Validation validation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testCheckEmailExists() {
//        // Create a test EmployeeInDto with an email that doesn't exist
//        EmployeeInDto employeeInDto = createTestEmployeeInDto();
//
//        // Ensure no exception is thrown
//        assertDoesNotThrow(() -> validation.checkEmailExists(employeeInDto));
//    }
//
//    @Test
//    public void testCheckRoleExists() {
//        // Test with a valid role
//        String validRole = "ROLE_USER";
//        assertDoesNotThrow(() -> validation.checkRoleExists(validRole));
//
//        // Test with an invalid role
//        String invalidRole = "INVALID_ROLE";
//        assertThrows(UserNotFound.class, () -> validation.checkRoleExists(invalidRole));
//    }
//
//    @Test
//    public void testCheckEmployeeIdExists() {
//        // Create a test EmployeeInDto with an empId that doesn't exist
//        EmployeeInDto employeeInDto = createTestEmployeeInDto();
//
//        // Ensure no exception is thrown
//        assertDoesNotThrow(() -> validation.checkEmployeeIdExists(employeeInDto));
//    }
//
//    @Test
//    public void testCheckContactNumberExists() {
//        // Create a test EmployeeInDto with a contactNo that doesn't exist
//        EmployeeInDto employeeInDto = createTestEmployeeInDto();
//
//        // Ensure no exception is thrown
//        assertDoesNotThrow(() -> validation.checkContactNumberExists(employeeInDto));
//    }
//
//    @Test
//    public void testCheckProject() {
//        // Create a test ProjectInDto with a projectName that doesn't exist
//        ProjectInDto projectInDto = createTestProjectInDto();
//
//        // Ensure no exception is thrown
//        assertDoesNotThrow(() -> validation.checkProject(projectInDto));
//    }

//    @Test
//    public void testCheckEmailNotExists() {
//        // Create a test LoginInDto with an email that exists
//        LoginInDto loginInDto = createTestLoginInDtoWithEmail();
//
//        // Ensure no exception is thrown
//        assertDoesNotThrow(() -> validation.checkEmailNotExists(loginInDto));
//    }
//
//    @Test
//    public void testDecodePassword() {
//        // Test decoding a password
//        String encodedPassword = "encodedPassword";
//        String decodedPassword = Validation.decodePassword(encodedPassword);
//        assertNotNull(decodedPassword);
//    }
//
//    @Test
//    public void testCheckLoginWithValidCredentials() {
//        // Create a test LoginInDto with valid credentials
//        LoginInDto loginInDto = createTestLoginInDtoWithValidCredentials();
//
//        // Ensure no exception is thrown
//        assertDoesNotThrow(() -> validation.checkLogin(loginInDto));
//    }
//
//    @Test
//    public void testCheckLoginWithInvalidPassword() {
//        // Create a test LoginInDto with invalid password
//        LoginInDto loginInDto = createTestLoginInDtoWithInvalidPassword();
//
//        // Ensure UserNotFound exception is thrown
//        assertThrows(UserNotFound.class, () -> validation.checkLogin(loginInDto));
//    }
//
//    @Test
//    public void testCheckAdmin() {
//        // Create a test EmployeeInDto for admin validation
//        EmployeeInDto empDto = createTestEmployeeInDto();
//
//        // Ensure no exception is thrown
//        assertDoesNotThrow(() -> validation.checkAdmin(empDto));
//    }
//
//    @Test
//    public void testCheckEmployee() {
//        // Create a test EmployeeInDto for employee validation
//        EmployeeInDto empDto = createTestEmployeeInDto();
//
//        // Ensure no exception is thrown
//        assertDoesNotThrow(() -> validation.checkEmployee(empDto));
//    }
//
//    @Test
//    public void testPatternValidationsWithValidInput() {
//        // Create a mock BindingResult with no errors
//        BindingResult bindingResult = createMockBindingResult(false);
//
//        // Ensure no exception is thrown
//        assertDoesNotThrow(() -> validation.patterValidations(bindingResult));
//    }
//
//    @Test
//    public void testPatternValidationsWithErrors() {
//        // Create a mock BindingResult with errors
//        BindingResult bindingResult = createMockBindingResult(true);
//
//        // Ensure CustomException is thrown
//        assertThrows(CustomException.class, () -> validation.patterValidations(bindingResult));
//    }
//
//    @Test
//    public void testGetValidationErrorsAsString() {
//        // Create a mock BindingResult with errors
//        BindingResult bindingResult = createMockBindingResult(true);
//
//        // Test getValidationErrorsAsString method
//        String validationErrors = validation.getValidationErrorsAsString(bindingResult);
//        assertNotNull(validationErrors);
//    }
//
//    @Test
//    public void testCheckEmployeeExists() {
//        // Create a test employee ID that exists
//        Long existingEmployeeId = 1L;
//
//        // Ensure no exception is thrown
//        assertDoesNotThrow(() -> validation.checkEmployeeExists(existingEmployeeId));
//    }
//
//    @Test
//    public void testCheckEmployeeExistsWithNonExistingId() {
//        // Create a test employee ID that does not exist
//        Long nonExistingEmployeeId = 9999L;
//
//        // Ensure UserNotFound exception is thrown
//        assertThrows(UserNotFound.class, () -> validation.checkEmployeeExists(nonExistingEmployeeId));
//    }
//
//    // Additional test methods for other Validation class methods...
//
//    // Helper methods to create test objects...
//
//    private EmployeeInDto createTestEmployeeInDto() {
//        // Create and return a test EmployeeInDto
//        // Populate with required data...
//    }
//
//    private ProjectInDto createTestProjectInDto() {
//        // Create and return a test ProjectInDto
//        // Populate with required data...
//    }

//    private LoginInDto createTestLoginInDtoWithEmail() {
//        // Create and return a test LoginInDto with an email that exists
//        // Populate with required data...
//    }
//
//    private LoginInDto createTestLoginInDtoWithValidCredentials() {
//        // Create and return a test LoginInDto with valid credentials
//        // Populate with required data...
//    }
//
//    private LoginInDto createTestLoginInDtoWithInvalidPassword() {
//        // Create and return a test LoginInDto with invalid password
//        // Populate with required data...
//    }
//
//    private BindingResult createMockBindingResult(boolean hasErrors) {
//        // Create and return a mock BindingResult
//        // Set whether it has errors...
//    }
}

