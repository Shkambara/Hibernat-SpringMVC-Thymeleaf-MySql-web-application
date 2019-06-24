package com.example.easynotes.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.IceCreamForm;
import com.example.easynotes.repository.IceCreamFormRepository;

@Service
public class IceCreamFormService {
	
	
	@Autowired
	IceCreamFormRepository iceCreamFormRepository;
	
	public List<IceCreamForm> getIceCreamForms(){
		return iceCreamFormRepository.findAll();
	}
	
	public Optional<IceCreamForm> getIceCreamFormById(Long id){
		if(!iceCreamFormRepository.existsById(id)) {
			
			throw new ResourceNotFoundException("DrinkDayData", "id", id);
		}
		
		return iceCreamFormRepository.findById(id);
	}
	
	public IceCreamForm createIceCreamForm(IceCreamForm iceCreamForm) {
		return iceCreamFormRepository.save(iceCreamForm);
	}
	
	//DELETING AND UPDATING LEFT TO DO
	
}
