package com.backend.EMS.DTO;


import java.util.List;

import com.backend.EMS.Model.Designation;
import com.backend.EMS.Model.Location;
import com.backend.EMS.Model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for handling admin information.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private Long id;

    /**
     * The name of the admin.
     */
    @NotBlank(message = "Name cannot be empty")
//    @Pattern(regexp="^[[A-Za-z\\s]$]")
    private String name;

    /**
     * The email of the admin.
     */
//    @Email(message = "Email must be a valid email address")
    @Pattern(regexp = ".+@nucleusteq\\.com$", message = "Email must end with @nucleusteq.com")
    private String email;

    /**
     * The employee ID of the admin.
     */
    @Pattern(regexp = "^N\\d{4}$", message = "Employee ID should be in the pattern NXXXX (X should be numbers)")
    private String empId;

    /**
     * The date of birth of the admin.
     */
    @NotBlank(message = "Date of birth cannot be empty")
    private String dob;

    /**
     * The date of joining of the admin.
     */
    @NotBlank(message = "Date of joining cannot be empty")
    private String doj;

    /**
     * The location of the admin.
     */
//    @NotBlank(message = "Location cannot be empty")
    private Location location;

    /**
     * The designation of the admin.
     */
//    @NotBlank(message = "Designation cannot be empty")-
    private Designation designation;

    /**
     * The contact number of the admin.
     */
//    @Pattern(regexp = "^[0-9]{10}$", message = "Contact No should have 10 digits")
    private Long contactNo;

    /**
     * The password of the admin.
     */
    @NotBlank(message = "Password should not be empty")
    private String password;

    /**
     * The confirmation password for validation.
     */
//    private String confirmPassword;
    /**
     * The role of employee.
     */
    private Role role;

    /**
     * The skills of employee
     */
    private List<String> skills;
    
    
  

}

