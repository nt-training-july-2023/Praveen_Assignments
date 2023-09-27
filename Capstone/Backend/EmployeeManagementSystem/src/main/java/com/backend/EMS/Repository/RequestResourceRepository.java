package com.backend.EMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.backend.EMS.Model.RequestResource;
@EnableJpaRepositories
public interface RequestResourceRepository extends JpaRepository<RequestResource, Long>{

    List<RequestResource> findByManagerId(Long employeeId);

    RequestResource findByEmployeeIdAndManagerId(Long employeeId, Long managerId);

    void deleteByEmployeeId(Long employeeId);

    List<RequestResource> findByEmployeeId(long id);
    
    
}

