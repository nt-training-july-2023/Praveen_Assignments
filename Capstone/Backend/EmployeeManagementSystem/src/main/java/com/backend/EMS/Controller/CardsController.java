package com.backend.EMS.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.EMS.DTO.EmployeeNameDto;
import com.backend.EMS.DTO.EmployeeOutDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.Service.CardsService;

/**
 * Controller class for handling Cards-related operations.
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class CardsController {
    /**
     * The CardsService instance used for managing cards-related operations.
     */
    @Autowired
    private CardsService cardsService;

    /**
     * Get a list of employees by their role.
     *
     * @param roleName The role name to filter employees by.
     * @return A list of EmployeeOutDto objects representing
     *  employees with the specified role.
     */
    @GetMapping("/all/{roleName}")
    public final List<EmployeeOutDto> getEmployeesByRole(@PathVariable
            final String roleName) {
        return cardsService.getEmployeesByRole(roleName);
    }

    /**
     * Get a list of all projects.
     *
     * @return A list of ProjectOutDto objects representing all projects.
     */
    @GetMapping("/projectCards")
    public final List<ProjectOutDto> getAllProjects() {
        return cardsService.getAllProject();
    }

    /**
     * Get a list of projects managed by a specific manager.
     *
     * @param managerId The ID of the manager to filter projects by.
     * @return A list of ProjectOutDto objects representing
     *  projects managed by the specified manager.
     */
    @GetMapping("/projectCards/{managerId}")
    public final List<ProjectOutDto> getAllByManagerId(@PathVariable
            final Long managerId) {
        return cardsService.getAllByManagerId(managerId);
    }
    /**
     * Get an employee's name by their ID.
     *
     * @param employeeId The ID of the employee.
     * @return An EmployeeNameDto object containing the employee's name.
     */
    @GetMapping("/employee/{employeeId}")
    public final EmployeeNameDto getByEmployeeId(@PathVariable
            final Long employeeId) {
        return cardsService.getEmployeeById(employeeId);
    }
}


