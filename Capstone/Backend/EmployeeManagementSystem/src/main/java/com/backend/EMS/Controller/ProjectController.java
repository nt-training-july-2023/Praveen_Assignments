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

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProjectController {
    @Autowired 
    private AddProjectService addProjectService;
    
    @PostMapping("/addProject")
    public final ResponseDto addProject(@RequestBody ProjectDto projectDto) {
        ResponseDto responseDto = new ResponseDto();
        if(addProjectService.addProject(projectDto)) {
            responseDto.setMessage("Project Added Successfully");
        }
        return responseDto;
        
    }

}
