package com.praveen.empMan.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.empMan.exception.EmployeeNotFoundException;
import com.praveen.empMan.model.Employee;
import com.praveen.empMan.repo.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private int nextId=100;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
		
	}
	
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with ID "+ id + " not found"));
	}
	
	public Employee addEmployee(Employee employee) {
		employee.setEmp_id(nextId++);
		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployee(Employee updatedEmployee) {
		Employee existingEmployee = employeeRepository.findById(updatedEmployee.getEmp_id())
				.orElseThrow(()-> new EmployeeNotFoundException("Employee with ID "+ updatedEmployee.getEmp_id()+" not found"));
		if(updatedEmployee.getEmp_name()!=null) {
			existingEmployee.setEmp_name(updatedEmployee.getEmp_name());
		}
		
		if(updatedEmployee.getEmp_age()!=0) {
		existingEmployee.setEmp_age(updatedEmployee.getEmp_age());
		}
		
		if(updatedEmployee.getEmp_city()!=null)
	    existingEmployee.setEmp_city(updatedEmployee.getEmp_city());
		
		
		if(updatedEmployee.getEmp_designation()!=null)
	    existingEmployee.setEmp_designation(updatedEmployee.getEmp_designation());
	
		return employeeRepository.save(existingEmployee);
	}
	
    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        employeeRepository.delete(employee);
    }
	

}
