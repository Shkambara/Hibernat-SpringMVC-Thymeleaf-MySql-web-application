package com.example.easynotes.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.EndOfTheDay;
import com.example.easynotes.repository.EndOfTheDayRepository;

@Service
public class EndOfTheDayService {
	
	
	@Autowired
	EndOfTheDayRepository endOfTheDayRepository;
	
	public List<EndOfTheDay> getEndOfTheDays(){
		return endOfTheDayRepository.findAll();
	}
	
	public Optional<EndOfTheDay> getEndOfTheDayById(Long id){
		if(!endOfTheDayRepository.existsById(id)) {
			
			throw new ResourceNotFoundException("EndOfTheDay", "id", id);
		}
		return endOfTheDayRepository.findById(id);
	}
	
	public EndOfTheDay createEndOfTheDay(EndOfTheDay endOfTheDay) {
		return endOfTheDayRepository.save(endOfTheDay);
	}
	
	//DELETING AND UPDATING LEFT TO DO
	
}
