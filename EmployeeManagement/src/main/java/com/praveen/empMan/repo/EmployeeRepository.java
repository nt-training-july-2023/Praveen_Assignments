package com.praveen.empMan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.praveen.empMan.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	
	}
