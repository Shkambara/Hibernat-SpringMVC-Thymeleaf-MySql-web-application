package com.example.easynotes.services;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.DrinkDayData;
import com.example.easynotes.model.DrinkForm;
import com.example.easynotes.repository.DrinkDayDataRepository;
import com.example.easynotes.repository.DrinkFormRepository;

@Service
public class DrinkDayDataService {
	
	
	@Autowired
	DrinkFormRepository drinkRepository;
	
	@Autowired
	DrinkDayDataRepository dayRepository;
	
	public List<DrinkDayData> getAllDays(){
		return dayRepository.findAll();
	}
	
	public Optional<DrinkDayData> getDayById(Long day_id){
		if(!dayRepository.existsById(day_id)) {
			throw new ResourceNotFoundException("DrinkDayData", "id", day_id);

		}
		
		return dayRepository.findById(day_id);
	}
	
	public DrinkDayData createDrinkDayData(Long drinkFormId, DrinkDayData drinkDayData) {
		Set<DrinkDayData> days = new HashSet<>();
		DrinkForm form1 = new DrinkForm();
		
		Optional<DrinkForm> form_id = drinkRepository.findById(drinkFormId);
		
		if(!form_id.isPresent()) {
			throw new ResourceNotFoundException("DrinkForm", "id", form_id);
		}
		
		DrinkForm form = form_id.get();
		drinkDayData.setDrinkForm(form);
		
		DrinkDayData day = dayRepository.save(drinkDayData);
		
		days.add(day);
		form1.setDayData(days);
		
		return day;
		
		}
	
}
