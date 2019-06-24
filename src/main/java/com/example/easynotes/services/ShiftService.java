package com.example.easynotes.services;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easynotes.model.Shift;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.EmployeeHourRecords;
import com.example.easynotes.repository.ShiftRepository;
import com.example.easynotes.repository.EmployeeHourRecordsRepository;

@Service
public class ShiftService {
	
	
	@Autowired
	EmployeeHourRecordsRepository recordsRepository;
	
	@Autowired
	ShiftRepository shiftRepository;
	
	public List<Shift> getAllDays(){
		return shiftRepository.findAll();
	}
	
	public Optional<Shift> getDayById(Long id){
		if(!shiftRepository.existsById(id)) {
			throw new ResourceNotFoundException("Shift", "id", id);

		}
		return shiftRepository.findById(id);
	}
	
	public Shift createShift(Long recordsId, Shift shift) {
		Set<Shift> shifts = new HashSet<>();
		EmployeeHourRecords records1 = new EmployeeHourRecords();
		
		Optional<EmployeeHourRecords> record_id = recordsRepository.findById(recordsId);
		
		if(!record_id.isPresent()) {
			throw new ResourceNotFoundException("EmployeeHourRecords", "id", record_id);
		}
		
		EmployeeHourRecords records = record_id.get();
		shift.setRecords(records);
		
		Shift shift1 = shiftRepository.save(shift);
		
		shifts.add(shift1);
		records1.setShifts(shifts);
		
		return shift;
		
		}
	
}
