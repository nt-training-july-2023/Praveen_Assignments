package com.backend.EMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EMS.DTO.ProjectDto;
import com.backend.EMS.DTO.ResponseDto;
import com.backend.EMS.Service.AddProjectService;

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
    private AddProjectService addProjectService;
    /**
     * Add a new project.
     *
     * @param projectDto The ProjectDto containing project details to be added.
     * @return A ResponseDto indicating the
        success or failure of the project addition.
     */
    @PostMapping("/addProject")
    public final ResponseDto addProject(@RequestBody
            final ProjectDto projectDto) {
        return addProjectService.addProject(projectDto);
    }
}

