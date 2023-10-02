package com.backend.EMS.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EMS.DTO.ProjectInDto;
import com.backend.EMS.DTO.ProjectOutDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Service.ProjectService;
import com.backend.EMS.Validation.Validation;

import jakarta.validation.Valid;


/**
 * Controller class for managing project-related operations.
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProjectController {
    /**
     * Autowired service for adding projects.
     */
    @Autowired
    private ProjectService projectService;
    /**
     * Autowired validation for validating projects.
     */
    @Autowired
    private Validation validation;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(EmployeeController.class);

    /**
     * Add a new project.
     *
     * @param projectInDto The ProjectInDto containing
     * project details to be added.
     * @param bindingResult The BindingResult containing errors.
     * @return A ResponseDto indicating the
        success or failure of the project addition.
     */
    @PostMapping("/addProject")
    public final ResponseDto addProject(@RequestBody @Valid
            final ProjectInDto projectInDto,
            final BindingResult bindingResult) {
        validation.checkProject(projectInDto);
        validation.patterValidations(bindingResult);
        LOGGER.info("Add project method invoked");
        ResponseDto responseDto = projectService.addProject(projectInDto);
        LOGGER.info("Added project successfully");
        return responseDto;

    }
    /**
     * Get a list of all projects.
     *
     * @return A list of ProjectOutDto objects representing all projects.
     */
    @GetMapping("/projectCards")
    public final List<ProjectOutDto> getAllProjects() {
        LOGGER.info("get all projects method invoked");
        List<ProjectOutDto> projectOutDtoList = projectService.
                getAllProject();
        LOGGER.info("retrieved all projects successfully");
        return projectOutDtoList;
    }
}

