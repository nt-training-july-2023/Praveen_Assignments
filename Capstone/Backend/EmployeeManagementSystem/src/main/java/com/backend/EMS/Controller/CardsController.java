package com.backend.EMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.EMS.DTO.AdminDto;
import com.backend.EMS.DTO.ProjectDto;
import com.backend.EMS.Model.Admin;
import com.backend.EMS.Model.Project;
import com.backend.EMS.Service.CardsService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CardsController {
    
    @Autowired
    private CardsService cardsService;
    
    @GetMapping("/all/{roleName}")
    public List<AdminDto> getEmployeesByRole(@PathVariable String roleName){
        return cardsService.getEmployeesByRole(roleName);
    }

    @GetMapping("/projectCards")
    public List<ProjectDto> getAllProjects(){
        System.out.println(cardsService.getAllProject());
        return cardsService.getAllProject();
           
    }
    
    @GetMapping("/projectCards/{managerId}")
    public List<ProjectDto> getAllByManagerId(@PathVariable Long managerId){
        return cardsService.getAllByManagerId(managerId);
        
    }

}
