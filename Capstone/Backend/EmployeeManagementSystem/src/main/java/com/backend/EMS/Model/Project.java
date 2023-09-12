package com.backend.EMS.Model;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
/**
 * Entity class representing an Project.
 */
@Entity
@Getter
@Setter
@Table(name="Project")
public class Project {
    /**
     * The unique identifier for the Project.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long id;
    /**
     * The name of the ProjectName.
     */
    @Column(unique=true)
    private String projectName;
    /**
     * The managerId of the project.
     */
    @Column
    private long managerId;
    /**
     * The Startdate of the  project.
     */
    @Column
    private String startDate;
    /**
     * The requiredskills of the project .
     */
    @Column
    private List<String> requiredSkills;
    /**
     * The description of the project.
     * 
     */
    @Column
    private String description;
    

}
