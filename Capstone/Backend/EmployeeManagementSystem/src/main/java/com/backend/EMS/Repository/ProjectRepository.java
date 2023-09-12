package com.backend.EMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.EMS.Model.Project;

public interface ProjectRepository extends JpaRepository<Project,Long>{

    Project findByProjectName(String projectName);

    List<Project> findAllByManagerId(Long managerId);

}
