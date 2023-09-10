package com.backend.EMS.DTO;

import java.util.List;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
    private long id;
    /**
     * The name of the ProjectName.
     */
    private String projectName;
    /**
     * The managerId of the project.
     */
    private long managerId;
    /**
     * The Startdate of the  project.
     */
    private String startDate;
    /**
     * The requiredskills of the project .
     */
    private List<String> requiredSkills;
    /**
     * The description of the project.
     * 
     */
 
    private String description;
    
    
    
    private String head;
    
    
    
    


}
