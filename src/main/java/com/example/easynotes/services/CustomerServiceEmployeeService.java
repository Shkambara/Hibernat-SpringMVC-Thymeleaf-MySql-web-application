package com.example.easynotes.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.CustomerServiceEmployee;
import com.example.easynotes.repository.CustomerServiceEmployeeRepository;

@Service
public class CustomerServiceEmployeeService {
	
	
	@Autowired
	CustomerServiceEmployeeRepository customerServiceEmployeeRepository;
	
	public List<CustomerServiceEmployee> getCustomerServiceEmployees(){
		return customerServiceEmployeeRepository.findAll();
	}
	
	public Optional<CustomerServiceEmployee> getCustomerServiceEmployeeById(Long id){
		if(!customerServiceEmployeeRepository.existsById(id)) {
			throw new ResourceNotFoundException("CustormerServiceEmployee", "id", id);

		}
		return customerServiceEmployeeRepository.findById(id);
	}
	
	public CustomerServiceEmployee createCustomerServiceEmployee(CustomerServiceEmployee customerServiceEmployee) {
		return customerServiceEmployeeRepository.save(customerServiceEmployee);
	}
	
	//DELETING AND UPDATING LEFT TO DO
	
}
