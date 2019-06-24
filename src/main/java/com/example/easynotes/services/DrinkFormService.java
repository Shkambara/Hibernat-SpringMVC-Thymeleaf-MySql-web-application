package com.example.easynotes.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.DrinkForm;
import com.example.easynotes.repository.DrinkFormRepository;

@Service
public class DrinkFormService {
	
	
	@Autowired
	DrinkFormRepository drinkFormRepository;
	
	public List<DrinkForm> getDrinkForms(){
		return drinkFormRepository.findAll();
	}
	
	public Optional<DrinkForm> getDrinkFormById(Long id){
		if(!drinkFormRepository.existsById(id)) {
			throw new ResourceNotFoundException("DrinkForm", "id", id);

		}
		
		return drinkFormRepository.findById(id);
	}
	
	public DrinkForm createDrinkForm(DrinkForm drinkForm) {
		return drinkFormRepository.save(drinkForm);
	}
	
	//DELETING AND UPDATING LEFT
	
}
