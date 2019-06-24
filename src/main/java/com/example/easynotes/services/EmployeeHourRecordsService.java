package com.example.easynotes.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.CustomerServiceEmployee;
import com.example.easynotes.model.EmployeeHourRecords;
import com.example.easynotes.repository.CustomerServiceEmployeeRepository;
import com.example.easynotes.repository.EmployeeHourRecordsRepository;

@Service
public class EmployeeHourRecordsService {
	
	
	@Autowired
	EmployeeHourRecordsRepository employeeHourRecordsRepository;
	
	@Autowired 
	CustomerServiceEmployeeRepository empRepository;
	
	public List<EmployeeHourRecords> getEmployeeHourRecordss(){
		return employeeHourRecordsRepository.findAll();
	}
	
	public Optional<EmployeeHourRecords> getEmployeeHourRecordsById(Long id){
		if(!employeeHourRecordsRepository.existsById(id)) {
			
			throw new ResourceNotFoundException("EmployeeHourRecords", "id", id);
		}
		
		return employeeHourRecordsRepository.findById(id);
	}
	
	public EmployeeHourRecords createEmployeeHourRecords(EmployeeHourRecords employeeHourRecords,Long id) {
		CustomerServiceEmployee emp1 = new CustomerServiceEmployee();
		
		Optional<CustomerServiceEmployee> emp_id = empRepository.findById(id);
		
		if(!emp_id.isPresent()) {
			throw new ResourceNotFoundException("CustomerServiceEmployee", "id", emp_id);
		}
		
		CustomerServiceEmployee emp = emp_id.get();
		employeeHourRecords.setEmployee(emp);
		
		EmployeeHourRecords records = employeeHourRecordsRepository.save(employeeHourRecords);
		
		emp1.setRecords(records);
		
		return records;
	}
	
	//DELETING AND UPDATING LEFT TO DO
	
}
