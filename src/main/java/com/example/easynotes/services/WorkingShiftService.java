package com.example.easynotes.services;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.easynotes.model.WorkingShift;
import com.example.easynotes.model.EndOfTheDay;
import com.example.easynotes.repository.WorkingShiftRepository;
import com.example.easynotes.repository.EndOfTheDayRepository;

@Service
public class WorkingShiftService {
	
	
	@Autowired
	EndOfTheDayRepository eotdRepository;
	
	@Autowired
	WorkingShiftRepository workingShiftRepository;
	
	public List<WorkingShift> getAllDays(){
		return workingShiftRepository.findAll();
	}
	
	public Optional<WorkingShift> getDayById(Long shift_id){
		if(!workingShiftRepository.existsById(shift_id)) {
			throw new ResourceAccessException("WorkingShift with id " + shift_id + " not found");

		}
		return workingShiftRepository.findById(shift_id);
	}
	
	public WorkingShift createWorkingShift(Long eotd_id, WorkingShift workingShift) {
		Set<WorkingShift> shifts = new HashSet<>();
		EndOfTheDay eotd1 = new EndOfTheDay();
		
		Optional<EndOfTheDay> form_id = eotdRepository.findById(eotd_id);
		if(!form_id.isPresent()) {
			throw new ResourceAccessException("DrinkForm with id " + form_id + " not found");
		}
		EndOfTheDay form = form_id.get();
		workingShift.setEndOfTheDay(form);
		
		WorkingShift day = workingShiftRepository.save(workingShift);
		
		shifts.add(day);
		eotd1.setShifts(shifts);
		
		return day;
		
		}
	
}
