package com.example.easynotes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Administrator;
import com.example.easynotes.model.Form;
import com.example.easynotes.model.State;
import com.example.easynotes.repository.AdministratorRepository;
import com.example.easynotes.repository.DrinkFormRepository;
import com.example.easynotes.repository.EndOfTheDayRepository;
import com.example.easynotes.repository.IceCreamFormRepository;

public class AdministratorService {
	@Autowired
	AdministratorRepository administratorRepository;
	
	@Autowired
	IceCreamFormRepository iceCreamFormRepository;
	
	@Autowired
	EndOfTheDayRepository endOfTheDayRepository;
	
	@Autowired
	DrinkFormRepository drinkFormRepository;
	
	public void AcceptForm(Form form) {
		
		form.setState(State.ACCEPTED);
	}
	
	public void DenyForm(Form form) {
		
		form.setState(State.DENIED);
	}
	
	public List<Administrator> getAdministrators(){
		return administratorRepository.findAll();
	}
	
	public Optional<Administrator> getAdministratorById(Long id){
		if(!administratorRepository.existsById(id)) {
			throw new ResourceNotFoundException("Administrator", "id", id);

		}
		return administratorRepository.findById(id);
	}
	
	public Administrator createAdministrator(Administrator administrator) {
		return administratorRepository.save(administrator);
	}
}
